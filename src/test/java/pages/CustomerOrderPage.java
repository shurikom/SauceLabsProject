package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class CustomerOrderPage {
	
	
	
	public CustomerOrderPage(RemoteWebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="#ctl00_MainContent_fmwOrder_ddlProduct")
	public WebElement sctOptions;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_txtQuantity")
	public WebElement quantity;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_txtName")
	public WebElement name;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox2")
	public WebElement street;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox3")
	public WebElement city;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox4")
	public WebElement state;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox5")
	public WebElement zip;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox6")
	public WebElement cardNum;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_TextBox1")
	public WebElement expDate;
	
	@FindBy(css="#ctl00_MainContent_fmwOrder_cardList td>input")
	public List<WebElement> allChkBoxes;
	
	@FindBy(id="ctl00_MainContent_fmwOrder_InsertButton")
	public WebElement submitButton;
	
	
	
}








