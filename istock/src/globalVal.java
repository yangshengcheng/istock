package com.gf.istock;

import java.util.*;


public class globalVal {
 private static globalVal instance;
 private static int  max_concurrent_thread = 500;
 private static int current_thread_num = 0;
 private static List<Thread> ThreadList = new ArrayList<Thread>();
 private static List<HashMap<String,String>> usersList = new ArrayList<HashMap<String,String>>();
 private static HashMap<String,Integer> userRetryCount = new HashMap<String,Integer>();
 
 private globalVal(){}

 
 private synchronized void init() 
 {
	 if(current_thread_num != 0)
	 {
		 current_thread_num = 0;
	 }
	 if(max_concurrent_thread <= 0)
	 {
		 max_concurrent_thread = 1000;
	 }
 }
 
 public static globalVal getInstance() 
 {
  if (instance==null) 
  {
	  instance = new globalVal();
	  instance.init();
	  return instance;
  }
  return instance;
 }
 
 public int get_current_thread_num()
 {
	 return current_thread_num;
 }
 
 public int get_max_concurrent_thread()
 {
	 return max_concurrent_thread;
 }
 
 public  synchronized void t_add()
 {
	 current_thread_num++;
 }
 
 public synchronized void t_reduce()
 {
	 current_thread_num--;
 }
 
 public synchronized void t_reset()
 {
	 current_thread_num = 0;
 }
 
 public synchronized void addThread(Thread t)
 {
	 ThreadList.add(t);
 }
 
 public synchronized void delThread(List<Thread> ls)
 {
	 ThreadList.removeAll(ls);
 }
 
 public synchronized List<Thread> getThreadList()
 {
	 return ThreadList;
 }
 
 public synchronized List<HashMap<String,String>> getUserList()
 {
	 return usersList;
 }
 
 public synchronized void adduser(HashMap<String,String> t)
 {
	 usersList.add(t);
 }
 
 public synchronized void deluser(List<HashMap<String,String>> ls)
 {
	 usersList.removeAll(ls);
 }
 
 public Integer getUserRetry(String s)
 {
	 for(String host:userRetryCount.keySet())
	 {
		 if(host.equalsIgnoreCase(s))
		 {
			 return userRetryCount.get(s);
		 }
	 }
	 
	 return 0;

 }
 
 
 public synchronized void userRetryAdd(String s)
 {
	 for(String host:userRetryCount.keySet())
	 {
		 if(host.equalsIgnoreCase(s))
		 {
			 userRetryCount.put(s, userRetryCount.get(s)+1);
			 return ;
		 }
	 }
	 //new user fail 
	 userRetryCount.put(s,1);
 }
 
 
 public synchronized void userRetryReset(String s)
 {
	 for(String host:userRetryCount.keySet())
	 {
		 if(host.equalsIgnoreCase(s))
		 {
			 userRetryCount.put(s,0);
		 }
	 }

 }
 
 
}