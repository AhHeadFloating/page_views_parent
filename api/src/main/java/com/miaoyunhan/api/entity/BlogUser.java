package com.miaoyunhan.api.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "blog_user")
public class BlogUser implements Serializable {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Long blogUserId;
    private String blogUserName;
    private Integer blogType;
    private Date createTime;
}
