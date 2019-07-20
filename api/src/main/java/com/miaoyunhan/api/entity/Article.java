package com.miaoyunhan.api.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "article")
public class Article implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long articleId;
    private Long blogUserId;
    private String articleTitle;
    private String articleUrl;

}
