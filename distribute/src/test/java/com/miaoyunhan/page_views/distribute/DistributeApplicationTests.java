package com.miaoyunhan.page_views.distribute;

import com.miaoyunhan.page_views.distribute.mq.MqUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DistributeApplicationTests {

    @Autowired
    private MqUtil myBean;

    @Test
    public void contextLoads() {
    }


}
