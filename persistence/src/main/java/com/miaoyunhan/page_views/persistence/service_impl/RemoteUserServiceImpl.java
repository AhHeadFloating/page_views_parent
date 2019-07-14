package com.miaoyunhan.page_views.persistence.service_impl;

import com.miaoyunhan.api.entity.ProxyIp;
import com.miaoyunhan.api.service.RemoteUserService;
import com.miaoyunhan.page_views.persistence.mapper.ProxyIpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@com.alibaba.dubbo.config.annotation.Service(version = "1.0.0",timeout = 10000,interfaceClass = RemoteUserService.class)
public class RemoteUserServiceImpl implements RemoteUserService {

    @Autowired
    private ProxyIpMapper proxyIpMapper;

    @Override
    public String sayHello(String name){
        System.out.println(name);
        return name+System.currentTimeMillis();
    }
    @Override
    public List<ProxyIp> findAll(){
        List<ProxyIp> select = proxyIpMapper.select(new ProxyIp());
        return select;
    }
 
 
}