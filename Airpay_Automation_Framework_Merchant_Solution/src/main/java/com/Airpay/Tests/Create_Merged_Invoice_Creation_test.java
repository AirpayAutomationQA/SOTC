package com.Airpay.Tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.Airpay.BusinessLogic.Create_Activity_BusinessLogic;
import com.Airpay.BusinessLogic.Student_Report_BuninessLogic;
import com.Airpay.InitialSetup.Driver_Setup;
import com.Airpay.PageObject.Student_Report_PageObject;
import com.Airpay.Reporting.Extent_Reporting;
import com.Airpay.Utilities.Log;

public class Create_Merged_Invoice_Creation_test extends Driver_Setup
{
	public static WebDriver webDriver = null;
	public static String tcID = null;
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
		create_act.Login("admin username","admin password");		
		Student_Report_BuninessLogic stu_report=new Student_Report_BuninessLogic(driver, TC_ID);				
		if(stu_report.approvalStatus()) 
		{
			stu_report.SearchSchoolName();			
			stu_report.Invoicetabchoice(Student_Report_PageObject.MergedRadioBtn);
			create_act.Login("School login UserName","School login PWD");
			stu_report.setTodaysDate();
			stu_report.createFeesStructureMenu();
			stu_report.createFeesStructure();
			stu_report.AddAnotherCreateFeesStructure();
			stu_report.inVoiceSubmitBtn();
			stu_report.SuccessMessage("Fee structures created successfully");
			stu_report.GenerateInvoice();
			create_act.Login("student user name","student password");
			stu_report.check_Merged_user_recordsSample();
			stu_report.check_invoice_detailsSample();
			stu_report.check_Payment_details();
			stu_report.Credit_cardValidation();
			stu_report.check_success_payment();
			stu_report.check_paid_merged_transaction(create_act);			
		}
		else
		{
			Extent_Reporting.Log_Fail("Invoice Home Page", "No Invoices Found", webDriver);
		}
//				
	}

	
}
