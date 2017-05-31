package com.httpThread;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;



import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.Call;

/**
 * Created by Administrator on 2016/8/3.
 */
public class Image_Loader {
    public void loadImagebyAsyncTask(ImageView imageView,String url){
        new Imageloader_AysncTask(imageView).execute(url);//其实execute方法就是调用doinbackground方法

    }
    class Imageloader_AysncTask extends AsyncTask<String,Void,Bitmap> {
        private ImageView index_image;//首页最新动态的用户头像

        public Imageloader_AysncTask(ImageView imageView) {
            this.index_image = imageView;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                return getBitmapFromUrl(params[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            index_image.setImageBitmap(bitmap);
        }
    }
    public Bitmap getBitmapFromUrl(String url) throws IOException {
        Bitmap bitmapphoto;
        InputStream inputStream;
        URL url1=new URL(url);
        HttpURLConnection connection= (HttpURLConnection) url1.openConnection();
        inputStream=new BufferedInputStream(connection.getInputStream());
        bitmapphoto= BitmapFactory.decodeStream(inputStream);
        connection.disconnect();
        inputStream.close();
        return bitmapphoto;
    }
}

