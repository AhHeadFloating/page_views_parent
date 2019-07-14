package com.miaoyunhan.api.service;

import com.miaoyunhan.api.entity.ProxyIp;

import java.util.List;

public interface RemoteUserService {
 
 
    String sayHello(String name);


    List<ProxyIp> findAll();
}