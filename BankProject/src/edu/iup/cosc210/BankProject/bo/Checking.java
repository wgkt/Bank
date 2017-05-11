package edu.iup.cosc210.BankProject.bo;

import java.util.Date;

import edu.iup.cosc210.BankProject.bo.Account;

/**
 * Checking class that holds variables unique to the checking account, such as
 * the number of overdrafts and the number of checks.
 * 
 * @author Meghan Cowan
 *
 */
public class Checking extends Account {
	public int noOverdrafts;
	public int noChecks;

	/**
	 * Constructor for the checking account, holds the super class variable
	 * accountNumber and balance
	 * 
	 * @param accountNumber
	 * @param balance
	 */
	public Checking(int accountNumber, double balance) {
		super(accountNumber, balance);
		this.noOverdrafts = 0;
	}

	/**
	 * Method to withdraw from the checking account.
	 */
	public void withdraw(double withdraw) {
		super.withdraw(withdraw);
		if (withdraw > getBalance()) {
			noOverdrafts++;
		}


		noChecks++;
	}

	/**
	 * Method to get the number of overdrafts
	 * 
	 * @return noOverdrafts
	 */
	public int getNoOverdrafts() {
		return noOverdrafts;
	}

	/**
	 * Method to get the number of checks
	 * 
	 * @return noChecks
	 */
	public int getNoChecks() {
		return noChecks;
	}

	/**
	 * Method to find the end of month charges. Finds and take out the check fee
	 * amount and the overdraft amount.
	 */
	public void endOfMonth() {
		// overdraft charge is $25.00
		// check charge is $.10;
		double overdraftCharge = getNoOverdrafts() * 25.00;
		double checkCharge = getNoChecks() * .10;

		if (overdraftCharge > 0) {
			Transaction overdraftTransaction = new Transaction(
					getAccountNumber(), new Date(), "Overdraft",
					overdraftCharge);
			super.withdraw(overdraftCharge);
			overdraftTransaction.setRunningBalance(getBalance());
			addTransaction(overdraftTransaction);
		}

		if (checkCharge > 0) {
			Transaction checkTransaction = new Transaction(getAccountNumber(),
					new Date(), "Check Fee", checkCharge);
			super.withdraw(checkCharge);
			checkTransaction.setRunningBalance(getBalance());
			addTransaction(checkTransaction);
		}

	}
/**
 * Method to get the type of account
 */
	public String getType() {
		return "Checking";
	}
/**
 * Method to get the label for the type of transaction
 */
	@Override
	public String getLabel(String transType) {
		if (transType.equals("W")) {
			return "Check";
		} else if (transType.equals("D")) {
			return "Deposit";
		} else if (transType.equals("Overdraft")) {
			return "Overdraft";
		} else if (transType.equals("Check Fee")) {
			return "Check Fee";
		}

		return null;
	}

}
