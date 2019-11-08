package cn.com.bmsoft.utils;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * httpClient工具类
 */
public class HttpClientUtil {
    /**
     *
     * @param jsonString 无参数时传null
     * @param uri 拼装好的url
     * @param type POST/GET
     * @return
     */
    public static HttpEntity doHttpParam(String jsonString, String uri, String type) {

        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost httpPost = null;
        HttpGet httpGet = null;
        if(type.equals("POST")){
            // 创建Post请求
            if(jsonString != null){
                httpPost = new HttpPost(uri);
                //传入参数
                StringEntity entity = new StringEntity(jsonString, "UTF-8");
                // post请求是将参数放在请求体里面传过去的;这里将entity放入post请求体中
                httpPost.setEntity(entity);
            }else {
                httpPost = new HttpPost(uri);
            }
            httpPost.setHeader("Content-Type", "application/json;charset=utf8");
        }
        else {
            httpGet = new HttpGet(uri);
        }

        // 响应模型
        CloseableHttpResponse response = null;
        HttpEntity responseEntity = null;
        try {
            // 由客户端执行(发送)Post请求
            if(type.equals("POST"))
                response = httpClient.execute(httpPost);
            else
                response = httpClient.execute(httpGet);
            // 从响应模型中获取响应实体
            responseEntity = response.getEntity();

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseEntity;
    }

}
