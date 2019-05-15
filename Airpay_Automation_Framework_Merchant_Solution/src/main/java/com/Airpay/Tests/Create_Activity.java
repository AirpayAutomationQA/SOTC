package com.Airpay.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.Airpay.BusinessLogic.Create_Activity_BusinessLogic;
import com.Airpay.InitialSetup.Driver_Setup;
import com.Airpay.Reporting.Extent_Reporting;
import com.Airpay.Utilities.Log;

public class Create_Activity extends Driver_Setup
{

	public static WebDriver webDriver = null;
	public static String tcID = null;
	
	//Business Logic Class Object list	
	@Test(priority=1)
	public void setup()
	{
		Log.info("Setup the variable for Test");
		webDriver = driver; 
		tcID = TC_ID;
		Log.info("Setup completed for the variable");
	}
	@Test(priority = 2)
	public void TestCase_Login() throws Exception
	{
		Create_Activity_BusinessLogic create_act=new Create_Activity_BusinessLogic(driver, tcID);
		
		create_act.Login("student user name","student password");
		
		String page_title=webDriver.findElement(By.xpath("//h1[@class='page-title']")).getText();
		if(page_title.equals("Dashboard"))
		{
			Extent_Reporting.Log_Pass("Login to School Pay", "Passed");
			Extent_Reporting.Log_report_img("Login to School Pay", "Passed", webDriver);
		}
		else
		{
			Extent_Reporting.Log_Fail("Login to School Pay", "User Id or Password is Incorrect", webDriver);
		}
		
		
		
		
		
		
	}
}
