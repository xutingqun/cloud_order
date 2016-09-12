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
	 * ͼƬ���ص�һ����ʾ������
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
				// �Ƿ��һ����ʾ
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					// ͼƬ����Ч��
					FadeInBitmapDisplayer.animate(imageView, 1000);
					displayedImages.add(imageUri);
				}
			}
		}
	}
	
	/**
	 * �����ȡͼƬ��������Բ��
	 * @param url ͼƬ·��
	 * @param photo_image ����ͼƬ��view
	 * @param loadingImage �ȴ���ȡʱ���õ�ͼƬ
	 * @param emptyImage ͼƬΪ��ʱ���õ�ͼƬ
	 * @param errorImage ��ȡͼƬ����ʱ���õ�ͼƬ
	 */
	public static void setImage(String url, ImageView photo_image,int loadingImage,int emptyImage,int errorImage) {		
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showStubImage(loadingImage) // ����ͼƬ�����ڼ���ʾ��ͼƬ
				.showImageForEmptyUri(emptyImage) // ����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
				.showImageOnFail(errorImage) // ����ͼƬ���ػ��������з���������ʾ��ͼƬ
				.cacheInMemory(true) // �������ص�ͼƬ�Ƿ񻺴����ڴ���
				.cacheOnDisc(true) // �������ص�ͼƬ�Ƿ񻺴���SD����
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
				// .displayer(new RoundedBitmapDisplayer(20)) // ���ó�Բ��ͼƬ
				.build(); // �������ù���DisplayImageOption����;
	
		imageLoader.displayImage(url, photo_image, options,
				animateFirstListener);
	}
	
	/**
	 *  �����ȡͼƬ������Բ��
	 * @param url ͼƬ·��
	 * @param photo_image ����ͼƬ��view
	 * @param loadingImage �ȴ���ȡʱ���õ�ͼƬ
	 * @param emptyImage ͼƬΪ��ʱ���õ�ͼƬ
	 * @param errorImage ��ȡͼƬ����ʱ���õ�ͼƬ
	 * @param roundedBitmapDisplayer ͼƬԲ�ǵ�ֵ
	 */
	public static void setSelectImage(String url, ImageView photo_image,int loadingImage,int emptyImage,int errorImage,int roundedBitmapDisplayer) {
		DisplayImageOptions options = new DisplayImageOptions.Builder()
				.showStubImage(loadingImage) // ����ͼƬ�����ڼ���ʾ��ͼƬ
				.showImageForEmptyUri(emptyImage) // ����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ
				.showImageOnFail(errorImage) // ����ͼƬ���ػ��������з���������ʾ��ͼƬ
				.cacheInMemory(true) // �������ص�ͼƬ�Ƿ񻺴����ڴ���
				.cacheOnDisc(true) // �������ص�ͼƬ�Ƿ񻺴���SD����
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
				 .displayer(new RoundedBitmapDisplayer(roundedBitmapDisplayer)) // ���ó�Բ��ͼƬ
				.build(); // �������ù���DisplayImageOption����;
	
		imageLoader.displayImage(url, photo_image, options,
				animateFirstListener);
	}
	
	/**
	 * ����ڴ滺��
	 */
	public static void onClearMemoryClick() {
		imageLoader.getInstance().clearMemoryCache();  // ����ڴ滺��
	}
	
	/**
	 * ������ػ���
	 */
    public static void onClearDiskClick() {
    	imageLoader.getInstance().clearDiskCache();  // ������ػ���
    }
	

}
