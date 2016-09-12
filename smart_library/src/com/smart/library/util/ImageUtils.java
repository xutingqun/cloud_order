package com.smart.library.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.HashCodeFileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.utils.StorageUtils;

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
    /***
    * 保存图片到SD卡
    */
    public static void saveImgToSD(String iamgeUrl,final Activity activity) { 
    	DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
    .cacheOnDisk(true).cacheInMemory(true).build();
    ImageLoader.getInstance().loadImage(iamgeUrl,displayImageOptions, new ImageLoadingListener() {
    @Override
    public void onLoadingStarted(String imageUri, View view) {
    }
    @Override
    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
    }
    @Override
    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
    String fileName = new HashCodeFileNameGenerator().generate(imageUri) + ".jpeg"; // String path =StorageUtils.getOwnCacheDirectory(PhotoActivity.this, "chuizhicai/image/userSaveImage").getPath();//保存的确切位置;/
    String path;
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.FROYO) {
    path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath()+"/joke";
    }else {
    path = StorageUtils.getOwnCacheDirectory(activity, "chuizhicai/image/userSaveImage").getPath();//保存的确切位置 }
    }
    File saveImageFile = ImageUtils.saveFile(loadedImage, fileName, path);
    if (saveImageFile!=null){
    ToastUtil.showToast(activity, String.format("%s%s/%s", "图片保存到", path, fileName));
    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
    Uri uri = Uri.fromFile(saveImageFile);
    intent.setData(uri);
    activity.sendBroadcast(intent);//这个广播的目的就是更新图库，发了这个广播进入相册就可以找到你保存的图片了！，记得要传你更新的file哦
    } else 
    	 ToastUtil.showToast(activity, "图片保存失败");
    //对bitmap进行垃圾回收
    loadedImage.recycle();
    }
    @Override
    public void onLoadingCancelled(String imageUri, View view) {
    }
    }); 
    }

    public static File saveFile(Bitmap bm, String fileName, String path) {
    File myCaptureFile = null;
    try {
    String subForder = path;
    File foder = new File(subForder);
    if (!foder.exists()) {
    foder.mkdirs();
    }
    myCaptureFile = new File(subForder, fileName);
    if (!myCaptureFile.exists()) {
    myCaptureFile.createNewFile();
    }
    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(myCaptureFile));
    bm.compress(Bitmap.CompressFormat.JPEG, 100, bos);
    bos.flush();
    bos.close();
    }catch (IOException e){
    e.printStackTrace();
    }
    return myCaptureFile;
    } 
}
