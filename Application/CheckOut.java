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

public class CheckOut extends JFrame
{
	JFrame mainFrame;
	JPanel controlPanel;
	static Connection conn;
	
	CheckOut()
	{
		prepareGUI();
	}
	
	public static void main(String[] args)
	{
		CheckOut checkOut=new CheckOut();
		//mainPage.show();
	}
	
	void prepareGUI()
	{
		mainFrame=new JFrame("Library Management System");
		mainFrame.setSize(500,300);
		mainFrame.setLocation(20, 50);
		//mainFrame.setLayout(new GridLayout(2,1));
		mainFrame.setMinimumSize(mainFrame.getSize());
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		controlPanel=new JPanel();
		controlPanel.setBorder(new EmptyBorder(10,10,10,10));
		setContentPane(controlPanel);
		GridBagLayout gbl_controlPanel=new GridBagLayout();
		gbl_controlPanel.columnWidths=new int[]{0,0};
		gbl_controlPanel.rowHeights = new int[]{0, 0, 0,0,0,0};
		gbl_controlPanel.columnWeights = new double[]{Double.MIN_VALUE, Double.MIN_VALUE};
		gbl_controlPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		controlPanel.setLayout(gbl_controlPanel);
		
		JLabel space1=new JLabel("  ",JLabel.CENTER);
		//searchLabel.setFont(new F);
		GridBagConstraints gbc_space1=new GridBagConstraints();
		gbc_space1.insets=new Insets(0,0,5,0);
		gbc_space1.gridx=0;
		gbc_space1.gridy=1;
		gbc_space1.gridwidth=2;
		controlPanel.add(space1, gbc_space1);

		//Book ISBN
		JLabel lblIsbn=new JLabel("     ISBN* :",JLabel.LEFT);
		lblIsbn.setFont(new Font("Times New Roman",Font.BOLD,14));
		GridBagConstraints gbc_lblIsbn=new GridBagConstraints();
		gbc_lblIsbn.insets=new Insets(0,0,5,0);
		gbc_lblIsbn.gridx=0;
		gbc_lblIsbn.gridy=2;
		//gbc_lblIsbn.gridwidth=2;
		controlPanel.add(lblIsbn, gbc_lblIsbn);
		
		//Isbn Text Box
		JTextField isbnText = new JTextField();
		
		isbnText.setFont(new Font("Ariel",Font.PLAIN,14));
		isbnText.setForeground(Color.black);
		GridBagConstraints gbc_isbnText=new GridBagConstraints();
		gbc_isbnText.fill=GridBagConstraints.HORIZONTAL;
		gbc_isbnText.insets=new Insets(0,0,5,0);
		gbc_isbnText.gridx=1;
		gbc_isbnText.gridy=2;
		//gbc_isbnText.gridwidth=2;
		controlPanel.add(isbnText, gbc_isbnText);
		//controlPanel.add(searchText);
		isbnText.setColumns(15);
		
		//Borrower Card No.
		JLabel lblcard=new JLabel("Card No.* :",JLabel.LEFT);
		lblcard.setFont(new Font("Times New Roman",Font.BOLD,14));
		GridBagConstraints gbc_lblcard=new GridBagConstraints();
		gbc_lblcard.insets=new Insets(0,0,5,0);
		gbc_lblcard.gridx=0;
		gbc_lblcard.gridy=3;
		//gbc_lblIsbn.gridwidth=2;
		controlPanel.add(lblcard, gbc_lblcard);
		
		//Card No.Text Box
		JTextField cardText = new JTextField();
	
		cardText.setFont(new Font("Ariel",Font.PLAIN,14));
		cardText.setForeground(Color.black);
		//cardText.setSize(200, 50);;
		GridBagConstraints gbc_cardText =new GridBagConstraints();
		gbc_cardText.fill=GridBagConstraints.HORIZONTAL;
		gbc_cardText.insets=new Insets(0,0,5,0);
		gbc_cardText.gridx=1;
		gbc_cardText.gridy=3;
		//gbc_isbnText.gridwidth=2;
		controlPanel.add(cardText, gbc_cardText);
		//controlPanel.add(searchText);
		cardText.setColumns(15);
		
		JButton checkOut=new JButton("Check Out");
		//checkOut.setLocation(50, 50);
		checkOut.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{if(isbnText.getText().equals("") || cardText.getText().equals("") )
			{
				JOptionPane.showMessageDialog(null, "Please fill in the required fields");
			}
			else{
				try{
				String isbn=isbnText.getText();
				int cardId=Integer.parseInt(cardText.getText());
				String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
				Calendar c = Calendar.getInstance();
				c.add(Calendar.DATE, 14);
				String dueDate = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
				
				//System.out.println(date + " " + dueDate);
				
				conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/proj1", "root", "sasi123");
				Statement stmt1=conn.createStatement();
				ResultSet rs1 = stmt1.executeQuery("select * from proj1.book where Isbn='"+isbn+"';");
				Statement stmt2 = conn.createStatement();
				ResultSet rs2 = stmt2.executeQuery("select * from proj1.borrower where Card_id='"+cardId+"';");
				
				if(rs1.next()&&rs2.next())
				{
					Statement stmt3 = conn.createStatement();
					ResultSet rs3 = stmt3.executeQuery("select * from proj1.book_loans where Isbn='"+isbn+"' and Date_in is null;");
					if(rs3.next())
					{
						JOptionPane.showMessageDialog(null, "This book has already been issued");	
						isbnText.setText(""); 
					}
					else
					{
						Statement stmt4 = conn.createStatement();
						ResultSet rs4 = stmt4.executeQuery("select * from proj1.book_loans where Card_id='"+cardId+"' and Date_in is null and Due_date<CAST(CURRENT_TIMESTAMP AS DATE);");
						if(rs4.next())
						{
							JOptionPane.showMessageDialog(null, "The borrower already has an overdue book");	
							isbnText.setText(""); 
							cardText.setText("");
						}
						else
						{
							Statement stmt5 = conn.createStatement();
							ResultSet rs5 = stmt5.executeQuery("select count(*) from proj1.book_loans where Card_id='"+cardId+"' and Date_in is null;");
							rs5.next();
							if(rs5.getInt(1)>=3)
							{
								JOptionPane.showMessageDialog(null, "The borrower already has 3 active book loans");
								isbnText.setText(""); 
								cardText.setText("");
							}
							else
							{
								Statement stmt6 = conn.createStatement();
								ResultSet rs6 = stmt6.executeQuery("select * from proj1.book_loans b,fines f where Card_id="+cardId+" and b.Loan_id=f.Loan_id and paid=0;");	
								if(rs6.next())
								{
									JOptionPane.showMessageDialog(null, "The borrower has to pay some fine");
									isbnText.setText(""); 
									cardText.setText("");
								}
								else{
									Statement stmt = conn.createStatement();
									ResultSet rs= stmt.executeQuery("select max(Loan_id) from book_loans");
									int id=0;
									if(rs.next())
									{				 	
									 id=rs.getInt(1)+1;
									}
									
									String sql="INSERT INTO Book_loans (Loan_id, Isbn, Card_id, Date_out, Due_date) VALUES ("+id+", '"+isbn+"', "+cardId+", '"+date+"', '"+dueDate+"');";
									Statement stmt7 = conn.createStatement();
									stmt7.executeUpdate(sql);
									JOptionPane.showMessageDialog(null, "Done");
									isbnText.setText(""); 
									cardText.setText("");
								}
							}
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null, "Invalid Isbn or Card Id");	
					isbnText.setText("");
					cardText.setText("");
				}
				
				
		    }
			catch(SQLException ex) {
				System.out.println("Error in connection: " + ex.getMessage());
			}
			
			}
			}
		});
		GridBagConstraints gbc_btnCheckOut=new GridBagConstraints();
		gbc_btnCheckOut.fill=GridBagConstraints.HORIZONTAL;
		gbc_btnCheckOut.insets = new Insets(0, 0, 5, 0);
		gbc_btnCheckOut.gridx = 0;
		gbc_btnCheckOut.gridy = 4;
		gbc_btnCheckOut.gridwidth=2;
		controlPanel.add(checkOut,gbc_btnCheckOut);
		
		JLabel space2=new JLabel("  ",JLabel.CENTER);
		//searchLabel.setFont(new F);
		GridBagConstraints gbc_space2=new GridBagConstraints();
		gbc_space2.insets=new Insets(0,0,5,0);
		gbc_space2.gridx=0;
		gbc_space2.gridy=5;
		gbc_space2.gridwidth=2;
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
		gbc_btnClose.gridy = 6;
		gbc_btnClose.anchor=GridBagConstraints.PAGE_END;
		gbc_btnClose.gridwidth=2;
		controlPanel.add(close, gbc_btnClose);		

		
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
	}
}