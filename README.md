父项目

	爬取、分发	distribute
	浏览	browsing	
	持久化	persistence
	公用接口和实体	api



distribute

	请求代理ip，通过activeMq发送给browsing
	接受http传来的csdn用户名，通过activeMq发送给browsing
	发送csdn用户名，
	定时器
		每天定时爬取固定网站，分发给browsing

browsing

	mq监听器接收到csdn用户名后，根据账号爬取博客列表，保存数据库
	mq监听器接收到ip和端口号后，浏览数据库中状态为浏览的博客

persistence
	
	负责操作数据库




distribute使用mq发送博客名字给browsing，而不是browsing直接通过http请求的原因是，这样让集群的逻辑比较简单，一个distribute生产，多个browsing就可以了
