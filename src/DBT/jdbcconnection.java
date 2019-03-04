package DBT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class jdbcconnection {

	public static void main(String[] args) throws SQLException, InterruptedException {
		// TODO Auto-generated method stub
		String host = "localhost";
		String port = "3306";
		//create a connection with database
	
		Connection con = DriverManager.getConnection("jdbc:mysql://" + host + ":" +port+ "/demo?useSSL=false", "root", "Naresh123");
		//create a path so you can execute mysql query
		Statement s=con.createStatement();
		 ResultSet rs=s.executeQuery("select * from Credentials where id=101;");
		while(rs.next()){
		WebDriver driver = new FirefoxDriver();
		driver.get("http://newtours.demoaut.com/");
		driver.findElement(By.name("userName")).sendKeys(rs.getString("username"));
		driver.findElement(By.name("password")).sendKeys(rs.getString("password"));
		driver.findElement(By.name("login")).click();
		Thread.sleep(5000);
		driver.quit();
		}
	}

}
