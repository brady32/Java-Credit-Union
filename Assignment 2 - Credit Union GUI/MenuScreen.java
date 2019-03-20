//Programmer: Emma Brady.
//Student ID: B00100648.
//Date Written: 17th April 2018.
//Function: For a user to input new account, close account, lodgement, withdraw & overdraft requests.
//Note: Credit Union GUI.


//Import Statements
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;

public class MenuScreen extends JFrame
{
	//Create Buttons
	private JButton OpenAccount;
	private JButton Lodgement;
	private JButton Withdrawal;
	private JButton Balance;
	private JButton Overdraft;
	private JButton CloseAccount;
	public JPanel panel;

	public MenuScreen()
	{
        super( "Main Menu" );

        JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("creditunion.jpg"));
		add(logo, BorderLayout.NORTH);

        OpenAccount = new JButton("Open Account");
        OpenAccount.addActionListener(new ActionListener()
        {
			public void actionPerformed(ActionEvent e)
			{
				new OpenAccount();
			}
		}
		);

		Lodgement = new JButton("Make Lodgement");
		Lodgement.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new Lodgement();
			}
		}
		);

		Withdrawal = new JButton("Make Withdrawal");
		Withdrawal.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new Withdrawal();
			}
		}
		);

		Balance = new JButton("Show Current Balance");
		Balance.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new Balance();
			}
		}
		);

		Overdraft = new JButton("Set Overdraft");
		Overdraft.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new Overdraft();
			}
		}
		);

		CloseAccount = new JButton("Close Account");
		CloseAccount.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				new CloseAccount();
			}
		}
		);

		panel = new JPanel(); //Creating panel for buttons.
		panel.setLayout(new FlowLayout()); //Setting panel layout.
		add(panel, BorderLayout.CENTER); //Setting positioning of panel.

		//Adding buttons to panel.
		panel.add(OpenAccount);
		panel.add(Lodgement);
		panel.add(Withdrawal);
		panel.add(Balance);
		panel.add(Overdraft);
		panel.add(CloseAccount);

		setSize( 250, 450);
		setVisible( true );
    }

//main method
	public static void main(String[] args )
	{
		new MenuScreen();
	}
} //end main class
