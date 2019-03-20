//Programmer: Emma Brady.
//Student ID: B00100648.
//Date Written: 17th April 2018.
//Function: For a user to input new account, close account, lodgement, withdraw & overdraft requests.
//Note: Credit Union GUI.

import java.io.*;
import javax.swing.*;

public class Record
{
	private int OpenAccount;
	private String FirstName;
	private String LastName;
	private double Balance;
	private double Withdrawal;
	private double Lodgement;
	private double Overdraft;

	public void read( RandomAccessFile file ) throws IOException //Read File
	{
		OpenAccount = file.readInt();

		char first[] = new char[15];

		for ( int i=0; i < first.length; i++ )
				first[ i ] = file.readChar();

		FirstName = new String (first);

		char last[] = new char[15];

		for (int i =0; i<last.length; i++)
				last[i] = file.readChar();

		LastName = new String (last);

		Balance = file.readDouble();
		Lodgement = file.readDouble();
		Overdraft = file.readDouble();

	}

	//Write File
	public void write( RandomAccessFile file) throws IOException //Write File
	{
		StringBuffer buf;

		file.writeInt( OpenAccount );

		if (FirstName != null)
				buf = new StringBuffer(FirstName);

		else
		buf = new StringBuffer(15);

		buf.setLength(15);

		file.writeChars( buf.toString() );

		if ( LastName != null )
		buf = new StringBuffer(LastName);
		else
		buf = new StringBuffer(15);

		buf.setLength(15);

		file.writeChars( buf.toString() );

		file.writeDouble(Balance);
		file.writeDouble(Lodgement);
		file.writeDouble(Overdraft);

	}
	public void setOpenAccount( int a ) { OpenAccount = a; }

	public int getOpenAccount() { return OpenAccount;}

	public void setFirstName( String f) {FirstName = f;}

	public String getFirstName() { return FirstName; }

	public void setLastName ( String l) { LastName = l; }

	public String getLastName() { return LastName; }

	public void setBalance( double b) {Balance = b;}

	public double getBalance() {return Balance;}

	public void setLodgement( double lo) {Lodgement = lo;}

	public double getLodgement() {return Lodgement;}

	public void setWithdrawal( double b) {Balance = b;}

	public double getWithdrawal() {return Balance;}

	public void setOverdraft( double o) {Overdraft = o;}

	public double getOverdraft() {return Overdraft;}

	public static int size() { return 80;} //determines size (bytes) of each file. Added extra for Ovedraft.

}//end main class


