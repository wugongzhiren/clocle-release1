package test;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.clocle.huxiang.clocle.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

/**
 * Created by Administrator on 2016/9/3.
 */
public class Test_Fresco extends Activity {
    SimpleDraweeView view;
    Uri uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
setContentView(R.layout.test_fresco);
        Toast.makeText(this,"测试2",Toast.LENGTH_SHORT).show();
         view= (SimpleDraweeView) findViewById(R.id.my_image_view);
      // uri = Uri.parse("assets:///addphoto.png");
      // uri = Uri.parse("res://com.clocle.huxiang.clocle/"+R.mipmap.addphoto);
       // SimpleDraweeView draweeView = (SimpleDraweeView) findViewById(R.id.my_image_view);
      //String url="res://com.clocle.huxiang.clocle/"+ Uri.parse(R.mipmap.addphoto+"");
       view.setImageURI("http://bmob-cdn-6342.b0.upaiyun.com/2016/10/10/33555581dc2446f69e376abb3fffde67.png");
       /* ControllerListener listener=new BaseControllerListener(){
            @Override
            public void onFailure(String id, Throwable throwable) {
                super.onFailure(id, throwable);
            }
        };
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setTapToRetryEnabled(true)
                .setOldController(view.getController())
                .setControllerListener(listener)
                .build();

        view.setController(controller);
        Toast.makeText(this,"完成了8",Toast.LENGTH_SHORT).show();*/
    }
    public void show(View v){
      /*  Intent intent=new Intent(this,TestShareSDK.class);
startActivity(intent);
*/
        Uri uri = Uri.parse("https://sqimg.qq.com/qq_product_operations/im/2015/bg3_1600.jpg");
        int width = 40, height = 40;
        ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                .setResizeOptions(new ResizeOptions(width, height))
                .setAutoRotateEnabled(true)
                .build();
        DraweeController controller1 = Fresco.newDraweeControllerBuilder()
                .setOldController(view.getController())
                .setImageRequest(request)
                .build();
        view.setController(controller1);
    }
}
