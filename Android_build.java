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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Android_build {
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
	
	String build = null;
	
	System.setProperty("webdriver.chrome.driver","/Users/mngdn/eclipse-workspace/chromedriver");
	
	WebDriver driver = new ChromeDriver();
    driver.get("https://electric-company.integ.amazon.com/");
    driver.manage().window().maximize();
    Thread.sleep(5000);
    driver.findElement(By.xpath("//a[contains(text(),'Search')]")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//a[@class='ui-collapsible-heading-toggle ui-btn ui-icon-plus ui-btn-icon-left ui-btn-d'][contains(text(),'Filters')]")).click();
    driver.findElement(By.xpath("//input[@id='search_artifact_name']")).sendKeys("PH7Debug.app");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//input[@id='search_version_set_full_name']")).sendKeys("PH7-Tangelo/release-21.6");
    Thread.sleep(2000);
    driver.findElement(By.xpath("//div[@class='ui-btn ui-input-btn ui-btn-e ui-corner-all ui-shadow ui-btn-inline']//input[@name='commit']")).click();
    Thread.sleep(2000);
    driver.findElement(By.xpath("//ul[@class='ui-listview ui-listview-inset ui-corner-all ui-shadow ui-group-theme-a']//li[2]//a[1]")).click();
    Thread.sleep(3000);
    WebElement elementLocator = driver.findElement(By.linkText("Download IPA"));
    String linkLocatin = elementLocator.getAttribute("href");
    System.out.println("Download Link "+linkLocatin);	
    try {        	
    	WebElement sev = driver.findElement(By.xpath("//h4[contains(text(),'Package Name Full Version:')]"));  	
    	Boolean flag = sev.isDisplayed();
    	   	
    	if (flag)
    		
    		build = driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div/ul/li[3]/p")).getText();
    	
    	
    	}
    	
    	catch (Exception e)
    	{
    		build ="-";
    		    		
    	}
    		
    		email.append("Hi Team,");
            email.append("<br />" + "<br />");
            email.append("There is a new build in Electric company. Please find the details and download link below,");
            email.append("<br />" + "<br />");
            email.append("iOS Build Check:" + " " + dateFormat.format(date)
                    + " ");
            email.append("<br />" + "<br />");
            email.append("Build Details: "+build);
            email.append("<br />" + "<br />");
            email.append("Download link: "+linkLocatin);
            email.append("<br />" + "<br />");
            email.append("Regards,");
            email.append("<br />");
            email.append("Pramodh");
            email.append("<br />" + "<br />");
            email.append("<html><table>");
            email.append("<tr>");
            mail(email);
    
} 

public static void mail(StringBuilder email) throws AddressException,
MessagingException {
Properties properties = System.getProperties();
properties.setProperty("mail.smtp.host", "smtp.amazon.com");
Session session = Session.getDefaultInstance(properties);
MimeMessage message = new MimeMessage(session);
message.setFrom(new InternetAddress("no-reply-Automated-BuildNotifier@amazon.com"));
// to address for email
message.addRecipient(Message.RecipientType.TO, new InternetAddress("mngdn@amazon.com"));
//cc address for email
//message.addRecipient(Message.RecipientType.CC, new InternetAddress("mprathee@amazon.com"));
//message.addRecipient(Message.RecipientType.CC, new InternetAddress("reddyv@amazon.com"));
//message.addRecipient(Message.RecipientType.CC, new InternetAddress("kosoba@amazon.com"));
message.setSubject("iOS New Build");
message.setContent(email.toString(), "text/html");
Transport.send(message);
email.delete(0, email.length()); 
}

}
