package edu.iup.cosc210.BankProject.bo;

import java.util.Date;

import edu.iup.cosc210.BankProject.bo.Account;

/**
 * Class that holds the unique characteristics of a Savings account
 * 
 * @author Meghan Cowan
 *
 */
public class Savings extends Account {
	public double interestRate;

	/**
	 * Creates a constructor containing the super class variables accountNumber
	 * and balance
	 * 
	 * @param accountNumber
	 * @param balance
	 */
	public Savings(int accountNumber, double balance) {
		super(accountNumber, balance);
	}

	/**
	 * Method to get the interest rate
	 * 
	 * @return interestRate
	 */
	public double getInterestRate() {
		return interestRate;
	}

	/**
	 * Method to withdraw from savings. If withdraw amount is greater than
	 * balance, only the balance will return.
	 */
	public void withdraw(double withdraw) {
		if (withdraw <= getBalance()) {
			super.withdraw(withdraw);
		} else {
			super.withdraw(getBalance());
		}
	}
/**
 * Method to generate the end of the month interest amount
 */
	public void endOfMonth() {
		double interestAmt = .05;
		interestRate = interestAmt * super.getBalance();

		if (interestRate > 0) {
			Transaction interestTransaction = new Transaction(
					getAccountNumber(), new Date(), "Interest", interestRate);
			super.withdraw(interestRate);
			interestTransaction.setRunningBalance(getBalance());
			addTransaction(interestTransaction);
		}

	}
/**
 * Method to get the account type
 */
	public String getType() {
		return "Savings";
	}
/**
 * Method to get the label for the transaction type
 */
	@Override
	public String getLabel(String transType) {
		if (transType.equals("W")) {
			return "Withdraw";
		} else if (transType.equals("D")) {
			return "Deposit";
		} else if (transType.equals("Interest")) {
			return "Interest";
		}
		return null;
	}

}
