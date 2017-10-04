import java.sql.*;

public class Clean1
{
	static Connection conn;
	
	public static void main(String[] args)
	{
		try
		{
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/proj1","root","sasi123");
			Statement stmt1=conn.createStatement();
			String query1="Select ISBN13, Author from proj1.books_data;";
			ResultSet rs=stmt1.executeQuery(query1);
			int count=0;
			while(rs.next())
			{
				
				String isbn=rs.getString(1);
				String authors=rs.getString(2);
				String author[]=authors.split(",");
				count++;
				for(int i=0;i<author.length;i++)
				{
					System.out.println("Line no : " + count );
					boolean c=true;
					for(int j=0;j<i;j++)
					{
						if(author[i].equals(author[j]))
							c=false;
					}
					
					if(c)
					{
						
						String query2="Insert into proj1.separated values('"+isbn+"','"+author[i]+"')";
						Statement stmt2=conn.createStatement();
						stmt2.executeUpdate(query2);
						
					}
				}				
			}
			conn.close();
		}
		catch(SQLException e)
		{
			System.out.println("Error in connection:" + e.getMessage());
		}
	
	}
}
