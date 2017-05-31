/*
package com.httpThread;


import android.content.Context;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;


import android.util.Log;
import android.widget.Toast;

import com.bean.Clocle_help;
import com.bean.Message;
import com.bean.Messages;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.zhy.http.okhttp.OkHttpUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Response;

import tool.RecycleViewAdapter;


*/
/**
 * 应用启动时，将服务器的最新listview信息显示在是圈圈帮页面中
 * Created by Administrator on 2016/8/1.
 *//*

public class Clocle_help_AsyncTask extends AsyncTask<String, Void, List<Clocle_help>> {
    private Gson mgson = new Gson();
    private RecycleViewAdapter adapter;
    private Context mcontext;
    private RecyclerView mrecycleView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private List<Clocle_help> adapterList = new ArrayList();
    private List<Clocle_help> mpageList;
    List<Messages> messagesList = new ArrayList();//存放服务器返回的jsonlist的beanList,也就是加载到的list，第一次进入会是预加载的15条数据

    public Clocle_help_AsyncTask(List<Clocle_help> pageList, SwipeRefreshLayout refreshLayout, Context mcontext, RecyclerView mrecycleView) {
        this.swipeRefreshLayout = refreshLayout;
        this.mcontext = mcontext;
        this.mrecycleView = mrecycleView;
        this.mpageList = pageList;
    }

    @Override
    protected List<Clocle_help> doInBackground(String... params) {
        return getIndex_Json_data(params[0]);
    }

    private List<Clocle_help> getIndex_Json_data(String url) {

        Boolean loadflag = false;
        String help_json = null;//help_json是包含多个Messags的json字符串
        try {
            if (mpageList.size() == 0) {
                //说明是第一次预加载，那么通知服务器这次我要取15条数据
                loadflag = true;

            }
            //但是不管是不是第一次加载，每次加载最多只取15条数据，以节省用户流量
            help_json = readInputStream(url, loadflag);
            if (help_json == null) {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

//将json转化为list，最多返回15条
        messagesList = mgson.fromJson(help_json, new TypeToken<List<Clocle_help>>() {
        }.getType());
        //  Log.i("tag", jsondatas);

        if (loadflag) {
            //表明是第一次加载
            mpageList.addAll(messagesList);
            return mpageList;
        } else {
            if (messagesList.size() == 15) {
                //刷新的最新的信息大到有15条了
                mpageList.clear();
                mpageList.addAll(messagesList);
                return mpageList;
            } else {
                //只加载到几条数据
                for(int i=0;i<messagesList.size();i++){
                   //根据加载到的数据移除对应的
                    mpageList.remove(i);
                    mpageList.add(i,messagesList.get(i));
                }

                return mpageList;
            }

        }

    }

    private String readInputStream(String url, Boolean flag) throws IOException {
        //result=new Get_Inner_String();
        String load = "no";
        Response response = null;
        if (flag) {
            load = "firstLoad";
        }
        response = OkHttpUtils.get().addParams("loadFlag", load).url(url).build().execute();

        if (response.isSuccessful()) {


            return inputStreamToString(response.body().byteStream());
        } else {
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Messages> messages) {
        super.onPostExecute(messages);
     //   Log.d("tag1", String.valueOf(adapterList.size()));
        if (messages == null) {
            //这里可以读入本地的暂存文件，避免显示空白
            Toast.makeText(mcontext, "加载失败，请重试", Toast.LENGTH_SHORT).show();
            return;
        }
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }
        */
/*for (int i = messages.size() - 1; i >= 0; i--) {
            adapterList.add(messages.get(i));
        }*//*

        adapter = new RecycleViewAdapter(mcontext, mpageList);

        mrecycleView.setAdapter(adapter);
        // mrecycleView.setOnScrollChangeListener(new PauseOnScrollListener(ImageLoader.getInstance(),true,true));
        //Myadpter listview=new Myadpter(MainActivity.this,)
        Toast.makeText(mcontext, "完成", Toast.LENGTH_SHORT).show();
    }

    private String inputStreamToString(InputStream is) throws IOException {
        String s = "";
        String line = "";

        // Wrap a BufferedReader around the InputStream
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));

        // Read response until the end
        while ((line = rd.readLine()) != null) {
            s += line;
        }

        // Return full string
        is.close();
        return s;
    }


}
*/
