package com.example.loadingbartest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ManyThreadDownload {

    public void downLoad() {
        //设置url和下载后的文件名
        String fileName = "meitu.exe";
        String path = "https://xiuxiu.dl.meitu.com/xiuxiu64_setup.exe";
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            //获得需要下载的文件的长度
            int fileLength = conn.getContentLength();
            System.out.println("需要下载的文件的长度" + fileLength);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
