package com.example.loadingbartest;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class DownLoadHelper {
    /**
     * 普通单线程下载文件：
     * @param path 下载路径
     * @param context 上下文
     */
    public static void singThreadDownLoad(String path, Context context) {
        try {
            URL url = new URL(path);
            InputStream is = url.openStream();
            String end = path.substring(path.lastIndexOf("."));
            OutputStream os = context.openFileOutput("Cache_" + System.currentTimeMillis() + end, Context.MODE_PRIVATE);
            byte[] buffer = new byte[1024];
            int len = 0;
            //从输入流中读取数据，读取到缓存区
            while ((len = is.read(buffer)) > 0){
                os.write(buffer,0,len);
            }
            is.close();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
