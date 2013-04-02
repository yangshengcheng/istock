package com.gf.istock;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

//arguments manage
import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException; 

//log4j
import org.apache.log4j.*;

/** 
 * @project istock 
 * @author yangshengcheng  
 * @verson 0.0.1 
 * @date   20130330 
 * @description  Stock Quotes Simulator 
 */ 


public class istock
{
	private static Thread mainThread;
	private static Thread serverThread;
	public static volatile boolean exitFlap = false;
	
	public static void main(String[] args) throws Exception
	{
		Logger logger = logSingleton.get_logger();
		globalVal global = globalVal.getInstance();
		mainThread = Thread.currentThread();
		
		//manage interrupt action
		Runtime.getRuntime().addShutdownHook(new Thread()
		{
			public void run()
			{
				System.out.println("istock service shut down....");
				mainThread.interrupt();
				serverThread.interrupt();
			}
		});
		
		
		String stockDescFile = "";
		int port = 9008;
		List<stock> stockList = new ArrayList<stock>();;
		//List<String> users = new ArrayList<String>();
		
		Options opts = null;
		BasicParser parser = null;
		CommandLine cl = null;
		
		  try
		  {
			   opts = new Options();
			   opts.addOption("h", false, "Print help for this application");
			   opts.addOption("f", true, "stock description file");
			   opts.addOption("p", true, "listen port,will be 9008 if not appoinded");
			   			   
			   parser = new BasicParser();
			   cl = parser.parse(opts, args);
			   
			   if (cl.hasOption('h')) 
			   {
				    HelpFormatter hf = new HelpFormatter();
				    hf.printHelp("OptionsTip", opts);
				    System.exit(0);
			   } 
	
			   if(cl.hasOption('f'))
			   {
				   stockDescFile = cl.getOptionValue("f");
			   }
			   else
			   {
				   stockDescFile = "stockList.csv";
			   }
			   
			   
			   if(cl.hasOption('p'))
			   {
				   port = Integer.parseInt(cl.getOptionValue("p"));
			   }
			   
		  }
		  catch(Exception e)
		  {
			  e.printStackTrace();
		  }
		  
		  
		  
		  //parse the file ,and load all stock information
		  try
			{
				File Rfile = new File(stockDescFile);
				if(! Rfile.isFile())
				{
					logger.error(stockDescFile + " is not a valid file!");
					System.exit(1);
				}
				
				FileReader fr = new FileReader(Rfile);
				BufferedReader br = new BufferedReader(fr);
				String currentLine ;
				
				while((currentLine = br.readLine()) != null)
				{								
					if(currentLine.matches("^\\s*\\d+\\s*$"))
					{
						stockList.add(new stock(currentLine));
					}
					else
					{
						continue;
					}								
				}
				br.close();	
											
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			
			
			if(stockList.size() <= 0)
			{				
				logger.info("no stocks in stockList");
				System.exit(0);
			}			
			
			//listen for connect
			serverThread = new Thread(new server(port,1000));
			serverThread.setName("istockServer");
			serverThread.start();
				
			global.addThread(serverThread);				
			//wait for 0.5 second
			Thread.sleep(500);			
			
			// Loop ,update stock quote and distribute
			try
			{
				while(!exitFlap)
				{
					// more then one user
					List<HashMap<String,String>> userlist = global.getUserList();
					if(userlist.size() > 0)
					{
						List<stock> updateList = new randomUpdate(stockList).getRandom(); 
						
						for(HashMap<String,String> hs:userlist)
						{
							//logger.info(hs);
							Thread t = new Thread(new issuance(hs,updateList));
							t.start();
							
						}
					}
					else
					{
						
					}
					
					Thread.sleep(1000);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}	
	}
	
}