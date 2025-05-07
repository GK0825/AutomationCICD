package rahulshettyacademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponents;

public class CheckoutPage extends AbstractComponents{
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver){
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	//PageFactory
	@FindBy(css="[placeholder='Select Country']")
	WebElement countryTextbox;
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cart_items;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkout;
	
	@FindBy(css=".ta-results button")
	List<WebElement> countries;
	
	By results = By.cssSelector(".ta-results");
	
	@FindBy(css=".action__submit")
	WebElement submitButton;
	
	public void SelectCountry(String country_name)
	{
		countryTextbox.sendKeys("ind");
		waitForElementToAppear(results);
		WebElement country = countries.stream().filter(s->
		s.getText().equalsIgnoreCase(country_name)).
				findFirst().orElse(null);
		country.click();
	}
	
	public ConfirmationPage submitOrder()
	{
		submitButton.click();
		return new ConfirmationPage(driver);
	}
	
	

}
