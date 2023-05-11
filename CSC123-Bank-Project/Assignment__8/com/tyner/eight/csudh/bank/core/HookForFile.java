package com.tyner.eight.csudh.bank.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class HookForFile extends Template{
	
	protected HookForFile(String source) {
		super(source);

	}

	@Override
	protected InputStream getInputStream(String source) throws Exception
	{
		
		
		
			return new FileInputStream(new File(source));
	
	
	}

}
