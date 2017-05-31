package test;

import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;


import com.clocle.huxiang.clocle.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;

import me.relex.photodraweeview.PhotoDraweeView;

/**
 * Created by Administrator on 2016/9/5.
 */
public class Test_PhotoView extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_fresco);
        PipelineDraweeControllerBuilder controller = Fresco.newDraweeControllerBuilder();
        final PhotoDraweeView view= (PhotoDraweeView) findViewById(R.id.photo_drawee_view);
        controller.setUri(Uri.parse("https://sqimg.qq.com/qq_product_operations/im/2015/bg3_1600.jpg"));
        controller.setOldController(view.getController());
        controller.setControllerListener(new BaseControllerListener<ImageInfo>(){
            @Override
            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
                if (imageInfo == null || view == null) {
                    return;
                }
                view.update(imageInfo.getWidth(), imageInfo.getHeight());
            }
        });
        view.setController(controller.build());
    }
}