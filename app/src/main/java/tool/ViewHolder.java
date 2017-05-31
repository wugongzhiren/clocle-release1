package tool;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**convertview是ListView布局的实例化对象
 * SparseArray中是listView中的控件的实例化对象
 * 可以理解为Item视图的容器，起到视图的缓冲池作用，这样每次加载就不用通过findviewbyid(ItemId)的方式渲染了
 * Created by Administrator on 2016/7/17.
 */
public class ViewHolder {
    private SparseArray<View> views;//view容器，类似于map，比map的效率要高
    private int mposition;
    private View convertview;

    public ViewHolder(Context context, ViewGroup parent, int layoutid, int position) {
        this.mposition = position;
        this.views = new SparseArray<View>();
        convertview = LayoutInflater.from(context).inflate(layoutid, parent, false);//这样就能根据布局的id来加载布局
    //convertview.setTag(this);
    }

    public static ViewHolder get(Context context, View convertView, ViewGroup parent, int layoutid, int position) {
        //if (convertView == null) {
            return new ViewHolder(context, parent, layoutid, position);
       // } else {
        //    ViewHolder holder = (ViewHolder) convertView.getTag();
          //  holder.mposition=position;
         //   return holder;
       // }
    }

    /**
     * 获取控件对象
     * @param viewid
     * @return
     */
    public  View  getView(int viewid){
       View view = views.get(viewid);
        if(view == null){
            view = convertview.findViewById(viewid);
            views.put(viewid,view);
        }
        return view;
    }
    public View getConvertview(){
        return convertview;
    }
}
