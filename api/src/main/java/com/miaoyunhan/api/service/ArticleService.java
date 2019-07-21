package com.miaoyunhan.api.service;

import com.miaoyunhan.api.entity.Article;

import java.util.List;

public interface ArticleService {
    int insertSlective(Article article);

    List<Article> select(Article article);
}
