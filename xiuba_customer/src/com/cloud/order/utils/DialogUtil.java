package com.cloud.order.utils;

import com.cloud.order.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

public class DialogUtil {

	private static ProgressDialog dialog;
	private static Dialog mDialog;
	private Context context;
		
	public DialogUtil(Context context) {
		super();
		this.context = context;
	}

	public static void showDialog(Context mContext,String s){
		dialog = new ProgressDialog(mContext);
		//dialog.setTitle("提示");		
		dialog.setMessage(s);
		dialog.show();
	}
	
	public static void dismissDialog(){
		dialog.dismiss();
	}
	
    public static void showRoundProcessDialog(Context mContext,String s)
    {
        OnKeyListener keyListener = new OnKeyListener()
        {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event)
            {
                if (keyCode == KeyEvent.KEYCODE_HOME || keyCode == KeyEvent.KEYCODE_SEARCH)
                {
                    return true;
                }
                return false;
            }
        };
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.dialog_white,null);
        TextView tvTitle = (TextView) contentView.findViewById(R.id.loading_process_dialog_anim_text);
        tvTitle.setText(s);
        
        mDialog = new AlertDialog.Builder(mContext).create();
        mDialog.setOnKeyListener(keyListener);
        mDialog.show();
        // 注意此处要放在show之后 否则会报异常
        mDialog.setContentView(contentView);
    }
    public static void closeRoundProcessDialog()
    {
    	try {
    		 mDialog.dismiss();
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
    }
}
