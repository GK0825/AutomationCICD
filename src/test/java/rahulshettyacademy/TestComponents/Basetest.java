package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import io.cucumber.core.internal.com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import rahulshettyacademy.pageobjects.LandingPage;

public class Basetest {

	public WebDriver driver;
	public LandingPage lp;
	
	public WebDriver initializeDriver() throws IOException
	{
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//rahulshettyacademy//resources//GlobalData.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser")!=null ? System.getProperty("browser"): prop.getProperty("browser");
		
		
		if(browserName.equalsIgnoreCase("chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			options.setAcceptInsecureCerts(true);
			System.setProperty("webdriver.chrome.driver, ", "C:/Users/gaura/Downloads/chrome-win64/chrome-win6/chrome.exe");
			driver = new ChromeDriver();			
		}
		else if(browserName.equalsIgnoreCase("Firefox"))
		{
			//firefox
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			//edge
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		return driver;
		
		
	}
	
	public List<HashMap<String, String>> getJsonDataToMap(String FilePath) throws IOException
	{
		//read json to String
		String jsonContent = FileUtils.readFileToString(new File(FilePath),StandardCharsets.UTF_8);
	
		
		//String to hashmap with Jackson databind
		ObjectMapper mapper =new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){			
		});
		
		return data;
	}
	
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts =(TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		lp= new LandingPage(driver);
		lp.goTo();
		return lp;
	}
	
	@AfterMethod(alwaysRun=true)
	public void teardown()
	{
		driver.close();
	}
	
	
}
