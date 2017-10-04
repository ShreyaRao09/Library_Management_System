import java.awt.*;
import java.awt.event.*;
import java.security.Timestamp;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;

import net.proteanit.sql.DbUtils;

import java.sql.Connection;

public class NewBorrower extends JFrame
{
	JFrame mainFrame;
	JPanel controlPanel;
	static Connection conn;
	
	NewBorrower()
	{
		prepareGUI();
	}
	
	public static void main(String[] args)
	{
		NewBorrower newBorrower=new NewBorrower();
		//mainPage.show();
	}
	
	void prepareGUI()
	{
		mainFrame=new JFrame("Library Management System");
		mainFrame.setSize(600,350);
		mainFrame.setLocation(20, 50);
		//mainFrame.setLayout(new GridLayout(2,1));
		//mainFrame.setMinimumSize(mainFrame.getSize());
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		controlPanel=new JPanel();
		controlPanel.setBorder(new EmptyBorder(10,10,10,10));
		setContentPane(controlPanel);
		GridBagLayout gbl_controlPanel=new GridBagLayout();
		gbl_controlPanel.columnWidths=new int[]{0,0,0,0};
		gbl_controlPanel.rowHeights = new int[]{0, 0, 0,0,0,0,0,0};
		gbl_controlPanel.columnWeights = new double[]{Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE};
		gbl_controlPanel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		controlPanel.setLayout(gbl_controlPanel);
		
		
		//Header
		JLabel lblHeader=new JLabel("New Borrower",JLabel.CENTER);
		lblHeader.setFont(new Font("Times New Roman",Font.BOLD,20));
		GridBagConstraints gbc_lblHeader=new GridBagConstraints();
		gbc_lblHeader.insets=new Insets(0,0,5,0);
		gbc_lblHeader.gridx=0;
		gbc_lblHeader.gridy=0;
		gbc_lblHeader.gridwidth=4;
		controlPanel.add(lblHeader, gbc_lblHeader);

		//First Name
		JLabel lblFName=new JLabel("First Name* :",JLabel.LEFT);
		lblFName.setFont(new Font("Times New Roman",Font.BOLD,14));
		GridBagConstraints gbc_lblFName=new GridBagConstraints();
		gbc_lblFName.insets=new Insets(0,0,5,0);
		gbc_lblFName.gridx=0;
		gbc_lblFName.gridy=1;
		//gbc_lblIsbn.gridwidth=2;
		controlPanel.add(lblFName, gbc_lblFName);
		
		//First Name Text Box
		JTextField fNameText = new JTextField();
		
		fNameText.setFont(new Font("Ariel",Font.PLAIN,14));
		fNameText.setForeground(Color.black);
		GridBagConstraints gbc_fNameText=new GridBagConstraints();
		gbc_fNameText.fill=GridBagConstraints.HORIZONTAL;
		gbc_fNameText.insets=new Insets(0,0,5,0);
		gbc_fNameText.gridx=1;
		gbc_fNameText.gridy=1;
		//gbc_isbnText.gridwidth=2;
		controlPanel.add(fNameText, gbc_fNameText);
		//controlPanel.add(searchText);
		fNameText.setColumns(15);
		
		//Last Name
		JLabel lblLName=new JLabel("Last Name* :",JLabel.LEFT);
		lblLName.setFont(new Font("Times New Roman",Font.BOLD,14));
		GridBagConstraints gbc_lblLName=new GridBagConstraints();
		gbc_lblLName.insets=new Insets(0,0,5,0);
		gbc_lblLName.gridx=2;
		gbc_lblLName.gridy=1;
		//gbc_lblIsbn.gridwidth=2;
		controlPanel.add(lblLName, gbc_lblLName);
		
		//Last NameText Box
		JTextField lNameText = new JTextField();
	
		lNameText.setFont(new Font("Ariel",Font.PLAIN,14));
		lNameText.setForeground(Color.black);
		//cardText.setSize(200, 50);;
		GridBagConstraints gbc_lNameText =new GridBagConstraints();
		gbc_lNameText.fill=GridBagConstraints.HORIZONTAL;
		gbc_lNameText.insets=new Insets(0,0,5,0);
		gbc_lNameText.gridx=3;
		gbc_lNameText.gridy=1;
		//gbc_isbnText.gridwidth=2;
		controlPanel.add(lNameText, gbc_lNameText);
		//controlPanel.add(searchText);
		lNameText.setColumns(15);
		
		//SSN
		JLabel lblSsn=new JLabel("          SSN* :",JLabel.LEFT);
		lblSsn.setFont(new Font("Times New Roman",Font.BOLD,14));
		GridBagConstraints gbc_lblSsn=new GridBagConstraints();
		gbc_lblSsn.insets=new Insets(0,0,5,0);
		gbc_lblSsn.gridx=0;
		gbc_lblSsn.gridy=2;
		//gbc_lblIsbn.gridwidth=2;
		controlPanel.add(lblSsn, gbc_lblSsn);
		
		//Ssn Text Box
		JTextField ssnText = new JTextField();
		
		ssnText.setFont(new Font("Ariel",Font.PLAIN,14));
		ssnText.setForeground(Color.black);
		GridBagConstraints gbc_ssnText=new GridBagConstraints();
		gbc_ssnText.fill=GridBagConstraints.HORIZONTAL;
		gbc_ssnText.insets=new Insets(0,0,5,0);
		gbc_ssnText.gridx=1;
		gbc_ssnText.gridy=2;
		//gbc_isbnText.gridwidth=2;
		controlPanel.add(ssnText, gbc_ssnText);
		//controlPanel.add(searchText);
		ssnText.setColumns(15);
		ssnText.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	           char c = e.getKeyChar();
	           if (!(!Character.isLetter(c) ||
	              (c == KeyEvent.VK_BACK_SPACE) ||
	              (c == KeyEvent.VK_DELETE))) {
	                e.consume();
	              }
	           if (ssnText.getText().length() >= 9 )
	        	   e.consume();
	         }
	       });
		
		//Email
		JLabel lblEmail=new JLabel("       Email* :",JLabel.LEFT);
		lblEmail.setFont(new Font("Times New Roman",Font.BOLD,14));
		GridBagConstraints gbc_lblEmail=new GridBagConstraints();
		gbc_lblEmail.insets=new Insets(0,0,5,0);
		gbc_lblEmail.gridx=2;
		gbc_lblEmail.gridy=2;
		//gbc_lblIsbn.gridwidth=2;
		controlPanel.add(lblEmail, gbc_lblEmail);
		
		//Email Text Box
		JTextField emailText = new JTextField();
	
		emailText.setFont(new Font("Ariel",Font.PLAIN,14));
		emailText.setForeground(Color.black);
		//cardText.setSize(200, 50);;
		GridBagConstraints gbc_emailText =new GridBagConstraints();
		gbc_emailText.fill=GridBagConstraints.HORIZONTAL;
		gbc_emailText.insets=new Insets(0,0,5,0);
		gbc_emailText.gridx=3;
		gbc_emailText.gridy=2;
		//gbc_isbnText.gridwidth=2;
		controlPanel.add(emailText, gbc_emailText);
		//controlPanel.add(searchText);
		emailText.setColumns(15);
		
		//Address
		JLabel lblAddress=new JLabel("    Address* :",JLabel.LEFT);
		lblAddress.setFont(new Font("Times New Roman",Font.BOLD,14));
		GridBagConstraints gbc_lblAddress=new GridBagConstraints();
		gbc_lblAddress.insets=new Insets(0,0,5,0);
		gbc_lblAddress.gridx=0;
		gbc_lblAddress.gridy=3;
		//gbc_lblIsbn.gridwidth=2;
		controlPanel.add(lblAddress, gbc_lblAddress);
		
		//Address Text Box
		JTextField addressText = new JTextField();
		
		addressText.setFont(new Font("Ariel",Font.PLAIN,14));
		addressText.setForeground(Color.black);
		GridBagConstraints gbc_addressText=new GridBagConstraints();
		gbc_addressText.fill=GridBagConstraints.HORIZONTAL;
		gbc_addressText.insets=new Insets(0,0,5,0);
		gbc_addressText.gridx=1;
		gbc_addressText.gridy=3;
		//gbc_isbnText.gridwidth=2;
		controlPanel.add(addressText, gbc_addressText);
		//controlPanel.add(searchText);
		addressText.setColumns(15);

		//City
		JLabel lblCity=new JLabel("         City* :",JLabel.LEFT);
		lblCity.setFont(new Font("Times New Roman",Font.BOLD,14));
		GridBagConstraints gbc_lblCity=new GridBagConstraints();
		gbc_lblCity.insets=new Insets(0,0,5,0);
		gbc_lblCity.gridx=2;
		gbc_lblCity.gridy=3;
		//gbc_lblIsbn.gridwidth=2;
		controlPanel.add(lblCity, gbc_lblCity);
		
		//City Text Box
		JTextField cityText = new JTextField();
		
		cityText.setFont(new Font("Ariel",Font.PLAIN,14));
		cityText.setForeground(Color.black);
		GridBagConstraints gbc_cityText=new GridBagConstraints();
		gbc_cityText.fill=GridBagConstraints.HORIZONTAL;
		gbc_cityText.insets=new Insets(0,0,5,0);
		gbc_cityText.gridx=3;
		gbc_cityText.gridy=3;
		//gbc_isbnText.gridwidth=2;
		controlPanel.add(cityText, gbc_cityText);
		//controlPanel.add(searchText);
		cityText.setColumns(15);
		
		//State
		JLabel lblState=new JLabel("        State* :",JLabel.LEFT);
		lblState.setFont(new Font("Times New Roman",Font.BOLD,14));
		GridBagConstraints gbc_lblState=new GridBagConstraints();
		gbc_lblState.insets=new Insets(0,0,5,0);
		gbc_lblState.gridx=0;
		gbc_lblState.gridy=4;
		//gbc_lblIsbn.gridwidth=2;
		controlPanel.add(lblState, gbc_lblState);
		
		//State Text Box
		JTextField stateText = new JTextField();
	
		stateText.setFont(new Font("Ariel",Font.PLAIN,14));
		stateText.setForeground(Color.black);
		//cardText.setSize(200, 50);;
		GridBagConstraints gbc_stateText =new GridBagConstraints();
		gbc_stateText.fill=GridBagConstraints.HORIZONTAL;
		gbc_stateText.insets=new Insets(0,0,5,0);
		gbc_stateText.gridx=1;
		gbc_stateText.gridy=4;
		//gbc_isbnText.gridwidth=2;
		controlPanel.add(stateText, gbc_stateText);
		//controlPanel.add(searchText);
		stateText.setColumns(15);
		
		//Phone
		JLabel lblPhone=new JLabel("      Phone* :",JLabel.LEFT);
		lblPhone.setFont(new Font("Times New Roman",Font.BOLD,14));
		GridBagConstraints gbc_lblPhone=new GridBagConstraints();
		gbc_lblPhone.insets=new Insets(0,0,5,0);
		gbc_lblPhone.gridx=2;
		gbc_lblPhone.gridy=4;
		//gbc_lblIsbn.gridwidth=2;
		controlPanel.add(lblPhone, gbc_lblPhone);
		
		//Phone Text Box
		JTextField phoneText = new JTextField();
	
		phoneText.setFont(new Font("Ariel",Font.PLAIN,14));
		phoneText.setForeground(Color.black);
		//cardText.setSize(200, 50);;
		GridBagConstraints gbc_phoneText =new GridBagConstraints();
		gbc_phoneText.fill=GridBagConstraints.HORIZONTAL;
		gbc_phoneText.insets=new Insets(0,0,5,0);
		gbc_phoneText.gridx=3;
		gbc_phoneText.gridy=4;
		//gbc_isbnText.gridwidth=2;
		controlPanel.add(phoneText, gbc_phoneText);
		//controlPanel.add(searchText);
		phoneText.setColumns(15);
		phoneText.addKeyListener(new KeyAdapter() {
	         public void keyTyped(KeyEvent e) {
	           char c = e.getKeyChar();
	           if (!(!Character.isLetter(c) ||
	              (c == KeyEvent.VK_BACK_SPACE) ||
	              (c == KeyEvent.VK_DELETE))) {
	                e.consume();
	              }
	           if (phoneText.getText().length() >= 10 )
	        	   e.consume();
	         }
	       });
		

		JLabel space1=new JLabel("  ",JLabel.CENTER);
		//searchLabel.setFont(new F);
		GridBagConstraints gbc_space1=new GridBagConstraints();
		gbc_space1.insets=new Insets(0,0,5,0);
		gbc_space1.gridx=0;
		gbc_space1.gridy=5;
		gbc_space1.gridwidth=4;
		controlPanel.add(space1, gbc_space1);

		//Add New Borrower Button
		JButton newBorrower=new JButton("Add");
		//checkOut.setLocation(50, 50);
		newBorrower.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try{
					String fName=fNameText.getText();
					String lName=lNameText.getText();
					String ssn=ssnText.getText();
					String email=emailText.getText();
					String address=addressText.getText();
					String city=cityText.getText();
					String state=stateText.getText();
					String phone=phoneText.getText();
					
					if(fNameText.getText().equals("") && lNameText.getText().equals("") && ssnText.getText().equals("")&& emailText.getText().equals("") && addressText.getText().equals("") && cityText.getText().equals("") && stateText.getText().equals("") && phoneText.getText().equals("") )
					{
						JOptionPane.showMessageDialog(null, "Please enter something in all required text boxes");
					}

					if(phone.length()!=0)
					{
						if(phone.length()<10)
						{
						JOptionPane.showMessageDialog(null, "Please enter correct phone number");
						phoneText.setText("");
						}
					}	
					if(ssn.length()<9)
					{
						JOptionPane.showMessageDialog(null, "Please enter correct SSN");
						ssnText.setText("");
					}
				
				else{
						conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/proj1", "root", "sasi123");
						Statement stmt1=conn.createStatement();
						ResultSet rs1 = stmt1.executeQuery("select * from proj1.borrower where Ssn='"+ssn+"';");
						if(rs1.next())
						{
							JOptionPane.showMessageDialog(null, "The borrower already exists in the system");
							ssnText.setText("");
						}
						else
						{
							Statement stmt2=conn.createStatement();
						ResultSet rs2 = stmt2.executeQuery("select max(Card_id) from proj1.borrower;");
						int id=0;
						if(rs2.next())
						{				 	
						 id=rs2.getInt(1)+1;
						}
						Statement stmt3=conn.createStatement();
						stmt3.executeUpdate("Insert into proj1.borrower(Card_id,Ssn,Bname,Email,Address,City,State,Phone) values("+id+",'"+ssn+"',Concat('"+fName+"',' ','"+lName+"'),'"+email+"','"+address+"','"+city+"','"+state+"','"+phone+"');");
						fNameText.setText("");
						lNameText.setText("");
						ssnText.setText("");
						emailText.setText("");
						addressText.setText("");
						cityText.setText("");
						stateText.setText("");
						phoneText.setText("");
						JOptionPane.showMessageDialog(null, "Card Id of new borrower is : "+id);
						}
					} 
					System.out.println("6");
					
				}
					catch (SQLException e1) {
						// TODO Auto-generated catch block
						System.out.println("Error in connection: " + e1.getMessage());
					}
					
					
					}
					
				
			
		});
		GridBagConstraints gbc_newBorrower=new GridBagConstraints();
		gbc_newBorrower.fill=GridBagConstraints.HORIZONTAL;
		gbc_newBorrower.insets = new Insets(0, 0, 5, 0);
		gbc_newBorrower.gridx = 0;
		gbc_newBorrower.gridy = 6;
		gbc_newBorrower.gridwidth=4;
		controlPanel.add(newBorrower,gbc_newBorrower);
		
		JLabel space2=new JLabel("  ",JLabel.CENTER);
		//searchLabel.setFont(new F);
		GridBagConstraints gbc_space2=new GridBagConstraints();
		gbc_space2.insets=new Insets(0,0,5,0);
		gbc_space2.gridx=0;
		gbc_space2.gridy=7;
		gbc_space2.gridwidth=4;
		controlPanel.add(space2, gbc_space2);
		
		//Close Button
		JButton close=new JButton("Back");
		close.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				mainFrame.setVisible(false);
				new MainPage();
			}
		});
		GridBagConstraints gbc_btnClose=new GridBagConstraints();
		gbc_btnClose.fill=GridBagConstraints.HORIZONTAL;
		gbc_btnClose.insets = new Insets(0, 0, 5, 0);
		gbc_btnClose.gridx = 0;
		gbc_btnClose.gridy = 8;
		gbc_btnClose.anchor=GridBagConstraints.PAGE_END;
		gbc_btnClose.gridwidth=4;
		controlPanel.add(close, gbc_btnClose);		

		
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
	}
}