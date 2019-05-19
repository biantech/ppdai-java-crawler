package com.ppdai.crawler.service;

import com.biantech.ppdai.crawler.util.JsonUtil;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SimpleHttpClientDemo {
    Logger logger = LoggerFactory.getLogger(SimpleHttpClientDemo.class);
    public static void main(String[] args) throws Exception {
        SimpleHttpClientDemo clientDemo = new SimpleHttpClientDemo();
        clientDemo.doPost();
    }

    public void doPost()  throws ParseException, IOException {
        String postUrl = "http://localhost:9080/findComment";
        HashMap<String,String> param = new HashMap<>();
        param.put("blogId","1");
        param.put("pageSize","10");
        String encoding = "utf-8";
        String contentType="application/json";
        //String result=sendPost(postUrl,param,encoding,contentType);
        //String json=JsonUtil.toJsonString(param);
        String result=doPost(postUrl,param,encoding,contentType);
        logger.info(result);
    }

    /**
     *
     * @param url
     * @param param
     * @param encoding
     * @param contentType
     * @return
     * @throws IOException
     */
    public String doPost(String url, HashMap<String,String> param, String encoding,String contentType) throws IOException {
        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url);
        String json=JsonUtil.toJsonString(param);
        //JSONObject response = null;
         StringEntity s = new StringEntity(json);
         s.setContentEncoding(encoding);
         s.setContentType(contentType);//"application/json"//发送json数据需要设置contentType
         post.setEntity(s);
         String result=getPostResult(encoding,httpclient,post);
//         HttpResponse res = httpclient.execute(post);
//        String result=null;
//         if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
//             result = EntityUtils.toString(res.getEntity());// 返回json格式：
//             //response = JSONObject.fromObject(result);
//         }
        //String result = JsonUtil.toJsonString(res);
        return result;
    }

    /**
     * 模拟请求
     * @param url        资源地址
     * @param map    参数列表
     * @param encoding    编码
     * @return
     * @throws ParseException
     * @throws IOException
     */
    public String sendPost(String url, HashMap<String,String> map, String encoding,String contentType ) throws ParseException, IOException {
        String body = "";

        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        //创建post方式请求对象
        HttpPost httpPost = new HttpPost(url);

        //装填参数
        List<NameValuePair> nvps = new ArrayList<>();
        if(map!=null){
            for (Map.Entry<String, String> entry : map.entrySet()) {
                nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        //设置参数到请求对象中
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, encoding));
        logger.info("请求地址："+url);
        logger.info("请求参数："+nvps.toString());
        //设置header信息
        //指定报文头【Content-type】、【User-Agent】
        //httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
        httpPost.setHeader("Content-type",contentType);
        httpPost.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        body = getPostResult(encoding, client, httpPost);
        return body;
    }

    private String getPostResult(String encoding, CloseableHttpClient client, HttpPost httpPost) throws IOException {
        String body=null;
        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpPost);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, encoding);
        }
        EntityUtils.consume(entity);
        //释放链接
        response.close();
        return body;
    }
}
