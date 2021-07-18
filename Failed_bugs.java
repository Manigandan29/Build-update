package Jira_bug;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

public class Failed_bugs {
	static StringBuilder email = new StringBuilder();
    static StringBuilder empty = new StringBuilder();
	static final String TDHEDERCENTER = "<td style='padding:8px;border: 1px solid black; background-color:lightblue; text-align: center; font-family: \"Trebuchet MS\"; font-weight: bold; font-size: 13px;'>";
	static final String TD = "<td style='padding:5px;border: 1px solid black; text-align: center; font-family: \"Trebuchet MS\"; font-size: 13.5px;'  >";
	static String TDBugType = "BugType";
    static final String green ="<p style='color:forestgreen;'> &#10004;</p>" ;
    static final String red = "<p style='color:red;'> &#10008;</p>" ;
    static DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    static Date date = new Date();
public static void main(String[] args) throws Exception{
	
	
	String Bugid;
	String Priority;
	String Title;
	String Reporter;
	String Severity = null;
	String qwerty = null;
	Boolean flag ;
	
	
	System.setProperty("webdriver.chrome.driver","/Users/mngdn/eclipse-workspace/chromedriver");
	
	WebDriver driver = new ChromeDriver();
    driver.get("https://quality-assistance.raspberrycorvette.net/index.php?/auth/login");
    driver.manage().window().maximize();
    Thread.sleep(5000);
    driver.findElement(By.id("//*[@id=\"name\"]")).sendKeys("mngdn@amazon.com");
    driver.findElement(By.id("//*[@id=\"password\"]")).sendKeys("Rahul@0974");
    driver.findElement(By.xpath("//button[contains(text(),'Log in')]")).click();
}
}
