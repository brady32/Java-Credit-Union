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

public class OpenAccount extends JFrame implements ActionListener
{
  //create GUI componements
  private JTextField OpenAccountField, FirstNameField, LastNameField, BalanceField, OverdraftField;
  private JButton enter;  //send record to file
  private JButton done; //exit program
  private RandomAccessFile output; //file for input & output
  private RandomAccessFile input;
  private Record data;

  int AccountNumber = 0;


	public OpenAccount()  //contructor
	{
		super("Create new account");

		//open file
		data = new Record();


	   //access record
		  try
		  {
			output = new RandomAccessFile( "credit.dat", "rw" );
			System.out.println("In first try block");
		  }
		  catch ( IOException e)
		  {
		   System.err.println( e.toString() );
		   System.exit(1);
		  }

	   //Create Buttons

		add( new JLabel("Account Number (1-50)") );
		OpenAccountField = new JTextField();
		add(OpenAccountField);

		add( new JLabel("First Name") );
		FirstNameField = new JTextField();
		add(FirstNameField);

		add( new JLabel("Last Name") );
		LastNameField = new JTextField();
		add(LastNameField);

		add( new JLabel("Balance") );
		BalanceField = new JTextField();
		add(BalanceField);

		enter = new JButton ("Add Account");
		enter.addActionListener(this);
		add (enter);

		done = new JButton ("Done");
		done.addActionListener(this);
		add (done);

		//set out layout
		setSize( 350, 200 );
	    setLayout(new GridLayout(5,2));
		setVisible( true );
	}

	//create method for adding records to file
	public void addRecord()
	{

	 Double Balance;

	 if ( ! OpenAccountField.getText().equals( "" ) );
	 {

	 try
	  {
	   AccountNumber = Integer.parseInt(OpenAccountField.getText() );
	   Balance = Double.parseDouble(BalanceField.getText() );
	   System.out.println("In second try block");

		if (AccountNumber < 1 || AccountNumber > 50)  //validate account number is in range
		 {
		  JOptionPane.showMessageDialog(this, "Account number must be between 1 & 50");
		 }

		else if (AccountNumber > 0 && AccountNumber <= 50 ) {

		   //read file to check if account number already exists.
		   output.seek((long) (AccountNumber - 1) * Record.size());
		   data.read(output);

		  if (data.getOpenAccount() == AccountNumber)  //if a/c exists,display dialog box to user
			{
			JOptionPane.showMessageDialog(this,"Account already in use! Please try a different account number");
			OpenAccountField.setText("");   // clear account textfield
			}

			  else   //once conditions are met, data is written to file.
			   {
				data.setOpenAccount(AccountNumber);
				data.setFirstName(FirstNameField.getText());
				data.setLastName(LastNameField.getText());
				Balance = new Double(BalanceField.getText());
				data.setBalance(Balance.doubleValue());
				output.seek((long) (AccountNumber-1) * Record.size());
				data.write(output);
				JOptionPane.showMessageDialog(this, "Account Details Saved");
			   }
		}
				//reset textfields
				OpenAccountField.setText("");
				FirstNameField.setText("");
				LastNameField.setText("");
				BalanceField.setText("");

	  }//end try statement
			  catch (NumberFormatException nfe )
			  {
				 System.err.println("You must enter an integer account number");
			   }
			   catch (IOException io)
			   {
			   System.err.println("error during write to file\n" + io.toString() );
			  }

	 }//end initial if statement
	} //end addRecord method


	public void actionPerformed (ActionEvent e)  //add actionperformed for exit button
	{

	 	addRecord();

		 if (e.getSource() == done)
		 {
		  	try
		  	{
		   		output.close();
		  	}
		  		catch (IOException io)
		  		{
		   			System.err.println( "File not closed properly\n" + io.toString() );
		  		}
		   setVisible(false);
		}
	}

	//Instantiate a WriteRandonFile object and start the program

		public static void main(String [] args )
		{
		 new OpenAccount();
		}
} //end main class

