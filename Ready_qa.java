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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.WebElement;


public class Ready_qa {

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
	String Title;
	String Requester;
	String Fixversion= null;
	//String qwerty = null;
	Boolean flag ;
	
	
	System.setProperty("webdriver.chrome.driver","/Users/mngdn/eclipse-workspace/chromedriver");
	
	WebDriver driver = new ChromeDriver();
    driver.get("https://project-management.raspberrycorvette.net/login.jsp");
    driver.manage().window().maximize();
    Thread.sleep(5000);
    driver.findElement(By.xpath(".//*[@id='login-form-username']")).sendKeys("mngdn");
    driver.findElement(By.xpath(".//*[@id='login-form-password']")).sendKeys("Rahul@0974");
   
    driver.findElement(By.id("login-form-submit")).click();
    driver.findElement(By.id("find_link")).click();
    Thread.sleep(5000);
    driver.findElement(By.id("issues_new_search_link")).click();
    Thread.sleep(2000);
    //driver.findElement(By.xpath("//div[@data-id='basic']")).click();
    String searchtypeText = driver.findElement(By.xpath("//a[contains(@class,'switcher-item active')]")).getText();
    System.out.println("Search Type Text is " +searchtypeText);
    
    if(searchtypeText.equalsIgnoreCase("Advanced"))
    {
    	driver.findElement(By.xpath("//a[contains(@class,'switcher-item active')]")).click();
    	  driver.findElement(By.id("advanced-search")).sendKeys("status = \"Ready for QA\" AND reporter in (mngdn, samili, kabilaa, tirumv, pramodhv, tnswathi, reddyv, kosoba)");
    }	

    else
    	 driver.findElement(By.id("advanced-search")).sendKeys("status = \"Ready for QA\" AND reporter in (mngdn, samili, kabilaa, tirumv, pramodhv, tnswathi, reddyv, kosoba)");
//    Thread.sleep(5000);
    
   
    Thread.sleep(2000);
    driver.findElement(By.xpath("//button[@class='aui-button aui-button-primary search-button']")).click();
    
    
    		Thread.sleep(5000);
    		
    		int size = driver.findElements(By.xpath("//span[@class='issue-link-key']")).size();
    		email.append("Hi Team,");
            email.append("<br />" + "<br />");
            email.append("These are the bugs which is Ready for QA,");
            email.append("<br />" + "<br />");
            email.append("Bug Report for" + " " + dateFormat.format(date)
                    + " ");
            email.append("<br />" + "<br />");
            email.append("<html><table>");
            email.append("<tr>");
            //Appending header
            email.append("<body> <div data-role='main' class='ui-content'> <div data-role='collapsible'> <style type='text/css'><table style='border-collapse: collapse; border: 1px solid black;'> <tr>"
                    + TDHEDERCENTER
                    + "BugID </td>"
                    + TDHEDERCENTER
                    + "Title </td>"
                    + TDHEDERCENTER
                    + "Fixversion</td>"
                    + TDHEDERCENTER
                    + "Requester </td><tr>");
           
                  
    	for (int i=1; i<=size; i++) 
    	{
    		driver.findElement(By.xpath("//*[@class ='issue-list']//li["+i+"]")).click();
        	Thread.sleep(3000);
        	System.out.println(size);	
        	Bugid = driver.findElement(By.id("key-val")).getText();
        	System.out.println(Bugid);
  
    	try {        	
    	WebElement bugtype = driver.findElement(By.xpath("//strong[contains(text(),'Fix Version/s:')]"));  	
    	flag = bugtype.isDisplayed();
    	   	
    	if (flag)
    		
    	  Fixversion  = driver.findElement(By.xpath("//span[@id='fixfor-val']")).getText();
    	
    	
    	}
    	
    	catch (Exception e)
    	{
    		Fixversion ="-";
    		    		
    	}
    	
    	
    
    	Title = driver.findElement(By.id("summary-val")).getText();
    	System.out.println(Title);
    	Requester = driver.findElement(By.xpath(".//ul[1]/li[1]/dl[2]/dd[1]/span[1]/span[1]")).getText();
    	System.out.println(Requester);
    	
    

        email.append(TD);

        email.append("<a href=\"" + "https://project-management.raspberrycorvette.net/browse/" + Bugid
                + "\" target=>" + Bugid + "</a> ");
        email.append("</td>");

        email.append(TD);
        email.append(Title);
        email.append("</td>");
        
        email.append(TD);            
        email.append(Fixversion);
        email.append("</td>");

        email.append(TD);
        email.append(Requester);
        email.append("</td>");

        email.append("</tr>");
		
    		
    	}	
    	
    	email.append("</div></body></table></html>");
        email.append("<br />" + "<br />");
        mail(email);
    
} 

public static void mail(StringBuilder email) throws AddressException,
MessagingException {
Properties properties = System.getProperties();
properties.setProperty("mail.smtp.host", "smtp.amazon.com");
Session session = Session.getDefaultInstance(properties);
MimeMessage message = new MimeMessage(session);
message.setFrom(new InternetAddress("no-reply-bug-report@amazon.com"));
// to address for email
message.addRecipient(Message.RecipientType.TO, new InternetAddress("mngdn@amazon.com"));
//cc address for email
//message.addRecipient(Message.RecipientType.CC, new InternetAddress("mprathee@amazon.com"));
//message.addRecipient(Message.RecipientType.CC, new InternetAddress("reddyv@amazon.com"));
//message.addRecipient(Message.RecipientType.CC, new InternetAddress("kosoba@amazon.com"));
message.setSubject("Ready for QA Bugs");
message.setContent(email.toString(), "text/html");
Transport.send(message);
email.delete(0, email.length()); 
}
}

