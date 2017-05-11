package edu.iup.cosc210.BankProject.bo;

import java.util.Date;

/**
 *
 * Account super class that holds common ground for Savings and Checking
 * accounts.
 * 
 * @author Meghan Cowan
 *
 */
public abstract class Account {
	private int accountNumber;
	private double balance;
	Transaction[] transactions = new Transaction[50];
	private int noTransactions;

	/**
	 * Creates a constructor for the Account
	 * 
	 * @param accountNumber
	 *            - number on the account
	 * @param balance
	 *            - final balance of the account
	 */
	public Account(int accountNumber, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
	}

	/**
	 * Method to get the account number
	 * 
	 * @return accountNumber
	 */
	public int getAccountNumber() {
		return accountNumber;
	}

	/**
	 * Method to get the balance
	 * 
	 * @return - balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Method to deposit into the account
	 * 
	 * @param amount
	 */
	public void deposit(double amount) {
		balance += amount;
	}

	/**
	 * Method to withdraw money from an account
	 * 
	 * @param amount
	 */
	public void withdraw(double amount) {
		balance -= amount;
	}

	/**
	 * Method to add a new transaction
	 * 
	 * @param transaction
	 */
	public void addTransaction(Transaction transaction) {
		transactions[noTransactions++] = transaction;
	}

	/**
	 * Method to get a specific transaction
	 * 
	 * @param i
	 * @return
	 */
	public Transaction getTransaction(int i) {
		return transactions[i];
	}

	/**
	 * Method to get the number of transactions
	 * 
	 * @return noTransactions
	 */
	public int getNoTransactions() {
		return noTransactions;
	}

	/**
	 * Abstract method that will be used by Savings and Checking to determine
	 * the end of month statement.
	 */
	public abstract void endOfMonth();

	/**
	 * Abstract method that will be used by Savings and Checking to identify
	 * their type
	 * 
	 * @return
	 */
	public abstract String getType();

	/**
	 * Abstract method used by Savings and Checking to get the label for the
	 * type of transaction.
	 * 
	 * @param transType
	 * @return
	 */
	abstract public String getLabel(String transType);
}
