package classdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class jdbcconnectiondemo {

	public static void main(String[] args) throws SQLException {
		//connect our script to mySQL database 
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/demo?useSSL=false", "root", "Naresh@123!");
		
		
		
		//create a path to database connection 
		Statement s=con.createStatement();
		// create a query and execute query
		ResultSet rs=s.executeQuery("Select * from logincredential;");
		while(rs.next()) {
		System.out.println(rs.getString("username"));
		System.out.println(rs.getString("password"));
		}
}
}
