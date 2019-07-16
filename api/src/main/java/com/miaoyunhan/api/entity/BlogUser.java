package com.miaoyunhan.api.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "blog_user_name")
public class BlogUser {
    @Id
    @GeneratedValue(generator = "JDBC")//返回自增长主键
    private Long blogUserId;
    private String blogUserName;
    private Integer blogType;
    private Date createTime;
}
