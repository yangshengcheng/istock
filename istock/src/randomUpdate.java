package com.gf.istock;


import java.util.Random;
import java.util.ArrayList;
import java.util.List;

/** 
 * @project istock 
 * @author yangshengcheng  
 * @verson 0.0.1 
 * @date   20130330 
 * @description  Stock Quotes Simulator 
 */ 

public class randomUpdate
{
	public List<stock> rlist;
	public randomUpdate(List<stock> ls)
	{
		this.rlist = new ArrayList<stock>();
		rlist = ls;
	}
	
	public List<stock> getRandom()
	{		

		List<stock> ls = this.rlist;
		Random rd = new Random();
		int i = Math.abs(rd.nextInt())%ls.size() + 1;
			
		java.util.Collections.shuffle(ls);
	
		Object[] array = ls.toArray();
			
		List<stock> temp = new ArrayList<stock>();
	
		for(int a = 0;a < i ;a++)
		{
			temp.add((stock)array[a]);
		}
			
		return temp;
	}
}