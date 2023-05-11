package com.tyner.eight.csudh.bank.core;

import java.net.Socket;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.Charset;
import java.io.*;

public class HookForHttp extends Template{
	
	
	public HookForHttp(String source) {
		super(source);
	}

	protected InputStream getInputStream(String source) throws Exception
	{
		
		// GET HELP READING HTTP
		
		HttpRequest.Builder builder = HttpRequest.newBuilder();
		builder.uri(URI.create(source));
		builder.method("GET", HttpRequest.BodyPublishers.noBody());
		
		HttpRequest req = builder.build();
		
		HttpClient client = HttpClient.newHttpClient();
		
		HttpResponse<String> response = null;
		
		String line = " ";
		
		response = client.send(req, HttpResponse.BodyHandlers.ofString());
		
		String bod = response.body();
		
		
		
		
		Socket socket = new Socket("www.usman.cloud", 80);

		// Sending request
		OutputStream out = socket.getOutputStream();
		out.write("GET /banking/exchange-rate.csv HTTP/1.1\r\n".getBytes());
		out.write("Host: www.usman.cloud\r\n".getBytes());
		out.write("\r\n".getBytes());
		
		return socket.getInputStream();
		
		
		
		//return (InputStream)bod.lines();
	}
	


}
