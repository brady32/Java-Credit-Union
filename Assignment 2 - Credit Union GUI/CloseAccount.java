//Programmer: Emma Brady.
//Student ID: B00100648.
//Date Written: 17th April 2018.
//Function: For a user to input new account, close account, lodgement, withdraw & overdraft requests.
//Note: Credit Union GUI.

//Import Statements
import java.io.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.text.DecimalFormat;

public class CloseAccount extends JFrame implements ActionListener
{
//create GUI components
private JTextField OpenAccountField, FirstNameField, LastNameField, BalanceField, OverdraftField;
private JButton closeBut;
private JButton exitBut;
private RandomAccessFile input, output;
private Record data;

	public CloseAccount()
	{
	  	super ("Close Account");

		try
		  {
		  //set up files for read & write
		  input = new RandomAccessFile( "credit.dat", "rw" );
		  output = new RandomAccessFile( "credit.dat", "rw" );
		  }
		  catch ( IOException e )
		  {
		   System.err.println(e.toString() );
		   System.exit(1);
		  }

		data = new Record();

		//Create Buttons

		add( new JLabel( "Account Number (1 - 50)" ) );
		OpenAccountField = new JTextField(15);
		OpenAccountField.setEditable( true );
		add( OpenAccountField );
		OpenAccountField.addActionListener(this);

		add( new JLabel( "First Name" ) );
		FirstNameField = new JTextField(15);
		FirstNameField.setEditable( false );
		add( FirstNameField );

		add( new JLabel( "Last Name" ) );
		LastNameField = new JTextField(15);
		LastNameField.setEditable( false );
		add( LastNameField );

		add( new JLabel( "Account Balance" ) );
		BalanceField = new JTextField(15);
		BalanceField.setEditable( false );
		add( BalanceField );

		add( new JLabel("Overdraft") );
		OverdraftField = new JTextField();
		OverdraftField.setEditable( false );
		add(OverdraftField);

		closeBut = new JButton( "Close Account");
		closeBut.addActionListener( this );
		add(closeBut);
		closeBut.setEnabled(false);

		exitBut = new JButton( "Exit" );
		exitBut.addActionListener( this );
		add(exitBut);

		//create the components of the Frame
		setSize( 350, 200 );
		setLayout( new GridLayout(6, 2) );
		setVisible( true );
	}

	public void actionPerformed( ActionEvent e )
	{
	  //read account details when account number entered
	  if (! OpenAccountField.getText().equals(""))
	  {
		readRecord(); //calling readRecord method
		closeBut.setEnabled(true);
	  }
	  	if (e.getSource() == exitBut)   //exit maintenance menu
	  	{
			setVisible(false);
	  	}
	  		if (e.getSource() == closeBut)
	  		{
	   			try
	   			{
			 		int accountNumber = Integer.parseInt( OpenAccountField.getText() );

			 		Double Balance = Double.parseDouble(BalanceField.getText());

					if(Balance != 0)
					{
						JOptionPane.showMessageDialog(this, "Balance must be zero");
					}
						else
						if(Balance == 0)
						{
						  data.setOpenAccount( 0 );
						  data.setFirstName( null );
						  data.setLastName( null);
						  data.setBalance( 0);
						  data.setOverdraft(0);

						  output.seek( (long) ( accountNumber-1 ) * Record.size() );
						  data.write( output );

						  JOptionPane.showMessageDialog(this, "Account has been deleted");
						  OpenAccountField.setText("");
						  FirstNameField.setText("");
						  LastNameField.setText("");
						  BalanceField.setText("");
						  OverdraftField.setText("");
						 }//end else

				}// close try
		 		catch (NumberFormatException nfe )
			 	{
			  		System.err.println("You must enter an integer account number");
			 	}
		 		catch (IOException io)
			 	{
			  	System.err.println("error during write to file\n" + io.toString() );
			 	}
	  		}// end if statement
	 } //end actionPerformed

	//create method for reading records to file

	public void readRecord()
	{
		DecimalFormat twoDigits = new DecimalFormat( "0.00" );
		try
		{
			int accountNumber = Integer.parseInt(OpenAccountField.getText());

			if (accountNumber < 1 || accountNumber > 50)
				{
				JOptionPane.showMessageDialog(this, "Account does not exist");
				}
				else
				{
					input.seek( (accountNumber - 1)*Record.size());
					data.read(input);

					OpenAccountField.setText(String.valueOf( data.getOpenAccount() ) );
					FirstNameField.setText( data.getFirstName() );
					LastNameField.setText( data.getLastName() );
					BalanceField.setText( String.valueOf(twoDigits.format( data.getBalance() ) ) );
					OverdraftField.setText( String.valueOf(twoDigits.format( data.getOverdraft() ) ) );
				}
				if (data.getOpenAccount() == 0)
				{
					JOptionPane.showMessageDialog(this, "Account does not exist");
					OpenAccountField.setText("");
				}
		}//end try statement
		catch (EOFException eof )
		{
		   closeFile();
		}
		catch (IOException e )
		{
		   System.err.println("Error during read from file\n " + e.toString() );
		   System.exit( 1 );
		}
	} // end readRecord method

	private void closeFile()
	{
		try
		{
		input.close();
		System.exit( 0 );
		}
		catch( IOException e)
		{
		System.err.println( "Error closing file \n" + e.toString() );
		}
	}// end closeFile method

	public static void main(String [] args)
	{
		 new CloseAccount();
	}

} //end main class