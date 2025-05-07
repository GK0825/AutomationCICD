package rahulshettyacademy.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyacademy.AbstractComponents.AbstractComponents;
import rahulshettyacademy.TestComponents.Basetest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.MyCartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidations extends Basetest{

	
	@Test(groups = {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation()
	{
		String productName = "ZARA COAT 3";
		lp.loginApplication("gk2026@gmail.com", "RS@2025");
		Assert.assertEquals("Incorrect email or password.", lp.getErrormessage());
	}
	
	@Test
	public void submitOrderTest() throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		String productName = "ZARA COAT 3";
		
		lp.loginApplication("gk2026@gmail.com", "RS@2025");
		Assert.assertEquals("Incorrect email or password.", lp.getErrormessage());		
	}
	
	

}
