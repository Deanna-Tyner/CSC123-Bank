package com.tyner.eight.csudh.bank.core;

import java.io.*;

import java.util.*;

public class Currency {
	
	
	protected  String code;
	
	protected  String moneyName;
	
	protected  double exchangeRate; //how do I parseInt the exchange rate?
	
	public Currency(String code, String moneyName, double exchangeRate)
	{
		this.code = code;
		
		this.moneyName = moneyName;
		
		this.exchangeRate = exchangeRate;
		
	}
	
	public String getCode()
	{
		
		
			return code;
		
		
	}
	
	public void setCode(String code)
	{
		
			this.code = code;
		
		
	}
	
	public String getMoneyName()
	{
		return moneyName;
	}
	
	public void setMoneyName(String moneyName)
	{
	
		
			this.moneyName = moneyName;
		
		
	}
	
	public double getExchangeRate()
	{
		
		
			return exchangeRate;
		
		
		
	}
	
	public void setExchangeRate(double exchangeRate)
	{
	
			this.exchangeRate = exchangeRate;
		
		
		
	}
	
	
	@Override
	public String toString() {
		return code + moneyName + exchangeRate;
	}
		
}


