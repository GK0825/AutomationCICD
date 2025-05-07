package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class MyCartPage extends AbstractComponents{
	
	WebDriver driver;
	
	public MyCartPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	//PageFactory
	@FindBy(css="[class=cart]")
	WebElement cart;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cart_items;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkout;
	
	public Boolean VerifyProductDisplay(String productName)
	{
		Boolean match=cart_items.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckoutPage goToCheckout()
	{
		checkout.click();
		return new CheckoutPage(driver); 
	}
	
	

}
