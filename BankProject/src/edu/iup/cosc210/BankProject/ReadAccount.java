package edu.iup.cosc210.BankProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import edu.iup.cosc210.BankProject.bo.Account;
import edu.iup.cosc210.BankProject.bo.Checking;
import edu.iup.cosc210.BankProject.bo.Savings;

/**
 * Class to read a file of Accounts
 * 
 * @author Meghan Cowan
 *
 */
public class ReadAccount {

	private BufferedReader in;

	/**
	 * Uses a BufferedReader to read a new fileName
	 * 
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public ReadAccount(String fileName) throws FileNotFoundException {
		in = new BufferedReader(new FileReader(fileName));
	}

	/**
	 * Method to read the accounts. Separates at the comma, and creates a new
	 * account.
	 * 
	 * @return
	 * @throws IOException
	 */
	public Account readAccount() throws IOException {
		String line = in.readLine();
		if (line == null) {
			return null;
		}

		String[] fields = line.split(",");

		int accountNumber = Integer.parseInt(fields[1]);
		double balance = Double.parseDouble(fields[2]);

		if (fields[0].equals("C")) {
			Checking account = new Checking(accountNumber, balance);
			return account;
		} else if (fields[0].equals("S")) {
			Savings account = new Savings(accountNumber, balance);

			return account;
		}
		return null;

	}
/**
 * Method to close the reader.
 * @throws IOException
 */
	public void close() throws IOException {
		in.close();
	}

}
