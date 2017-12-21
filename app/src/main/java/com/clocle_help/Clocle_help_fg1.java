package com.clocle_help;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.adapter.Clocle_help_adapter;
import com.application.App;
import com.bean.ClocleResult;
import com.bean.Clocle_help;
import com.clocle.huxiang.clocle.R;
import com.http.repository.Clocle_Help_ContentRs;
import com.view.EmptyLayout;
import com.view.EmptyRecleView;

import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tool.ShowToast;


/**
 * 圈圈帮-求助页-tab1
 * Created by Administrator on 2016/8/13.
 */
public class Clocle_help_fg1 extends android.support.v4.app.Fragment {
    private EmptyRecleView recleView;
    private Clocle_help_adapter adapter;
    private Retrofit retrofit;
    private Clocle_Help_ContentRs repository;
    EmptyLayout emptyView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.clocle_help_fg1_layout, container, false);
        emptyView = (EmptyLayout) LayoutInflater.from(getActivity()).inflate(R.layout.empty_view, container, false);
        recleView = (EmptyRecleView) view.findViewById(R.id.clocle_help_rv);
        ViewGroup viewGroup = (ViewGroup) recleView.getParent();
        viewGroup.addView(emptyView);
        //emptyView.setVisibility(View.GONE);
        recleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new Clocle_help_adapter(getActivity());
        retrofit = App.getRetrofit();
        repository = retrofit.create(Clocle_Help_ContentRs.class);
        //加载请求帮助信息
        initGetHelp();
        return view;
    }

    /**
     * 加载请求帮助信息
     */
    private void initGetHelp() {
        //设置正在加载中
        emptyView.setType(2);
        Call<ClocleResult<Clocle_help>> call = repository.getContent(1);
        call.enqueue(new Callback<ClocleResult<Clocle_help>>() {
            @Override
            public void onResponse(Call<ClocleResult<Clocle_help>> call, Response<ClocleResult<Clocle_help>> response) {
                ClocleResult<Clocle_help> result = response.body();
                if (result.getCode() == 100) {
                    //获取数据
                    ShowToast.showToast(getActivity(), "未获得数据");
                    List<Clocle_help> helplist = result.getT();
                    // adapter=new Clocle_help_adapter(getActivity());
                    recleView.setEmptyView(emptyView);
                    recleView.setAdapter(adapter);
                    adapter.setDatas(helplist);
                }
            }

            @Override
            public void onFailure(Call<ClocleResult<Clocle_help>> call, Throwable t) {
                ShowToast.showToast(getActivity(), "服务器连接错误");
                recleView.setAdapter(adapter);
                //出错

                emptyView.setType(3);
                //adapter.notifyDataSetChanged();
            }
        });
    }

    /**
     * 加载提供帮助用户信息
     */
    private void initProvider() {
    }
}






