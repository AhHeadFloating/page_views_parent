package com.miaoyunhan.api.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据工具类
 */
public class ResponseUtil {

    public static Map<String,Object> ok(){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("code",200);
        return map;
    }

    public static Map<String,Object> ok(Object msg){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("code",200);
        map.put("msg",msg);
        return map;
    }

    public static Map<String,Object> err(){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("code",500);
        return map;
    }

    public static Map<String,Object> err(Object msg){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("code",500);
        map.put("msg",msg);
        return map;
    }
}
