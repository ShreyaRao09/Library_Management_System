import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Clean2
{
	static Connection conn;
	
	public static void main(String[] args)
	{
		try
		{
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/proj1","root","sasi123");
			Statement stmt1=conn.createStatement();
			String query1="Select * from proj1.seperated;";
			ResultSet rs=stmt1.executeQuery(query1);
			int count=0;
			while(rs.next())
			{
				String author=rs.getString(2);
				
				if(!author.equals("none"))
				{
					Statement stmt2 = conn.createStatement();
					String queryCheck = "SELECT * from proj1.authors WHERE Name= '" + author+"';";
					ResultSet rs1 = stmt2.executeQuery(queryCheck);
					if(!(rs1.absolute(1)))
					{
						Statement stmt3 = conn.createStatement();
						ResultSet rs2 = stmt3.executeQuery("select max(author_id) from proj1.authors");
						int id=0;
						if(rs2.next())
						{				 	
						 id=rs2.getInt(1)+1;
						}
					  String sql = "INSERT INTO proj1.authors VALUES("+id+",'"+author+"');";
					  stmt2.executeUpdate(sql);
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
