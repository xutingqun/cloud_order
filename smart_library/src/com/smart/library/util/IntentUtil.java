package com.smart.library.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * 界面跳转处理
 * 
 * @author xutingqun
 * 
 */
public final class IntentUtil {

	/**
	 * 不重复界面跳转，不足之处要配合不让按钮快速点击
	 * 
	 * @param ac
	 * @param mClass
	 */
	public static void mStartActivity(Activity ac, Class<?> mClass) {
		// if (AppManager.isOnlyOneActivity(ac, mClass)) {
		ac.startActivity(new Intent(ac, mClass));
		// }
	}
	/**
	 * 不重复界面跳转，不足之处要配合不让按钮快速点击
	 * 
	 * @param context
	 * @param mClass
	 */
	public static void mStartActivity(Context ac, Class<?> mClass) {
		// if (AppManager.isOnlyOneActivity(ac, mClass)) {
		ac.startActivity(new Intent(ac, mClass));
		// }
	}
	/**
	 * 带Bundle数据跳转
	 * 
	 * @param ac
	 * @param mClass
	 */
	public static void mStartActivityWithBundle(Activity ac, Class<?> mClass,
			Bundle bundle) {
		// if (AppManager.isOnlyOneActivity(ac, mClass)) {
		Intent intent = new Intent();
		intent.setClass(ac, mClass); // 描述起点和目标
		Bundle mbundle = bundle; // 创建Bundle对象
		// bundle.putString("contentId", midContentId[position - 1] + "");
		// // 装入数据
		intent.putExtras(mbundle); // 把Bundle塞入Intent里面
		ac.startActivity(intent); // 开始切换
		// ac.startActivity(new Intent(ac,mClass));
		// }
	}
}
