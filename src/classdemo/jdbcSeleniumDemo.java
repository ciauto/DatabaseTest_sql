package classdemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class jdbcSeleniumDemo {

	public static void main(String[] args) throws SQLException {
		
		//connect our script to mySQL database 
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ciat?useSSL=false", "root", "Naresh@123!");
		
		//create a path to database connection 
		Statement s=con.createStatement();
		// create a query and execute query
		ResultSet rs=s.executeQuery("Select * from credentialzerobank;");
		while(rs.next()) {
		System.setProperty("webdriver.chrome.driver", "c:\\driver\\chromedriver.exe");
		WebDriver driver=new ChromeDriver();
		driver.get("http://zero.webappsecurity.com/");
		driver.findElement(By.id("signin_button")).click();
		driver.findElement(By.id("user_login")).sendKeys(rs.getString("uname"));
		driver.findElement(By.id("user_password")).sendKeys(rs.getString("pword"));
		driver.findElement(By.name("submit")).click();
		driver.close();
		}
		
		
		
		
	}

}
