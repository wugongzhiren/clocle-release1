package com.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;



/**
 * Created by Administrator on 2016/7/2.
 */
public class SlidingMenu extends HorizontalScrollView {
    private LinearLayout mWrapper;
    private ViewGroup mMenu;
    private ViewGroup mContent;
    private int screenwidth;
    private int menuwidth;
    private int menuRightMargin = 100;
    private boolean once = false;
    private boolean isopen = false;

    /* public SlidingMenu(Context context, AttributeSet attrs, int defStyleAttr) {
         super(context, attrs, defStyleAttr);
         //获取自定义的属性
         TypedArray array=context.getTheme().obtainStyledAttributes(attrs, R.styleable.SlidingMenu,defStyleAttr,0);
     int count =array.getIndexCount();//获取自定义属性的数量
         //System.out.print(count);
         Log.i("info",String.valueOf(count));
         for(int i=0;i<count;i++){
               int att= array.getIndex(i);
             switch (att){
                 case R.styleable.SlidingMenu_rightmargin:
                     menuRightMargin =array.getDimensionPixelSize(att,(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,50,context.getResources().getDisplayMetrics()));
             break;
             }

         }
         array.recycle();
     }
 */
    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        //this(context,attrs,0);

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(outMetrics);
        screenwidth = outMetrics.widthPixels;

        //把DP转化为SP
        menuRightMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics());
    }

    //  public SlidingMenu(Context context) {
    //    this(context,null);
    // }

    /**
     * 设置子类和自己的宽和高
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (!once) {
            mWrapper = (LinearLayout) getChildAt(0);
            mMenu = (ViewGroup) mWrapper.getChildAt(0);
            mContent = (ViewGroup) mWrapper.getChildAt(1);
            menuwidth = mMenu.getLayoutParams().width = screenwidth - menuRightMargin;
            mContent.getLayoutParams().width = screenwidth;


            once = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    /**
     * 通过设置偏移量，将menu隐藏
     *
     * @param changed
     * @param l
     * @param t
     * @param r
     * @param b
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (changed) {
            this.scrollTo(menuwidth, 0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                int scrollX = getScrollX();
                if (scrollX > menuwidth / 2) {
                    this.smoothScrollTo(menuwidth, 0);
                    isopen = false;

                } else {
                    this.smoothScrollTo(0, 0);
                    isopen = true;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }

    public void openMenu() {
        if (isopen) return;
        smoothScrollTo(0, 0);
    }

    public void closeMenu() {
        if (!isopen) return;
        smoothScrollTo(menuwidth, 0);
    }


    /**
     * 监视滚动条的变化
     *
     * @param l    scrollX（向右边拉了多少距离）的值
     * @param t
     * @param oldl
     * @param oldt
     */
    @Override
    public void onScrollChanged(int l, int t, int oldl, int oldt) {

        super.onScrollChanged(l, t, oldl, oldt);
  /*      float scale = l * 1.0f / menuwidth;//l的初始值为menuwidth的长度
        float rightscale = 0.7f + 0.3f * scale;
        float leftscale = 1.0f - scale * 0.3f;
        float leftAlpha = 0.6f + 0.4f * (1 - scale);
        ViewHelper.setTranslationX(mMenu, menuwidth * scale * 0.7f);
        ViewHelper.setScaleX(mMenu, leftscale);
        ViewHelper.setScaleY(mMenu, leftscale);
        ViewHelper.setAlpha(mMenu, leftAlpha);
        //  设置content的缩放的中心点
        ViewHelper.setPivotX(mContent, 0);
        ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
        ViewHelper.setScaleX(mContent, rightscale);
        ViewHelper.setScaleY(mContent, rightscale);*/
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isopen) {
closeMenu();
                return true;
            }
        }
        return true;

    }
}
