package com.gf.istock;


import java.io.BufferedReader;  
import java.io.IOException;  
import java.io.InputStreamReader;  
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.*;
import java.util.List;
import java.util.ArrayList;

import org.apache.log4j.Logger;
/** 
 * @project istock 
 * @author yangshengcheng  
 * @verson 0.0.1 
 * @date   20130330 
 * @description  Stock Quotes Simulator 
 */ 

public class register implements Runnable
{
	private  Socket sock = null;  
	private BufferedReader read = null;  
	private PrintStream print = null;
	private static String INVALID_RG_CODE = "invalid_rg_code";
	private static String RG_SUCESS = "re_success";
	//private static String ISTOCK_READY = "istock_ready";
	
	public register(Socket sock)
	{
		this.sock = sock;
		try
		{  
			this.read = new BufferedReader(new InputStreamReader(sock.getInputStream()));  
			this.print = new PrintStream(sock.getOutputStream());  
		}
		catch (IOException e)
		{  
			e.printStackTrace();  
		} 
	}
	
	
    public void run()
    {
    	Logger logger = logSingleton.get_logger();
    	globalVal global = globalVal.getInstance();
    	
    	logger.info(this.sock);
    	HashMap<String,String> hs= new HashMap<String,String>();
    	
    	boolean registerStatus = false;
    	int maxRetry = 3;
    	int start = 0;
    	boolean flag = false;
    	
    	try 
    	{  
    		String message = null;  
            while (!registerStatus)
            {  
            	message = read.readLine();
            	Pattern p1 = Pattern.compile("(\\w+)#(\\S+)#(\\d+)");
            	Matcher m1 = p1.matcher(message);
        		boolean b1 = m1.matches();
        		
        		if(b1)
        		{
        			//System.out.println(m1.group(1));
        			hs.put("id", m1.group(1));
        			hs.put("hostname", m1.group(2));
        			hs.put("port", m1.group(3));
        			
        			List<HashMap<String,String>> templs = global.getUserList();
        			for(HashMap<String,String> hash: templs)
        			{
        				if(hash.get("id").equals(hs.get("id")) && hash.get("hostname").equals(hs.get("hostname")) && hash.get("port").equals(hs.get("port")))
        				{
        					logger.info("already register!");
        					flag = true;
        				}
        			}
        			
        			if(!flag)
        			{
        				global.adduser(hs);
        			}
        			
        			registerStatus = true;
        			
        			print.println(RG_SUCESS);        			
        			
        		}
        		else
        		{
        			if(start > maxRetry)
        			{
        				registerStatus = true;
        			}
        			start++;
        			print.println(INVALID_RG_CODE);
        			Thread.sleep(500);
        		}
        		
            }  
        } 
    	catch (Exception e) 
    	{  
    		e.printStackTrace();
        } 
    	finally 
    	{   
            try 
            {  
                if(!this.sock.isClosed())
                {  
                	this.sock.close();  
                }  
            } 
            catch (Exception e1)
            {  
                e1.printStackTrace();  
            }  
        }  
    }
    
    
}
    	
    	

    