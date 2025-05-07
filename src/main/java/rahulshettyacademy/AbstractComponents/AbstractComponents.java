package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.MyCartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponents {

	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*=cart]")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*=myorders]")
	WebElement orderHeader;

	public void waitForElementToAppear(By findby)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findby));
	}
	
	public void waitForWebElementToAppear(WebElement ele)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}
	
	public MyCartPage goToCartPage()
	{
		cartHeader.click();
		MyCartPage mcp = new MyCartPage(driver);
		return mcp;
		
	}
	
	public OrderPage goToOrdersPage()
	{
		orderHeader.click();
		OrderPage op = new OrderPage(driver);
		return op;
		
	}
	
	public void waitForElementToDisappear(WebElement element) throws InterruptedException
	{
		Thread.sleep(2000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//		wait.until(ExpectedConditions.invisibilityOf(element));
	}
}
