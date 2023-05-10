package com.tyner.csudh.bank.core;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.*;



import com.tyner.csudh.bank.core.*;
import com.tyner.csudh.bank.core.Currency;

import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;




public class Bank {
	
	//MAKE A METHOD THAT ASKS FOR USER INPUT FOR CODE, USE IGNORE CASE
	
	private static Map<Integer,Account> accounts=new TreeMap<Integer,Account>();
	
	protected static Map<String, Currency> money = new HashMap<String,Currency>();
	
	
	//USE .LOOKUPCODE(CODE) INSTEAD OF .GET(CODE)
	
	//MAKE METHOD TO SEE IF FILE EXISTS
	public static Account openCheckingAccount(String firstName, String lastName, String ssn, double overdraftLimit, String code) throws NoSuchCodeException {
		Customer c=new Customer(firstName,lastName, ssn);
		Currency cur = null;
		
		/*try {
			cur = lookUpCode(code);
		}
		catch(NoSuchCodeException e) {
			cur=new Currency("USD","USD",1);
			System.out.println(e.getMessage());

		}*/
		
		cur = money.get(code);
		
		if(cur == null)
		{
			cur=new Currency("USD","USD",1);
		}
		
		
		Account a=new CheckingAccount(c,overdraftLimit, cur);
		accounts.put(a.getAccountNumber(), a);
		return a;
		
	}
	
	public static void readWebsite() 
	{
		
		//HOW DO I SEPERATE THE EACH OF THE LINES INTO MAP
		
		
		HttpRequest.Builder builder = HttpRequest.newBuilder();
		builder.uri(URI.create("http://www.usman.cloud/banking/exchange-rate.csv"));
		builder.method("GET", HttpRequest.BodyPublishers.noBody());
		
		HttpRequest req = builder.build();
		
		HttpClient client = HttpClient.newHttpClient();
		
		HttpResponse<String> response = null;
		
		Currency currency = null;
		
		String line = " ";
		
		
		
		try {
			
			
			response = client.send(req, HttpResponse.BodyHandlers.ofString());

			
			
			
			String[] r = response.body().split("\n");
			
		

			
			String a = response.body();
		
		//	System.out.println(a);
					
			//System.out.println(r);
			
			
			for(String s:r)
			{
				
				
				String [] values = s.split((","));
				

				currency = new Currency (values[0].toString(), values[1].toString(), Double.parseDouble(values[2]));
				
				money.put(currency.getCode(), currency);
				
				//System.out.println(Double.parseDouble(values[2]));
				
			}
			
		//	System.out.println(money.keySet());
			
			
		
			
			
		} catch (IOException e) 
		{
		
			e.printStackTrace();
		} catch (InterruptedException e) 
		{
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
		
		//System.out.println(has+" "+hass);
		
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
		

	
	public static boolean websiteHasBeenRead()
	{
		return money.size() > 1; //PLACEHOLDER!!!!!
		
	}
	
	
	
	public static Account openSavingAccount(String firstName, String lastName, String ssn, String code) throws NoSuchCodeException {
		Customer c=new Customer(firstName,lastName, ssn);
		Currency cur = null;
		
		/*try {
			cur = lookUpCode(code);
		}
		catch(NoSuchCodeException e) {
			cur=new Currency("USD","USD",1);
			System.out.println(e.getMessage());

		}*/
		
		cur = money.get(code);
		
		if(cur == null)
		{
			cur=new Currency("USD","USD",1);
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
		
		if(!money.containsKey(code.toUpperCase()))
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
	
	/*public static void makeDepositExchange(int accountNumber, double amount, String code)throws AccountClosedException, NoSuchAccountException,NoSuchCodeException
	{
		
		lookup(accountNumber).depositExchange(amount);
	}*/
	
	public static void makeWithdrawal(int accountNumber, double amount) throws InsufficientBalanceException, NoSuchAccountException {
		lookup(accountNumber).withdraw(amount);
	}
	
	
	/*public static void makeWithdrawalExchange(int accountNumber, double amount) throws InsufficientBalanceException, NoSuchAccountException {
		lookup(accountNumber).withdrawExchange(amount);
	}*/

	
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
