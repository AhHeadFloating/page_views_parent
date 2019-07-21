package com.miaoyunhan.page_views.persistence.service_impl;

import com.miaoyunhan.api.entity.Article;
import com.miaoyunhan.api.service.ArticleService;
import com.miaoyunhan.page_views.persistence.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@com.alibaba.dubbo.config.annotation.Service(timeout = 10000)
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public int insertSlective(Article article) {
        int i = articleMapper.insertSelective(article);
        return i;
    }

    @Override
    public List<Article> select(Article article) {
        List<Article> select = articleMapper.select(article);
        return select;
    }
}
