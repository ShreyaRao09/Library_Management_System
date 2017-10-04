import java.awt.*;
import java.awt.event.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.*;

import net.proteanit.sql.DbUtils;

import java.sql.Connection;

public class MainPage extends JFrame
{
	JFrame mainFrame;
	JPanel controlPanel;
	JLabel headerLabel;
	
	static Connection conn;
	
	MainPage()
	{
		prepareGUI();
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				try{
					MainPage mainPage=new MainPage();
					//mainPage.setVisible(true);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		
	}
	
	void prepareGUI()
	{
		mainFrame=new JFrame("Library Management System");
		mainFrame.setSize(500,500);
		mainFrame.setLocation(20, 50);
		mainFrame.setLayout(new GridLayout(2,1));
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		controlPanel=new JPanel();
		controlPanel.setBorder(new EmptyBorder(10,10,10,10));
		setContentPane(controlPanel);
		GridBagLayout gbl_controlPanel=new GridBagLayout();
		gbl_controlPanel.columnWidths=new int[]{0,0};
		gbl_controlPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,0,0,0};
		gbl_controlPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_controlPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		controlPanel.setLayout(gbl_controlPanel);
		
		//Heading
		headerLabel=new JLabel("Library Management System",JLabel.CENTER);
		headerLabel.setFont(new Font("Times New Roman",Font.BOLD,22));
		headerLabel.setForeground(Color.black);
		headerLabel.setBackground(Color.green);
		/*GridBagConstraints gbc_lblHeader=new GridBagConstraints();
		gbc_lblHeader.fill=GridBagConstraints.BOTH;
		gbc_lblHeader.insets = new Insets(0, 0, 5, 0);
		gbc_lblHeader.gridx = 0;
		gbc_lblHeader.gridy = 1;
		//gbc_lblHeader.gridwidth=2;
		controlPanel.add(headerLabel, gbc_lblHeader);*/

		
		
		//search button
		JButton search=new JButton("Search For A Book");
		search.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
					mainFrame.setVisible(false);
					try {
						new Search();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			}
		});
		GridBagConstraints gbc_btnSearch=new GridBagConstraints();
		gbc_btnSearch.fill=GridBagConstraints.HORIZONTAL;
		gbc_btnSearch.insets = new Insets(0, 0, 5, 0);
		gbc_btnSearch.gridx = 0;
		gbc_btnSearch.gridy = 1;
		gbc_btnSearch.gridwidth=2;
		controlPanel.add(search, gbc_btnSearch);
		
				
		
		//Check Out Button
		JButton checkOut=new JButton("Check Out");
		checkOut.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				mainFrame.setVisible(false);
				new CheckOut();
			}
		});
		GridBagConstraints gbc_btnCheckOut=new GridBagConstraints();
		gbc_btnCheckOut.fill=GridBagConstraints.HORIZONTAL;
		gbc_btnCheckOut.insets = new Insets(0, 0, 5, 0);
		gbc_btnCheckOut.gridx = 0;
		gbc_btnCheckOut.gridy = 2;
		gbc_btnCheckOut.gridwidth=2;
		controlPanel.add(checkOut,gbc_btnCheckOut);
		
		//Check In Button
		JButton checkIn=new JButton("Check In");
		checkIn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				mainFrame.setVisible(false);
				new CheckIn();
			}
		});
		GridBagConstraints gbc_btnCheckIn=new GridBagConstraints();
		gbc_btnCheckIn.fill=GridBagConstraints.HORIZONTAL;
		gbc_btnCheckIn.insets = new Insets(0, 0, 5, 0);
		gbc_btnCheckIn.gridx = 0;
		gbc_btnCheckIn.gridy = 3;
		gbc_btnCheckIn.gridwidth=2;
		controlPanel.add(checkIn, gbc_btnCheckIn);

		
		//New Borrower Button
		JButton newBorrower = new JButton("New Borrower");
		newBorrower.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				mainFrame.setVisible(false);
				new NewBorrower();
			}
		});
		GridBagConstraints gbc_btnNewBorrower=new GridBagConstraints();
		gbc_btnNewBorrower.fill=GridBagConstraints.HORIZONTAL;
		gbc_btnNewBorrower.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewBorrower.gridx = 0;
		gbc_btnNewBorrower.gridy = 4;
		gbc_btnNewBorrower.gridwidth=2;
		controlPanel.add(newBorrower,gbc_btnNewBorrower);
		
		//Fines Button
		JButton fines=new JButton("Fines");
		fines.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
		
				//
				mainFrame.setVisible(false);
				new Fines();
			}
		});
		GridBagConstraints gbc_btnFines=new GridBagConstraints();
		gbc_btnFines.fill=GridBagConstraints.HORIZONTAL;
		gbc_btnFines.insets = new Insets(0, 0, 5, 0);
		gbc_btnFines.gridx = 0;
		gbc_btnFines.gridy = 5;
		gbc_btnFines.gridwidth=2;
		controlPanel.add(fines, gbc_btnFines);		
		
		//JLabel
		JLabel space=new JLabel(" ");
		GridBagConstraints gbc_lblSpace=new GridBagConstraints();
		gbc_lblSpace.fill=GridBagConstraints.HORIZONTAL;
		gbc_lblSpace.insets = new Insets(0, 0, 5, 0);
		gbc_lblSpace.gridx = 0;
		gbc_lblSpace.gridy = 6;
		controlPanel.add(space, gbc_lblSpace);		


		//Close Button
		JButton close=new JButton("Close");
		close.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				mainFrame.dispose();
			}
		});
		GridBagConstraints gbc_btnClose=new GridBagConstraints();
		gbc_btnClose.fill=GridBagConstraints.HORIZONTAL;
		gbc_btnClose.insets = new Insets(0, 0, 5, 0);
		gbc_btnClose.gridx = 1;
		gbc_btnClose.gridy = 7;
		gbc_btnClose.anchor=GridBagConstraints.PAGE_END;
		controlPanel.add(close, gbc_btnClose);		

		
		mainFrame.add(headerLabel);
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
	}
	
	
}