package com.Airpay.BusinessLogic;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Airpay.PageObject.Student_Report_PageObject;
import com.Airpay.Reporting.Extent_Reporting;
import com.Airpay.TestData.Excel_Handling;
import com.Airpay.Utilities.ElementAction;
import com.Airpay.Utilities.Log;

import net.sourceforge.htmlunit.corejs.javascript.regexp.SubString;

public class Student_Report_BuninessLogic extends Student_Report_PageObject
{

	//public ArrayList<Integer,String,String,String,String, String, String> report_data_list=new ArrayList<Integer,String,String,String,String, String, String>();
	int cols=0;
	int i=0;
	double multi_line_total=0.00;
	int arr_index,fee_description_loc,stu_name_loc,Amount_loc,no_of_invoices;
	
	//public HashMap<Integer, String[]> report_data_list;
	
	ArrayList<String[]> report_data_list;
	public WebDriver driver;
	public String TC_ID = "";
	ElementAction Assert = new ElementAction();
	String student_name,Fee_desc,quarter,amount,status,invoice_total_amt,invoice_page_url,roll_no;
	Boolean single_select=false;
	List<WebElement> list_table_header,invoice_details__table_header;
	List<WebElement> list_tr_data;

	int invoice_details_position, check_position;
	
	public Student_Report_BuninessLogic(WebDriver driver, String TC_ID)
	{
		Log.info("Airpay_School_Pay_BusinessLogic");
		this.driver = driver;
		this.TC_ID=TC_ID;
	}
	
	
	public boolean approvalStatus() throws Exception{
		try {			
			if(Assert.isElementDisplayed(driver, ApprovedSchoolTab, "Approved School")) {
				Assert.Clickbtn(driver, ApprovedSchoolTab, "Approved School");
				single_select=true;
			}else {
				Extent_Reporting.Log_Fail("Approved school link does not exist", "Fail", driver);
				single_select=false;
			}					
			}catch (Exception e) 
			{
			Extent_Reporting.Log_Fail("Login not successfully", "Failed", driver);
			e.printStackTrace();
			single_select=false;
			//throw new Exception("Test failed due to local host page not displayed");
			}
		return single_select;
		}
	
	//check the school name is exist or not
	
	public void SearchSchoolName() throws Exception{
		try {		
			List<WebElement> SchoolNames=driver.findElements(By.xpath(totalSchoolNames));
			List<WebElement> MailID=driver.findElements(By.xpath(RegisteredMailID));			
			System.out.println(SchoolNames.size());
			for(int j=0;j<=SchoolNames.size()-1;j++)
			{		
				String SchoolName = SchoolNames.get(j).getText().trim();
				String RegisterMailID = MailID.get(j).getText().trim();
				if(SchoolName.equalsIgnoreCase(Excel_Handling.Get_Data(TC_ID, "School_Name").trim())
						&&  RegisterMailID.equalsIgnoreCase(Excel_Handling.Get_Data(TC_ID, "registeredMailID").trim()))
				{					
					Extent_Reporting.Log_report_img("School name is exist", "Passed", driver);				
					SchoolNames.get(j).click();
					break;
					
				}
				if(j==SchoolNames.size()-1) {
					Extent_Reporting.Log_Fail("School name does not exist", "Fail", driver);
				}
							
			}
			
				
			
		}catch(Exception e){
			Extent_Reporting.Log_Fail("Login not successfully", "Failed", driver);
			e.printStackTrace();
			single_select=false;
		}
	}
	
	//to check Invoice tab and select Individual and merged school name
	public void Invoicetabchoice(String InvoiceRadiobtn) throws Throwable{
		try {
			Thread.sleep(5000);
			String iframeID = driver.findElement(By.xpath(Frameswitch)).getAttribute("name").trim();
			driver.switchTo().frame(iframeID);
			if(Assert.isElementDisplayed(driver, InvoiceTab, "Invoice Tab"))
			{
				Assert.Javascriptexecutor_forClick(driver, InvoiceTab, "Invoice Tabis ");
				Extent_Reporting.Log_report_img("Invocie tab", "Passed", driver);
				Extent_Reporting.Log_Pass("Invoice tab is exist", "Passed");
				Assert.Clickbtn(driver, InvoiceRadiobtn, "Invoice Radio button");
				Extent_Reporting.Log_report_img("Invoice details save", "Passed", driver);
				Assert.Clickbtn(driver, SaveInvoiceDetails, "Save button");	
				driver.switchTo().alert().accept();
				Thread.sleep(2000);
				driver.switchTo().alert().accept();
				driver.switchTo().defaultContent();
				Thread.sleep(2000);
				Assert.Clickbtn(driver, FancyBoxClosebtn, "Close");
			}else {
				Extent_Reporting.Log_Fail("Invoice tab Does not exist", "Fail", driver);
			}		
		}catch(Exception e){
			Extent_Reporting.Log_Fail("Login not successfully", "Failed", driver);
			e.printStackTrace();
			single_select=false;
		}
	}
	
	
	//to set todays date for Invoice Creation
	public void setTodaysDate() throws Exception
	{
		try {			
			if(Assert.isElementDisplayed(driver, ConfigurationMenu, "Configuration Menu")) 
			{
				Extent_Reporting.Log_Pass("Configuration Menu is exist asexpected", "Passed");
				Assert.Clickbtn(driver, ConfigurationMenu, "Configuration Menu");
				DateFormat dateFormat = new SimpleDateFormat("MMMM dd yyyy");
				  java.util.Date date = new java.util.Date();
				  System.out.println("Current Date : "+dateFormat.format(date));
				  String midDaynumb = dateFormat.format(date);
				  String Day = midDaynumb.substring(5, 6);
				  System.out.println("Day "+Day);
				 Assert.Clickbtn(driver, SchoolPayTab, "School pay Setting");
				 Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, SelectInvoiceDayDrop, Day, "Day Number");
				 Assert.Clickbtn(driver, SetDateSaveBtn, "save button");			 
				 String SaveMsg = Assert.getInputTextValue(driver, DetailsSaveToSetDate, "Save Message");
				 if(SaveMsg.equalsIgnoreCase("School Setting has been updated successfully.")) {				 
					 Extent_Reporting.Log_report_img("Save Message is exist", "Passed", driver);
				 }else {
					 Extent_Reporting.Log_Fail("Details not saved successfully", "Failed", driver);
				 }
				
			}else {
				Extent_Reporting.Log_Fail("Configuratmenu does not exist", "Fail", driver);
			}		
		}catch(Exception e){
			Extent_Reporting.Log_Fail("Login not successfully", "Failed", driver);
			e.printStackTrace();
			single_select=false;
		}
	}
	
	//to set todays date for Invoice Creation
		public void GenerateInvoice() throws Exception
		{
			try {			
				if(Assert.isElementDisplayed(driver, ConfigurationMenu, "Configuration Menu")) 
				{
					Extent_Reporting.Log_Pass("Configuration Menu is exist asexpected", "Passed");
					Assert.Clickbtn(driver, ConfigurationMenu, "Configuration Menu");
					 Assert.Clickbtn(driver, GenerateInvoice, "School pay Setting");
					 Assert.Clickbtn(driver, GenerateinvoiceBtn, "Generate invoice button");
					// Assert.Clickbtn(driver, SetDateSaveBtn, "save button");			 
					 String SaveMsg = Assert.getInputTextValue(driver, DetailsSaveToSetDate, "Save Message");
					 if(SaveMsg.equalsIgnoreCase("Invoices has been created.")) {				 
						 Extent_Reporting.Log_report_img("Generate Invoice", "Passed", driver);
					 }else {
						 Extent_Reporting.Log_Fail("Generate Invoice Not generated", "Failed", driver);
					 }					
				}else {
					Extent_Reporting.Log_Fail("Configuratmenu does not exist", "Fail", driver);
				}		
			}catch(Exception e){
				Extent_Reporting.Log_Fail("Login not successfully", "Failed", driver);
				e.printStackTrace();
				single_select=false;
			}
		}
	
	
	//to create the Invoice from fees structure menu function
	
	public void createFeesStructureMenu() throws Exception{
		try{
			
			if(Assert.isElementDisplayed(driver, FeesStructureMenu, "Fees Structure")){				
				Assert.Clickbtn(driver, FeesStructureMenu, "Fees Structure");
				Assert.Clickbtn(driver, CreateFessMenu, "Create fees menu");
				Extent_Reporting.Log_Pass("Create Fees menu is exist", "Passed");
				Extent_Reporting.Log_report_img("Create fees structure", "Passed", driver);
			}else {
				Extent_Reporting.Log_Fail("Fees structure menu does not exist", "Fail", driver);
				
			}		
		}catch(Exception e) {
			Extent_Reporting.Log_Fail("Fees structure not created", "Failed", driver);
			e.printStackTrace();
		}
	}
	
	public void createFeesStructure() throws Throwable{
		try{
			
			if(Assert.isElementDisplayed(driver, FeesDetailspage, "Fees Details Page")){
				
				Assert.Clickbtn(driver, SelectClassClick, "Fees Structure");
				Assert.Clickbtn(driver, "//ul[@id='select2-selclass-results']/li[text()='"+Excel_Handling.Get_Data(TC_ID, "select Class").trim()+"']", "Select class");
				Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, SelectfeeHeading, Excel_Handling.Get_Data(TC_ID, "FeesHeading").trim(), "Select fees Heading");
				Assert.inputText(driver, FeeNameVal, Excel_Handling.Get_Data(TC_ID, "FeeName"), "Fee Name");
				Assert.inputText(driver, FeeValueVal, Excel_Handling.Get_Data(TC_ID, "Feevalue"), "Fee Name");
				Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, paymentDuration, Excel_Handling.Get_Data(TC_ID, "PaymentDuration").trim(), "Payment Duration");
				Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, QuarterDrop, Excel_Handling.Get_Data(TC_ID, "Quarter").trim(), "Select Quarter");
				Assert.Clickbtn(driver, PmtStartDate, "payment Start Date");
				WebDriverWait wait=new WebDriverWait(driver, 20);
				WebElement guru99seleniumlink;
				guru99seleniumlink= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DatePopup)));
				guru99seleniumlink.isDisplayed();			
				//((JavascriptExecutor)driver).executeScript("arguments[0].value=arguments[1]", expiredOnDate, "15-Mar-2019, 12:00 am");							
				Thread.sleep(5000);
				Assert.Javascriptexecutor_forClick(driver, PreviouseDate, "Next Extend Date");
				Assert.Clickbtn(driver, PmtEndDate, "payment End Dates");			
				guru99seleniumlink= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DatePopup)));
				guru99seleniumlink.isDisplayed();			
				//((JavascriptExecutor)driver).executeScript("arguments[0].value=arguments[1]", expiredOnDate, "15-Mar-2019, 12:00 am");							
				Thread.sleep(5000);
				Assert.Javascriptexecutor_forClick(driver, PreviouseDate, "Next Extend Date");				
				Assert.Clickbtn(driver, PaymentLateFeesRadiobtn, "Create fees menu");
				Assert.inputText(driver, lateFeesInput, Excel_Handling.Get_Data(TC_ID, "lateFees"), "Fee Name");		
				Extent_Reporting.Log_Pass("Create Fees Details is exist", "Passed");
				Extent_Reporting.Log_report_img("Create fees", "Passed", driver);
			}else {
				Extent_Reporting.Log_Fail("Fees Details page does not exist", "Fail", driver);
				
			}		
		}catch(Exception e) {
			Extent_Reporting.Log_Fail("Fees Details page not exist", "Failed", driver);
			e.printStackTrace();
		}
	}

	//to create the Invoice from submit button Function
	public void inVoiceSubmitBtn() throws Exception
	{
		try{
			if(Assert.isElementDisplayed(driver, FormSubmitBtn, "Save button")){
				Extent_Reporting.Log_Pass("Save button", "Passed");
				Assert.Clickbtn(driver, FormSubmitBtn, "Save button");
			}else{
				Extent_Reporting.Log_Fail("Save button does no exist", "Failed", driver);
			}
		}catch(Exception e){
			e.printStackTrace();
			Extent_Reporting.Log_Fail("Save button does no exist", "Failed", driver);

		}
	}
	
	public void SuccessMessage(String alertmsg) throws Exception{
		try{
			 String SaveMsg = Assert.getInputTextValue(driver, DetailsSaveToSetDate, "Fees structure");
			 if(SaveMsg.equalsIgnoreCase(alertmsg)) {				 
				 Extent_Reporting.Log_report_img("Save Message is exist", "Passed", driver);
			 }else {
				 Extent_Reporting.Log_Fail("Fees structure not saved successfully", "Failed", driver);
			 }
		}catch(Exception e){
			e.printStackTrace();
			Extent_Reporting.Log_Fail("Deatils not saved successfully", "Failed", driver);

		}
	}
	
	

	
	// TO check the records of the Logged user (Logged user should be able to view only their record.)
	public boolean check_user_records() throws Exception
	{
		boolean invoices_flag= true;
		try {
		int name_ctr=0;		
		Assert.Clickbtn(driver, report_menu, "Report Menu");
		Assert.Clickbtn(driver, report_sub_menu, "Billing Sub Menu");	
		String log_user_name=Assert.getInputTextValue(driver, username_string, "Logged User Name");	
		list_table_header=driver.findElements(By.xpath(table_headers));
		list_tr_data=driver.findElements(By.xpath(table_row_count));
		List<WebElement> data_list=driver.findElements(By.xpath(table_data));
		cols=list_table_header.size();
		no_of_invoices=list_tr_data.size();		
		System.out.println(data_list.size());
		if (data_list.size()>1 )
		{	
		for(int j=1;j<=list_tr_data.size();j++)
		{
				for(int i=1;i<=list_table_header.size();i++)
				{
					
					String headerText= list_table_header.get(i-1).getText();
					System.out.println(headerText);
					if(headerText.equals("Student Name"))
					{
						student_name=Assert.getInputTextValue(driver, table_row_count+"["+j+"]//td["+i+"]", "Student Name");;
					}
				}
				
				if(log_user_name.equals(student_name))
				{
					name_ctr++;
				}
				
		}
		
		if(name_ctr>0)
		{
			Extent_Reporting.Log_Pass("User able to other users records", "Passed");
		}
		else
		{
			Extent_Reporting.Log_Fail("User able to see their own records only", "Failed", driver);
			
		}
		
		return invoices_flag;}
		else
		{
			invoices_flag=false;
			Extent_Reporting.Log_Fail("No Invoices found", "Failed", driver);
			return invoices_flag;
		}
		}catch (Exception e) 
			{
			Log.error("Test failed due to local host page not displayed");
			e.printStackTrace();
			//throw new Exception("Test failed due to local host page not displayed");
			}
		return invoices_flag;
		
		
	/*
		List<WebElement> list_table_header=driver.findElements(By.xpath(table_headers));
		for(int i=1;i<=list_table_header.size();i++)
		{
			String headerText= list_table_header.get(i).getText();
			if(headerText.equalsIgnoreCase("Student Name"))
			{
				String name=Assert.getInputTextValue(driver, table_data+"["+i+"]", "Logged User Name");
				System.out.println(log_user_name + " "+name);
				if(log_user_name.equals(name))
				{
					Extent_Reporting.Log_Pass("", "Passed");
					break;
				}
				else
				{
					Extent_Reporting.Log_Fail("Login Failed", "Login Failed", driver);
				}
			}
			
		}*/
	}
	
	public static String Fees_Name = null;
	boolean FeeVal = false;
	public static int headerPosition=0;
	
	public boolean check_user_recordsSample() throws Exception
	{
		boolean invoices_flag= true;
		try 
		{
			Assert.Clickbtn(driver, report_menu, "Report Menu");
			Assert.Clickbtn(driver, report_sub_menu, "Billing Sub Menu");	
			Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, PaytypeDropDown, "Unpaid", "pay type drop down");
			Assert.Clickbtn(driver, PaytypesubmitBtn, "Search button");
			String log_user_name=Assert.getInputTextValue(driver, username_string, "Logged User Name");			
			list_table_header=driver.findElements(By.xpath(table_headers));
			list_tr_data=driver.findElements(By.xpath(table_row_count));			
			for(int i=1;i<=table_headers.length();i++)
			{
				String headerName = driver.findElement(By.xpath(table_headers+"["+i+"]")).getText().trim();
				if(headerName.equalsIgnoreCase("Fee Description"))
				{
					headerPosition= i;
					for(int j=1;j<=table_row_count.length();j++)
					{
						String FeeName = driver.findElement(By.xpath(table_row_count+"["+j+"]/td["+i+"]")).getText().trim();						
						if(FeeName.equalsIgnoreCase(Excel_Handling.Get_Data(TC_ID, "FeeName").trim()))
						{
							Extent_Reporting.Log_Pass("Record Found at "+j+ " Row", "Passed");
							Extent_Reporting.Log_report_img("record Found", "Passsed",driver);
							FeeVal=true;
							Assert.Clickbtn(driver, table_row_count+"["+j+"]/td["+i+"]//following::a[1]", "View Link");						
							break;
						}					
					}
					if(FeeVal){
						break;
					}
				}
						
			}			
		}catch(Exception e){
			Extent_Reporting.Log_Fail("Invoice Records does not exist", "Failed", driver);

		}
		return invoices_flag;	
	}
	
	
	/// TO get the Report home page data (Get the data from the report page, handled as  
		public void get_page_dataSample() throws Exception
		{
			String[] args;
			double line_amt=0;
			//report_data_list=new HashMap<Integer, String[]>();
			
			report_data_list= new ArrayList<String[]>();
			boolean link_text=false;
			for(int j=1;j<=list_tr_data.size();j++)
			{
				try {
					if(single_select)
					{
						break;
					}
					else
					{
					args=new String[cols];
				//arr_index=0;
				System.out.println(list_tr_data.size());
				//driver.navigate().refresh();
				list_table_header=driver.findElements(By.xpath(table_headers));
				Thread.sleep(1000);
				
					for(i=1;i<=list_table_header.size();i++)
					{
						//System.out.println(list_table_header.size());
						//System.out.println(list_table_header.get(i-1).getText());
						
						//Assert.isElementDisplayed(driver,list_table_header+"["+i+"]", "Username");
						
						
						String headerText= list_table_header.get(i-1).getText();
						System.out.println(headerText);
						if(list_tr_data.size() > 1)
						{
							args=save_dataSample(headerText, driver,table_row_count+"["+j+"]//td["+i+"]",args);
							//System.out.println("I"+i+"J"+j);	
						}
						else
						{
							args=save_dataSample(headerText, driver,table_data+"["+i+"]",args );
						}
						if(headerText.equals("Invoice Details"))
						{
							invoice_details_position=i;
							System.out.println(invoice_details_position);		
							String details_link_text=Assert.getInputTextValue(driver, table_row_count+"["+j+"]//td["+i+"]", "Invoice Details Link");
							if(details_link_text.equals("View"))
							{
								link_text=true;
							}
						}
						if(headerText.equalsIgnoreCase("Select to Pay"))
						{
							check_position=i;
						}
						}//inner loop ends here 
						
				//	report_data_list.put(j, args);
						if(link_text )
						{
							report_data_list.add(args);
							single_select=true;
							multi_line_total=Double.parseDouble(amount);
							Assert.Clickbtn(driver,table_row_count+"["+j+"]//td["+invoice_details_position+"]/a" , "Click for Invoice Details");
							
							//check_invoice_details();
							//Assert.Clickbtn(driver, Report_pay_now, "Pay Now button on Invoice details page");
							break;
							
						}
						else
						{
							report_data_list.add(args);
							
							if(driver.findElement(By.xpath( table_row_count+"["+j+"]//td["+check_position+"]//input")).isSelected())
							{
								
								multi_line_total=multi_line_total+Double.parseDouble(amount);
							}
							else
							{
								Assert.Clickbtn(driver, table_row_count+"["+j+"]//td["+check_position+"]//input", "Select to multi Invoices");
								line_amt= Double.parseDouble(amount);
								multi_line_total= multi_line_total+line_amt; 
							}
						}
						Assert.Clickbtn(driver, Report_pay_now, "Pay Now on Report Page");

					}
//					Extent_Reporting.Log_report_img(, string, driver);
				}
				catch (Exception e) 
					{
						Log.error("Test failed due to local host page not displayed");
						e.printStackTrace();
						//throw new Exception("Test failed due to local host page not displayed");
					}	
			}//outer loop ends here
		}
	
	
	
	/// TO get the Report home page data (Get the data from the report page, handled as  
	public void get_page_data() throws Exception
	{
		String[] args;
		double line_amt=0;
		//report_data_list=new HashMap<Integer, String[]>();
		
		report_data_list= new ArrayList<String[]>();
		boolean link_text=false;
		for(int j=1;j<=list_tr_data.size();j++)
		{
			try {
				if(single_select)
				{
					break;
				}
				else
				{
				args=new String[cols];
			//arr_index=0;
			System.out.println(list_tr_data.size());
			//driver.navigate().refresh();
			list_table_header=driver.findElements(By.xpath(table_headers));
			Thread.sleep(1000);
			
				for(i=1;i<=list_table_header.size();i++)
				{
					//System.out.println(list_table_header.size());
					//System.out.println(list_table_header.get(i-1).getText());
					
					//Assert.isElementDisplayed(driver,list_table_header+"["+i+"]", "Username");
					
					
					String headerText= list_table_header.get(i-1).getText();
					System.out.println(headerText);
					if(list_tr_data.size() > 1)
					{
						args=save_data(headerText, driver,table_row_count+"["+j+"]//td["+i+"]",args);
						//System.out.println("I"+i+"J"+j);	
					}
					else
					{
						args=save_data(headerText, driver,table_data+"["+i+"]",args );
					}
					if(headerText.equals("Invoice Details"))
					{
						invoice_details_position=i;
						System.out.println(invoice_details_position);		
						String details_link_text=Assert.getInputTextValue(driver, table_row_count+"["+j+"]//td["+i+"]", "Invoice Details Link");
						if(details_link_text.equals("View"))
						{
							link_text=true;
						}
					}
					if(headerText.equalsIgnoreCase("Select to Pay"))
					{
						check_position=i;
					}
					}//inner loop ends here 
					
			//	report_data_list.put(j, args);
					if(link_text )
					{
						report_data_list.add(args);
						single_select=true;
						multi_line_total=Double.parseDouble(amount);
						Assert.Clickbtn(driver,table_row_count+"["+j+"]//td["+invoice_details_position+"]/a" , "Click for Invoice Details");
						
						//check_invoice_details();
						//Assert.Clickbtn(driver, Report_pay_now, "Pay Now button on Invoice details page");
						break;
						
					}
					else
					{
						report_data_list.add(args);
						
						if(driver.findElement(By.xpath( table_row_count+"["+j+"]//td["+check_position+"]//input")).isSelected())
						{
							
							multi_line_total=multi_line_total+Double.parseDouble(amount);
						}
						else
						{
							Assert.Clickbtn(driver, table_row_count+"["+j+"]//td["+check_position+"]//input", "Select to multi Invoices");
							line_amt= Double.parseDouble(amount);
							multi_line_total= multi_line_total+line_amt; 
						}
					}
					Assert.Clickbtn(driver, Report_pay_now, "Pay Now on Report Page");

				}
//				Extent_Reporting.Log_report_img(, string, driver);
			}
			catch (Exception e) 
				{
					Log.error("Test failed due to local host page not displayed");
					e.printStackTrace();
					//throw new Exception("Test failed due to local host page not displayed");
				}	
		}//outer loop ends here
	}// Method get_data ends here 
	
	// To save Report home page data 
	public String[] save_data(String header_text, WebDriver driver,String element,String []ar) throws Exception
	{
		
		if(header_text.equalsIgnoreCase("Student Name"))
		{
			
			student_name=Assert.getInputTextValue(driver, element, "Student Name");
			ar[i]=student_name;
			System.out.println(student_name);
			stu_name_loc=i;
		}
		if(header_text.equalsIgnoreCase("Fee Description"))
		{
			 Fee_desc=Assert.getInputTextValue(driver, element, "Fee Description");
			 ar[i]=Fee_desc;
			 System.out.println(Fee_desc);
			 fee_description_loc=i;
		}
		/*if(header_text.equalsIgnoreCase("Quarter"))
		{
			 quarter=Assert.getInputTextValue(driver, element, "Quarter");
			 ar[arr_index++]=quarter;
			 System.out.println(quarter);
		}*/
		if(header_text.equalsIgnoreCase("Amount"))
		{
			 amount=Assert.getInputTextValue(driver, element, "Amount");
			 amount=amount.substring(6, amount.length());
			 ar[i]=amount;
			System.out.println(amount);
			Amount_loc=i;
		}
		
		return ar;
	}
	
	public String[] save_dataSample(String header_text, WebDriver driver,String element,String []ar) throws Exception
	{
		
		if(header_text.equalsIgnoreCase("Student Name"))
		{
			
			student_name=Assert.getInputTextValue(driver, element, "Student Name");
			ar[i]=student_name;
			System.out.println(student_name);
			stu_name_loc=i;
		}
		if(header_text.equalsIgnoreCase("Fee Description"))
		{
			 Fee_desc=Assert.getInputTextValue(driver, element, "Fee Description");
			 ar[i]=Fee_desc;
			 System.out.println(Fee_desc);
			 fee_description_loc=i;
		}
		/*if(header_text.equalsIgnoreCase("Quarter"))
		{
			 quarter=Assert.getInputTextValue(driver, element, "Quarter");
			 ar[arr_index++]=quarter;
			 System.out.println(quarter);
		}*/
		if(header_text.equalsIgnoreCase("Amount"))
		{
			 amount=Assert.getInputTextValue(driver, element, "Amount");
			 amount=amount.substring(6, amount.length());
			 ar[i]=amount;
			System.out.println(amount);
			Amount_loc=i;
		}
		
		return ar;
	}

	// to get the data from the MAP collection
	public  String[] get_list_fields(int row)
	{
		//Iterator itr=actual_data_list.iterator()
		return report_data_list.get(row);
	}
	
		
	// TO check payment details on Payment page
	public void check_Payment_details() throws Exception
	{	
		try {
		System.out.println("on payment page");
		
		String payment_page_tot,payment_page_amt, cov_fee;
		double expected_amt, actual_amt;
		if(single_select)
		{
			Assert.Clickbtn(driver, invoice_details_pay_now_button, "Pay Now on Invoice Details Page");
		}
		else
		{
			Assert.Clickbtn(driver, invoice_details_pay_now_button_multi, "Pay Now on Invoice Details Page");
		}
		Thread.sleep(3000);
		Assert.Clickbtn(driver, payment_page_see_surcharge_button, "View Surcharge");
		
		payment_page_tot=Assert.getInputTextValue(driver, payment_page_total, "Payment Page Total");
		cov_fee=Assert.getInputTextValue(driver, payment_page_surcharge_amt, "Surcharge");
		payment_page_amt=Assert.getInputTextValue(driver, payment_page_amt_with_surcharge, "Payment page total with Surcharge");
		System.out.println(payment_page_tot + cov_fee+payment_page_amt);
		
		double con_fee,total;
		
		con_fee=Double.parseDouble(cov_fee);
		total=Double.parseDouble(payment_page_tot);
		expected_amt=(con_fee+total);
		actual_amt=Double.parseDouble(payment_page_amt);
//		String str=expected_amt.concat("0");
		
		if(invoice_total_amt.equals(payment_page_tot)  && expected_amt==actual_amt )
		{
			Extent_Reporting.Log_Pass("Payment page Details are correct", "Passed");
			Extent_Reporting.Log_report_img("Payment Details are correct", "Passed", driver);
		}
		else
		{
			Extent_Reporting.Log_Fail("Payment Page Details are not correct", "Failed", driver);
		}
		
	}catch(Exception e)
	{
	Log.error("Test failed due to local host page not displayed");
	e.printStackTrace();
	}
	}
		
  public static int tblheadDesPositon;
  public static int tblheaderAmr;
//to check invoice details page data
      public void check_invoice_details() throws Exception
      {
    	  String [] pid = new String[no_of_invoices];
    	  boolean invoice_check_flag=false;
    	  String[] cmp_array;
    	  try {
    		  invoice_page_url=driver.getCurrentUrl();
    		  //System.out.println(invoice_page_url);
    		  String str_to_found="pid=";
    		  int pid_index=invoice_page_url.indexOf(str_to_found);
    		  roll_no=Assert.getInputTextValue(driver, invoice_roll_no, "Roll No");
    		  String pid_string=invoice_page_url.substring(pid_index+str_to_found.length(),invoice_page_url.length());
			  
    		  System.out.println(pid_string);
    		  int indx=pid_string.indexOf(",");
    		  //System.out.println(pid_string.substring(pid_string.indexOf(","),6));
    		  System.out.println(indx);
    			  int z=0;
    				  do
    			  {
    					  if(single_select)
    					  {
    						 pid[z]= pid_string;
    						 break;
    					  }
    					  else 
    					  {
    						  
    						  String str=pid_string.substring(pid_string.indexOf(","),pid_string.indexOf(",")+6) ;
    						  	pid[i]= str;
    				 			
    				 			
    					  }
    					  z++;
    			  }while(z<no_of_invoices);
    	  	String invoice_student_name=Assert.getInputTextValue(driver, invoice_details_student_name, "Student Name");
    		  List<WebElement> tblheader = driver.findElements(By.xpath(invoice_details_table_data));
    		  
    		  for( int i =0;i<tblheader.size();i++) {
    			  
    			  String tblHeaderDes = driver.findElement(By.xpath(invoice_details_table_data+"["+(i+1)+"]")).getText().trim();
    			 
    			  if(tblHeaderDes.contains("Description")) {
    				  
    				  tblheadDesPositon = (i+1); 
    				  continue;
    			  }
    			  if(tblHeaderDes.contains("Amount")) 
    			  {
    				  tblheaderAmr = (i+1); 
    				  continue;
    			  }
    		  }
    		List<WebElement> tbData = driver.findElements(By.xpath("(//div[@class='particular-table'])"));
    		for(int l=1;l<tbData.size();l++) 
    		{
				cmp_array= get_list_fields(l-1);	 		
				for(int k=1;k<tbData.size();k++)
				{
    			String DataName = driver.findElement(By.xpath("(//div[@class='particular-table'])["+(k+1)+"]/div["+tblheadDesPositon+"]")).getText().trim();
    			String Amtval = driver.findElement(By.xpath("(//div[@class='particular-table'])["+(k+1)+"]/div["+tblheaderAmr+"]")).getText().trim();		
    			System.out.println("DataName"+DataName);
    			System.out.println("Amtval"+Amtval); 		
    			if(DataName.contains(cmp_array[fee_description_loc]) && Amtval.contains(cmp_array[Amount_loc]) ) 
    			{
    				invoice_check_flag=true;
    				break;
    			}
    		}
    		}
    		invoice_total_amt=Assert.getInputTextValue(driver,invoice_details_total_amount , "Invoice Amount");
      		
      		invoice_total_amt=invoice_total_amt.substring(4, invoice_total_amt.length());
      		String report_total_fees=Double.toString(multi_line_total);
      		
      		if(invoice_total_amt.contains(".") && report_total_fees.contains("."))
      		{
      			
      		}
      		else 
      		{
      			invoice_total_amt=invoice_total_amt.concat(".00");
      			report_total_fees=report_total_fees.concat("0");
      		}
      		

      		
    		if(invoice_check_flag && report_total_fees.equals(invoice_total_amt) && student_name.equals(invoice_student_name) )
    		{
    			Extent_Reporting.Log_Pass("Invoice Page details are correct","Passed");
    			Extent_Reporting.Log_report_img("Invoice Page details are correct", "Passed", driver);
    		}
    		else
    		{
    			Extent_Reporting.Log_Fail("Invoice Page details are not correct", "Failed", driver);
    		}
    		
    	  }
    	  catch(Exception e) 
    	  {
    		  Log.error("Test failed due to local host page not displayed");
    			e.printStackTrace();
    	  }
    	  
      }
      
      
      public void check_invoice_detailsSample() throws Exception
      {
    	  String [] pid = new String[no_of_invoices];
    	  boolean invoice_check_flag=false;
    	  String[] cmp_array;
    	  try {
    	    invoice_page_url=driver.getCurrentUrl();
    		String str_to_found="pid=";
    		int pid_index=invoice_page_url.indexOf(str_to_found);
    		roll_no=Assert.getInputTextValue(driver, invoice_roll_no, "Roll No");
    		roll_no=Assert.getInputTextValue(driver, invoice_roll_no, "Roll No");
    		String pid_string=invoice_page_url.substring(pid_index+str_to_found.length(),invoice_page_url.length());		  
    		  System.out.println(pid_string);
    		String invoice_student_name=Assert.getInputTextValue(driver, invoice_details_student_name, "Stsudent Name");
    		List<WebElement> tblheader = driver.findElements(By.xpath(invoice_details_table_data));		  
    		  for( int i =0;i<tblheader.size();i++) {
    			  
    			  String tblHeaderDes = driver.findElement(By.xpath(invoice_details_table_data+"["+(i+1)+"]")).getText().trim();
    			 
    			  if(tblHeaderDes.contains("Description")) {
    				  
    				  tblheadDesPositon = (i+1); 
    				  continue;
    			  }
    			  if(tblHeaderDes.contains("Amount")) 
    			  {
    				  tblheaderAmr = (i+1); 
    				  continue;
    			  }
    		  }
    	
    		invoice_total_amt=Assert.getInputTextValue(driver,invoice_details_total_amount , "Invoice Amount");
      		
      		invoice_total_amt=invoice_total_amt.substring(4, invoice_total_amt.length());
      		String report_total_fees=Double.toString(multi_line_total);
      		
      		if(invoice_total_amt.contains(".") && report_total_fees.contains("."))
      		{
      			
      		}
      		else 
      		{
      			invoice_total_amt=invoice_total_amt.concat(".00");
      			report_total_fees=report_total_fees.concat("0");
      		}
      		

      		
    		if(invoice_check_flag && report_total_fees.equals(invoice_total_amt) && student_name.equals(invoice_student_name) )
    		{
    			Extent_Reporting.Log_Pass("Invoice Page details are correct","Passed");
    			Extent_Reporting.Log_report_img("Invoice Page details are correct", "Passed", driver);
    		}
    		else
    		{
    			Extent_Reporting.Log_Fail("Invoice Page details are not correct", "Failed", driver);
    		}
    		
    	  }
    	  catch(Exception e) 
    	  {
    		  Log.error("Test failed due to local host page not displayed");
    			e.printStackTrace();
    	  }
    	  
      }

      public void Credit_cardValidation() throws Throwable
      {
    	  boolean sandbox_mode=false;
  		try
  		{	
  			try
  			{
  				String sandbox_txt= driver.findElement(By.xpath(sandbox_text)).getText();
  				if(sandbox_txt.equals("You are in Sandbox mode (Your account will not be charged)"))
  				{
  					sandbox_mode=true;	
  				}
  				
  			}catch(Exception e)
  			{
  				System.out.println("Sandbox Mode is not active");
  				
  			}
  			if(sandbox_mode)
  			{
  			Assert.inputText(driver, CreditCardNoInput, Excel_Handling.Get_Data(TC_ID, "CardNumber").trim(), "Credit card Number input field");						  			  
  			//Assert.inputText(driver, CreditCardHolderName, Excel_Handling.Get_Data(TC_ID, "CardHolderName").trim(), "Credit   card Number Exp Date");
  			//CardName = driver.findElement(By.xpath(CreditCardHolderName)).getAttribute("data-pri-type");
  			Assert.inputText(driver, CreditCardExpDate,Excel_Handling.Get_Data(TC_ID, "CardExpDate").trim(), "Credit   card Number Exp Date");
  			Assert.inputText(driver, CreditCardCVVCode,Excel_Handling.Get_Data(TC_ID, "CardCVVCode").trim(), "Credit card Number CVVCode");
  			Assert.Clickbtn(driver, CreditCardMakePaymtBtn, "Credit Card make payment button");		   
  			Extent_Reporting.Log_report_img("Details has been Entered", "Passed", driver);
  			}
  			else
  			{
  				check_payment_cancel();
  			}

  		}catch(Exception e)	
  		{
  			Extent_Reporting.Log_Fail("Some fields are not disp", "Failed", driver);   
  			Log.error("Test failed due to page is navigating to payment page");
  			e.printStackTrace();
  			throw new Exception("Test failed due to local host page not displayed");
  		}
  	}
      
      
     public void check_payment_cancel() throws Exception
     {
    	 Assert.Clickbtn(driver, payment_page_cancel_btn, "Cancel payment");
    	   	 
    	 String orderid, res_amount, res_message = null; 
    	 List<WebElement> list_message= driver.findElements(By.xpath(receipt_message));
    	 String res_text=Assert.getInputTextValue(driver, cancel_receipt_text, "Cancel Receipt Text");
    	// System.out.println(res_text);
    	 for (int m = 1; m <= list_message.size(); m++) 
    	 {
    		 
    		 String res_msg=Assert.getInputTextValue(driver,receipt_message+"["+m+"]" , "Response Parameters");
    		 /*if(res_msg.equals("Order Id:"))
    		 {
    			 orderid=list_message.get(m).getText();
    			 continue;
    		 }
    		
    		 if(res_msg.equals("Amount:"))
    		 {
    			 res_amount=list_message.get(m).getText();
    			 continue;
    		 }*/ 
    		 if(res_msg.equals("Message:"))
    		 {
    			 res_message=driver.findElement(By.xpath(receipt_param+"["+m+"]")).getText();
    			 continue;
    		 }
    	 }
    	 
    	 	if(res_text.equals("  Your transaction was failed.") && res_message.equals("TRANSACTION CANCELLED"))
    	 	{
    	 		Extent_Reporting.Log_Pass("Cancellation Page details are correct", "Passed");
    	 		Extent_Reporting.Log_report_img("Cancellation Page details are correct", "Passed", driver);
    	 	}
    	 	else
    	 	{
    	 		Extent_Reporting.Log_Fail("Cancellation message is not correct", "Failed", driver);
    	 	}
    	 		
     }
      
     
     // THis method is used to check the receipt of the payment.
     public void check_success_payment() throws Exception
     {
    	 
    	 String  success_Msg_text=Assert.getInputTextValue(driver, receipt_message_text, "Sucess Message");
    	 String success_msg_param=Assert.getInputTextValue(driver, receipt_success_param, "Success Parameters");
    	 String text_found="amount";
    	 System.out.println(success_msg_param);
    	// System.out.println(success_msg_param.indexOf("amount"));
    	 
    	 int index=success_msg_param.indexOf(text_found)+text_found.length()+1; 	 
    	// System.out.println(index + invoice_total_amt.length());

    	 int end_index=invoice_total_amt.length();
    	 
    	 String receipt_amt=success_msg_param.substring(index,index+end_index);
    	 System.out.println(receipt_amt);
    	 //String success_amount= success_msg_param.substring();
    			 
    	 if(receipt_amt.equals(invoice_total_amt ))
    	 {
    		Extent_Reporting.Log_Pass("Payment Success with Correct Amount","Passed");
 			Extent_Reporting.Log_report_img("Payment Success Receipt ", "Passed", driver);
 	
    	 }
    	 else
    	 {
 			Extent_Reporting.Log_Fail("Payment Success with inCorrect Amount", "Failed", driver);

    	 }
    	 Thread.sleep(3000);
     }

     //This method is used to check the paid invoice from Admin login.
     public void check_paid_transaction(Create_Activity_BusinessLogic create_act) throws Exception
     {
    	 
    	 int action_position=0;
    	 create_act.Login("admin username","admin password");
    	 Thread.sleep(2000);
    	 Assert.Clickbtn(driver, report_menu, "Report Menu");
    	 Assert.Clickbtn(driver, student_report_sub_menu, "Student Report Sub Menu");
    	 Assert.Clickbtn(driver, advance_search, "Advanced Search");

    	 Assert.inputText(driver,roll_no_textbox , "test2", "Roll No");
/*    	     
    	 Select pay_status=new Select(driver.findElement(By.xpath(status)));
    	 pay_status.selectByValue("Y");
*/    	 Assert.Clickbtn(driver, search_button, "Search on Admin invoice");
    	 
    	 Thread.sleep(2000);    	 

      	 List<WebElement> invoice_click=driver.findElements(By.xpath(invoce_link));

      	 for(int p=0;p<invoice_click.size();p++)
      	 {
      		 invoice_click.get(p).click();
      		 Thread.sleep(1000);  
      		 String url= driver.getCurrentUrl();
      		 
      		 System.out.println(url);
      		 
      	 }
    	 
     }
    	 
    	 
    	 
    	 }
