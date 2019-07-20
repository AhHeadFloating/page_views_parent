package com.miaoyunhan.page_views.browsing.config;

import com.alibaba.dubbo.config.annotation.Reference;
import com.miaoyunhan.api.service.ArticleService;
import com.miaoyunhan.api.service.BlogUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class SystemConfig {

    @Reference(check = false)
    private BlogUserService blogUserService;
    @Reference(check = false)
    private ArticleService articleService;

    @Bean
    public BlogUserService getBlogUserService(){
        return this.blogUserService;
    }

    @Bean
    public ArticleService getArticleService(){
        return this.articleService;
    }
}
