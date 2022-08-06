package org.example;

import okhttp3.*;

import java.io.IOException;

/**
 * @Author: GuYan
 * @Time: 2022/8/6 16:35
 * @Description: 使用 OkHttp 请求框架从后台模拟前端请求
 **/
public class OkHttpDemo {
    public static void main(String[] args) {
        String result = runGet("http://localhost:8808/test");
        System.out.println(result);
        System.out.println("---------- Post 请求开始 -------------");
        System.out.println(runPost("http://localhost:8808/test"));
    }

    /**
     * 模拟 Get 请求
     *
     * @param url
     * @return
     */
    private static String runGet(String url) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 模拟 Post 请求
     *
     * @param url
     * @return
     */
    private static String runPost(String url) {
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder().add("parameters", "hello world").build();
        Request request = new Request.Builder().url(url).post(formBody).build();

        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
