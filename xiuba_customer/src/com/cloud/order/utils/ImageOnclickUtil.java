package com.cloud.order.utils;

import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class ImageOnclickUtil {
	
	public static void viewSetOnTouchListene(View view,final ImageView imgView){
		
		view.setOnTouchListener(new OnTouchListener() {		
			@Override
			public boolean onTouch(View view, MotionEvent event) {
				// TODO Auto-generated method stub
				switch (event.getAction()) {
				case MotionEvent.ACTION_UP:
					changeLight(imgView, 0);
					// onclick
					break;
				case MotionEvent.ACTION_DOWN:
					changeLight(imgView, -80);
					break;
				case MotionEvent.ACTION_MOVE:
					// changeLight(view, 0);
					break;
				case MotionEvent.ACTION_CANCEL:
					changeLight(imgView, 0);
					break;
				default:
					break;
				}
				return true;
			}
		});
		
		
	}
	
	private static void changeLight(ImageView imageview, int brightness) {
		ColorMatrix matrix = new ColorMatrix();
		matrix.set(new float[] { 1, 0, 0, 0, brightness, 0, 1, 0, 0,
				brightness, 0, 0, 1, 0, brightness, 0, 0, 0, 1, 0 });
		imageview.setColorFilter(new ColorMatrixColorFilter(matrix));

	}
}
