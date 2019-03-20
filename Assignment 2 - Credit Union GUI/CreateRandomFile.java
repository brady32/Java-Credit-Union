//Programmer: Emma Brady.
//Student ID: B00100648.
//Date Written: 17th April 2018.
//Function: For a user to input new account, close account, lodgement, withdraw & overdraft requests.
//Note: Credit Union GUI.

import java.io.*;

public class CreateRandomFile //Create Randon Access Files
{
	  private Record blank;
	  private RandomAccessFile file;

	  public CreateRandomFile()
	  {
		blank = new Record();

		  try
		   {
			file = new RandomAccessFile( "credit.dat", "rw" );

			for (int i=0; i<50; i++) // Will write 50 empty records
			blank.write( file );
		   }

		  catch(IOException e)

		  {
		   System.err.println("File not opened properly\n" + e.toString() );
		   System.exit( 1 );
  		  }
	  }

	public static void main( String [] args )
	{
	  CreateRandomFile accounts = new CreateRandomFile();
	}

} //end main class
