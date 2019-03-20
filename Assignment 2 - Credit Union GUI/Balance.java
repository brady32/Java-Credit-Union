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

public class Balance extends JFrame implements ActionListener
{
//create GUI components
private JTextField OpenAccountField, FirstNameField, LastNameField, BalanceField, OverdraftField;
private JButton exitBut;
private RandomAccessFile input, output;
private Record data;

	public Balance()
	{
	  	super ("Current Balance");

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
		 new Balance();
	}

} //end main class