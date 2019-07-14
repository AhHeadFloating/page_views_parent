package com.miaoyunhan.api.entity;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


@Data
@Table(name = "proxy_ip")
public class ProxyIp implements Serializable {

    @Id
    @GeneratedValue(generator = "JDBC")//返回自增长主键
    private Long id;
    private String ip;
    private Integer port;
}
