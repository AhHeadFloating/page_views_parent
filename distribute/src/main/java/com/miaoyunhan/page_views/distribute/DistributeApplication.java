package com.miaoyunhan.page_views.distribute;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfiguration
public class DistributeApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributeApplication.class, args);
    }

}
