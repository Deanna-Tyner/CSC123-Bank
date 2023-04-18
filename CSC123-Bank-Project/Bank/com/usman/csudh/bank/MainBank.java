package com.usman.csudh.bank;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.usman.csudh.bank.core.Account;
import com.usman.csudh.bank.core.AccountClosedException;
import com.usman.csudh.bank.core.Bank;
import com.usman.csudh.bank.core.InsufficientBalanceException;
import com.usman.csudh.bank.core.NoSuchAccountException;
import com.usman.csudh.bank.core.NoSuchCodeException;
import com.usman.csudh.util.UIManager;

import java.util.*;

public class MainBank {

	//HOW DO I MAKE ANOTHER THING TO ASK FOR A STRING?
	
	//IT READS THE CODE :D!! BUT IT STILL CONTINUES TO WORK WITH IT EVEN THOUGH IT SHOULDN'T
	//ASK HOW TO CHANGE THAT/ADD EXCEPTION THAT DOSEN'T CRASH THE PROGRAM
	
	//MAKE IT THE BANK START NORMALLY IF THE BANK DOESN'T EXIST
	
	
	// 4/9: ABLE TO MAKE IT WORK WITHOUT THE FILE, WON'T PRINT ACCOUNT INFO THOUGH
	
	//4/9: FIND OUT HOW TO PRINT OUT EXCHANGE AND PRINT ACCOUNT INFO
	
	
	// 4/11: EVERYTHING WORKS!! BUT I DECIDED TO USE PRINT STATEMENTS IN THE METHODS BECAUSE
	//		 I JUST CAN'T UNDERSTAND HIS PRINT METHODS
	
	
	// 4/11: EVERYTHING STILL WORKS :DDDDD!!!!!!!
	//		 THE ONLY ISSUE IS THAT I NEED TO FORMAT THE BALANCES
	
	//All messages are declared as constants to make it easier to change. Also, to ensure future proofing in case the application need to be made available
	//in more than one languages
	public static final String MSG_ACCOUNT_OPENED = "%nAccount opened, account number is: %s%n%n";
	public static final String MSG_ACCOUNT_CLOSED = "%nAccount number %s has been closed, balance is %s%n%n";
	public static final String MSG_ACCOUNT_NOT_FOUND = "%nAccount number %s not found! %n%n";
	public static final String MSG_FIRST_NAME = "Enter first name:  \n";
	public static final String MSG_LAST_NAME = "Enter last name:  \n";
	public static final String MSG_SSN = "Enter Social Security Number:  \n";
	public static final String MSG_ACCOUNT_NAME = "Enter account name:  \n";
	public static final String MSG_ACCOUNT_OD_LIMIT = "Enter overdraft limit:  \n";
	public static final String MSG_ACCOUNT_CREDIT_LIMIT = "Enter credit limit:  \n";
	public static final String MSG_AMOUNT = "Enter amount: \n";
	public static final String MSG_ACCOUNT_NUMBER = "Enter account number: \n";
	public static final String MSG_ACCOUNT_ACTION = "%n%s was %s, account balance is: %s%n%n";
	public static final String MSG_CODE = "Enter type of currency\n";
	public static final String MSG_SELLING = "Enter the currency you are selling (PUT CURRENCY IN UPPERCASE): \n";
	public static final String MSG_BUYING = "Enter the currency you are buying (PUT CURRENCY IN UPPERCASE): \n";
	public static final String MSG_AMOUNT2 = "Enter the amount you are selling: \n";
	//public static final String  = "The result it $20\n";
	public static final int MSG_RESULT = 8;
	
	

	//Declare main menu and prompt to accept user input
	public static final String[] menuOptions = { "Open Checking Account%n","Open Saving Account%n", "List Accounts%n","View Statement%n", "Show Account Information%n","Deposit Funds%n", "Withdraw Funds%n",
			"Currency Conversion%n","Close an Account%n", "Exit%n" };
	public static final String MSG_PROMPT = "%nEnter choice: ";

	
	//Declare streams to accept user input / provide output
	InputStream in;
	OutputStream out;
	
	
	//Constructor
	public MainBank(InputStream in, OutputStream out) {
		this.in=in;
		this.out=out;
	}
	
	
	//Main method. 
	public static void main(String[] args) {

		new MainBank(System.in,System.out).run();

	}
	
	
	//The core of the program responsible for providing user experience.
	public void run() {

		Account acc;
		int option = 0;
		
		

		UIManager ui = new UIManager(this.in,this.out,menuOptions,MSG_PROMPT);
		try {

			//Bank.readCurrencies():
			
			
			Bank.readFile(); // THIS DETERMINES IF WHETHER OR NOT THE FILE IS BEING READ
			
			if(!Bank.fileExists())
			{
				System.out.println("File not found!");
				
				do {
					option = ui.getMainOption(); //Render main menu

					switch (option) {
					case 1:
						
						
						//Compact statement to accept user input, open account, and print the result including the account number
						//HAS TO ASK FOR TYPE OF EXCHANGE CODE
						
						//IT TAKES THE CODE, FIND OUT HOW TO CONNECT IT TO ACCOUNTS
						
						
						try {
						ui.print(MSG_ACCOUNT_OPENED,
								new Object[] { Bank.openCheckingAccount(ui.readToken(MSG_FIRST_NAME),
										ui.readToken(MSG_LAST_NAME), ui.readToken(MSG_SSN),
										ui.readDouble(MSG_ACCOUNT_OD_LIMIT), null).getAccountNumber()});//FIND A WAY TO CONNECT EVERYTHING
						
						//Bank.lookUpCode(MSG_CODE);
						
					
						}
						catch(NoSuchCodeException e3)
						{
							this.handleException(ui, e3);
						}
						
						break;
					
					case 2:
						
						//Compact statement to accept user input, open account, and print the result including the account number
						//HAS TO ASK FOR TYPE OF EXCHANGE CODE
						
						try
						{
							
						
						ui.print(MSG_ACCOUNT_OPENED,
								new Object[] { Bank
										.openSavingAccount(ui.readToken(MSG_FIRST_NAME),
												ui.readToken(MSG_LAST_NAME), ui.readToken(MSG_SSN),null)//FIND A WAY TO CONNECT EVERYTHING
										.getAccountNumber() });
						}
						catch(NoSuchCodeException e3)
						{
							this.handleException(ui, e3);
						}
						break;

					case 3:
						
						//Get bank to print list of accounts to the output stream provided as method arguemnt
						Bank.listAccounts(this.out);
						break;
						
					case 4:
						
						//find account and get the account to print transactions to the  output stream provided in method arguments
						try {
							Bank.printAccountTransactions(ui.readInt(MSG_ACCOUNT_NUMBER),this.out);
						} catch (NoSuchAccountException e1) {
							this.handleException(ui, e1);

						}		
						
						break;

					case 5:
					{
						//WIL ASK FOR ACCOUNT INFO. E.I TYPE OF ACCOUNT, ACCOUNT NUMBER, TYPE OF CURRENCY, CURRENCY IN USD, ETC
						
						//WHY DOES THIS ACT LIKE IT CAN DO DEPOSITS? THATS 6'S JOB
						
						//Account.AccInfo();
						
						
						
						try {
							Bank.printExAcc(ui.readInt(MSG_ACCOUNT_NUMBER));
							
						} catch (NoSuchAccountException e) {

							e.printStackTrace();
						}
						break;
						
					}
					case 6:
						//find account, deposit money and print result
					{
						try {
							int accountNumber=ui.readInt(MSG_ACCOUNT_NUMBER);
							Bank.makeDeposit(accountNumber, ui.readDouble(MSG_AMOUNT));
							ui.print(MSG_ACCOUNT_ACTION, new Object[] {"Deposit","successful",Bank.getBalance(accountNumber)});
						}
						catch(NoSuchAccountException | AccountClosedException e) {
							this.handleException(ui, e);

						}
						break;
					}

					case 7://find account, withdraw money and print result
						try {
							int accountNumber=ui.readInt(MSG_ACCOUNT_NUMBER);
							Bank.makeWithdrawal(accountNumber, ui.readDouble(MSG_AMOUNT));
							ui.print(MSG_ACCOUNT_ACTION, new Object[] {"Withdrawal","successful",Bank.getBalance(accountNumber)});
							
						}
						catch(NoSuchAccountException | InsufficientBalanceException e) {
							this.handleException(ui, e);

						}
						break;
						
					case 8:
						
						/*double Amount2 = ui.readDouble(MSG_AMOUNT2);
						String buy = ui.readLine(MSG_BUYING);
						String sell = ui.readLine(MSG_SELLING);*/
						
						//ui.print(sell, menuOptions);
						try
						{
							double Amount2 = ui.readDouble(MSG_AMOUNT2);
							String dummy = ui.readLine("");
							String buy = ui.readLine(MSG_BUYING);
							String sell = ui.readLine(MSG_SELLING);
							
							
							
							//ui.printDouble(MSG_RESULT, new Object[] {Bank.Exchange(buy, sell, ui.readDouble(MSG_AMOUNT2))});
							
							Bank.Exchange(buy, sell, Amount2);
							
						
							
						}
						catch(NoSuchCodeException o)
						{
							this.handleException(ui, o);
						}
						break;
						
						
					case 9:
						//find account and close it
						
						
						try {
							int accountNumber=ui.readInt(MSG_ACCOUNT_NUMBER);
							Bank.closeAccount(accountNumber);
							ui.print(MSG_ACCOUNT_CLOSED,
									new Object[] { accountNumber, Bank.getBalance(accountNumber) });
							
						} catch (NoSuchAccountException e) {
							this.handleException(ui, e);

						}
						break;
					case 10:
						
						System.out.println("Thank you for using this bank, goodbye");
						System.exit(0);
						
					}

				} while (option != menuOptions.length);
				
			}
			else if(Bank.fileExists() == true)
			{
				do {
				option = ui.getMainOption(); //Render main menu

				switch (option) {
				case 1:
					
					
					//Compact statement to accept user input, open account, and print the result including the account number
					//HAS TO ASK FOR TYPE OF EXCHANGE CODE
					
					//IT TAKES THE CODE, FIND OUT HOW TO CONNECT IT TO ACCOUNTS
					
					
					try {
						
						
						
					ui.print(MSG_ACCOUNT_OPENED,
							new Object[] { Bank.openCheckingAccount(ui.readToken(MSG_FIRST_NAME),
									ui.readToken(MSG_LAST_NAME), ui.readToken(MSG_SSN),
									ui.readDouble(MSG_ACCOUNT_OD_LIMIT), ui.readToken(MSG_CODE)).getAccountNumber()});//FIND A WAY TO CONNECT EVERYTHING
					
				//	Bank.lookUpCode(MSG_CODE);
					
					String dum = ui.readLine("");
					
					
				
					}
					catch(NoSuchCodeException e3)
					{
						this.handleException(ui, e3);
					}
					
					break;
				
				case 2:
					
					//Compact statement to accept user input, open account, and print the result including the account number
					//HAS TO ASK FOR TYPE OF EXCHANGE CODE
					
					try
					{
						
					
					ui.print(MSG_ACCOUNT_OPENED,
							new Object[] { Bank
									.openSavingAccount(ui.readToken(MSG_FIRST_NAME),
											ui.readToken(MSG_LAST_NAME), ui.readToken(MSG_SSN),ui.readToken(MSG_CODE))//FIND A WAY TO CONNECT EVERYTHING
									.getAccountNumber() });
					}
					catch(NoSuchCodeException e3)
					{
						this.handleException(ui, e3);
					}
					break;

				case 3:
					
					//Get bank to print list of accounts to the output stream provided as method arguemnt
					Bank.listAccounts(this.out);
					break;
					
				case 4:
					
					//find account and get the account to print transactions to the  output stream provided in method arguments
					try {
						Bank.printAccountTransactions(ui.readInt(MSG_ACCOUNT_NUMBER),this.out);
					} catch (NoSuchAccountException e1) {
						this.handleException(ui, e1);

					}		
					
					break;

				case 5:
				{
					//WIL ASK FOR ACCOUNT INFO. E.I TYPE OF ACCOUNT, ACCOUNT NUMBER, TYPE OF CURRENCY, CURRENCY IN USD, ETC
					
					//WHY DOES THIS ACT LIKE IT CAN DO DEPOSITS? THATS 6'S JOB
					
					try {
						Bank.printExAcc(ui.readInt(MSG_ACCOUNT_NUMBER));
					} catch (NoSuchAccountException e) {

						e.printStackTrace();
					}
					break;
					
				}
				case 6:
					//find account, deposit money and print result
				{
					try {
						int accountNumber=ui.readInt(MSG_ACCOUNT_NUMBER);
						Bank.makeDeposit(accountNumber, ui.readDouble(MSG_AMOUNT));
						ui.print(MSG_ACCOUNT_ACTION, new Object[] {"Deposit","successful",Bank.getBalanceEx(accountNumber)});
					}
					catch(NoSuchAccountException | AccountClosedException e) {
						this.handleException(ui, e);

					}
					break;
				}

				case 7://find account, withdraw money and print result
					try {
						int accountNumber=ui.readInt(MSG_ACCOUNT_NUMBER);
						Bank.makeWithdrawal(accountNumber, ui.readDouble(MSG_AMOUNT));
						ui.print(MSG_ACCOUNT_ACTION, new Object[] {"Withdrawal","successful",Bank.getBalanceEx(accountNumber)});
						
					}
					catch(NoSuchAccountException | InsufficientBalanceException e) {
						this.handleException(ui, e);

					}
					break;
					
				case 8:
					
					try
					{
						String dum;
						double Amount2 = ui.readDouble(MSG_AMOUNT2);
						String dummy = ui.readLine("");
						String buy = ui.readLine(MSG_BUYING);
						String sell = ui.readLine(MSG_SELLING);
						
						//ui.print("Hello\n", new Object[] {Bank.Exchange(MSG_BUYING, MSG_SELLING, Amount2)});
						
						Bank.Exchange(buy, sell, Amount2);
						
						
					}
					catch(NoSuchCodeException o)
					{
						this.handleException(ui, o);
					}
					break;
					
					
				case 9:
					//find account and close it
					
					
					try {
						int accountNumber=ui.readInt(MSG_ACCOUNT_NUMBER);
						Bank.closeAccount(accountNumber);
						ui.print(MSG_ACCOUNT_CLOSED,
								new Object[] { accountNumber, Bank.getBalanceEx(accountNumber) });
						
					} catch (NoSuchAccountException e) {
						this.handleException(ui, e);

					}
					break;
				case 10:
					
					System.out.println("Thank you for using this bank, goodbye");
					System.exit(0);
					
				}

			} while (option != menuOptions.length);
			}
			
			
			

		} catch (IOException e) {
			e.printStackTrace();

		}
	}

	
	private  void handleException(UIManager ui, Exception e) throws IOException{
		ui.print(e.getMessage(), new Object[] { });
	}
	


}

