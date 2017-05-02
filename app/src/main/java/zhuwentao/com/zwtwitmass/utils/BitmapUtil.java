package zhuwentao.com.zwtwitmass.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Bitmap工具类
 * Created by zhuwentao on 2017-04-27.
 */
public class BitmapUtil {

    /**
     * 保存图片路径
     *
     * @param mbitmap 图片
     * @return
     */
    public static void saveBitmapToSD(Bitmap mbitmap, Context context) {
        FileOutputStream outStream = null;
        // 指定图片保存的路径和文件名
        try {
            outStream = new FileOutputStream(getPhotoFileName(context));
            // 把数据写入文件
            mbitmap.compress(Bitmap.CompressFormat.PNG, 100, outStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (outStream != null) {
                    outStream.close();
                }
                if (mbitmap != null) {
                    mbitmap.recycle();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 把原图按1/5的比例压缩
     *
     * @param path 原图的路径
     * @return 压缩后的图片
     */
    public static Bitmap compressBitmap(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        options.inSampleSize = 5;  // 图片的长宽设置为原来的五分之一
        Bitmap bmp = BitmapFactory.decodeFile(path, options);
        options = null;
        return bmp;
    }


    /**
     * 获取图片种类
     *
     * @param path 图片路径
     * @return 图片种类: png/jpg/... 不能识别的返回png
     */
    public static String getPhotoType(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        String type = options.outMimeType;
        if (TextUtils.isEmpty(type)) {
            type = "png";
        }
        // image/*
        type = type.substring(6, type.length());
        options = null;
        return type;
    }


    /**
     * 使用当前系统时间作为图片的名称
     *
     * @return SD卡的路径和图片名称
     */
    public static String getPhotoFileName(Context context) {
        String photoFils = "/photo";
        File file = new File(context.getExternalCacheDir().getAbsolutePath() + photoFils);
        // 判断文件是否已经存在，不存在则创建
        if (!file.exists()) {
            file.mkdirs();
        }
        // 设置图片文件名称
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss", Locale.getDefault());
        Date date = new Date(System.currentTimeMillis());
        String time = format.format(date);
        String photoName = "/" + time + ".png";
        return file + photoName;
    }


    /**
     * 剪切图片
     * 9998 - 9999 这设置是为了防止有的手机默认的截图框是圆形的
     *
     * @param uri 地址
     */
    public void CutPhoto(Uri uri, Context context) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 9998);
        intent.putExtra("aspectY", 9999);
        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.PNG.toString());

        // 在有的手机，如小米部分机型上，会出现截图无法保存的情况，这时需要我们指定截图保存的URL位置
        String cropFilePath = getPhotoFileName(context);
        File file = new File(cropFilePath);
        String filePaht = file.getAbsolutePath(); // 截图后保存的图片路径
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
        ((Activity) context).startActivityForResult(intent, 101);
    }
}
