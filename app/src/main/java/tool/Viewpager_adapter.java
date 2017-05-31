package tool;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/8/30.
 */
public class Viewpager_adapter extends PagerAdapter {
    private List<View> views;
    private List<String> tabnameList;
    private Context mcontext;

    public Viewpager_adapter(List<View> viewList, List<String> tabname, Context context) {
        this.views = viewList;
        this.mcontext = context;
        this.tabnameList = tabname;
    }

    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.remove(position));
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabnameList.get(position);
    }
}
