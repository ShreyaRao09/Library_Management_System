import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.border.*;

import java.sql.Connection;

public class Search extends JFrame
{
	JFrame mainFrame;
	JPanel controlPanel;
	static Connection conn=null;
	
	Search() throws SQLException
	{
		prepareGUI();
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable(){
			public void run(){
				try{
					Search search=new Search();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		});
		
	}
	
	void prepareGUI() throws SQLException
	{
		mainFrame=new JFrame("Library Management System");
		mainFrame.setSize(500,300);
		//mainFrame.setBounds(100,100,1100,1000);
		//mainFrame.setLayout(new GridLayout(2,1));
		mainFrame.setLocation(20, 50);
		//mainFrame.pack();
		mainFrame.setMinimumSize(mainFrame.getSize());
		mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		controlPanel=new JPanel();
		controlPanel.setBorder(new EmptyBorder(10,10,10,10));
		setContentPane(controlPanel);
		GridBagLayout gbl_controlPanel=new GridBagLayout();
		gbl_controlPanel.columnWidths=new int[]{0,0};
		gbl_controlPanel.rowHeights = new int[]{0, 0, 0, 0, 0,0,0,0};
		gbl_controlPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_controlPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
		controlPanel.setLayout(gbl_controlPanel);
		
		
		JLabel searchLabel=new JLabel("Search for a book",JLabel.CENTER);
		searchLabel.setFont(new Font("Times New Roman",Font.BOLD,18));
		GridBagConstraints gbc_searchLabel=new GridBagConstraints();
		gbc_searchLabel.insets=new Insets(0,0,5,0);
		gbc_searchLabel.gridx=0;
		gbc_searchLabel.gridy=1;
		gbc_searchLabel.gridwidth=2;
		controlPanel.add(searchLabel, gbc_searchLabel);
		
		//Text Box
		JLabel textLabel = new JLabel("Enter the ISBN, Author name or Title",JLabel.LEFT);
		textLabel.setFont(new Font("Ariel",Font.PLAIN,14));
		
		GridBagConstraints gbc_textLabel=new GridBagConstraints();
		gbc_textLabel.insets=new Insets(0,0,5,0);
		gbc_textLabel.gridx=0;
		gbc_textLabel.gridy=2;
		gbc_textLabel.gridwidth=2;
		controlPanel.add(textLabel, gbc_textLabel);
		
		JLabel space1=new JLabel("  ",JLabel.CENTER);
		//searchLabel.setFont(new F);
		GridBagConstraints gbc_space1=new GridBagConstraints();
		gbc_space1.insets=new Insets(0,0,5,0);
		gbc_space1.gridx=0;
		gbc_space1.gridy=3;
		gbc_space1.gridwidth=2;
		controlPanel.add(space1, gbc_space1);

		
		JTextField searchText = new JTextField();
		
		searchText.setFont(new Font("Ariel",Font.PLAIN,12));
		searchText.setForeground(Color.black);
		GridBagConstraints gbc_searchText=new GridBagConstraints();
		gbc_searchText.fill=GridBagConstraints.HORIZONTAL;
		gbc_searchText.insets=new Insets(0,0,5,0);
		gbc_searchText.gridx=1;
		gbc_searchText.gridy=4;
		gbc_searchText.gridwidth=2;
		controlPanel.add(searchText, gbc_searchText);
		//controlPanel.add(searchText);
		searchText.setColumns(20);
		
		
		JButton search=new JButton("Search");
		search.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e) 
			{
			
				//if(searchText.getText().equals(""))
				//{
					//JOptionPane.showMessageDialog(null, "Please enter something in the text box");
				//}
				//else{
				//mainFrame.setVisible(false);
				try {
					new SearchedData(searchText.getText());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//}
			}
		});
		GridBagConstraints gbc_btnSearch=new GridBagConstraints();
		gbc_btnSearch.fill=GridBagConstraints.HORIZONTAL;
		gbc_btnSearch.insets = new Insets(0, 0, 5, 0);
		gbc_btnSearch.gridx = 1;
		gbc_btnSearch.gridy = 5;
		controlPanel.add(search, gbc_btnSearch);
		
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
		gbc_btnClose.gridx = 1;
		gbc_btnClose.gridy = 7;
		gbc_btnClose.anchor=GridBagConstraints.PAGE_END;
		controlPanel.add(close, gbc_btnClose);		

		mainFrame.add(controlPanel);
		mainFrame.setVisible(true);
	}
}