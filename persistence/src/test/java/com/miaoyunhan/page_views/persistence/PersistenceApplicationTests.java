package com.miaoyunhan.page_views.persistence;

import com.miaoyunhan.api.entity.ProxyIp;
import com.miaoyunhan.page_views.persistence.mapper.ProxyIpMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersistenceApplicationTests {

    @Autowired
    private ProxyIpMapper proxyIpMapper;

    @Test
    public void contextLoads() {
    }

    @Test
    public void mybatisTest(){
        List<ProxyIp> select = proxyIpMapper.select(new ProxyIp());
        System.out.println(select);
    }
}
