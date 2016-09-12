package com.smart.library;

import java.io.File;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;

import android.app.Application;
import android.content.Context;

public class SmartApplication extends Application{
	
	private static SmartApplication xbApplication;
	private Context context;
    public static Md5FileNameGenerator md5FileNameGenerator = new Md5FileNameGenerator();
	public static SmartApplication getInstance() {
		return xbApplication;
	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		xbApplication = this;
		
		 File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "smart_hr/image");
	        ImageLoaderConfiguration config = new ImageLoaderConfiguration
	                .Builder(this)
	                .threadPoolSize(1)//线程池内加载的数量
	                .threadPriority(Thread.NORM_PRIORITY - 2)
	                .memoryCache(new WeakMemoryCache())
	                // .denyCacheImageMultipleSizesInMemory()
	                .diskCacheFileNameGenerator(md5FileNameGenerator)
	                        // 将保存的时候的URI名称用MD5 加密
	                .tasksProcessingOrder(QueueProcessingType.LIFO)
	                .diskCache(new UnlimitedDiscCache(cacheDir ,null ,md5FileNameGenerator))//自定义缓存路径
	                .defaultDisplayImageOptions(DisplayImageOptions.createSimple())
	                // .writeDebugLogs() // Remove for release app
	                .build();//开始构建
	        ImageLoader.getInstance().init(config);
		
	}
}
