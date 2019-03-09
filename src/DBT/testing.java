package DBT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

public class testing {
	// Connection object
	static Connection conn = null;
	// Statement object
	private static Statement stmt;
	// Result Set
	private static ResultSet results = null;
	// Constant for Database URL
	public static String DB_URL = "jdbc:mysql://localhost:3306/ciat"; // ORacle "jdbc:oracle:thin:@localhost:1521/sid"
	// Constant for Database Username
	public static String DB_USER = "root";
	// Constant for Database Password
	public static String DB_PASSWORD = "Naresh@123!";
	// Driver
	public static String dv = "com.mysql.jdbc.Driver"; // "oracle.jdbc.driver.OracleDriver"
	// WebDriver
	public static WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// Intialize WebDriver
		System.setProperty("webdriver.chrome.driver", "c:\\driver\\chromedriver.exe");
		driver = new ChromeDriver();
		
		// Properties for creating connection to database
		Properties props = new Properties();
		props.setProperty("user", "root");
		props.setProperty("password", "Naresh@123!");
	    
		try {
			// STEP 1: Register JDBC driver
			Class.forName(dv).newInstance();
			// STEP 2: Get connection to DB
			System.out.println("Connecting to a selected database...");
			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			// conn = DriverManager.getConnection(DB_URL, props);		
			System.out.println("Connected database successfully...");
			// STEP 3: Statement object to send the SQL statement to the Database
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test() throws SQLException {
        String query = "select * from credentialzerobank";
        try {
        	// STEP 4: Extract data from result set
        	results = stmt.executeQuery(query);
        	while (results.next()) {
        		String loginName = results.getString("uname");
        		String passWord = results.getString("pword");
        		
        		// Display Values
        		System.out.println("User Name" +loginName);
        		System.out.println("Password" + passWord);
        		
        		//From GUI
//        		WebElement element = dv.findElement(By.id("uname"));
//        		String actualUserName = element.getText();
//        		Assert.assertEquals(actualUserName, first);
            }
        	results.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		try {
			if (results != null)
				results.close();
			if (stmt != null)
				conn.close();
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}