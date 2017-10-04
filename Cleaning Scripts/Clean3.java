import java.sql.*;

public class Clean3     {
	
	static Connection conn = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/proj1","root","sasi123");
			Statement stmt1=conn.createStatement();
			ResultSet rs1 = stmt1.executeQuery("select * from proj1.seperated");
			while(rs1.next()){
				
				String isbn=rs1.getString(1);
				String author=rs1.getString(2);
				
				if(!author.equals("none"))
				{
				Statement stmt2 = conn.createStatement();
				String queryCheck = "SELECT Author_id from proj1.authors WHERE Name= '" + author+"';";
				ResultSet rs2 = stmt2.executeQuery(queryCheck);
				if(rs2.absolute(1))
				{
					Statement stmt3 = conn.createStatement();
					String sql = "INSERT INTO proj1.book_authors VALUES("+rs2.getInt(1)+",'"+isbn+"');";
					stmt3.executeUpdate(sql);  
				}
				} 			
			}
			
		}
		catch(SQLException ex) {
			System.out.println("Error in connection: " + ex.getMessage());
		}
		System.out.println("done");


	}

}
