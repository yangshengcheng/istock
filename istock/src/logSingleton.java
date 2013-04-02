package com.gf.istock;


//log4j
import org.apache.log4j.Logger;
import org.apache.log4j.*;

/** 
 * @project istock 
 * @author yangshengcheng  
 * @verson 0.0.1 
 * @date   20130330 
 * @description  Stock Quotes Simulator 
 */ 

public class logSingleton
{
	private static logSingleton instance = null;
	private static Logger logger = null;
	public static boolean init_mark = false;
	
	private logSingleton()
	{
		
	}
	
	public static synchronized Logger get_logger()
	{
		if(instance == null)
		{
			instance = new  logSingleton();
			if(logger == null)
			{
				logger = Logger.getLogger("");
			}
			instance.init();
			return logger;
		}
		else
		{
			if(logger == null)
			{
				logger = Logger.getLogger(logSingleton.class);
			}
			return logger;
		}
	}
	
	/* initialize the logger's variables */
	public synchronized void init()
	{
		if(init_mark == true)
		{
			return ;
		}
		else
		{
			// some initialization code 
			init_mark = true;
			return ;
		}
		
	}
	
}