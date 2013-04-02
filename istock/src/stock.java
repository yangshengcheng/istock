package com.gf.istock;

/** 
 * @project istock 
 * @author yangshengcheng  
 * @verson 0.0.1 
 * @date   20130330 
 * @description  Stock Quotes Simulator 
 */ 

public class stock {  
    public quote Qt;
    public String id;
    
    public stock(String id)
    {
    	this.id = id;
    	this.Qt = new quote();
    }
    
    public void updateQt()
    {
    	this.Qt.getNewQuote();
    }
    
   
}  