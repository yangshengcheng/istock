#!/usr/bin/sh
#######################
# author: yangshengcheng
# name: istock build script
########################

echo 
echo "istock Build System!"
echo "----------------------------------------"

echo  "check env for the JAVA_HOME enviroment variable"
JAVA_HOME=`env|grep  -i "^JAVA_HOME"|awk -F "=" '{print $NF}'`
if [ $JAVA_HOME = "" ]; then
	echo "please set the proper JAVA_HOME enviroment variable"
	exit
fi

#make package dir
mkdir -p  com/gf/istock
chmod -R 777 com/gf/istock

packageDir="com/gf/istock"

# set istock classpath
PWD=`pwd`
export CLASSPATH=.:${PWD}/lib/commons-cli-1.2.jar:${PWD}/lib/log4j-1.2.8.jar

# compile the java files
"${JAVA_HOME}/bin/javac" -d $packageDir ${PWD}/src/*.java

#make a jar package
"${JAVA_HOME}/bin/jar" cvfm   istock.jar  manifest.mf -C com/gf/istock/ .


clean()
{
	if [ -d "${PWD}/com/gf/istock" ];then
		rm  -rf com/
	fi
}


if [ -f "${PWD}/istock.jar" ];then
	chmod  755 istock.jar
	echo "build itock success !"
	clean
else
	echo "build itock fail,please investigate!!"
	clean
fi





