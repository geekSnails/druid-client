package com.qiyi.search.druidclient.util;

import com.qiyi.search.druidclient.DruidQueryException;
import com.qiyi.search.druidclient.entity.DruidQuery;
import com.qiyi.search.druidclient.entity.DruidResponse;
import com.qiyi.search.druidclient.load.DuidLoad;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

public class DruidClient {

    private String host;

    private CloseableHttpClient httpClient;

    public DruidClient(String host, CloseableHttpClient httpClient) {
        this.host = host;
        this.httpClient = httpClient;
    }

    public DruidResponse execute(DuidLoad duidLoad) {
        return post(host + duidLoad.getSendUrl(), duidLoad.getLoadJSON());
    }

    public DruidResponse execute(DruidQuery duidQuery) {
        return post(host + duidQuery.getSendUrl(), duidQuery.getQueryJSON());
    }

    // 发送post请求
    private DruidResponse post(String url, String context) {
        DruidResponse druidResponse = new DruidResponse();
        HttpPost postRequest = null;
        try {
            postRequest = new HttpPost(url);
            StringEntity message = new StringEntity(context, "UTF-8");
            postRequest.setEntity(message);
            postRequest.addHeader("Content-Type", "application/json");
            HttpResponse response = httpClient.execute(postRequest);
            StatusLine status = response.getStatusLine();
            if (status.getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String responseString = EntityUtils.toString(entity, "UTF-8");
                druidResponse.setAttatch(responseString);
            } else {
                throw new DruidQueryException(
                        "请求失败：" + status.getStatusCode() + "," + status.getReasonPhrase());
            }
        } catch (Exception e) {
            throw new DruidQueryException(e.getMessage(), e);
        } finally {
            if (postRequest != null) {
                postRequest.releaseConnection();
            }
        }
        return druidResponse;
    }
}
