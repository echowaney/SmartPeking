package com.example.hashwaney.zhbj33.utils.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by HashWaney on 2017/1/31.
 */

public class BitmapFromNet {

    private static final String TAG = "BitmapFromNet";
    private Bitmap  mBitmap;
    private Context mContext;

    public void loadBitmapFormNet(Context context, ImageView imageView, String url) {

        mContext = context;
        new BitmapTask().execute(imageView, url);
        //        imageView.setTag(mBitmap);  标记打错了
        imageView.setTag(url);  //应该是以url地址为唯一的标志

        //将数据缓存到磁盘中

        //将数据缓存到内存中

    }

    //需要异步执行加载网络图片
    class BitmapTask
            extends AsyncTask<Object, Void, Bitmap>
    {

        private ImageView mImageView;
        private String    mUrl;


        @Override
        protected Bitmap doInBackground(Object... params) {
            mUrl = (String) params[1];
            mImageView = (ImageView) params[0];
            //下载图片的
            mBitmap = loadBitmap(mUrl);
            //保存到本地磁盘中
            BitmapFromLocal.saveLocalFile(mContext, mUrl, mBitmap);

            //保存到内存中
            BitmapFromCache.saveCache(mUrl,mBitmap);
            return mBitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //显示bitmap
            //            if (bitmap != null) {
            //                mImageView.setImageBitmap(bitmap);
            //
            //            }
            //            Bitmap mBitmap = (Bitmap) mImageView.getTag();
            String url = (String) mImageView.getTag();
            if (bitmap != null && mUrl.equals(url)) {
                //这样做就双重保险了 不会发生图片的错乱

                mImageView.setImageBitmap(bitmap);
            }

        }
    }

    private Bitmap loadBitmap(String url) {
        Bitmap            mBitmap = null;
        HttpURLConnection mConn   = null;
        try {
            URL uRl = new URL(url);
            mConn = (HttpURLConnection) uRl.openConnection();
            mConn.setRequestMethod("GET");
            mConn.setReadTimeout(3000);
            mConn.setConnectTimeout(5000);
            int responseCode = mConn.getResponseCode();
            if (responseCode == 200) {
                //请求成功
                InputStream inputStream = mConn.getInputStream();

                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inSampleSize = 2;
                options.inJustDecodeBounds = true; //设置true 代表着用户   不需要去
                //                int  outHeight = options.outHeight;
                //                int  outWidth  = options.outWidth;
                //                Rect rect      = new Rect(0, 0, outWidth, outHeight);

                //                mBitmap = BitmapFactory.decodeStream(inputStream, null, options);

                mBitmap = BitmapFactory.decodeStream(inputStream);

                Log.e(TAG, "loadBitmap:  从网上加载图片了 ");
                //                imageView.setImageBitmap(mBitmap);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //关闭连接
            mConn.disconnect();
        }


        return mBitmap;
    }


}
