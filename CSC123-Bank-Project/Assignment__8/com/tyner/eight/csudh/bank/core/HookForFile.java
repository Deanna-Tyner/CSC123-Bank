package com.tyner.eight.csudh.bank.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class HookForFile extends Template{
	
	@Override
	protected InputStream getInputStream() throws Exception
	{
		
		
		
			return new FileInputStream(new File("C:\\Different-Currencies\\exchange-rate.csv"));
	
	
	}

}
