package com.miaoyunhan.page_views.browsing.utils;

import com.miaoyunhan.api.entity.Article;

import java.util.List;

public class BrowseThreadUtil implements Runnable {

    private List<Article> articleList;

    public List<Article> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<Article> articleList) {
        this.articleList = articleList;
    }

    @Override
    public void run() {
        try {
            BrowseUtils browseUtils = new BrowseUtils();
            browseUtils.setUrls(this.articleList);
            browseUtils.browse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
