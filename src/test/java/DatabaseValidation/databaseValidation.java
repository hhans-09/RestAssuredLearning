package DatabaseValidation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class databaseValidation {

	
	public static void main(String[] args) throws SQLException  {
		
		
		String insert_query = "insert into users values(101,'Username','password')";
		
		executeQuery(insert_query);
		
		String select_query = "select uname from users";
		ResultSet rs = executeSelectQuery(select_query);
		
		while(rs.next()) {
			String username = rs.getString("uname");
			String password = rs.getString("password");
			
			
			System.out.print("username is "+username );
			System.out.println("password is "+password );
		}
		

	}
	
	
	public static void executeQuery(String query) throws SQLException {
		
		Connection con = DriverManager.getConnection("url","username","password");
		
		Statement stmt = con.createStatement();
		
		
		stmt.executeQuery(query);
		
		con.close();
		
		
	}
	
	public static ResultSet executeSelectQuery(String query) throws SQLException {
		
		Connection con = DriverManager.getConnection("url","username","password");
		
		Statement stmt = con.createStatement();
		
		
		ResultSet rs = stmt.executeQuery(query);
		
		con.close();
		
		return rs;
		
		
	}

}
