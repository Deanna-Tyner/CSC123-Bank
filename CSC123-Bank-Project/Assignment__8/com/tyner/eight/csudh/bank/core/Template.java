package com.tyner.eight.csudh.bank.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;



public abstract class Template {
	
	public static Template newTemplate(String type) throws Exception
	{
		
		if(type.equalsIgnoreCase("file"))
		{
			return new HookForFile();
		}
		else if(type.equalsIgnoreCase("webservice"))
		{
			return new HookForHttp();
		}
		else
		{
			throw new Exception("Type " + type + " unkown!");
		}
	}
	
	public ArrayList<String> readCurrencies() throws Exception
	{
		InputStream in = getInputStream();
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		
		ArrayList<String> list = new ArrayList<String>();
		
		String line = null;
		
		try
		{
			
			while((line = br.readLine()) != null)
			{
				if(!line.contains("HTTP")&&!line.contains(":")&&!line.strip().equals("")) {
				list.add(line);
				}
			}
		}
		catch(IOException o)
		{
			System.out.println(o.getMessage());
		}
		
		return list;
		
		
	}
	
	protected abstract InputStream getInputStream() throws Exception;

}
