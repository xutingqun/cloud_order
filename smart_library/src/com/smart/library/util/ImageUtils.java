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
    /***
    * ����ͼƬ��SD��
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
    String fileName = new HashCodeFileNameGenerator().generate(imageUri) + ".jpeg"; // String path =StorageUtils.getOwnCacheDirectory(PhotoActivity.this, "chuizhicai/image/userSaveImage").getPath();//�����ȷ��λ��;/
    String path;
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.FROYO) {
    path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getPath()+"/joke";
    }else {
    path = StorageUtils.getOwnCacheDirectory(activity, "chuizhicai/image/userSaveImage").getPath();//�����ȷ��λ�� }
    }
    File saveImageFile = ImageUtils.saveFile(loadedImage, fileName, path);
    if (saveImageFile!=null){
    ToastUtil.showToast(activity, String.format("%s%s/%s", "ͼƬ���浽", path, fileName));
    Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
    Uri uri = Uri.fromFile(saveImageFile);
    intent.setData(uri);
    activity.sendBroadcast(intent);//����㲥��Ŀ�ľ��Ǹ���ͼ�⣬��������㲥�������Ϳ����ҵ��㱣���ͼƬ�ˣ����ǵ�Ҫ������µ�fileŶ
    } else 
    	 ToastUtil.showToast(activity, "ͼƬ����ʧ��");
    //��bitmap������������
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
