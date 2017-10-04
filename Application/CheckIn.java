import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class CheckIn extends JFrame
{
	JFrame mainFrame;
	JPanel controlPanel;
	
	CheckIn()
	{
		prepareGUI();
	}
	
	public static void main(String[] args)
	{
		CheckIn checkIn=new CheckIn();
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
		gbl_controlPanel.rowHeights = new int[]{0, 0, 0,0,0,0,0};
		gbl_controlPanel.columnWeights = new double[]{Double.MIN_VALUE, Double.MIN_VALUE};
		gbl_controlPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
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
		JLabel lblIsbn=new JLabel("                 ISBN :",JLabel.LEFT);
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
		JLabel lblcard=new JLabel("           Card No. :",JLabel.RIGHT);
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
		
		//Borrower
		JLabel lblBorrower=new JLabel("Borrower Name :",JLabel.RIGHT);
		lblBorrower.setFont(new Font("Times New Roman",Font.BOLD,14));
		GridBagConstraints gbc_lblBorrower=new GridBagConstraints();
		gbc_lblBorrower.insets=new Insets(0,0,5,0);
		gbc_lblBorrower.gridx=0;
		gbc_lblBorrower.gridy=4;
		//gbc_lblIsbn.gridwidth=2;
		controlPanel.add(lblBorrower, gbc_lblBorrower);
		
		//Borrower Text Box
		JTextField borrowerText = new JTextField();
		
		borrowerText.setFont(new Font("Ariel",Font.PLAIN,14));
		borrowerText.setForeground(Color.black);
		GridBagConstraints gbc_borrowerText=new GridBagConstraints();
		gbc_borrowerText.fill=GridBagConstraints.HORIZONTAL;
		gbc_borrowerText.insets=new Insets(0,0,5,0);
		gbc_borrowerText.gridx=1;
		gbc_borrowerText.gridy=4;
		//gbc_isbnText.gridwidth=2;
		controlPanel.add(borrowerText, gbc_borrowerText);
		//controlPanel.add(searchText);
		isbnText.setColumns(15);
		
		
		JButton checkOut=new JButton("Check In");
		//checkOut.setLocation(50, 50);
		checkOut.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(isbnText.getText().equals("") && cardText.getText().equals("") && borrowerText.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Please enter something in any the text box");
				}
				else{
				try {
	
				new   CheckInList(isbnText.getText(),cardText.getText(),borrowerText.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
			}
		});
		GridBagConstraints gbc_btnCheckOut=new GridBagConstraints();
		gbc_btnCheckOut.fill=GridBagConstraints.HORIZONTAL;
		gbc_btnCheckOut.insets = new Insets(0, 0, 5, 0);
		gbc_btnCheckOut.gridx = 0;
		gbc_btnCheckOut.gridy = 5;
		gbc_btnCheckOut.gridwidth=2;
		controlPanel.add(checkOut,gbc_btnCheckOut);
		
		JLabel space2=new JLabel("  ",JLabel.CENTER);
		//searchLabel.setFont(new F);
		GridBagConstraints gbc_space2=new GridBagConstraints();
		gbc_space2.insets=new Insets(0,0,5,0);
		gbc_space2.gridx=0;
		gbc_space2.gridy=6;
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
		gbc_btnClose.gridy = 7;
		gbc_btnClose.anchor=GridBagConstraints.PAGE_END;
		gbc_btnClose.gridwidth=2;
		controlPanel.add(close, gbc_btnClose);		

		
		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
	}
}
