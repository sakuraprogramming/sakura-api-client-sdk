package com.sakura.sakuraapiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.sakura.sakuraapiclientsdk.model.User;
import com.sakura.sakuraapiclientsdk.utils.SignUtils;

import java.util.HashMap;
import java.util.Map;


public class SakuraClient {
    private static final String GATEWAY_HOST = "http://localhost:8123";

    private String accessKey;
    private String secretKey;

    public SakuraClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    public String getNameByGet(String name) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpRequest.get(GATEWAY_HOST + "/api/name/")
                .addHeaders(getHeaders(name))
                .body(name)
                .form(paramMap)
                .execute()
                .body();
        return result;
    }

    public String getNameByPost(String name) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        String result = HttpRequest.post(GATEWAY_HOST + "/api/name/")
                .body(name)
                .form(paramMap)
                .addHeaders(getHeaders(name))
                .execute()
                .body();
        return result;
    }

    private Map<String, String> getHeaders(String body) {
        Map<String, String> headers = new HashMap<>();
        headers.put("accessKey", accessKey);
//        headers.put("secretKey", secretKey);
        headers.put("nonce", RandomUtil.randomNumbers(4));
        headers.put("body", body);
        headers.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        headers.put("sign", SignUtils.genSign(body, secretKey));
        return headers;
    }

    public String getUserNameByPost(User user) {
        String json = JSONUtil.toJsonStr(user);
        HttpResponse httpResponse = HttpRequest.post(GATEWAY_HOST + "/api/name/user")
                .addHeaders(getHeaders(json))
                .body(json)
                .form(json)
                .execute();
        System.out.println(httpResponse.getStatus());
        String result = httpResponse.body();
        return result;
    }

    public String getNameByRandom() {
        String result = HttpRequest.get(GATEWAY_HOST + "/api/name/get")
                .addHeaders(getHeaders("sakura"))
                .execute()
                .body();
        return result;
    }

}
