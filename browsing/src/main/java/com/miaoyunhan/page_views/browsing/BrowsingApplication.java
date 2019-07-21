package com.miaoyunhan.page_views.browsing;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class BrowsingApplication {

    public static void main(String[] args) {
        SpringApplication.run(BrowsingApplication.class, args);
    }

}
