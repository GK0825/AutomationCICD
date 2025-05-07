package rahulshettyacademy.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;

public class Listeners extends Basetest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent=ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTest=new ThreadLocal();
	
	@Override
	public void onTestStart(ITestResult result) {
		System.out.println("Successfully executed listeners pass code");
		test = extent.createTest(result.getMethod().getMethodName());
		extentTest.set(test);
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Successfully executed listeners pass code");
		extentTest.get().log(Status.PASS,"Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		//screenshot method/logic
		//api code validation on failure, getting response and response code
		System.out.println("Successfully executed listeners failed code "+result.getName());
		extentTest.get().fail(result.getThrowable());
		// the below block of code is to find the driver instance and use it for the file path to capture 
		// screenshots and related methods, which test instance screenshot has to be taken, can be identified 
		// by the class for which driver is active currently and working on
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		String filePath=null;
		try {
			filePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		extentTest.get().addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
	}
	
	@Override
	public void onTestSkipped(ITestResult result) {
		System.out.println();
	}
	
	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		System.out.println();
	}
	
	@Override
	public void onStart(ITestContext context) {
		System.out.println();
	}
	
	@Override
	public void onFinish(ITestContext context) {
		// this will generate the report once everything is finished, after taking and attaching all the screenshots
		extent.flush(); 
		
	}

}
