package com.gf.istock;



import java.util.Random;
import java.math.BigDecimal;

/** 
 * @project istock 
 * @author yangshengcheng  
 * @verson 0.0.1 
 * @date   20130330 
 * @description  Stock Quotes Simulator 
 */ 


public class quote
{
	public double buy_1;
//	public double buy_2;
//	public double buy_3;
//	public double buy_4;
//	public double buy_5;
	
	public double sell_1;
//	public double sell_2;
//	public double sell_3;
//	public double sell_4;
//	public double sell_5;
	
	public long buy_1_amount; 
//	public long buy_2_amount;
//	public long buy_3_amount;
//	public long buy_4_amount;
//	public long buy_5_amount;
	public long sell_1_amount;
//	public long sell_2_amount;
//	public long sell_3_amount;
//	public long sell_4_amount;
//	public long sell_5_amount;
	
	public quote()
	{
		this.getNewQuote();
	}
	
	/**
	 * get new Quote
	 * **/
	public void getNewQuote()
	{
		Random rd = new Random();
		int i = Math.abs(rd.nextInt())%1000 + 1;
		double d =  Math.abs(rd.nextDouble());
		double basePrice =  Math.round((i+d)*100)/100.0;		
		
//		this.buy_5 = basePrice + 0.1;
//		this.buy_4 = basePrice + 0.2;
//		this.buy_3 = basePrice + 0.3;
//		this.buy_2 = basePrice + 0.4;
		this.buy_1 = (new BigDecimal(basePrice + 0.5)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		
		this.sell_1 = (new BigDecimal(basePrice + 0.6)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
//		this.sell_2 = basePrice + 0.7;
//		this.sell_3 = basePrice + 0.8;
//		this.sell_4 = basePrice + 0.9;
//		this.sell_5 = basePrice + 1;
		
		this.buy_1_amount = Math.abs(rd.nextInt()); 
//		this.buy_2_amount = Math.abs(rd.nextInt());
//		this.buy_3_amount = Math.abs(rd.nextInt());
//		this.buy_4_amount = Math.abs(rd.nextInt());
//		this.buy_5_amount = Math.abs(rd.nextInt());
		this.sell_1_amount = Math.abs(rd.nextInt());
//		this.sell_2_amount = Math.abs(rd.nextInt());
//		this.sell_3_amount = Math.abs(rd.nextInt());
//		this.sell_4_amount = Math.abs(rd.nextInt());
//		this.sell_5_amount = Math.abs(rd.nextInt());		
	}
	
}