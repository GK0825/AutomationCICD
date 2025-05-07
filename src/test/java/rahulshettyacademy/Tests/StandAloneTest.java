package rahulshettyacademy.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String productName = "ZARA COAT 3";
		String country_name = "India";
		String orderPlaced = "THANKYOU FOR THE ORDER.";
		System.setProperty("webdriver.chrome.driver, ", "C:/Users/gaura/Downloads/chrome-win64/chrome-win6/chrome.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client");
		driver.findElement(By.id("userEmail")).sendKeys("gk2026@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("RSa@2025");
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		List<WebElement> items = driver.findElements(By.cssSelector(".mb-3"));
		WebElement prod = items.stream().filter(s->
		s.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//class of waiting for invisibility
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink*=cart]")));
		
		driver.findElement(By.cssSelector("[routerlink*=cart]")).click();
		
		WebElement cart = driver.findElement(By.cssSelector("[class=cart]"));
		List<WebElement> cart_items = cart.findElements(By.cssSelector("ul"));
		Boolean match=cart_items.stream().anyMatch(s->s.findElement(By.cssSelector("h3")).getText().equalsIgnoreCase(productName));
		Assert.assertTrue(match);
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();

		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[placeholder='Select Country']")));
		driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("ind");
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ta-results"))));
		List<WebElement> countries = driver.findElements(By.cssSelector(".ta-results button"));
		
		WebElement country = countries.stream().filter(s->
		s.getText().equalsIgnoreCase(country_name)).
				findFirst().orElse(null);
		country.click();
		driver.findElement(By.cssSelector(".action__submit")).click();
		String orderId=driver.findElement(By.cssSelector(".ng-star-inserted:nth-child(3) td label")).getText();
		System.out.println(orderId);
		Assert.assertEquals(orderPlaced,driver.findElement(By.cssSelector(".hero-primary")).getText());
		driver.close();
		
	}

}
