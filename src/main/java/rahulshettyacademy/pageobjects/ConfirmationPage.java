package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class ConfirmationPage extends AbstractComponents{

    WebDriver driver;
	
	public ConfirmationPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement confirmationMessage;
	
	@FindBy(css=".ng-star-inserted:nth-child(3) td label")
	WebElement orderDetails;
	
	public String getConfirmationMessage()
	{
		String orderConfirmation = confirmationMessage.getText();
		return orderConfirmation;
	}
	
	public String getOrderDetails()
	{
		String orderId = orderDetails.getText();
		return orderId;
	}
}
