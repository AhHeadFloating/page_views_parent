package com.miaoyunhan.page_views.distribute.job;


public class GetProxyIpWuYou implements Runnable {

    public void getIpAndSendMq(){
        System.out.println(System.currentTimeMillis());
    }

    @Override
    public void run() {
        getIpAndSendMq();
    }
}
