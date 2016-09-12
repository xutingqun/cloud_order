package com.smart.library.view.vebview;

import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;

public class MyWebChromeClient extends WebChromeClient {
	ProgressBar progressBar;

	public MyWebChromeClient(ProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	@Override
	public void onProgressChanged(WebView view, int newProgress) {
		// TODO Auto-generated method stub
		Log.d("hujun", "newProgress--->" + newProgress);
		if (newProgress == 100) {
			// Õ¯“≥º”‘ÿÕÍ≥…
			progressBar.setVisibility(View.GONE);
		} else {
			if (View.INVISIBLE == progressBar.getVisibility()) {
				progressBar.setVisibility(View.VISIBLE);
			}
			progressBar.setProgress(newProgress);
		}
		super.onProgressChanged(view, newProgress);
	}

	@Override
	public void onReceivedTitle(WebView view, String title) {
		// TODO Auto-generated method stub
		super.onReceivedTitle(view, title);
	}

	@Override
	public void onReceivedIcon(WebView view, Bitmap icon) {
		// TODO Auto-generated method stub
		super.onReceivedIcon(view, icon);
	}

	@Override
	public boolean onJsAlert(WebView view, String url, String message,
			JsResult result) {
		// TODO Auto-generated method stub
		return super.onJsAlert(view, url, message, result);
	}

	@Override
	public boolean onJsConfirm(WebView view, String url, String message,
			JsResult result) {
		// TODO Auto-generated method stub
		return super.onJsConfirm(view, url, message, result);
	}

	@Override
	public boolean onJsPrompt(WebView view, String url, String message,
			String defaultValue, JsPromptResult result) {
		// TODO Auto-generated method stub
		return super.onJsPrompt(view, url, message, defaultValue, result);
	}

	@Override
	public boolean onJsBeforeUnload(WebView view, String url, String message,
			JsResult result) {
		// TODO Auto-generated method stub
		return super.onJsBeforeUnload(view, url, message, result);
	}

	@Override
	public boolean onJsTimeout() {
		// TODO Auto-generated method stub
		return super.onJsTimeout();
	}

}
