package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents{
	
	WebDriver driver;
	
	public OrderPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	//PageFactory
	@FindBy(css="[class=cart]")
	WebElement cart;
		
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	public Boolean VerifyOrderDisplay(String productName)
	{
		Boolean match=productNames.stream().anyMatch(s->s.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	

}
