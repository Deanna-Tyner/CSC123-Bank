package com.tyner.eight.csudh.bank.core;


import com.tyner.eight.csudh.bank.core.*;

public class SavingAccount extends Account{
	private static final long serialVersionUID = 1L;

	public SavingAccount(Customer customer, Currency cur) {
		super("Saving", customer,cur);
	}



	//Withdrawals only allowed against positive balances 
	public void withdraw(double amount) throws InsufficientBalanceException {
		if (getBalance() - amount < 0) {
			throw new InsufficientBalanceException("Not enough funds to cover withdrawal");

		}
		super.withdraw(amount);

	}

}
