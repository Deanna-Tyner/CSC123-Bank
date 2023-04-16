package com.usman.csudh.bank.core;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import com.usman.csudh.util.UniqueCounter;
import java.util.*;


public class Account implements Serializable { //I WANNA SAY MAKE A WHOLE
											   //NEW METHOD TO DO CONVERSIONS
											   //AND NOT HAVE TO RISK MESSING
											   //WITH THE BALANCE
	
	private static final long serialVersionUID = 1L;
	private String accountName;
	private Customer accountHolder;
	private ArrayList<Transaction> transactions;
	private String code;
	private Currency currency; //WHAT DO I USE CURRENCY FOR?
	private boolean open=true;
	private int accountNumber;

	protected Account(String name, Customer customer,Currency cur) {
		accountName=name;
		accountHolder=customer;
		transactions=new ArrayList<Transaction>();
		accountNumber=UniqueCounter.nextValue();
		currency=cur;
		
	}
	
	public String getCode()
	{
		
			return code;

	}
	
	public void setCode(String code)
	{
	
			this.code = code;

	}
	
	public Currency getCurrency()
	{
		return Bank.money.get(code);
	}
	
	

	public String getAccountName() {
		return accountName;
	}

	public Customer getAccountHolder() {
		return accountHolder;
	}

	public double getBalance() //CHANGE TO USE OTHER MONEY TYPES
	{
		
		double workBalance = 0;
		
		for(Transaction trxn : transactions)
		{
			if(trxn.getType() == Transaction.CREDIT)workBalance += trxn.getAmount();
			else workBalance -= trxn.getAmount();
		}
		
		return workBalance;
		
	}
	
	public double getBalanceUSD()
	{
		
		double workingBalance = getBalance();
		
	
		
			return workingBalance * currency.getExchangeRate();

		
	}
	
	
	
	
	public void deposit(double amount)  throws AccountClosedException{
		double balance=getBalance();
		if(!isOpen()&&balance>=0) {
			throw new AccountClosedException("\nAccount is closed with positive balance, deposit not allowed!\n\n");
		}
		transactions.add(new Transaction(Transaction.CREDIT,amount));
	}
	
	
	
	public void withdraw(double amount) throws InsufficientBalanceException {
			
		double balance=getBalance();
			
		if(!isOpen()&&balance<=0) {
			throw new InsufficientBalanceException("\nThe account is closed and balance is: "+balance+"\n\n");
		}
		
		transactions.add(new Transaction(Transaction.DEBIT,amount));
	}
	
	
	public void close() {
		open=false;
	}
	
	public boolean isOpen() {
		return open;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}

	
	public String AccInfo()
	{
		
		
		String info = "Account Number: " + accountNumber + "\nName: " + accountHolder.getFirstName() + " " + accountHolder.getLastName() + "\nSSN: " + accountHolder.getSSN() +"\nCurrency: " + currency.getCode() + "\nCurrency Balance: " + currency.getCode() + " " + getBalance() + "\nUSD Balance: "  + "USD " + getBalanceUSD() + "\n";
		return info;
	}
	
	
	public String toString() {
		
		NumberFormat formatter = new DecimalFormat("#0.0");
		
		formatter.format(getBalance());
		
		String aName=accountNumber+"("+accountName+")"+" : "+accountHolder.toString()+ " : "+ currency.getCode() + " : " +getBalance()+" : " + "USD : " + getBalanceUSD() + " : " +(open?"Account Open":"Account Closed");
		
			return aName;
		
		
	}
	 
	public void printTransactions(OutputStream out) throws IOException {
		
		out.write("\n\n".getBytes());
		out.write("------------------\n".getBytes());
	
		for(Transaction t: transactions) {
			out.write(t.toString().getBytes());
			out.write((byte)10);
		}
		out.write("------------------\n".getBytes());
		out.write(("Balance: "+getBalance()+"\n\n\n").getBytes());
		out.flush();
		
	}
}
