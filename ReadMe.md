һ����Ŀ����
===================================
���Сdemo��Ŀ�����������֣��ֱ�λ�������ļ�����
###1��istock�ļ���
��Ʊ����ģ�ⷢ����,ʹ��java��д���ļ��б����£�
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

###2��istock_push�ļ���
��Ϣ������������connect��ܵ�nodeJS�м��
>istock_push.js
>
>original.htm
>
>package.json
>
>server.js

��������Ҫ��
===================================
+ RedHat Linux version 2.6
+ GNU Make 3.81 ���� (����)
+ gcc version 4.1.2 ���� (����)
+ JDK 1.5 version ���� (����)
+ node v0.8.9
+ python 2.7.x (ֻ����2.7ϵ�а汾)


������װ����(��root�û���ִ��)
===================================
###1�����뻷����װ
		һ��ϵͳ��װʱ�Ѿ���make��gcc��װ������û�а�װ����Ӱ�װ������ҵ���Ӧ��rpm�ļ����а�װ
###2��jdk��װ
	# chmod 755 jdk-6u24-linux-i586.bin 
	#./jdk-6u24-linux-i586.bin

	�� /etc/profile  ��������
	export JAVA_HOME=/usr/local/Java/jdk1.6.0_24
	
	������Ч
	#source /etc/profile
	�鿴��װ���
	#java -version
	java version "1.6.0_24"  
	Java(TM) SE Runtime Environment (build 1.6.0_24-b07)  
	Java HotSpot(TM) Server VM (build 19.1-b02, mixed mode)

###3��python��װ������node�ı���(ԭpython�汾��2.7ϵ�е����)
	#tar xvfj Python-2.7.3.tar.bz2
	#cd Python-2.7.3
	#./configure --prefix=/usr/local/python2.7
	#make 
	#make install
	
	�����°汾������
	#rm -rf  /usr/bin/python
	#ln  -s /usr/local/python2.7/bin/python2.7 /usr/bin/python

###4��node��װ
	#tar  zxvf node-v0.8.9.tar.gz
	#cd node-v0.8.9
	#./configure --prefix=/usr/local/nodejs8.9
	#make 
	#make install

	��node��binĿ¼����PATH������,������ϵͳ������ʱ�����Ч
	�޸� /etc/rc.local
	set PATH=$PATH:/usr/local/nodejs8.9/bin

###5������istock����,����һ����Ϊistock.jar��jar��
	�л���istockĿ¼,�����ļ���Ӷ�дִ��Ȩ��,����ǰȷ���Ѿ�������ȷJAVA_HOME��������
	#cd istock
	#chmod -R 755 ../istock
	#sh build.sh

###6����װistock_push������nodeJSģ��
	�л���istock_pushĿ¼
	#cd istock_push
	#npm install socket.io
	#npm install connect

�ġ����ú�ִ��ָ��
===================================
###1��istock ���ú�ִ��
	�л���istockĿ¼��ִ��,�����κβ���ִ��
	#sh istock.sh 
	��Ĭ��ʹ�õ�ǰĿ¼��stockList.csv��Ϊ��Ʊ�б�
	Ĭ��ʹ��9008��Ϊ�����˿�


	��Ҫָ����Ʊ�б��ļ��ͼ����˿���ʹ�����µĲ�����ʽ����istock
	sh istock.sh -f [��Ʊ�б��ļ��ľ���·��] -p [�����˿�]
	��:
	#sh istock.sh -f /tmp/stockList.csv -p 1234

###2��istock_push�м�����ú�����(��������istock��������istock_push�м��)
	istock_push����ʹ��ϵͳ�ں˵�ģ�飬��ʹ����connect ��socket.io������������ģ�飬�뽫������ģ�鰲װ��istock_pushĿ¼��

	istock_push��web�������нű�Ϊserver.js,��Ĭ�ϰ󶨱��ص�ַ ,Ĭ�ϵļ�����ַΪ 8888��������̨�������ĵ�ַΪ172.16.11.110 
	��������������� http://172.16.11.110:8888, �����ӵ����м��������

	istock_push�м��Ĭ��ʹ�ñ��ص�ַ�Ͷ˿�9001��istock��Ʊģ��������ע�ᡢ��Ϣ���ݣ�����Ҫ����Ӧ�ı��������server.js���޸�middlewareHost��middlewarePort��������Ӧ�ı���

	����ʵ�ʻ��������޸�֮����istock_pushִ�������������д��м������
	#node server.js
	




