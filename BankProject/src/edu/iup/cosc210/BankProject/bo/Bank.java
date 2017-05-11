package edu.iup.cosc210.BankProject.bo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

import edu.iup.cosc210.BankProject.AccountWriter;
import edu.iup.cosc210.BankProject.ReadAccount;
import edu.iup.cosc210.BankProject.ReadTrans;

/**
 * Main class that prints the Account and Transaction information, including the
 * end of month information. Also writes a new file holding the account
 * information.
 * 
 * @author Meghan Cowan
 *
 */
public class Bank {
	private int noAccounts;
	private Account[] accounts = new Account[20];

	/**
	 * Method to add an account
	 * 
	 * @param account
	 */
	public void addAccount(Account account) {
		accounts[noAccounts++] = account;
	}

	/**
	 * Method to get the number of accounts
	 * 
	 * @return
	 */
	public int getNoAccounts() {
		return noAccounts;
	}

	/**
	 * Method to get a specific account
	 * 
	 * @param i
	 * @return accounts
	 */
	public Account getAccount(int i) {
		return accounts[i];
	}

	/**
	 * Method to find a specific account by account number
	 * 
	 * @param accountNumber
	 * @return account, null
	 */
	public Account findAccount(int accountNumber) {

		for (int i = 0; i < noAccounts; i++) {
			Account account = getAccount(i);
			if (accountNumber == account.getAccountNumber()) {
				return account;
			}
		}
		return null;
	}

	/**
	 * Main method that checks for exceptions, then prints the accounts and
	 * transactions.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length < 2) {
			System.out.println("Pass in [account file] and [transaction file]");
			System.exit(-100);
		}

		Bank bankReport = new Bank();

		try {
			bankReport.loadAccounts(args[0]);
			bankReport.loadTransactions(args[1]);
			bankReport.processEndOfMonth();
			bankReport.printBank();
			bankReport.printAccounts("NewAccounts.txt");
		} catch (FileNotFoundException e) {
			System.out.println("Unable to run: " + e.getMessage());
			System.exit(-1);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
/**
 * Method to print the accounts into a new text file.
 * @param fileName
 * @throws FileNotFoundException
 */
	private void printAccounts(String fileName) throws FileNotFoundException {
		AccountWriter out = new AccountWriter(fileName);
		for (int i = 0; i < getNoAccounts(); i++) {
			Account account = getAccount(i);
			out.writeAccount(account);
		}

		out.close();
	}
/**
 * Method to process the end of month methods in Savings and Checking.
 */
	private void processEndOfMonth() {
		for (int i = 0; i < getNoAccounts(); i++) {

			Account account = getAccount(i);

			account.endOfMonth();
		}
	}
/**
 * Method to load the accounts using a FileReader
 * @param fileName
 * @throws IOException
 */
	public void loadAccounts(String fileName) throws IOException {
		ReadAccount in = new ReadAccount(fileName);
		Account account;

		while ((account = in.readAccount()) != null) {
			addAccount(account);
		}
	}
/**
 * Method to load the transactions using a FileReader
 * @param fileName
 * @throws IOException
 * @throws ParseException
 */
	public void loadTransactions(String fileName) throws IOException,
			ParseException {
		ReadTrans in = new ReadTrans(fileName);
		Transaction transaction;

		while ((transaction = in.readTrans()) != null) {
			Account account = findAccount(transaction.getAccountNumber());
			if (transaction.getTransType().equals("D")) {
				account.deposit(transaction.getAmount());
			} else if (transaction.getTransType().equals("W")) {
				account.withdraw(transaction.getAmount());
			}
			transaction.setRunningBalance(account.getBalance());
			account.addTransaction(transaction);
		}
	}
/**
 * Method to print the bank account and transaction information.
 */
	public void printBank() {
		for (int i = 0; i < getNoAccounts(); i++) {

			Account account = getAccount(i);

			System.out.printf("\nAccount: %s\n", account.getAccountNumber());
			System.out.printf("Type: %s \n", account.getType());
			System.out.printf("Balance: $%7.2f\n", account.getBalance());
			System.out.println();

			if (account.getNoTransactions() > 0) {
				System.out
						.printf("Date       Transaction      Amount        Balance\n");
				for (int x = 0; x < account.getNoTransactions(); x++) {
					Transaction transaction = account.getTransaction(x);
					System.out.printf(
							"%td/%tm/%tY %-10s       $%8.2f     $%8.2f",
							transaction.getDate(), transaction.getDate(),
							transaction.getDate(),
							account.getLabel(transaction.getTransType()),
							transaction.getAmount(),
							transaction.getRunningBalance());
					System.out.println();
				}
			}

		}
	}
}
