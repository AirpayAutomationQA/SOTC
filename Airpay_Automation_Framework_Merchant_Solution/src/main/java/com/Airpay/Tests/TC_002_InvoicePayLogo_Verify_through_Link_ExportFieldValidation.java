package com.Airpay.Tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.Airpay.BusinessLogic.SOTC_Application_BusinessLogic;
import com.Airpay.InitialSetup.Driver_Setup;
import com.Airpay.PageObject.Airpay_CAPanel_PageObject;
import com.Airpay.Utilities.Log;

public class TC_002_InvoicePayLogo_Verify_through_Link_ExportFieldValidation extends Driver_Setup{
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
	public void TC_TestCaseName() throws Throwable {
		try {
			Log.info("Script Starts..");
			SOTC_Application_BusinessLogic CA_panel = new SOTC_Application_BusinessLogic(driver, TC_ID);
			CA_panel.Invoice_Panel_Login();
			CA_panel.InvoiceLogoVerify(Airpay_CAPanel_PageObject.InvoiceLogo);
			CA_panel.InvoiceExportsBtnVerify();
			Log.info("Scripts Ends....");
		} catch (Exception e) {
			Log.error(e.getMessage());
			System.out.println(e);
		}
	}
	@AfterTest
	public void tearDown()
	{
		if(webDriver != null)
			webDriver.close();
	}
}

