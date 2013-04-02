package com.gf.istock;


import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.PrintStream;  
import java.net.ServerSocket;  
import java.net.Socket;

import org.apache.log4j.Logger;
/** 
 * @project istock 
 * @author yangshengcheng  
 * @verson 0.0.1 
 * @date   20130330 
 * @description  Stock Quotes Simulator 
 */ 

public class server implements Runnable 
{
	private int port = 9008;
	private int maxRegister = 1000;
	public static int count = 0;
	ServerSocket ss = null;
	public static volatile boolean ServerExit = false;
	
	
    public server(int port,int max) 
    {  
    	this.port = port;
    	this.maxRegister = max;
    }
    
    public void run()
    {   	
        try 
        {    
        	Logger logger = logSingleton.get_logger();
        	globalVal global = globalVal.getInstance();
        	
            this.ss = new ServerSocket(this.port);    
            logger.info("server ready on " + this.port );
            while(! ServerExit)
            {   
                Socket s = ss.accept();
                if(count < this.maxRegister)  
                {
                	count++;
                	//register a new connect
                	Thread t = new Thread(new register(s)); 
                	t.setName("register-" + count);
                	t.start();
                	global.addThread(t);
                }
                else
                {
                	logger.info("reach the max manage size " + this.maxRegister);
                }
            }  
        }
        catch (IOException e) 
        {  
            e.printStackTrace();  
        }

    }
}