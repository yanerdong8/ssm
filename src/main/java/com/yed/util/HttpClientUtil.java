package com.yed.util;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/*
 * 利用HttpClient进行post请求的工具类 
 */
public class HttpClientUtil {
    public static HttpResponse doPost(String url, Map<String, String> map, String charset) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        HttpResponse response = null;
        try {
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            //设置参数   
            List<NameValuePair> list = new ArrayList<NameValuePair>();
            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> elem = (Entry<String, String>) iterator.next();
                list.add(new BasicNameValuePair(elem.getKey(), elem.getValue()));
            }
            if (list.size() > 0) {
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list, charset);
                httpPost.setEntity(entity);
            }
            response = httpClient.execute(httpPost);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return response;
    }

    public static HttpResponse doPost(String url, String json, String charset) throws ClientProtocolException, IOException {
        HttpPost post = new HttpPost(url);
        HttpClient client = HttpClients.createDefault();
        String entityArgv = new String(json.getBytes("utf-8"), "ISO-8859-1");
        StringEntity entity = new StringEntity(entityArgv);
        entity.setContentEncoding("UTF-8");
        entity.setContentType("application/json");//设置为 json数据
        post.setEntity(entity);
        HttpResponse response = client.execute(post);

        return response;

    }
}  