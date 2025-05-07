package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.Basetest;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.MyCartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends Basetest{

	public LandingPage landingPage;
	public ProductCatalogue pc;
	public MyCartPage mcp;
	public CheckoutPage cp;
	public ConfirmationPage confirmationPage;
	
	String country_name="India";
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException
	{
		landingPage=launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username, String password)
	{
		pc=lp.loginApplication(username,password);
	}
	
	@When("^I add productname (.+) to cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products = pc.getProductList();
		pc.addProductToCart(productName);
	}
	
	@When ("^Checkout (.+) and submit the order$")
	public void checkout_submit_order(String productName)
	{
		mcp=pc.goToCartPage();
		
		Boolean match = mcp.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		cp = mcp.goToCheckout();
		
		cp.SelectCountry(country_name);
		confirmationPage = cp.submitOrder();
	}
	
	@Then("{string} message is displayed on ConfirmationPage")
	public void message_displayed_confirmation_page(String message)
	{
		String confirmationMessage=confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmationMessage.equalsIgnoreCase(message));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void error_message_is_displayed(String errorMessage)
	{
		Assert.assertEquals(errorMessage, lp.getErrormessage());
		driver.close();
	}
}
