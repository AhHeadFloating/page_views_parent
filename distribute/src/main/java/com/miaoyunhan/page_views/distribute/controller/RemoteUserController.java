package com.miaoyunhan.page_views.distribute.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.miaoyunhan.api.entity.ProxyIp;
import com.miaoyunhan.api.service.RemoteUserService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RemoteUserController {
 
    //timeout 可以不指定，但是version一定要指定 不然会找不到服务 直连需要加url="dubbo://localhost:20880"
    @Reference(version = "1.0.0",check = false)
    private RemoteUserService remoteUserService;
 
 
    @RequestMapping(value="/dubbo/say/{name}")
    public String sayHello(@PathVariable("name") String name){
 
        String result=remoteUserService.sayHello(name);
        return result;
    }

    @RequestMapping(value = "/findAll")
    public List<ProxyIp> findAllIp(){
        List<ProxyIp> all = remoteUserService.findAll();
        return all;
    }
 
 
}