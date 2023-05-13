package com.tyner.eight.csudh.bank.core;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.*;
import java.util.stream.Stream;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.io.*;


public class Bank {
	
	
	
	private static Map<Integer,Account> accounts=new TreeMap<Integer,Account>();
	
	protected static Map<String, Currency> money = new HashMap<String,Currency>();
	
	
	
	public static Account openCheckingAccount(String firstName, String lastName, String ssn, double overdraftLimit, String code) throws NoSuchCodeException {
		Customer c=new Customer(firstName,lastName, ssn);
		Currency cur = null;
		
		try {
			cur = lookUpCode(code);
		}
		catch(NoSuchCodeException e) {
			cur=new Currency("USD","USD",1);
			System.out.println(e.getMessage());

		}
		
		
		Account a=new CheckingAccount(c,overdraftLimit, cur);
		accounts.put(a.getAccountNumber(), a);
		return a;
		
	}
	
	public static boolean configTrue()
	{
		
		boolean flag = false;
		
		try
		{
			File config = new File ("C:\\Different-Currencies\\config.txt\\");
			
			Scanner scan = new Scanner(config);
			
			String data = null;
			
			String d = null;
			

			
			while(scan.hasNextLine())
			{
				 data = scan.nextLine();
				
				 d = data.split("=")[1];
				
				Stream s = null;
				
				if((data.contains("support")))
				{

					if(d.equalsIgnoreCase("true"))
					{
						flag = true;
					}
					else if(d.equalsIgnoreCase("false"))
					{
						flag = false;
					}

				}

		}
		}
		
		
		catch(Exception v)
		{
			v.printStackTrace();
		}
		
		return flag;
		
	}
	
	
	
	public static boolean configHasBeenRead()
	{
		return configTrue();
	}
	
	
	
	public static String readingFile()
	{
		
		String line = null;
		try 
		{
			
			File config = new File ("C:\\Different-Currencies\\config.txt\\");
			
			Scanner scan = new Scanner(config);
			
			String data = null;
			
			String d = null;
			
			File file = null;
			
			Scanner reader = null;
			
			
			while(scan.hasNextLine())
			{
				data = scan.nextLine();
				
				d = data.split("=")[1];
				
				if(data.contains("currency"))
				{
					if(d.contains("csv"))
					{
						
						file = new File(d);
						
						line = file.getPath();
						
					}
				}
			}	
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return line;	
	}
	
	
	
	public static String readingHttp()
	{
		String line = null;
		try 
		{
			

			File config = new File ("C:\\Different-Currencies\\config.txt\\");
			
			Scanner scan = new Scanner(config);
			
			String data = null;
			
			String d = null;
			
			
			while(scan.hasNextLine())
			{
				
				data = scan.nextLine();
				
				d = data.split("=")[1];
				
				if(data.contains("url"))
				{
					if(d.contains("csv"))
					{
						line = d;
					}
				}	
			}	
	}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return line;
	}
	
	public static void readConfig()
	{
		
		String linee = " ";
		try 
		{
			
			Currency currency = null;
			
			File config = new File ("C:\\Different-Currencies\\config.txt\\");
			
			Scanner scan = new Scanner(config);
			
			String data = null;
			
			String d = null;
			
			Template tem = null;
			
			if(configTrue() == true)
			{
				while(scan.hasNextLine())
			{
				data = scan.nextLine();
				
				 d = data.split("=")[1];
				 
				 if((data.contains("source")))
				 {
					 
					
					 System.out.println(data);
					 
					 if(d.equalsIgnoreCase("file"))
					 {
						tem = Template.newTemplate(readingFile(),"file");
					 }
					 else if(d.equalsIgnoreCase("webservice"))
					 {
						 tem = Template.newTemplate(readingHttp(),"webservice");
					 }
			}
			
			}
			
			
			
			for(String line : tem.readCurrencies())
			{
			
				
				String [] values = line.split(",");
			
				currency = new Currency(values[0].toString(),values[1].toString(),Double.parseDouble(values[2]));
				
				money.put(currency.getCode(), currency);
				
			}
			
			
		}
			else if(configTrue()==false)
			{
				currency = new Currency("USD","United States Dollar",1.0);
				money.put(currency.getCode(), currency);
			}
		
		}
		 catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static double Exchange(String buying, String selling, double Amount) throws NoSuchCodeException
	
	{
		
		double rate = 0;
		double rate1 = 0;
		String o = buying;
		String h = selling;
		
		Currency has = money.get(o);		
		Currency hass = money.get(h);
		
		String AH = "USD";
		
		
		
		if(!(money.containsKey(buying)) || !(money.containsKey(selling))) 
		{
			
			throw new NoSuchCodeException ("One or both of the codes dosen't use United States Dollars\n");
		}
		
		
		if(money.get(o)==null||money.get(h)==null) {
			throw new NoSuchCodeException("One or both of the codes dosen't use any exchange rate\n");
		}
	
		
		
		double result = 0;
		
		if(money.containsKey(buying) && money.containsKey(selling))
		{
			if(buying.equalsIgnoreCase("USD"))
			{
				result = Amount *  hass.getExchangeRate(); // 4/9: FIND OUT HOW TO GET EXCHANGE RATES
				rate = hass.getExchangeRate();
			}
			else if(selling.equalsIgnoreCase("USD"))
			{
				result = Amount/has.getExchangeRate();
				rate = has.getExchangeRate();
			}
			
		}
		
		System.out.printf("The exchange rate is " + rate + " and you will get USD %.2f", result);
		System.out.println("");
		
		return result;
	}
		
	
	
	
	public static Account openSavingAccount(String firstName, String lastName, String ssn, String code) throws NoSuchCodeException {
		Customer c=new Customer(firstName,lastName, ssn);
		Currency cur = null;
		
		try {
			cur = lookUpCode(code);
		}
		catch(NoSuchCodeException e) {
			cur=new Currency("USD","USD",1);
			System.out.println(e.getMessage());

		}
		Account a=new SavingAccount(c, cur);//WHAT DO I DO HERE?
		accounts.put(a.getAccountNumber(), a);
		return a;
		
	}

	
	public static Account lookup(int accountNumber) throws NoSuchAccountException{
		if(!accounts.containsKey(accountNumber)) {
			throw new NoSuchAccountException("\nAccount number: "+accountNumber+" nout found!\n\n");
		}
		
		return accounts.get(accountNumber);
	}
	
	
	public static Currency lookUpCode(String code) throws NoSuchCodeException
	{
		
		if(!money.containsKey(code))
		{
			throw new NoSuchCodeException("\nCode: " + code + " not found!\n\n");
		}
		
		return money.get(code);
		
	}
	
	public static void printExAcc(int accNum) throws NoSuchAccountException
	{
		
		System.out.println( lookup(accNum).AccInfo());
		
	}
	
	
	
	
	public static void makeDeposit(int accountNumber, double amount) throws AccountClosedException, NoSuchAccountException{
		
		lookup(accountNumber).deposit(amount);
		
	}
	
	
	public static void makeWithdrawal(int accountNumber, double amount) throws InsufficientBalanceException, NoSuchAccountException {
		lookup(accountNumber).withdraw(amount);
	}
	
	
	
	public static void closeAccount(int accountNumber) throws NoSuchAccountException {
		lookup(accountNumber).close();
	}

	
	public static double getBalance(int accountNumber) throws NoSuchAccountException {
		return lookup(accountNumber).getBalanceUSD();
	}
	
	public static double getBalanceEx(int accountNumber) throws NoSuchAccountException
	{
		return lookup(accountNumber).getBalance();
	}

	public static void showAccInfo(OutputStream out) throws IOException
	{
		out.write((byte)10);
		
		
		
	}
	
	public static void listAccounts(OutputStream out) throws IOException{
		
		out.write((byte)10);
		Collection<Account> col=accounts.values();
		
		for (Account a:col) {
			out.write(a.toString().getBytes());
			out.write((byte)10);
		}
		
		out.write((byte)10);
		out.flush();
	}
	

	
	public static void printAccountTransactions(int accountNumber, OutputStream out) throws IOException,NoSuchAccountException{
		
		lookup(accountNumber).printTransactions(out);
	}
				
	
	
	
	
}
