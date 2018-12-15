package pomdesign;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.Assert;
import pages.AllOrdersPage;
import pages.CustomerOrderPage;
import pages.ProductsPage;
import pages.WebOrderLoginPage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;


public class WebOrderTest {
	
	public static final String USERNAME = "shurikom";
    public static final String ACCESS_KEY = "7f73b1a8-5f83-4c1c-9bf5-4bd0e23b6780";
    public static final String URL = "http://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:80/wd/hub";
	
	
    RemoteWebDriver driver;
	String userId = "Tester";
	
	WebOrderLoginPage loginPage;
	AllOrdersPage ordersPage;
	ProductsPage productPage;
	CustomerOrderPage custOrderPage;
	
	
	@BeforeTest
	public void setUp() throws MalformedURLException {
		DesiredCapabilities caps = DesiredCapabilities.chrome();
		caps.setCapability("platform", "Windows 7");
		caps.setCapability("version", "56.0");
		
		driver = new RemoteWebDriver(new URL(URL), caps);
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
	}
	
	@AfterTest
	public void afterTest() throws MalformedURLException {

		driver.close();
		driver.quit();
	}
	//@Ignore
	@Test(priority=1)
	public void checkLinks() throws MalformedURLException {

		
		ordersPage = new AllOrdersPage(driver);
		loginPage = new WebOrderLoginPage(driver);
		productPage = new ProductsPage(driver);
		
		Assert.assertEquals("Web Orders Login",driver.getTitle());
		
		loginPage.userName.sendKeys(userId);
		loginPage.password.sendKeys("test");
		loginPage.loginButton.click();
		Assert.assertEquals(driver.getTitle(),"Web Orders");
		
		Assert.assertEquals(ordersPage.welcomeMsg.getText().replace(" | Logout", ""), "Welcome, "+userId+"!");
		
		Assert.assertTrue("List of All Orders Not Displayed",ordersPage.listOfAllOrders.isDisplayed());
		Assert.assertTrue("Order Link is Not Displayed",ordersPage.orderLink.isDisplayed());
		Assert.assertTrue("View of All Orders Not Displayed",ordersPage.ViewOrdersLink.isDisplayed());
		Assert.assertTrue("List of All Products Not Displayed",ordersPage.ViewProductsLink.isDisplayed());
		Assert.assertTrue("Order Not Displayed",ordersPage.webOrder.isDisplayed());
		
		ordersPage.logoutMethod();
	}
	//@Ignore
	@Test(priority=2)
	public void ProductTest() throws InterruptedException, MalformedURLException {

		
		ordersPage = new AllOrdersPage(driver);
		loginPage = new WebOrderLoginPage(driver);
		productPage = new ProductsPage(driver);
		
		Assert.assertEquals(driver.getTitle(),"Web Orders Login");
		
		loginPage.userName.sendKeys(userId);
		loginPage.password.sendKeys("test");
		loginPage.loginButton.click();
		
		ordersPage.ViewProductsLink.click();
		Assert.assertEquals(driver.getTitle(),"Web Orders");
		List<String> actProducts = new ArrayList<>();
		List<String> expectedProducts = Arrays.asList("MyMoney","FamilyAlbum","ScreenSaver");
		
		
		for (WebElement each : productPage.productNames) {
			actProducts.add(each.getText());
		}
		System.out.println(actProducts);
		Assert.assertEquals(actProducts, expectedProducts);
		
		for(WebElement row : productPage.productRow) {
			
			String[] productData = row.getText().split(" ");
			
			switch(productData[0]) {
			case "MyMoney":
				Assert.assertEquals("$100", productData[1]);
				Assert.assertEquals("8%", productData[2]);
				break;
			case "FamilyAlbum":
				Assert.assertEquals("$80", productData[1]);
				Assert.assertEquals("15%", productData[2]);
				break;
			case "ScreenSaver":
				Assert.assertEquals("$20", productData[1]);
				Assert.assertEquals("10%", productData[2]);
			}
			System.out.println(row.getText());
		}
		ordersPage.logoutMethod();
	}
	//@Ignore
	@Test(priority=3)
	public void customerOrder() throws IOException, InterruptedException {
		

		
		ordersPage = new AllOrdersPage(driver);
		loginPage = new WebOrderLoginPage(driver);
		custOrderPage = new CustomerOrderPage(driver);
		Properties prop = new Properties();
		
		FileInputStream ip = new FileInputStream("/Users/owner/Desktop/Selenium/testing-maven/selenium-maven-pom/src/test/resources/customer.properties");
		prop.load(ip);
		
		
		
		loginPage.userName.sendKeys("Tester");
		loginPage.password.sendKeys("test");
		loginPage.loginButton.click();
		
		ordersPage.orderLink.click();
		//Thread.sleep(2000);
		Assert.assertEquals(prop.getProperty("url"), driver.getCurrentUrl());
		
		Select slct = new Select(custOrderPage.sctOptions);
		for(int i=1; i<4; i++) {
			slct.selectByIndex(i-1);
	custOrderPage.quantity.sendKeys(prop.getProperty("Quantity"+i));	
		custOrderPage.name.sendKeys(prop.getProperty("Name"+i));
		custOrderPage.street.sendKeys(prop.getProperty("Street"+i));
		custOrderPage.city.sendKeys(prop.getProperty("City"+i));
		custOrderPage.state.sendKeys(prop.getProperty("State"+i));
		custOrderPage.zip.sendKeys(prop.getProperty("Zip"+i));
		
		custOrderPage.allChkBoxes.get(i-1).click();
		
		custOrderPage.cardNum.sendKeys(prop.getProperty("CardNum"+i));
		custOrderPage.expDate.sendKeys(prop.getProperty("ExpDate"+i));
		custOrderPage.submitButton.click();
		}
	
		ordersPage.ViewOrdersLink.click();


	
    SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
    Date date = new Date();
   //Avoid using the abbreviations when fetching time zones.
  // Use the full Olson zone ID instead.
    sd.setTimeZone(TimeZone.getTimeZone("IST"));//Indian Time
    System.out.println(sd.format(date));
        
	
	for(int i=1; i<ordersPage.allRows.size(); i++) {
		List<String> actCellData = new ArrayList<>();
		if(i==1) {
			List<String> expCellData = Arrays.asList(prop.getProperty("Name"+(i+2))+" "+prop.getProperty("Product"+(i+2))+" "+prop.getProperty("Quantity"+(i+2))+" "+sd.format(date)+" "+prop.getProperty("Street"+(i+2))+" "+prop.getProperty("City"+(i+2))+" "+prop.getProperty("State"+(i+2))+" "+prop.getProperty("Zip"+(i+2))+" "+prop.getProperty("CardType"+(i+2))+" "+prop.getProperty("CardNum"+(i+2))+" "+prop.getProperty("ExpDate"+(i+2)));
actCellData.add(ordersPage.allRows.get(i).getText());
Assert.assertEquals(expCellData, actCellData);			
		}else if(i==2) {
			List<String> expCellData = Arrays.asList(prop.getProperty("Name"+i)+" "+prop.getProperty("Product"+i)+" "+prop.getProperty("Quantity"+i)+" "+sd.format(date)+" "+prop.getProperty("Street"+i)+" "+prop.getProperty("City"+i)+" "+prop.getProperty("State"+i)+" "+prop.getProperty("Zip"+i)+" "+prop.getProperty("CardType"+i)+" "+prop.getProperty("CardNum"+i)+" "+prop.getProperty("ExpDate"+i));
actCellData.add(ordersPage.allRows.get(i).getText());
Assert.assertEquals(expCellData, actCellData);			
		}else if(i==3) {
			List<String> expCellData = Arrays.asList(prop.getProperty("Name"+(i-2))+" "+prop.getProperty("Product"+(i-2))+" "+prop.getProperty("Quantity"+(i-2))+" "+sd.format(date)+" "+prop.getProperty("Street"+(i-2))+" "+prop.getProperty("City"+(i-2))+" "+prop.getProperty("State"+(i-2))+" "+prop.getProperty("Zip"+(i-2))+" "+prop.getProperty("CardType"+(i-2))+" "+prop.getProperty("CardNum"+(i-2))+" "+prop.getProperty("ExpDate"+(i-2)));
actCellData.add(ordersPage.allRows.get(i).getText());
Assert.assertEquals(expCellData, actCellData);			
		}
		
	}
  }

}
