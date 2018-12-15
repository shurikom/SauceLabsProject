//package pomdesign;
//
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Ignore;
//import org.testng.annotations.Test;
//
//import io.github.bonigarcia.wdm.WebDriverManager;
//import junit.framework.Assert;
//import pages.AllOrdersPage;
//import pages.WebOrderLoginPage;
//
//import java.util.concurrent.TimeUnit;
//
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//
//
//public class WebOrderLoginTest {
//	
//	WebDriver driver = null;
//	
//	
//	@BeforeTest
//	public void setUp() {
//		WebDriverManager.chromedriver().setup();
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
//		driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
//	}
//	
//	@AfterTest
//	public void afterTest() {
//		driver.quit();
//	}
//	
//	@Ignore
//	@Test
//	public void login() {
//		driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
//		driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
//		driver.findElement(By.id("ctl00_MainContent_login_button")).click();
//		
//	}
//	@Ignore
//	@Test
//	public void negativeLogin() {
//		//creating an instance from page
//		WebOrderLoginPage loginPage = new WebOrderLoginPage(driver);
//		loginPage.userName.sendKeys("Test");
//		loginPage.password.sendKeys("tester");
//		loginPage.loginButton.click();
//		String errTxt = loginPage.error.getText();
//		Assert.assertTrue(loginPage.error.isDisplayed());
//		Assert.assertEquals("Invalid Login", errTxt);
//		
//	}
//	
//	@Test
//	public void loginUsingPom() {
//		
//		WebOrderLoginPage loginPage = new WebOrderLoginPage(driver);
//		loginPage.userName.sendKeys("Tester");
//		loginPage.password.sendKeys("test");
//		loginPage.loginButton.click();
//		Assert.assertEquals("Web Orders", driver.getTitle());
//		
//	}
//
//
//}
//
//
//
//
//
//
//
//
//
