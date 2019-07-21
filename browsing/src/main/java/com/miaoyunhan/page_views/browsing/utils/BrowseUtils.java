package com.miaoyunhan.page_views.browsing.utils;

import com.arronlong.httpclientutil.HttpClientUtil;
import com.arronlong.httpclientutil.builder.HCB;
import com.arronlong.httpclientutil.common.HttpConfig;
import com.arronlong.httpclientutil.common.SSLs;
import com.arronlong.httpclientutil.exception.HttpProcessException;
import com.miaoyunhan.api.entity.Article;
import com.miaoyunhan.api.entity.ProxyIp;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ThreadUtils;
import org.apache.http.client.HttpClient;

import java.util.List;

@Slf4j
public class BrowseUtils {
    private List<Article> urls;

    public void setUrls(List<Article> urls) {
        this.urls = urls;
    }

    public void browse() throws Exception {
        ProxyIp ip = null;
        ip = ProxyIpUtils.getIp();
        HCB hcb = HCB.custom()
                .proxy(ip.getIp(), ip.getPort())
                .pool(1000, 100) //启用连接池，每个路由最大创建10个链接，总连接数限制为100个
                .sslpv(SSLs.SSLProtocolVersion.TLSv1_2)    //设置ssl版本号，默认SSLv3，也可以调用sslpv("TLSv1.2")
                .ssl()        //https，支持自定义ssl证书路径和密码，ssl(String keyStorePath, String keyStorepass)
                ;
        HttpClient client = hcb.build();
        int errCount = 0;
        for (Article article : urls) {
            //插件式配置请求参数（网址、请求参数、编码、client）
            HttpConfig config = HttpConfig.custom().timeout(2000)
                    .url(article.getArticleUrl())              //设置请求的url
                    .encoding("utf-8") //设置请求和返回编码，默认就是Charset.defaultCharset()
                    .client(client)    //如果只是简单使用，无需设置，会自动获取默认的一个client对象
                    ;
            try{
                log.info("==============开始访问，当前线程数："+ ThreadUtils.findThreadsByName("wuYouJob").size() +"=======================");
                HttpClientUtil.get(config);     //get请求
            }catch (HttpProcessException e){
                e.printStackTrace();
                errCount++;
                if(errCount >= 2){
                    break;
                }
            }

            log.info(article.getArticleUrl());
        }

    }
}
