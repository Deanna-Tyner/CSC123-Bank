package com.tyner.eight.csudh.bank.core;

import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.io.*;

public class HookForHttp extends Template{
	
	
	public HookForHttp(String source) {
		super(source);
	}
	
	public String config()
	{
		String line1 = null;
		
		String all1 = null;
		try 
		{
			

			File config1 = new File ("C:\\Different-Currencies\\config.txt\\");
			
			Scanner scan1 = new Scanner(config1);
			
			String data1 = null;
			
			String d1 = null;
			
			
			while(scan1.hasNextLine())
			{
				
				data1 = scan1.nextLine();
				
				d1 = data1.split("=")[1];
				
				if(data1.contains("url"))
				{
					if(d1.contains("csv"))
					{
						
						line1 = d1;
						
						String a = line1.split("http://www.usman.cloud/")[1];
						
						all1 = a;

					}
				}	
			}	
	}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return all1;
	}

	protected InputStream getInputStream(String source) throws Exception
	{
		
		Socket socket = new Socket(source, 80); 

		// Sending request

		String host = "Host: "+source+"\r\n";
		
		String get = "GET /"+config()+" HTTP/1.1\r\n";
		
		OutputStream out = socket.getOutputStream();
		out.write(get.getBytes());
		out.write(host.getBytes());
		out.write("\r\n".getBytes());
		
		return socket.getInputStream();
		
	}
	


}
