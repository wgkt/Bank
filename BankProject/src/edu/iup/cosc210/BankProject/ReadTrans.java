package edu.iup.cosc210.BankProject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.iup.cosc210.BankProject.bo.Transaction;

/**
 * Class to read a file of transactions
 * 
 * @author Meghan Cowan
 *
 */

public class ReadTrans {

	private BufferedReader in;
	private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

	/**
	 * Uses a BufferedReader to read new fileName
	 * 
	 * @param fileName
	 * @throws FileNotFoundException
	 */
	public ReadTrans(String fileName) throws FileNotFoundException {
		in = new BufferedReader(new FileReader(fileName));
	}

	/**
	 * Method to read the transactions. Separates fields at each comma, and
	 * creates a new transaction.
	 * 
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public Transaction readTrans() throws ParseException, IOException {
		String line = in.readLine();
		if (line == null) {
			return null;
		}

		String[] fields = line.split(",");

		int accountNumber = Integer.parseInt(fields[0]);
		Date date = sdf.parse(fields[1]);
		String type = fields[2];
		double amount = Double.parseDouble(fields[3]);

		Transaction transaction = new Transaction(accountNumber, date, type,
				amount);

		return transaction;
	}
/**
 * Method to close the reader.
 * @throws IOException
 */
	public void close() throws IOException {
		in.close();
	}

}
