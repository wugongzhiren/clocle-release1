package com.httpThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Handler;

/**
 * 处理http请求
 * Created by Administrator on 2016/7/11.
 */
public class Reg_http extends Thread {
    private String urladdress;//地址
    private Handler handler;
    private String name;
    private String password;

    public Reg_http(String urladdress, String name, String password) {
        this.urladdress = urladdress+"?user_name="+name+"&user_password="+password;
        this.name = name;
        this.password = password;
    }

    public void regConnect() throws IOException {
        URL url = new URL(urladdress);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String response;
            StringBuffer sb=new StringBuffer();
            while ((response = reader.readLine()) != null) {
                sb.append(response);
            }

        } catch (Exception e) {
System.out.print("出错啦");
        }
    }

    @Override
    public void run() {
        try {
            regConnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        super.run();
    }
}
