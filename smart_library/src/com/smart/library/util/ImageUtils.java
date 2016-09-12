package com.smart.library.util;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class ImageUtils {
	static ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	static ImageLoader imageLoader = ImageLoader.getInstance();
	
	/**
	 * 图片加载第一次显示监听器
	 * 
	 * @author Administrator
	 * 
	 */
	private static class AnimateFirstDisplayListener extends
			SimpleImageLoadingListener implements ImageLoadingListener {

		static final List<String> displayedImages = Collections
				.synchronizedList(new LinkedList<String>());

		@Override
		public void onLoadingComplete(String imageUri, View view,
				Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				// 是否第一次显示
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					// 图片淡入效果
					FadeInBitmapDisplayer.animate(imageView, 1000);
					displayedImages.add(imageUri);
				}
			}
		}
	}
	
	/**
	 * 网络获取图片，不设置圆角
	 * @param url 图片路径
	 * @param photo_image 设置图片的view
	 * @param loadingImage 等待获取时设置的图片
	 * @param emptyImage 图片为空时设置的图片
	 * @param errorImage 获取图片错误时设置的图片
	 */
	public static void setImage(String url, ImageView photo_image,int loadingImage,int emptyImage,int errorImage) {		
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showStubImage(loadingImage) // 设置图片下载期间显示的图片
				.showImageForEmptyUri(emptyImage) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(errorImage) // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
				// .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
				.build(); // 创建配置过得DisplayImageOption对象;
	
		imageLoader.displayImage(url, photo_image, options,
				animateFirstListener);
	}
	
	/**
	 *  网络获取图片，设置圆角
	 * @param url 图片路径
	 * @param photo_image 设置图片的view
	 * @param loadingImage 等待获取时设置的图片
	 * @param emptyImage 图片为空时设置的图片
	 * @param errorImage 获取图片错误时设置的图片
	 * @param roundedBitmapDisplayer 图片圆角的值
	 */
	public static void setSelectImage(String url, ImageView photo_image,int loadingImage,int emptyImage,int errorImage,int roundedBitmapDisplayer) {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showStubImage(loadingImage) // 设置图片下载期间显示的图片
				.showImageForEmptyUri(emptyImage) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(errorImage) // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
				 .displayer(new RoundedBitmapDisplayer(roundedBitmapDisplayer)) // 设置成圆角图片
				.build(); // 创建配置过得DisplayImageOption对象;
	
		imageLoader.displayImage(url, photo_image, options,
				animateFirstListener);
	}
	
	/**
	 * 清除内存缓存
	 */
	public static void onClearMemoryClick() {
		imageLoader.getInstance().clearMemoryCache();  // 清除内存缓存
	}
	
	/**
	 * 清除本地缓存
	 */
    public static void onClearDiskClick() {
    	imageLoader.getInstance().clearDiskCache();  // 清除本地缓存
    }
	

}
