package personal.praveen.projects.StockManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseOps {
	
	private final String DB_NAME = "stockmanager";
	private final String DB_USER_NAME = "****";
	private final String DB_PASSWORD = "***";
	private final String DB_URL = "localhost:3306/";
	private Connection con = null;
	
	public Connection connectToDB() throws SQLException, ClassNotFoundException {
		if(con != null)
			return con;
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection(  
			"jdbc:mysql://"+DB_URL+DB_NAME,DB_USER_NAME,DB_PASSWORD);  
		return con;
//		Statement stmt=con.createStatement();  
//			ResultSet rs=stmt.executeQuery("select * from emp");  
//			while(rs.next())  
//			System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
//			con.close();     
	}
	
	private void closeDBConnection() throws SQLException {
		if(con != null)
			con.close();
	}
	
	
}
