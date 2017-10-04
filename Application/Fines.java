import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import java.sql.Connection;

public class Fines extends JFrame
{
	JFrame mainFrame;
	JPanel controlPanel;
	static Connection conn;
	
	Fines()
	{
		prepareGUI();
	}
	
	public static void main(String[] args)
	{
		Fines fines=new Fines();
		//mainPage.show();
	}
	
	void prepareGUI()
	{
		mainFrame=new JFrame("Library Management System");
		mainFrame.setSize(500,300);
		mainFrame.setLocation(20, 50);
		//mainFrame.setLayout(new GridLayout(2,1));
		//mainFrame.setMinimumSize(mainFrame.getSize());
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		controlPanel=new JPanel();
		controlPanel.setBorder(new EmptyBorder(10,10,10,10));
		setContentPane(controlPanel);
		GridBagLayout gbl_controlPanel=new GridBagLayout();
		gbl_controlPanel.columnWidths=new int[]{0,0,0};
		gbl_controlPanel.rowHeights = new int[]{0, 0, 0,0,0,0};
		gbl_controlPanel.columnWeights = new double[]{Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE};
		gbl_controlPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		controlPanel.setLayout(gbl_controlPanel);
		
		//update Fines table
		
		
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/proj1", "root", "sasi123");
			Statement stmt1=conn.createStatement();
			String date = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
			//System.out.println(date);
			ResultSet rs1=stmt1.executeQuery("Select * from proj1.book_loans where Due_date<'"+date+"';");
			while(rs1.next())
			{
				int loan_id=Integer.parseInt(rs1.getString(1));
				String dueDate=rs1.getString(5);
				
				DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date due=format.parse(dueDate);
				java.util.Date today=format.parse(date);
				
				double x =  (today.getTime() - due.getTime());
				//System.out.println(x);
            	x=x / (1000 * 60 * 60 * 24);
            	double fine=0.25*x;
            	//System.out.println(fine);
            	
            	Statement stmt2=conn.createStatement();
            	ResultSet rs2=stmt2.executeQuery("Select * from proj1.fines where Loan_id="+loan_id+" and Paid=0;");
            	
            	if(rs2.next())
            	{
            		Statement stmt3 = conn.createStatement();
					stmt3.executeUpdate("Update proj1.fines set Fine_amt="+fine+" where Loan_id="+loan_id+";");
            	}
            	else
            	{
            		Statement stmt5=conn.createStatement();
                	ResultSet rs5=stmt5.executeQuery("Select * from proj1.fines where Loan_id="+loan_id+" and Paid=1;");
                	
                	if(!rs5.next())
                	{
            		Statement stmt4 = conn.createStatement();
            		stmt4.executeUpdate("INSERT INTO proj1.fines (Loan_id,Fine_amt, Paid) VALUES ('"+loan_id+"', '"+fine+"',0);");
                	}
                }
			}
			conn.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("Sql Error : "+ e1.getMessage());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			System.out.println("Parse Exception : "+ e1.getMessage());
		}
		

		//Header
		JLabel header=new JLabel("Fines",JLabel.CENTER);
		header.setFont(new Font("Times New Roman",Font.BOLD,20));
		GridBagConstraints gbc_header=new GridBagConstraints();
		gbc_header.insets=new Insets(0,0,5,0);
		gbc_header.gridx=0;
		gbc_header.gridy=1;
		gbc_header.gridwidth=3;
		controlPanel.add(header, gbc_header);		
		
		//
		JLabel space1=new JLabel("  ",JLabel.CENTER);
		//searchLabel.setFont(new F);
		GridBagConstraints gbc_space1=new GridBagConstraints();
		gbc_space1.insets=new Insets(0,0,5,0);
		gbc_space1.gridx=0;
		gbc_space1.gridy=2;
		gbc_space1.gridwidth=3;
		controlPanel.add(space1, gbc_space1);
		
		//Borrower Card No.
		JLabel lblcard=new JLabel("Card No.*  :",JLabel.LEFT);
		lblcard.setFont(new Font("Times New Roman",Font.BOLD,14));
		GridBagConstraints gbc_lblcard=new GridBagConstraints();
		gbc_lblcard.insets=new Insets(0,0,5,0);
		gbc_lblcard.gridx=0;
		gbc_lblcard.gridy=3;
		//gbc_lblcard.gridwidth=2;
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
		gbc_cardText.gridwidth=2;
		controlPanel.add(cardText, gbc_cardText);
		//controlPanel.add(searchText);
		cardText.setColumns(15);
		
		//All Fines Due
		JButton finesDue=new JButton("All Fines Due");
		//checkOut.setLocation(50, 50);
		finesDue.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					new FinesDue(cardText.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_finesDue=new GridBagConstraints();
		//gbc_finesDue.fill=GridBagConstraints.HORIZONTAL;
		gbc_finesDue.insets = new Insets(0, 0, 5, 0);
		gbc_finesDue.gridx = 0;
		gbc_finesDue.gridy = 4;
		//gbc_finesDue.gridwidth=2;
		controlPanel.add(finesDue,gbc_finesDue);
		
		//Paid Fines
		JButton paidFines=new JButton("Paid Fines");
		//checkOut.setLocation(50, 50);
		paidFines.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					new PaidFines(cardText.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_paidFines=new GridBagConstraints();
		//gbc_paidFines.fill=GridBagConstraints.HORIZONTAL;
		gbc_paidFines.insets = new Insets(0, 0, 5, 0);
		gbc_paidFines.gridx = 1;
		gbc_paidFines.gridy = 4;
		//gbc_paidFines.gridwidth=2;
		controlPanel.add(paidFines,gbc_paidFines);
		
		//Total Amount Due***************************************
		JButton totalDue=new JButton("Total Amount Due");
		//checkOut.setLocation(50, 50);
		totalDue.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				
				try {
					conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/proj1", "root", "sasi123");
					Statement stmt1=conn.createStatement();
					int card_id=Integer.parseInt(cardText.getText());
					ResultSet rs1=stmt1.executeQuery("Select b.Card_id, SUM(f.Fine_amt) from proj1.fines as f left outer join proj1.book_loans as b on b.Loan_id=f.Loan_id where f.Paid=0 group by b.Card_id having b.Card_id="+card_id+";");
					if(rs1.next())
					{
						BigDecimal fine=rs1.getBigDecimal(2);
						JOptionPane.showMessageDialog(null, "Total Fine Amount Due : " + fine.toString());
					}
					else
					{
						JOptionPane.showMessageDialog(null, "No Fines Due");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println("SQL Exception : " + e1.getMessage());
				}
				
			}
		});
		GridBagConstraints gbc_totalDue=new GridBagConstraints();
		gbc_totalDue.insets = new Insets(0, 0, 5, 0);
		gbc_totalDue.gridx = 2;
		gbc_totalDue.gridy = 4;
		controlPanel.add(totalDue,gbc_totalDue);
		
		JLabel space2=new JLabel("  ",JLabel.CENTER);
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
		gbc_btnClose.gridwidth=3;
		controlPanel.add(close, gbc_btnClose);		

		
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
	}
}