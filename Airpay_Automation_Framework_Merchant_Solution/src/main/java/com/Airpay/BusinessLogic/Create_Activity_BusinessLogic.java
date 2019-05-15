package com.Airpay.BusinessLogic;

import org.openqa.selenium.WebDriver;

import com.Airpay.PageObject.Create_Activity_PageObject;
import com.Airpay.TestData.Excel_Handling;
import com.Airpay.Utilities.ElementAction;
import com.Airpay.Utilities.Log;

public class Create_Activity_BusinessLogic extends Create_Activity_PageObject
{
	boolean first_login=true;
	public WebDriver driver;
	public String TC_ID = "";
	ElementAction Assert = new ElementAction();
	public Create_Activity_BusinessLogic(WebDriver driver, String TC_ID)
	{
		Log.info("Airpay_School_Pay_BusinessLogic");
		this.driver = driver;
		this.TC_ID=TC_ID;
	}

	public void Login(String username,String passw) throws Exception
	{
		String School_Pay_Url=Excel_Handling.Get_Data(TC_ID, "URL");
		driver.get(School_Pay_Url);
		
		if(first_login)
		{
			Assert.inputText(driver, userid, Excel_Handling.Get_Data(TC_ID, username).trim(), "UserId");
			Assert.inputText(driver, password, Excel_Handling.Get_Data(TC_ID, passw).trim(), "Password");
		
			Assert.Clickbtn(driver, submit_button, "Click on Submit button");
			Assert.waitForPageToLoad(driver);
			Thread.sleep(3000);
			Assert.isElementDisplayed(driver,Student_Report_BuninessLogic.username_string, "Username");
			first_login=false;
		}
		else
		{
			Assert.Clickbtn(driver, Student_Report_BuninessLogic.logout_link, "Logout link");
	    	Assert.Clickbtn(driver, Student_Report_BuninessLogic.logout_button, "Logout Click");
			Assert.inputText(driver, userid, Excel_Handling.Get_Data(TC_ID, username).trim(), "UserId");
			Assert.inputText(driver, password, Excel_Handling.Get_Data(TC_ID, passw).trim(), "Password");
		
			Assert.Clickbtn(driver, submit_button, "Click on Submit button");
			//Assert.waitForPageToLoad(driver);
			//Thread.sleep(3000);		
			//Assert.isElementDisplayed(driver,Student_Report_BuninessLogic.username_string, "Username");
		}
		
			
		
		
	}

	
	
	

}
