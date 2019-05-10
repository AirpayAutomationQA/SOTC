package com.Airpay.Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.Airpay.BusinessLogic.Create_Activity_BusinessLogic;
import com.Airpay.BusinessLogic.Student_Report_BuninessLogic;
import com.Airpay.InitialSetup.Driver_Setup;
import com.Airpay.Reporting.Extent_Reporting;
import com.Airpay.Utilities.Log;

public class Student_Report extends Driver_Setup
{
	public static WebDriver webDriver = null;
	public static String tcID = null;
	
	//Business Logic Class Object list	
	@Test(priority=1)
	public  void setup()
	{
		Log.info("Setup the variable for Test");
		webDriver = driver; 
		tcID = TC_ID;
		Log.info("Setup completed for the variable");
	}
	@Test(priority = 2)
	public void TestCase_Login() throws Throwable
	{
		Create_Activity_BusinessLogic create_act=new Create_Activity_BusinessLogic(driver, tcID);
		create_act.Login("student user name","student password");		
		Student_Report_BuninessLogic stu_report=new  Student_Report_BuninessLogic(driver, tcID);		
		if(stu_report.check_user_records()) 
		{
			stu_report.get_page_data();
			stu_report.check_invoice_details();
			stu_report.check_Payment_details();
			stu_report.Credit_cardValidation();
			stu_report.check_success_payment();
			stu_report.check_paid_transaction(create_act);
			
		}
		else
		{
			Extent_Reporting.Log_Fail("Invoice Home Page", "No Invoices Found", webDriver);
		}
//				
	}

	
}
