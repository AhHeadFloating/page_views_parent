package com.miaoyunhan.page_views.distribute.constant;

import com.miaoyunhan.page_views.distribute.job.GetProxyIpWuYou;

import java.util.HashMap;
import java.util.concurrent.ScheduledFuture;

public class JobConstant {
    public static final HashMap<String,ScheduledFuture> scheduledFutureMap = new HashMap();
    public static final HashMap<String,Runnable> jobMap = new HashMap();

    static {
        jobMap.put("wuYou",new GetProxyIpWuYou());
    }
}
