package com.miaoyunhan.page_views.browsing.request;

import cn.edu.hfut.dmic.webcollector.plugin.net.OkHttpRequester;
import com.miaoyunhan.api.entity.ProxyIp;
import com.miaoyunhan.page_views.browsing.utils.ProxyIpUtils;
import lombok.Data;
import okhttp3.OkHttpClient;

import java.net.*;

// 自定义的请求插件
// 可以设置随机代理选择器
@Data
public class MyRequester extends OkHttpRequester {

    @Override
    public OkHttpClient.Builder createOkHttpClientBuilder() {
        ProxyIp proxyIp = null;
        try {
            proxyIp = ProxyIpUtils.getIp();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.createOkHttpClientBuilder().proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyIp.getIp(), proxyIp.getPort())));
    }
}