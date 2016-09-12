package com.smart.library.view;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ScrollView;

public class MyScrollView extends ScrollView{

	// y�����ϵ�ǰ�������ǰһ�μ�¼λ��
		private int previousY = 0;
		// y�����ϵĴ��������ʼ��¼λ��
		private int startY = 0;
		// y�����ϵĴ����㵱ǰ��¼λ��
		private int currentY = 0;
		// y�����������ƶ����ƶ�����Ծ���
		private int deltaY = 0;

		// ��һ������ͼ
		private View childView;

		// ���ڼ�¼childView�ĳ�ʼλ��
		private Rect topRect = new Rect();
	
	
	
	public MyScrollView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public MyScrollView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
	}

	public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		
		
	}

	@Override
	protected void onFinishInflate() {
		if (getChildCount() > 0) {
			childView = getChildAt(0);
		}
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		if (null == childView) {
			return super.dispatchTouchEvent(event);
		}

		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			startY = (int) event.getY();
			previousY = startY;
			break;
		case MotionEvent.ACTION_MOVE:
			currentY = (int) event.getY();
			deltaY = previousY - currentY;
			previousY = currentY;

			if (0 == getScrollY()
					|| childView.getMeasuredHeight() - getHeight() <= getScrollY()) {
				// ��¼childView�ĳ�ʼλ��
				if (topRect.isEmpty()) {
					topRect.set(childView.getLeft(), childView.getTop(),
							childView.getRight(), childView.getBottom());
				}

				// ����childView��λ��
				childView.layout(childView.getLeft(), childView.getTop()
						- deltaY / 3, childView.getRight(),
						childView.getBottom() - deltaY / 3);
			}
			break;
		case MotionEvent.ACTION_UP:
			if (!topRect.isEmpty()) {
				upDownMoveAnimation();
				// �ӿؼ��ص���ʼλ��
				childView.layout(topRect.left, topRect.top, topRect.right,
						topRect.bottom);
			}

			startY = 0;
			currentY = 0;
			topRect.setEmpty();
			break;
		default:
			break;
		}

		return super.dispatchTouchEvent(event);
	}

	// ��ʼ�����»ص��Ķ���Ч��
	private void upDownMoveAnimation() {
		TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,
				childView.getTop(), topRect.top);
		animation.setDuration(200);
		animation.setInterpolator(new AccelerateInterpolator());
		childView.setAnimation(animation);
	}


}
