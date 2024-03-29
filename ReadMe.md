一、项目总览
===================================
这个小demo项目包含两个部分，分别位于两个文件夹内
###1、istock文件夹
股票行情模拟发生器,使用java编写，文件列表如下：
>lib
>
>>commons-cli-1.2.jar
>
>>log4j-1.2.8.jar
>
>src
>
>>globalVal.java
>
>>issuance.java
>
>>istock.java
>
>>logSingleton.java
>
>>quote.java
>
>>randomUpdate.java
>
>>register.java
>
>>server.java
>
>>stock.java
>
>log4j.properties
>
>manifest.mf
>
>stockList.csv
>
>istock.sh
>
>build.sh

###2、istock_push文件夹
消息推送器，基于connect框架的nodeJS中间件
>istock_push.js
>
>original.htm
>
>package.json
>
>server.js

二、环境要求
===================================
+ RedHat Linux version 2.6
+ GNU Make 3.81 以上 (包含)
+ gcc version 4.1.2 以上 (包含)
+ JDK 1.5 version 以上,推荐安装jdk1.6版本 (包含)
+ node v0.8.9
+ python 2.7.x (只限于2.7系列版本)


三、安装步骤(在root用户下执行)
===================================
###1、编译环境安装
		执行以下命令查看系统版本和编译工具版本
		#cat /proc/version
		#gcc -v
		一般系统安装时已经将make和gcc安装，若是没有安装或者版本不正确，请从操作系统安装光碟中找到相应的rpm文件进行安装

###2、jdk安装
	# chmod 755 ./jdk-6u29-linux-i586-rpm.bin 
	#./jdk-6u29-linux-i586-rpm.bin

	一般安装在/usr/java/jdk1.6.0_29这个目录下
	
	#chmod u+wx /etc/profile
	然后在 /etc/profile  最后面加入
	export JAVA_HOME=/usr/java/jdk1.6.0_29
	
	立即生效
	#source /etc/profile
	查看安装结果
	#$JAVA_HOME/bin/java -version
	java version "1.6.0_29"
	Java(TM) SE Runtime Environment (build 1.6.0_29-b11)
	Java HotSpot(TM) Server VM (build 20.4-b02, mixed mode)


###3、python安装，用于node的编译(原python版本非2.7系列的情况)
	#tar xvfj Python-2.7.3.tar.bz2
	#cd Python-2.7.3
	#./configure --prefix=/usr/local/python2.7
	#make 
	#make install
	
	更新新版本的链接
	#rm -rf  /usr/bin/python
	#ln  -s /usr/local/python2.7/bin/python2.7 /usr/bin/python

###4、node安装
	#tar  zxvf node-v0.8.9.tar.gz
	#cd node-v0.8.9
	#./configure --prefix=/usr/local/nodejs8.9
	#make 
	#make install

	修改当前用户主目录下的.bash_profile文件,将node的bin目录添加到PATH环境变量中去
	加入以下内容:
	export PATH=$PATH:/usr/local/nodejs8.9/bin
	执行让其立即生效
	#. .bash_profile
	
	将node的bin目录加入PATH变量中,让其在系统启动的时候就生效
	修改 /etc/rc.local
	export PATH=$PATH:/usr/local/nodejs8.9/bin

###5、编译istock程序,生成一个名为istock.jar的jar包
	切换到istock目录,所有文件添加读写执行权限,编译前确认已经配置正确JAVA_HOME环境变量
	#cd istock
	#chmod -R 755 ../istock
	#dos2unix build.sh
	#sh build.sh

###6、安装istock_push依赖的nodeJS模块
	切换到istock_push目录,以下操作需要连接到互联网
	#cd istock_push
	#npm install socket.io
	#npm install connect

四、配置和执行指引
===================================
###1、istock 配置和执行
	切换到istock目录
	首先执行以下命令确保istock.sh的格式为unix格式
	#dos2unix istock.sh

	不带任何参数运行
	#sh istock.sh 
	将默认使用当前目录的stockList.csv做为股票列表
	默认使用9008作为监听端口


	想要指定股票列表文件和监听端口请使用以下的参数格式运行istock
	sh istock.sh -f [股票列表文件的绝对路径] -p [监听端口]
	如:
	#sh istock.sh -f /tmp/stockList.csv -p 1234

###2、istock_push中间的配置和运行(请先启动istock，再启动istock_push中间件)
	istock_push除了使用系统内核的模块，还使用了connect 、socket.io这两个第三方模块，请将这两个模块安装于istock_push目录下

	istock_push的web服务运行脚本为server.js,他默认绑定本地地址 ,默认的监听地址为 8889，假如这台服务器的地址为172.16.11.110 
	请在浏览器中输入 http://172.16.11.110:8889, 则连接到了中间件服务器
	请根据实际情况修改server.js内的以下变量(假如istock和istock_push都是部署在同一台机器则无需修改，使用默认即可)：
	行情模拟器的ip地址  istockHost
	行情模拟器的监听端口 istockPort
	中间件数据接收ip地址   middlewareHost
	中间件数据接收监听端口   middlewarePort
	web服务监听端口   webPort
	
	* 注意1: 出现Error: listen EADDRINUSE错误时，请检查端口是否被占用.被占用的话请使用另外的端口
	* 注意2: 假如istock和istock_push部署在不同的服务器上,请务必修改istockHost和middlewareHost这两个变量



	istock_push中间件默认使用本地地址和端口9001和istock股票模拟器进行注册、消息传递，若需要做相应的变更，
	请在server.js中修改middlewareHost和middlewarePort这两个对应的变量

	按照实际环境进行修改之后，在istock_push目录下执行以下命令运行此中间件服务
	#node server.js
	




