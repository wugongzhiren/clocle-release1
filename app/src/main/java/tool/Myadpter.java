package tool;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bean.Message;
import com.bean.Messages;
import com.clocle.huxiang.clocle.R;
import com.httpThread.Image_Loader;


import java.util.List;

import okhttp3.Call;
import okhttp3.Request;

/**首页的adapter
 * Created by Administrator on 2016/7/17.
 */
public class Myadpter extends BaseAdapter {
    private List<Messages> messages;
    private LayoutInflater layoutInflater;
    private Image_Loader image_loader=new Image_Loader();

    public Myadpter(Context mcontext, List<Messages> messages) {
        this.messages = messages;
        layoutInflater = LayoutInflater.from(mcontext);
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderSimple simple=null;
        if(convertView==null){
         simple =new ViewHolderSimple();
        convertView = layoutInflater.inflate(R.layout.messege_layout, parent, false);
        simple.name = (TextView) convertView.findViewById(R.id.namedongtai);
        simple.contexttext = (TextView) convertView.findViewById(R.id.contextdongtai);
        simple.self_info = (TextView) convertView.findViewById(R.id.timetext);
       // simple.photo = (ImageView) convertView.findViewById(R.id.photodongtai);

        convertView.setTag(simple);


          }
           else{
               simple= (ViewHolderSimple) convertView.getTag();
           }
        simple.name.setText(((Messages) getItem(position)).getName());
        simple.contexttext.setText(((Messages) getItem(position)).getMessage());
       // simple.self_info.setText(((Messages) getItem(position)).getSelf_info());
        //simple.photo.setImageResource(((Message) getItem(position)).getPic());
        String imageUrl=((Messages) getItem(position)).getPic();//获取头像地址
Log.i("tag" ,imageUrl);
        image_loader.loadImagebyAsyncTask(simple.photo,imageUrl);

        //simple.photo.setImageResource(R.mipmap.t6);
        //showImage(imageUrl,simple.photo);
        return convertView;
    }
    /*public void showImage(String url,final ImageView mimageView){
        OkHttpUtils
                .get()//
                .url(url)//
                .build()//
                .execute(new BitmapCallback() {
                    @Override
                    public void onError(Call call, Exception e, int i) {

                    }

                    @Override
                    public void onResponse(Bitmap bitmap, int i) {
                        mimageView.setImageBitmap(bitmap);
                    }
                });
    }*/
}
