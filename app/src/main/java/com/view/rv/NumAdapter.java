package com.view.rv;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.clocle.huxiang.clocle.R;

import java.util.List;

/**
 * Created by Angel on 2017/4/7.
 */
public class NumAdapter extends SWRecyclerAdapter<String> {

    private List<String> list;
    private Context context;

    public NumAdapter(Context context, List<String> list) {
        super(context, list);
        this.context = context;
        this.list = list;
    }

    public int getItemLayoutId(int layoutID) {
        return R.layout.item;
    }

    public void bindData(final SWViewHolder holder, final int position, String item) {
        TextView text = holder.getTextView(R.id.text);
//        TextView text_top = holder.getTextView(R.id.text_top);

        text.setText(list.get(position) + "");
      /*  text.setOnClickListener(new View.OnClickListener() {
           *//* public void onClick(View v) {
                if (SWSlipeManager.getInstance().haveOpened()) {
                    SWSlipeManager.getInstance().close();
                } else {*//*
                    //Toast.makeText(context, position + 1 + "", Toast.LENGTH_SHORT).show();
              //  }
            }
        });*/
    /*    text_delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                delete(holder.getLayoutPosition());
                SWSlipeManager.getInstance().close();
            }
        });*/
//        text_top.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                SWSlipeManager.getInstance().close();
//                setTop(holder.getLayoutPosition());
//            }
//        });
    }

}
