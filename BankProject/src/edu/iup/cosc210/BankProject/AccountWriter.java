package edu.iup.cosc210.BankProject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import edu.iup.cosc210.BankProject.bo.Account;
/**
 * Class that writes a new file holding the account information
 * @author Meghan Cowan
 *
 */
public class AccountWriter {
	private PrintWriter out;
/**
 * Creates a new PrintWriter
 * @param fileName
 * @throws FileNotFoundException
 */
	public AccountWriter(String fileName) throws FileNotFoundException {
		out = new PrintWriter(fileName);
	}
/**
 * Method to write the account information.
 * @param account
 */
	public void writeAccount(Account account) {
		out.printf("%d,%s,%f", account.getAccountNumber(), account.getType(),
				account.getBalance());
	}
/**
 * Method to close the writer.
 */
	public void close() {
		out.close();
	}
}
