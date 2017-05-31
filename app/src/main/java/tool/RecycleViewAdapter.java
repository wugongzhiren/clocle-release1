package tool;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;

import android.widget.TextView;

import com.adapter.Rv_single_imgs_adapter;
import com.application.App;
import com.bean.Clocle_help;

import com.clocle.huxiang.clocle.Bmob_UserBean;
import com.clocle.huxiang.clocle.Other_Self_infos;
import com.clocle.huxiang.clocle.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.function.Clocle_help_details;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.view.Preview_photo;


import java.util.ArrayList;
import java.util.List;

/**
 * 圈圈帮寻求帮助的RV适配器
 * Created by Administrator on 2016/8/15.
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private LayoutInflater inflater;
    private List<Clocle_help> datas;
    public static Context mcontext;

    private final static int NO_IMAGE = 0;
    private final static int SINGLE_IMAGE = 1;
    private final static int MUTI_IMAGE = 2;
    private final static int DOUBLE_IMAGE=3;
    private final static int FOOTER=4;//recycleview的footer
    private int deviceWidth;
    private int deviceHeight;
    private int marginleft;
    private int img0width;
    private int img1width;
    private int img0height;
    private int img1height;


    DisplayImageOptions options;

    public RecycleViewAdapter(Context context, List<Clocle_help> datas) {
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
        this.mcontext = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("tag", "创建Viewholder");
        View view = null;
        view =inflater.inflate(R.layout.clocle_gethelp_item,parent,false);
        return new MyViewHolder(view);
    }

    /**
     * 此方法需要大规模的优化
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        final String pic=datas.get(position).getBmob_userBean().getphotoUrl();
        holder.itemView.setTag(datas.get(position).getObjectId());
        if (holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).photo.setImageURI(pic);
            ((MyViewHolder) holder).name.setText(datas.get(position).getBmob_userBean().getUsername());
            ((MyViewHolder) holder).contexttext.setText(datas.get(position).getContent());
            //设置imgsRV的高度
            if(datas.get(position).getImgs()!=null) {
                ((MyViewHolder) holder).imgs.setAdapter(new Rv_single_imgs_adapter(datas.get(position).getImgs(), mcontext));
            }
            else{
                ((MyViewHolder) holder).imgs.setAdapter(null);
            }
        }
        /*//无图片
        if (holder instanceof ViewHolderwithoutImg) {
            //性别
            if (datas.get(position).getBmob_userBean().getSex().equals("女")) {
                ((ViewHolderwithoutImg) holder).sex.setImageResource(R.mipmap.woman);
            } else {
                ((ViewHolderwithoutImg) holder).sex.setImageResource(R.mipmap.man);
            }
            //头像

            ((ViewHolderwithoutImg) holder).photo.setImageURI(Uri.parse(datas.get(position).getBmob_userBean().getphotoUrl()));

            //  ImageLoader.getInstance().displayImage(datas.get(position).getPic(), ((ViewHolderwithoutImg) holder).photo, options);
            //昵称，时间，学校
            final String nickname=datas.get(position).getBmob_userBean().getUsername();
            final String text=datas.get(position).getContent();
            ((ViewHolderwithoutImg) holder).name.setText(nickname);//昵称
            ((ViewHolderwithoutImg) holder).contexttext.setText(text);//正文内容
            ((ViewHolderwithoutImg) holder).itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(App.getContext(),Clocle_help_details.class);
                    intent.putExtra("nickname",nickname);
                    intent.putExtra("detailsText",text);
                    intent.putStringArrayListExtra("urls",null);
                    intent.putExtra("userphoto",pic);
                    mcontext.startActivity(intent);
                }
            });
        }*/

        //之所以出现错乱，是因为holder复用了img1url为空的视图
        //不显示图片

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

/*    //每个Item的点击事件
    @Override
    public void onClick(View v) {

        Toast.makeText(mcontext, getItemId()+"点击了", Toast.LENGTH_SHORT).show();
    }*/
class ViewHolderFooter extends RecyclerView.ViewHolder {
private TextView textView;

    public ViewHolderFooter(View itemView) {
        super(itemView);
        textView= (TextView) itemView.findViewById(R.id.footer);

    }
}

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;//昵称
        public TextView time;//发表日期
        public TextView contexttext;//正文
        public SimpleDraweeView photo;//头像
        public TextView school;//学校
        public TextView tag;//标签
        public ImageView sex;//性别可选
        public TextView people_limit;//可参与人数
        public TextView money;//金额
        public ImageView get;//抢单
        public ImageView sendmsg;//发送私信
        public ImageView share;//分享
        public RecyclerView imgs;
        //private RecycleViewAdapter.OnItemClickListener monItemClickListener;
        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.nickname);
            contexttext = (TextView) itemView.findViewById(R.id.clocle_content);
            time = (TextView) itemView.findViewById(R.id.date);
            photo = (SimpleDraweeView) itemView.findViewById(R.id.user_photo);
            school = (TextView) itemView.findViewById(R.id.school);
            sex = (ImageView) itemView.findViewById(R.id.help_sex);
            imgs= (RecyclerView) itemView.findViewById(R.id.imgs);
            people_limit= (TextView) itemView.findViewById(R.id.people_limit);
            money= (TextView) itemView.findViewById(R.id.money);
            get=(ImageView) itemView.findViewById(R.id.gethelp);
            sendmsg=(ImageView) itemView.findViewById(R.id.sendmsg);
            share=(ImageView) itemView.findViewById(R.id.share);
            imgs.setLayoutManager(new GridLayoutManager(mcontext,3));
        }

        @Override
        public void onClick(View v) {
            Intent intent = null;
            String itemid = (String) itemView.getTag();
            switch (v.getId()) {
                //点击头像跳转个人资料页
                case R.id.help_user_photo:
                    Log.d("tagtiaozhuang", (String) itemView.getTag());
                    //tag是help_id+userid
                    intent = new Intent(App.getContext(), Other_Self_infos.class);
                    intent.putExtra("itemtag", itemid);
                    mcontext.startActivity(intent);
                    break;
                //点击评论跳转到详情页
                case R.id.help_comment:
                    intent = new Intent(App.getContext(), Clocle_help_details.class);
                    intent.putExtra("itemtag", itemid);
                    mcontext.startActivity(intent);
                    break;
                case R.id.help_order:
                    new AlertDialog.Builder(mcontext).setTitle("确认帮助TA")
                            .setMessage("帮助TA后您可获取300颗圈圈豆，请尽快帮TA完成").setNegativeButton("我能完成", null).setPositiveButton("点错了", null).show();
                default:
                    break;
            }
        }
    }


    /*class ViewHolderwithSingleImg extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView time;
        public TextView contexttext;//正文
        public SimpleDraweeView photo;
        public TextView school;
        public TextView help_order;
        public SimpleDraweeView only_one_image;


        public ImageView sex;
        //private RecycleViewAdapter.OnItemClickListener monItemClickListener;

        public ViewHolderwithSingleImg(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.namedongtai);
            contexttext = (TextView) itemView.findViewById(R.id.contextdongtai);
            time = (TextView) itemView.findViewById(R.id.timetext);
            photo = (SimpleDraweeView) itemView.findViewById(R.id.help_userphoto1);
            school = (TextView) itemView.findViewById(R.id.school);
            only_one_image = (SimpleDraweeView) itemView.findViewById(R.id.only_one_image);
            //获取view的宽高像素
          //  ViewGroup.LayoutParams lp = only_one_image.getLayoutParams();
          //  lp.width = img0width;
          //  lp.height = img0height;

            help_order = (TextView) itemView.findViewById(R.id.help_order);
            sex = (ImageView) itemView.findViewById(R.id.help_sex);
            photo.setOnClickListener(this);
            // this.monItemClickListener=onItemClickListener;
            //itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = null;
            String itemid = (String) itemView.getTag();
            switch (v.getId()) {
                //点击头像跳转个人资料页
                case R.id.help_userphoto1:
                    Log.d("tagtiaozhuang", (String) itemView.getTag());
                    //tag是help_id+userid
                    intent = new Intent(App.getContext(), Other_Self_infos.class);
                    intent.putExtra("itemtag", itemid);
                    mcontext.startActivity(intent);
                    break;
                //点击评论跳转到详情页
                case R.id.help_comment:
                    intent = new Intent(App.getContext(), Clocle_help_details.class);
                    intent.putExtra("itemtag", itemid);
                    mcontext.startActivity(intent);
                    break;
                case R.id.help_order:
                    new AlertDialog.Builder(mcontext).setTitle("确认帮助TA")
                            .setMessage("帮助TA后您可获取300颗圈圈豆，请尽快帮TA完成").setNegativeButton("我能完成", null).setPositiveButton("点错了", null).show();
                default:
                    break;
            }
        }

    }
    class ViewHolderwithDoubleImg extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView time;
        public TextView contexttext;//正文
        public SimpleDraweeView photo;
        public TextView school;
        public TextView help_comment;
        public TextView help_order;
        public SimpleDraweeView help_imgs1;
        public SimpleDraweeView help_imgs2;

        public ImageView sex;

        //private RecycleViewAdapter.OnItemClickListener monItemClickListener;

        public ViewHolderwithDoubleImg(View itemView) {

            super(itemView);


            name = (TextView) itemView.findViewById(R.id.namedongtai_double);
            contexttext = (TextView) itemView.findViewById(R.id.contextdongtai_double);
            time = (TextView) itemView.findViewById(R.id.timetext);
            photo = (SimpleDraweeView) itemView.findViewById(R.id.help_userphoto_double);
            school = (TextView) itemView.findViewById(R.id.school);
            help_comment = (TextView) itemView.findViewById(R.id.help_comment);
            help_imgs1 = (SimpleDraweeView) itemView.findViewById(R.id.first_image);
            //help_imgs1.getLayoutParams().height= help_imgs1.getLayoutParams().width;
            //获取view的宽高像素
            //  ViewGroup.LayoutParams lp1 = help_imgs1.getLayoutParams();
            //  lp1.height = img1height;
            //  lp1.width = img1width;

            help_imgs2 = (SimpleDraweeView) itemView.findViewById(R.id.second_image);
           // help_imgs2.getLayoutParams().height= help_imgs2.getLayoutParams().width;
            //   ViewGroup.LayoutParams lp2 = help_imgs2.getLayoutParams();
            //   lp2.height = img1height;
            //    lp2.width = img1width;

            //    ViewGroup.LayoutParams lp3 = help_imgs3.getLayoutParams();
            //    lp3.height = img1height;
            //    lp3.width = img1width;

            sex = (ImageView) itemView.findViewById(R.id.help_sex);
            help_order = (TextView) itemView.findViewById(R.id.help_order);
            help_order.setOnClickListener(this);
            photo.setOnClickListener(this);
            help_comment.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent = null;
            String itemid = (String) itemView.getTag();
            switch (v.getId()) {
                case R.id.help_userphoto3:
                    //跳转到查看个人信息的页面

                    intent = new Intent(App.getContext(), Other_Self_infos.class);
                    intent.putExtra("itemtag", itemid);
                    mcontext.startActivity(intent);


                    break;
                case R.id.help_comment:
                    Log.i("tag2", "查看悬赏" + itemView.getTag());
                    intent = new Intent(App.getContext(), Clocle_help_details.class);
                    mcontext.startActivity(intent);
                    break;


                case R.id.help_order:
                    new AlertDialog.Builder(mcontext).setTitle("确认帮助TA")
                            .setMessage("帮助TA后您可获取300颗圈圈豆，请尽快帮TA完成").setNegativeButton("我能完成", null).setPositiveButton("点错了", null).show();
                default:
                    break;
            }
        }
    }

    *//**
     * image的加载用了fresco
     *//*

    class ViewHolderwithMutiImg extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public TextView time;
        public TextView contexttext;//正文
        public SimpleDraweeView photo;
        public TextView school;
        public TextView help_comment;
        public TextView help_order;
        public SimpleDraweeView help_imgs1;
        public SimpleDraweeView help_imgs2;
        public SimpleDraweeView help_imgs3;
        public ImageView sex;

        //private RecycleViewAdapter.OnItemClickListener monItemClickListener;

        public ViewHolderwithMutiImg(View itemView) {

            super(itemView);


            name = (TextView) itemView.findViewById(R.id.namedongtai);
            contexttext = (TextView) itemView.findViewById(R.id.contextdongtai);
            time = (TextView) itemView.findViewById(R.id.timetext);
            photo = (SimpleDraweeView) itemView.findViewById(R.id.help_userphoto3);
            school = (TextView) itemView.findViewById(R.id.school);
            help_comment = (TextView) itemView.findViewById(R.id.help_comment);
            help_imgs1 = (SimpleDraweeView) itemView.findViewById(R.id.help_imgs1);
            //获取view的宽高像素
          //  ViewGroup.LayoutParams lp1 = help_imgs1.getLayoutParams();
          //  lp1.height = img1height;
          //  lp1.width = img1width;

            help_imgs2 = (SimpleDraweeView) itemView.findViewById(R.id.help_imgs2);
         //   ViewGroup.LayoutParams lp2 = help_imgs2.getLayoutParams();
         //   lp2.height = img1height;
        //    lp2.width = img1width;
            help_imgs3 = (SimpleDraweeView) itemView.findViewById(R.id.help_imgs3);
        //    ViewGroup.LayoutParams lp3 = help_imgs3.getLayoutParams();
        //    lp3.height = img1height;
        //    lp3.width = img1width;

            sex = (ImageView) itemView.findViewById(R.id.help_sex);
            help_order = (TextView) itemView.findViewById(R.id.help_order);
            help_order.setOnClickListener(this);
            photo.setOnClickListener(this);
            help_comment.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent = null;
            String itemid = (String) itemView.getTag();
            switch (v.getId()) {
                case R.id.help_userphoto3:
                    //跳转到查看个人信息的页面

                    intent = new Intent(App.getContext(), Other_Self_infos.class);
                    intent.putExtra("itemtag", itemid);
                    mcontext.startActivity(intent);


                    break;
                case R.id.help_comment:
                    Log.i("tag2", "查看悬赏" + itemView.getTag());
                    intent = new Intent(App.getContext(), Clocle_help_details.class);
                    mcontext.startActivity(intent);
                    break;


                case R.id.help_order:
                    new AlertDialog.Builder(mcontext).setTitle("确认帮助TA")
                            .setMessage("帮助TA后您可获取300颗圈圈豆，请尽快帮TA完成").setNegativeButton("我能完成", null).setPositiveButton("点错了", null).show();
                default:
                    break;
            }
        }
    }*/
}
