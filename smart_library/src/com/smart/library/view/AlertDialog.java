package com.smart.library.view;

import java.util.List;

import com.smart.library.R;
import com.smart.library.util.DeviceUtils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

public class AlertDialog extends Dialog implements android.view.View.OnClickListener {

	private CharSequence mTitle, mMessage;
	private CharSequence mGrayBtn, mGreenBtn;
	private int mMsgTopDrawableRes = 0;
	private OnClickListener mGrayClickListener, mGreenClickListener;
	
	private int mTitleGravity = Gravity.CENTER;
	private String[] mItems;
	
	public AlertDialog(Context context) {
		super(context, R.style.Dialog_AlertDialog);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.alert_dialog_layout);
		
		TextView title = (TextView) findViewById(R.id.title);
		TextView content = (TextView) findViewById(R.id.message);
		View space = findViewById(R.id.space);
		Button graybtn = (Button) findViewById(R.id.graybtn);
		Button greenbtn = (Button) findViewById(R.id.greenbtn);
		
		if(mTitle == null) {
			if (mMsgTopDrawableRes > 0) {
				title.setVisibility(View.GONE);//有图没提�?
			} else {
				title.setText(R.string.warm_prompt);
				title.setGravity(Gravity.CENTER);
			}
		} else {
			title.setText(mTitle);
			title.setGravity(mTitleGravity);
		}
		
		if(mItems != null) {
			content.setVisibility(View.GONE);
			
			ListView list = (ListView) findViewById(R.id.list_items);
			ItemAdapter adapter = new ItemAdapter();
			adapter.setData(mItems);
			list.setAdapter(adapter);
		} else {
			content.setText(mMessage);
			if (mMsgTopDrawableRes > 0) {
				LayoutParams params = (LayoutParams)content.getLayoutParams();
				params.gravity = Gravity.CENTER_HORIZONTAL;
				content.setCompoundDrawablesWithIntrinsicBounds(0, mMsgTopDrawableRes, 0, 0);
				content.setCompoundDrawablePadding((int)DeviceUtils.dipToPx(16));
			}
		}
		
		graybtn.setOnClickListener(this);
		greenbtn.setOnClickListener(this);
		
		if(mGrayBtn != null && mGreenBtn != null) {
			graybtn.setText(mGrayBtn);
			greenbtn.setText(mGreenBtn);
		} else {
			space.setVisibility(View.GONE);
			if(mGrayBtn == null) {
				graybtn.setVisibility(View.GONE);
			} else {
				graybtn.setText(mGrayBtn);
			}
			if(mGreenBtn == null) {
				greenbtn.setVisibility(View.GONE);
			} else {
				greenbtn.setText(mGreenBtn);
			}
		}
		
		DisplayMetrics dm = getContext().getResources().getDisplayMetrics();
		WindowManager.LayoutParams params = getWindow().getAttributes();
		params.width = (int) (dm.widthPixels * 0.85);
	}
	
	@Override
	public void setTitle(int title) {
		mTitle = getContext().getText(title);
	}
	
	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
	}
	
	public AlertDialog setMsgTopDrawable(int resId) {
		mMsgTopDrawableRes = resId;
		return this;
	}
	
	public AlertDialog setMessage(int message) {
		mMessage = getContext().getText(message);
		return this;
	}
	
	public AlertDialog setMessage(CharSequence message) {
		mMessage = message;
		return this;
	}

	public AlertDialog setItems(String[] items) {
		mItems = items;
		return this;
	}
	
	public AlertDialog setItems(List<String> items) {
		if(items != null) {
			mItems = new String[items.size()];
			items.toArray(mItems);
		}
		return this;
	}
	
	public AlertDialog setGrayButtonListener(int resId, OnClickListener listenter) {
		return setGrayButtonListener(getContext().getText(resId), listenter);
	}
	
	public AlertDialog setTitleGravity(int gravity) {
		mTitleGravity = gravity;
		return this;
	}
	
	public AlertDialog setGrayButtonListener(CharSequence text, OnClickListener listenter) {
		mGrayBtn = text;
		mGrayClickListener = listenter;
		return this;
	}
	
	public AlertDialog setGreenButtonListener(int resId, OnClickListener listenter) {
		return setGreenButtonListener(getContext().getText(resId), listenter);
	}
	
	public AlertDialog setGreenButtonListener(CharSequence text, OnClickListener listenter) {
		mGreenBtn = text;
		mGreenClickListener = listenter;
		return this;
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		if (id == R.id.graybtn) {
			dismiss();
			if(mGrayClickListener != null) {
				mGrayClickListener.onClick(this, BUTTON1);
			}
		} else if (id == R.id.greenbtn) {
			dismiss();
			if(mGreenClickListener != null) {
				mGreenClickListener.onClick(this, BUTTON2);
			}
		}
	}
	
	private class ItemAdapter extends BaseAdapter<String> {

		private int padding = (int) DeviceUtils.dipToPx(3);
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			TextView view = null;
			if(convertView == null) {
				view = new TextView(getContext());
				view.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
				view.setTextColor(0xff666666);
				view.setPadding(0, padding, 0, padding);
			} else {
				view = (TextView) convertView;
			}
			
			view.setText(getItem(position));
			return view;
		}
		
	}
}