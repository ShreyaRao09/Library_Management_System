import java.awt.*;
import java.awt.event.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.*;

import net.proteanit.sql.DbUtils;

import java.sql.Connection;

public class FinesDue extends JFrame
{
	JFrame mainFrame;
	JPanel controlPanel;
	static Connection conn=null;
	
	int loan_id=0;

	JTable table;
	
	FinesDue(String card_str) throws SQLException
	{
		prepareGUI(card_str);
	}
	
	void prepareGUI(String card_str) throws SQLException
	{
		
		int card=Integer.parseInt(card_str);
		
		mainFrame=new JFrame("Library Management System");
		mainFrame.setSize(500,500);
		mainFrame.setLocation(20, 50);
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		controlPanel=new JPanel();
		controlPanel.setBorder(new EmptyBorder(10,10,10,10));
		setContentPane(controlPanel);
		GridBagLayout gbl_controlPanel=new GridBagLayout();
		gbl_controlPanel.columnWidths=new int[]{0,0};
		gbl_controlPanel.rowHeights = new int[]{0, 0, 0};
		gbl_controlPanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_controlPanel.rowWeights = new double[]{0.0,0.0,1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		controlPanel.setLayout(gbl_controlPanel);
		
		
		//details
		JLabel details=new JLabel("Click on the desired row and then click Pay",JLabel.LEFT);
		details.setFont(new Font("Times New Roman", Font.PLAIN,16));
		GridBagConstraints gbc_details=new GridBagConstraints();
		gbc_details.insets=new Insets(0,0,5,0);
		gbc_details.gridx=0;
		gbc_details.gridy=0;
		gbc_details.gridwidth=2;
		controlPanel.add(details, gbc_details);
				
		//space
		JLabel space1=new JLabel(" ",JLabel.CENTER);
		//space2.setFont(new Font("Times New Roman", Font.PLAIN,14));
		GridBagConstraints gbc_space1=new GridBagConstraints();
		gbc_space1.insets=new Insets(0,0,5,0);
		gbc_space1.gridx=0;
		gbc_space1.gridy=1;
		gbc_space1.gridwidth=2;
		controlPanel.add(space1, gbc_space1);
						
		
		//close button
		JButton close= new JButton("Close");
		close.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false); 
				mainFrame.dispose();
			}
		});
		
		
		
		JLayeredPane layeredPane=new JLayeredPane();
		GridBagConstraints gbc_layeredPane = new GridBagConstraints();
		gbc_layeredPane.insets = new Insets(0, 0, 5, 0);
		gbc_layeredPane.fill = GridBagConstraints.BOTH;
		gbc_layeredPane.gridx = 0;
		gbc_layeredPane.gridy = 2;
		controlPanel.add(layeredPane, gbc_layeredPane);
		
		table=new JTable();
		JScrollPane scrollPane=new JScrollPane(table);
		scrollPane.setBounds(6,6,600,400);
		layeredPane.add(scrollPane);
		
		
		scrollPane.setViewportView(table);

		table.setColumnSelectionAllowed(false);;
		table.setRowSelectionAllowed(true);
		
		table.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent evnt)
			{
				if(evnt.getClickCount() ==1 || evnt.getClickCount()==2)
				{
					loan_id=(int)table.getModel().getValueAt(table.rowAtPoint(evnt.getPoint()), 0);
					setBackground(Color.blue);
				}
			}
		});


		//Pay
		JButton pay=new JButton("Pay Fine");
		pay.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				try {
					
					conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/proj1", "root", "sasi123");
					Statement stmt1=conn.createStatement();
					ResultSet rs=stmt1.executeQuery("Select * from proj1.book_loans where Loan_id="+loan_id+" and Date_in is null;");

					if(!rs.next())
					{
					conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/proj1", "root", "sasi123");
					Statement stmt2=conn.createStatement();
					stmt2.executeUpdate("Update proj1.fines set Paid=1 where Loan_id="+loan_id+";");
					JOptionPane.showMessageDialog(null, "Fines for Loan Id "+loan_id+" has been paid ");
					mainFrame.setVisible(false);
					}
					else
					{
						JOptionPane.showMessageDialog(null, "This book has not been returned");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		GridBagConstraints gbc_pay=new GridBagConstraints();
		gbc_pay.fill=GridBagConstraints.HORIZONTAL;
		gbc_pay.insets = new Insets(0, 0, 5, 0);
		gbc_pay.gridx = 0;
		gbc_pay.gridy = 3;
		gbc_pay.gridwidth=2;
		controlPanel.add(pay, gbc_pay);
	
		//space
		JLabel space2=new JLabel("  ",JLabel.CENTER);
		//searchLabel.setFont(new F);
		GridBagConstraints gbc_space2=new GridBagConstraints();
		gbc_space2.insets=new Insets(0,0,5,0);
		gbc_space2.gridx=0;
		gbc_space2.gridy=4;
		gbc_space2.gridwidth=2;
		controlPanel.add(space2, gbc_space2);

		
		GridBagConstraints gbc_btnClose = new GridBagConstraints();
		gbc_btnClose.fill=GridBagConstraints.HORIZONTAL;
		gbc_btnClose.insets = new Insets(0, 0, 5, 0);
		gbc_btnClose.gridx = 0;
		gbc_btnClose.gridy = 5;
		gbc_btnClose.anchor=GridBagConstraints.PAGE_END;
		controlPanel.add(close, gbc_btnClose);		
		try{		
			//jdbc connection to database
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/proj1", "root", "sasi123");
			Statement stmt=conn.createStatement();
			ResultSet rs= null;
			
			
			rs= stmt.executeQuery("Select f.Loan_id, b.Isbn, f.Fine_amt from proj1.fines as f left outer join proj1.book_loans as b on b.Loan_id=f.Loan_id where b.Card_id="+card+" and Paid=0;");

			table.setModel(DbUtils.resultSetToTableModel(rs));
			table.setEnabled(false);
			
			table.getColumnModel().getColumn(0).setPreferredWidth(150);
			table.getColumnModel().getColumn(1).setPreferredWidth(150);
			table.getColumnModel().getColumn(2).setPreferredWidth(150);
			//table.getColumnModel().getColumn(3).setPreferredWidth(100);
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
			}
			catch(SQLException e)
			{
				System.out.println(e.getMessage());
			}
		
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
		
	}
	}

