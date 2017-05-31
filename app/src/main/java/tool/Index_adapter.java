package tool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bean.Message;
import com.clocle.huxiang.clocle.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/9.
 */
public class Index_adapter extends BaseAdapter {
    private List<Message> messeageList;
    private Context context;

    public Index_adapter(List<Message> messeageList, Context context) {
        this.messeageList = messeageList;
        this.context = context;
    }

    @Override

    public int getCount() {
        return messeageList.size();
    }

    @Override
    public Message getItem(int position) {
        return messeageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 此方法还可以优化
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override

    public View getView(int position, View convertView, ViewGroup parent) {

        //此方法是将listview的item项的布局转化为缓冲视图
        convertView= LayoutInflater.from(context).inflate(R.layout.messege_layout,parent,false);
        //实例化Item的布局控件
        // ImageView photo=(ImageView)convertView.findViewById(R.id.photodongtai);
        TextView name=(TextView)convertView.findViewById(R.id.namedongtai);
        TextView messagecontext=(TextView)convertView.findViewById(R.id.contextdongtai);
        TextView datetime=(TextView)convertView.findViewById(R.id.timetext) ;

        // photo.setImageResource(getItem(position).getPic());
        //datetime.setText(getItem(position).getDatetime());

         name.setText(getItem(position).getName());
         messagecontext.setText(getItem(position).getMessage());
         //return convertView;
       // ViewHolder holder = ViewHolder.get(context, convertView, parent, R.layout.messege_layout, position);
       // Message message = getItem(position);
       // ((ImageView) holder.getView(R.id.photodongtai)).setImageResource(message.getPic());//头像
       // ((TextView) holder.getView(R.id.namedongtai)).setText(message.getName());//昵称
       // ((TextView) holder.getView(R.id.timetext)).setText(message.getDatetime());//时间
       // ((ImageView) holder.getView(R.id.qiangdan)).setImageResource(message.getQiangdan());//抢单图片
       // ((TextView) holder.getView(R.id.contextdongtai)).setText(message.getMessage());//正文
        //((ImageView) holder.getView(R.id.photodongtai)).setImageResource(message.getPic());//评论
       // ((ImageView) holder.getView(R.id.photodongtai)).setImageResource(message.getPic());//点赞
    return convertView;
    }
}
