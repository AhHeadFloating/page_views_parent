package com.miaoyunhan.page_views.distribute;

import com.alibaba.dubbo.config.annotation.Reference;
import com.miaoyunhan.api.entity.BlogUser;
import com.miaoyunhan.api.service.BlogUserService;
import com.miaoyunhan.page_views.distribute.mq.MqUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistributeApplicationTests {

    @Autowired
    private MqUtil myBean;

    @Reference(check = false)
    private BlogUserService blogUserService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void Test1() throws Exception {
        /*Long startTime = System.currentTimeMillis();
        CsdnAticleCrawl shujian_tianya = new CsdnAticleCrawl("blogdevteam",true);
        shujian_tianya.start(1);
        System.out.println("==========================================");
        System.out.println((System.currentTimeMillis() - startTime)/1000);
        System.out.println("==========================================");*/
        List<BlogUser> select = blogUserService.select(new BlogUser());
        System.out.println(select);
    }

}
