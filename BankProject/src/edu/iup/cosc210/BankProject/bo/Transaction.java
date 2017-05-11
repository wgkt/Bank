package edu.iup.cosc210.BankProject.bo;

import java.util.Date;

/**
 * Class that holds information about the transaction, including account number,
 * date, amount of the transaction, new balance, and transaction type (deposit or withdrawal).
 * 
 * @author Meghan Cowan
 *
 */
public class Transaction {
	public int accountNumber;
	private Date date = new Date();
	private double amount;
	private double runningBalance;
	private String transType;
/**
 * Constructor for a transaction, includes account number, date, transaction type and amount.
 * @param accountNumber
 * @param date
 * @param transType
 * @param amount
 */
	public Transaction(int accountNumber, Date date, String transType,
			double amount) {
		super();
		this.accountNumber = accountNumber;
		this.date = date;
		this.transType = transType;
		this.amount = amount;
	}
/**
 * Method to get the "running" balance - balance after individual transaction.
 * @return runningBalance
 */
	public double getRunningBalance() {
		return runningBalance;
	}
/**
 * Method to get the transaction type
 * @return transType
 */
	public String getTransType() {
		return transType;
	}
/**
 * Method to get the account number
 * @return accountNumber
 */
	public int getAccountNumber() {
		return accountNumber;
	}
/**
 * Method to get the date of the transaction
 * @return date
 */
	public Date getDate() {
		return date;
	}
/**
 * Method to get the amount of the transaction
 * @return amount
 */
	public double getAmount() {
		return amount;
	}
/**
 * Method to set the running balance
 * @param runningBalance
 */
	public void setRunningBalance(double runningBalance) {
		this.runningBalance = runningBalance;
	}

}
