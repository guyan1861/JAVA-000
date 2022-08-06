package org.example;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.HttpHeaders;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: GuYan
 * @Time: 2022/8/6 12:41
 * @Description: 使用 HttpClient 请求框架发出请求和处理结果
 **/
public class HttpClientDemo {
    public static void main(String[] args) throws IOException {

        getUrl("http://localhost:8808/test");
        System.out.println("-------Post 请求来了---------");
        postUrl("http://localhost:8808/test");
    }

    /**
     * 使用 get请求的形式
     *
     * @param url
     */
    private static void getUrl(String url) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            httpGet.addHeader("Connection", "keep-alive");
            httpGet.addHeader(HttpHeaders.USER_AGENT, "Googlebot");
            response = client.execute(httpGet);
            System.out.println(response.getCode());
            System.out.println(response.getReasonPhrase());
            HttpEntity entity = response.getEntity();
            if(entity != null) {
                System.out.println(EntityUtils.toString(entity));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            close(client, response);
        }
    }

    private static void close(CloseableHttpClient client, CloseableHttpResponse response) throws IOException {
        if(response != null) {
            response.close();
        }
        if(client != null) {
            client.close();
        }
    }

    /**
     * 使用 post 请求的方式
     *
     * @param url
     */
    private static void postUrl(String url) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpResponse response = null;
        try {
            List<NameValuePair> urlParameters = new ArrayList<>();
            urlParameters.add(new BasicNameValuePair("name", "hello world"));
            httpPost.setEntity(new UrlEncodedFormEntity(urlParameters));
            response = client.execute(httpPost);
            System.out.println(response.getCode());
            System.out.println(response.getReasonPhrase());
            HttpEntity entity = response.getEntity();
            if(entity != null) {
                System.out.println(EntityUtils.toString(entity));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            close(client, response);
        }
    }
}
