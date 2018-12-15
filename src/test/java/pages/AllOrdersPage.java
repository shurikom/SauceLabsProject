package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AllOrdersPage {

	
	//constructor with the same className
	public AllOrdersPage(RemoteWebDriver driver) {
		
		PageFactory.initElements(driver, this);
		

	}
	
	
	@FindBy(css="#aspnetForm>table>tbody>tr>td> h1")
	public WebElement webOrder;
	
	@FindBy(css="#aspnetForm>table>tbody>tr>td li:nth-child(1)")
	public WebElement ViewOrdersLink;
	
	@FindBy(css="#aspnetForm>table>tbody>tr>td li:nth-child(2)")
	public WebElement ViewProductsLink;

	
	@FindBy(css="#aspnetForm>table>tbody>tr>td li:nth-child(3)")
	public WebElement orderLink;
	
	
	@FindBy(css=".content>h2")
	public WebElement listOfAllOrders;
	
	@FindBy(css=".login_info")
	public WebElement welcomeMsg;
	
	@FindBy(id="ctl00_logout")
	public WebElement logOutLink;
	
	@FindBy(xpath="//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[position()<=4]")
	public List<WebElement> allRows;
	//creating function
	public void logoutMethod() {
		logOutLink.click();
	}
	
	
}
