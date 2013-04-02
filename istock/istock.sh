#!/bin/sh
#########################
# author: yangshengcheng
# description: manage istock 
#########################

usage()
{
	echo "usage: sh istock.sh"
	echo "\n"
}


#check the JAVA_HOME first 
#echo  "check env for the JAVA_HOME enviroment variable"
JAVA_HOME=`env|grep  -i "^JAVA_HOME"|awk -F "=" '{print $NF}'`
if [ $JAVA_HOME = "" ]; then
	echo "please set the proper JAVA_HOME enviroment variable"
	exit
fi
PWD=`pwd`

#check istock.jar file 
if [ ! -f "${PWD}/istock.jar" ];then
	echo "can not file the istock.jar file!"
	exit 1
fi

#set CLASSPATH

export CLASSPATH=.:${PWD}/lib/commons-cli-1.2.jar:${PWD}/lib/log4j-1.2.8.jar:${PWD}/istock.jar

# start the istock service 
"${JAVA_HOME}/bin/java"  com/gf/istock/istock $@
