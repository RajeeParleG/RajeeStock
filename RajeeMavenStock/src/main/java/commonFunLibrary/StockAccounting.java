package commonFunLibrary;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class StockAccounting
{
	WebDriver driver;
	String res;
	  //app launch
	
	public String applaunch(String url)
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\rajesh.k\\workspace\\RajeeMavenStock\\ExecutableFiles\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		//validation
		if(driver.findElement(By.id("username")).isDisplayed())
		{
			res="pass";
		}else
		{
			res="fail";
		}
		return res;
	}
	
	//app login
	public String appLogin(String username, String password)
	{
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("btnsubmit")).click();
		//validation
		if(driver.findElement(By.id("logout")).isDisplayed())
		{
			res="pass";
		}else
		{
			res="fail";
		}
		return res;
	}
	
	//appLogout
	
	public String appLogout() throws Throwable
	{
		Thread.sleep(2000);
		driver.findElement(By.id("logout")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='OK!']")).click();
		Thread.sleep(2000);
		//validation
				if (driver.findElement(By.id("username")).isDisplayed()) 
				{
					
					res="Pass";
				}else
				{
					
					res="Fail";
				}
				
				return res;
				}
	
    //appClose
	
	public void appClose()
	{
		driver.close();
	}
	
	//supplierCreation
	
	public String SupplierCreation(String sName, String address, String city, String country, String cP, String phNo, String eMail, String mNo, String Notes) throws InterruptedException
	{
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='mi_a_suppliers']")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a")).click();
		Thread.sleep(2000);
		String exp_data=driver.findElement(By.id("x_Supplier_Number")).getAttribute("value");
		Thread.sleep(2000);
		System.out.println(exp_data);
		Thread.sleep(2000);
		driver.findElement(By.id("x_Supplier_Name")).sendKeys(sName);
		driver.findElement(By.id("x_Address")).sendKeys(address);
		driver.findElement(By.id("x_City")).sendKeys(city);
		driver.findElement(By.id("x_Country")).sendKeys(country);
		driver.findElement(By.id("x_Contact_Person")).sendKeys(cP);
		driver.findElement(By.id("x_Phone_Number")).sendKeys(phNo);
		driver.findElement(By.id("x__Email")).sendKeys(eMail);
		driver.findElement(By.id("x_Mobile_Number")).sendKeys(mNo);
		driver.findElement(By.id("x_Notes")).sendKeys(Notes);
		 //Scroll down the page
		  Actions action=new Actions(driver);
		  action.sendKeys(Keys.PAGE_DOWN).build().perform(); 
		Thread.sleep(2000);
		driver.findElement(By.id("btnAction")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[text()='OK!']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[text()='OK'])[6]")).click();
		
		//validation
		if(driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).isDisplayed())
		{
			driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();
			Thread.sleep(2000);
			driver.findElement(By.id("psearch")).clear();
			driver.findElement(By.id("psearch")).sendKeys(exp_data);
			driver.findElement(By.id("btnsubmit")).click();
	}else
	{
		driver.findElement(By.id("psearch")).clear();
		driver.findElement(By.id("psearch")).sendKeys(exp_data);
		driver.findElement(By.id("btnsubmit")).click();
	}
	String act_data=driver.findElement(By.xpath("//*[@id='el1_a_suppliers_Supplier_Number']/span")).getText();
	System.out.println(act_data);
	
	if(exp_data.equals(act_data))
	{
		res="Pass";
	}
	else{
		res="Fail";
	}
	driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();
	return res;
	}

	public static void main(String[] args) throws Throwable 
	{
		StockAccounting ra=new StockAccounting();
		/*//aap launch
		System.out.println(ra.applaunch("http://webapp.qedge.com"));
		ra.appClose();
		
		//app login
		System.out.println(ra.applaunch("http://webapp.qedge.com"));
		System.out.println(ra.appLogin("admin", "master"));
		//app logout
		System.out.println(ra.appLogout());
		ra.appClose();
		*/
		//supplierCreation
		System.out.println(ra.applaunch("http://webapp.qedge.com"));
		System.out.println(ra.appLogin("admin", "master"));
		System.out.println(ra.SupplierCreation("rajee", "tilaknagar", "hyderabad", "india", "parleg", "9848012345", "iamme@gmail.com", "27272727", "Never Give UP "));
		System.out.println(ra.appLogout());
		ra.appClose();
	}

}
