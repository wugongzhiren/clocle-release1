package tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipeline;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Fresco帮助类
 * Created by Administrator on 2016/12/4.
 */

public class ImageLoadFresco {
     //private Bitmap frescoBitmap;
    public static void getFrescoCacheBitmap(final Handler handler, Uri uri, Context context){
       // final Bitmap frescoTepBm;
    ImageRequest imageRequest = ImageRequestBuilder
            .newBuilderWithSource(uri)
            .setProgressiveRenderingEnabled(true)
            .build();
    ImagePipeline imagePipeline = Fresco.getImagePipeline();
    DataSource<CloseableReference<CloseableImage>>
            dataSource = imagePipeline.fetchDecodedImage(imageRequest, context);
    dataSource.subscribe(new BaseBitmapDataSubscriber() {
        @Override
        public void onNewResultImpl(Bitmap bitmap) {
            if (bitmap == null) {
               // Log.e(TAG,"保存图片失败啦,无法下载图片");
                handler.sendEmptyMessage(0);
                return;
            }
            Message message=new Message();
            message.obj=bitmap;
            handler.sendMessage(message);
        }

        @Override
        public void onFailureImpl(DataSource dataSource) {
            handler.sendEmptyMessage(0);
        }
    }, CallerThreadExecutor.getInstance());
  }
}
