package rahulshettyacademy.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.AbstractComponents.AbstractComponents;
import rahulshettyacademy.TestComponents.Basetest;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.MyCartPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends Basetest{

	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData", groups={"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		String country_name = "India";
		
		String orderPlaced = "THANKYOU FOR THE ORDER.";
		ProductCatalogue pc=lp.loginApplication(input.get("email"),input.get("password"));
		
		pc.addProductToCart(input.get("product"));
		MyCartPage mcp=pc.goToCartPage();
		
		Boolean match = mcp.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage cp = mcp.goToCheckout();
		
		cp.SelectCountry(country_name);
		ConfirmationPage confirmationPage = cp.submitOrder();
		String confirmationMessage=confirmationPage.getConfirmationMessage();
		String orderId = confirmationPage.getOrderDetails();
		System.out.println(orderId);
		Assert.assertEquals(orderPlaced,confirmationMessage);
		System.out.println(" Test Successfully Completed, closing the browser ");		
		
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue pc=lp.loginApplication("gk2026@gmail.com", "RSa@2025");
		OrderPage orderPage=pc.goToOrdersPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		
//		HashMap<String,String> map=new HashMap<String,String>();
//		map.put("email", "gk2026@gmail.com");
//		map.put("password", "RSa@2025");
//		map.put("productName", "ZARA COAT 3");
//		
//		HashMap<String,String> map1=new HashMap<String,String>();
//		map1.put("email", "gk2026@gmail.com");
//		map1.put("password", "RSa@2025");
//		map1.put("productName", "ZARA COAT 3");
		
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
