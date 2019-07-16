package com.miaoyunhan.api.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long articleId;
    private String articleTitle;
    private String articleUrl;
}
