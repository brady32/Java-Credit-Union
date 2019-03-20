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

public class Overdraft extends JFrame implements ActionListener
{
//create GUI components
private JTextField OpenAccountField, FirstNameField, LastNameField, BalanceField, LodgementField, OverdraftField;
private JButton OverBut;
private JButton exitBut;
private RandomAccessFile input;
private RandomAccessFile output;
private Record data;

	public Overdraft()
	{
	  	super ("Set Overdraft");

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
		BalanceField = new JTextField();
		BalanceField.setEditable( false );
		add( BalanceField );

		add( new JLabel("Overdraft") );
		OverdraftField = new JTextField();
		OverdraftField.setEditable(true);
		add (OverdraftField);

		OverBut = new JButton("Add Overdraft");
		OverBut.addActionListener( this );
		add(OverBut);

		exitBut = new JButton("Exit");
		exitBut.addActionListener( this );
		add(exitBut);

		//create the components of the Frame
		setSize( 350, 200 );
		setLayout( new GridLayout(6, 2) );
		setVisible( true );
	}

	public void actionPerformed( ActionEvent e )
	{
		Double Balance;

	  //read account details when account number entered
	  if (! OpenAccountField.getText().equals(""))
	  {
		readRecord(); //calling readRecord method
		OverBut.setEnabled(true);
	  }
	  	if (e.getSource() == OverBut)   //exit maintenance menu
	  	  {
	  		Overdraft();
	  	  }

	  		if (e.getSource() == exitBut)   //exit maintenance menu
	  		{
				setVisible(false);
	  		}

	} //end actionPerformed

	//create method for reading records to file

	public void readRecord()
	{

		DecimalFormat twoDigits = new DecimalFormat( "0.00" );
		try
		{
			int AccountNumber = Integer.parseInt(OpenAccountField.getText());

			if (AccountNumber < 1 || AccountNumber > 50)
				{
					JOptionPane.showMessageDialog(this, "Account does not exist");
				}
				else
				{
					input.seek( (AccountNumber - 1)*Record.size());
					data.read(input);

					OpenAccountField.setText(String.valueOf( data.getOpenAccount() ) );
					FirstNameField.setText( data.getFirstName() );
					LastNameField.setText( data.getLastName() );
					BalanceField.setText( String.valueOf(twoDigits.format( data.getBalance() ) ) );
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

	//Calling method for Overdraft

	public void Overdraft()
	{
		Double Balance;
		int AccountNumber;
		Double Overdraft;

		try
			{
				AccountNumber = Integer.parseInt(OpenAccountField.getText());
				Overdraft = Double.parseDouble(OverdraftField.getText());
				data.setOpenAccount(AccountNumber);
				data.setFirstName(FirstNameField.getText());
				data.setLastName(LastNameField.getText());
				data.setOverdraft(Overdraft);
				Balance = data.getBalance();

				OpenAccountField.setText("");
				FirstNameField.setText("");
				LastNameField.setText("");
				BalanceField.setText("");
			 	OverdraftField.setText("");
				output.seek((long) (AccountNumber-1) * Record.size());
				data.write(output);
				JOptionPane.showMessageDialog(this, "Overdraft Completed: New Limit " + Overdraft);
			}
		catch (NumberFormatException nfe )
			{
				System.err.println("You must enter an integer account number");
			}
		catch (IOException io)
			{
				System.err.println("error during write to file\n" + io.toString() );
			 }

	 }

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
		 new Overdraft();
	}

} //end main class