package com.Airpay.BusinessLogic;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.Airpay.PageObject.Airpay_CAPanel_PageObject;
import com.Airpay.Reporting.Extent_Reporting;
import com.Airpay.TestData.Excel_Handling;
import com.Airpay.Utilities.ElementAction;
import com.Airpay.Utilities.Log;


public class SOTC_Application_BusinessLogic extends Airpay_CAPanel_PageObject {
	public WebDriver driver;
	public String TC_ID = "";
	ElementAction Assert = new ElementAction();
	public SOTC_Application_BusinessLogic(WebDriver driver, String TC_ID)
	{
		Log.info("AirPay_payment_Mode_Wallet_BusinessLogic");
		this.driver = driver;
		this.TC_ID=TC_ID;
	}

	/**
	 * @author sakole
	 * @throws Exception
	 */
	public static String title= "";
	public void CA_Panel_Login() throws Exception{
		try{

			String[] MA_URL = Excel_Handling.Get_Data(TC_ID, "CollectionURL").split(";");	
			driver.get(MA_URL[0]);		
			if(Assert.isElementDisplay(driver, CAloginID)){
				Assert.inputText(driver, CAloginID, Excel_Handling.Get_Data(TC_ID, "User_Name").trim(), "CA Panel USer ID");
				Assert.inputText(driver, CAPwd, Excel_Handling.Get_Data(TC_ID, "PWD").trim(), "Ca panel PWD");
				Extent_Reporting.Log_report_img("Login Details entered", "Passed", driver);
				Assert.clickButton(driver,CASubmitBtn, "Sign In button");
				Assert.waitForPageToLoad(driver);
				Thread.sleep(5000);
			}	
			for(int URL=0;URL<MA_URL.length;URL++){	
				System.out.println(MA_URL[URL]);
				driver.get(MA_URL[URL]);
				String title=   driver.getTitle();
				Extent_Reporting.Log_Pass("SOTC Logo is exist for :"+title, "Passed");
				Verify_SCOT_Logo();		   
				Assert.waitForPageToLoad(driver);			
				Verify_SCOT_Logo();
				//CA_Panel_Logout();
			}
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("SOTC Logo is exist for :"+title, "Failed", driver);
			throw new Exception("SCOT panel page issue");
		}
	}


	public void SOTC_Panel_Login() throws Exception{
		try{			
			String MA_URL = Excel_Handling.Get_Data(TC_ID, "CollectionURL").trim();	
			driver.get(MA_URL);		
			if(Assert.isElementDisplayed(driver, CAloginID,"SOTC Login")){
				Assert.inputText(driver, CAloginID, Excel_Handling.Get_Data(TC_ID, "User_Name").trim(), "CA Panel USer ID");
				Assert.inputText(driver, CAPwd, Excel_Handling.Get_Data(TC_ID, "PWD").trim(), "Ca panel PWD");
				Extent_Reporting.Log_report_img("Login Details entered", "Passed", driver);
				Assert.clickButton(driver,CASubmitBtn, "Sign In button");
				Assert.waitForPageToLoad(driver);
				Thread.sleep(10000);
			}				
			Verify_SCOT_Logo();			
			Assert.waitForPageToLoad(driver);			
			//CA_Panel_Logout();
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("SCOT Panel User field does not exist", "Failed", driver);
			throw new Exception("SCOT panel page issue");
		}
	}
	
	public void Invoice_Panel_Login() throws Exception{
		try{			
			String MA_URL = Excel_Handling.Get_Data(TC_ID, "CollectionURL").trim();	
			driver.get(MA_URL);		
			if(Assert.isElementDisplayed(driver, CAloginID,"SOTC Login")){
				Assert.inputText(driver, CAloginID, Excel_Handling.Get_Data(TC_ID, "User_Name").trim(), "CA Panel USer ID");
				Assert.inputText(driver, CAPwd, Excel_Handling.Get_Data(TC_ID, "PWD").trim(), "Ca panel PWD");
				Extent_Reporting.Log_report_img("Login Details entered", "Passed", driver);
				Assert.clickButton(driver,CASubmitBtn, "Sign In button");
				Assert.waitForPageToLoad(driver);
				Thread.sleep(10000);
			}				
			Assert.waitForPageToLoad(driver);			
			//CA_Panel_Logout();
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("SCOT Panel User field does not exist", "Failed", driver);
			throw new Exception("SCOT panel page issue");
		}
	}
	
	public void Invoice_Write_Data() throws Exception{
		try{	
			Excel_Handling.Put_Data(TC_ID, "Write_DataTest", "Pass");
			String MA_URL = Excel_Handling.Get_Data(TC_ID, "CollectionURL").trim();	
			driver.get(MA_URL);	
			if(Assert.isElementDisplayed(driver, CAloginID,"SOTC Login")){
				
				
				//Assert.inputText(driver, CAloginID, Excel_Handling.Put_Data_Replace(TC_ID, "Write_DataTest", "Pass").trim(), "CA Panel USer ID");
			
			}				
			Assert.waitForPageToLoad(driver);			
			//CA_Panel_Logout();
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("SCOT Panel User field does not exist", "Failed", driver);
			throw new Exception("SCOT panel page issue");
		}
	}
	
	
	
	
	public void InvoiceLogoVerify(String xpath) throws Exception{
		try{							
			if(Assert.isElementDisplayed(driver, xpath,"Logo Invoice")){
				Assert.Verify_Image(driver, xpath, "Invoice Logo");			
				Assert.waitForPageToLoad(driver);
				Thread.sleep(10000);
			}else{
				Extent_Reporting.Log_report_img("Logo does not exist", "Failed", driver);
			}
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_report_img("Logo does not exist", "Failed", driver);
			throw new Exception("SCOT panel page issue");
		}
	}
	
	
	public void InvoiceImportBtnVerify() throws Exception {
		try{							
			if(Assert.isElementDisplayed(driver, InvoiceImportBtn,"Import Button")){
				Assert.Clickbtn(driver, InvoiceImportBtn, "Import Button");			
				Assert.waitForPageToLoad(driver);
				InvoiceLogoVerify(InvoiceLogo);
				Assert.isElementDisplayed(driver, ChooseLink, "Choose Link Field");
				Assert.isElementDisplayed(driver, InvoiceSendMail, "Invoice ");
				Assert.isElementDisplayed(driver, InvoiceSendSMS, "Choose Link Field");
				Assert.isElementDisplayed(driver, ExportSubmitBtn, "Choose Link Field");
				Thread.sleep(10000);
			}else{
				Extent_Reporting.Log_report_img("Import Button does not exist", "Failed", driver);
			}
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_report_img("Import Button does not exist", "Failed", driver);
			throw new Exception("SCOT panel page issue");
		}
	}
	
	public void TanishqImportBtnVerify() throws Exception {
		try{							
			if(Assert.isElementDisplayed(driver, InvoiceExportBtn,"Import Button")){
				Assert.isElementDisplayed(driver, InvoiceExportBtn, "Choose Link Field");
				Assert.Clickbtn(driver, ManualTanishqBtn, "manual Button");			
				Assert.waitForPageToLoad(driver);
				InvoiceLogoVerify(TanishqLogo);
				Assert.isElementDisplayed(driver, DownLoadManual, "DownLoad Link Field");
				Assert.isElementDisplayed(driver, ViewManual, "View Link Field");				
				Extent_Reporting.Log_report_img("Import Button is exist as expected", "passed", driver);
			}else{
				Extent_Reporting.Log_report_img("Import Button does not exist", "Failed", driver);
			}
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_report_img("Import Button does not exist", "Failed", driver);
			throw new Exception("SCOT panel page issue");
		}
	}
	
	
	public void InvoiceExportsBtnVerify() throws Exception {
		try{							
			if(Assert.isElementDisplayed(driver, InvoiceExportBtn,"Export Button")){
				Assert.Clickbtn(driver, InvoiceExportBtn, "Export Button");			
				Assert.waitForPageToLoad(driver);
				InvoiceLogoVerify(InvoiceLogo);
				Extent_Reporting.Log_Pass("Export Button Is exist", "Passed");
				Extent_Reporting.Log_report_img("Screen print", "Passed", driver);
				Thread.sleep(10000);
			}else{
				Extent_Reporting.Log_report_img("Export Button does not exist", "Failed", driver);
			}
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_report_img("Export Button does not exist", "Failed", driver);
			throw new Exception("Export Button");
		}
	}
	
	
	
	public static String ImageVerify = null; 

	public void Verify_SCOT_Logo() throws Exception {
		try{ 

			ImageVerify = Excel_Handling.Get_Data(TC_ID, "Image_Name").trim();
			String Logo = "//img[contains(@src,'"+ImageVerify+"')]";
			Log.info("Navigating To Payment Page");	
			Assert.waitForPageToLoad(driver);
			Thread.sleep(2000);
			if(Assert.isElementDisplayed(driver, Logo, "DashBoard" ))
			{         	
				Assert.Verify_Image(driver, Logo, ImageVerify +"Logo");
				Assert.isElementDisplayed(driver, Logo, ImageVerify +"Fav icon");
				Extent_Reporting.Log_report_img("Respective Details is exist", "Passed", driver);
			}else{
				Extent_Reporting.Log_Fail(ImageVerify +"Logo  page does not exis",	"Failed",driver);
				Log.error(ImageVerify +"Logo page not successfully displayed");
				throw new Exception(ImageVerify+"Logo does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail(ImageVerify +"Logo does not exist",	"Failed",driver);
			Log.error(ImageVerify+" Logo does not exist does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to Logo does not displayed");
		}
	}

	public void Verify_SCOT_MerchantLogo() throws Exception {
		try{ 
			Log.info("Navigating To Payment Page");
			String Merchantlogo = Excel_Handling.Get_Data(TC_ID, "SRCXpath").trim();
			Assert.waitForPageToLoad(driver);
			Thread.sleep(2000);
			if(Assert.isElementDisplayed(driver, "//img[@src='"+Merchantlogo+"']", "DashBoard" ))
			{         	
				Assert.Verify_Image(driver, "//img[@src='"+Merchantlogo+"']", ImageVerify +"Logo");
				Assert.isElementDisplayed(driver, "//img[@src='"+Merchantlogo+"']", ImageVerify+" Fav icon");
				Extent_Reporting.Log_report_img("Respective Details is exist", "Passed", driver);
			}else{
				Extent_Reporting.Log_Fail(ImageVerify+" Logo  page does not exis",	"Failed",driver);
				Log.error(ImageVerify+" Logo page not successfully displayed");
				throw new Exception(ImageVerify+" Logo does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail(ImageVerify+" Logo does not exist",	"Failed",driver);
			Log.error(ImageVerify+" Logo does not exist does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to Logo does not displayed");
		}
	}


	public static int flag;
	public void Manage_Invoices_payNow() throws Exception{
		try{

			List<WebElement> tblHeaderCount = driver.findElements(By.xpath("(//table)[1]//thead/tr/th"));
			System.out.println("TotHeader: "+tblHeaderCount.size() );
			for(int i=0;i<tblHeaderCount.size();i++){
				String tblHeader = driver.findElement(By.xpath("(//table)[1]//thead/tr/th["+(i+1)+"]")).getText().trim();
				if(tblHeader.contains("Payment Url")){
					flag = (i+1);
					System.out.println("flagValue: "+flag);
					break;

				}else if(i==(tblHeaderCount.size()-1)){
					Extent_Reporting.Log_Fail("There is no header of Payment URL", "Failed", driver);
				}
			}					
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("SCOT Logo does not exist",	"Failed",driver);
			Log.error("SCOT Logo does not exist does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to Logo does not displayed");
		}



	}

	public void Manage_invoicePayNowLink() throws Throwable{
		try{
			List<WebElement> tblbodyCount = driver.findElements(By.xpath("(//table)[1]//tbody/tr"));
			System.out.println("TotHeader: "+tblbodyCount.size() );
			for(int i=0;i<tblbodyCount.size();i++){
				String tblHeader = driver.findElement(By.xpath("(//table)[1]//tbody/tr["+(i+1)+"]/td["+flag+"]")).getText().trim();
				if(tblHeader.contains("Pay Now")){
					Extent_Reporting.Log_Pass("pay now find out the records at:"+(i)+" Position" , "Passed");
					Extent_Reporting.Log_report_img("Image of Pay Now", "Passed", driver);
					Assert.Javascriptexecutor_forClick(driver, "(//table)[1]//tbody/tr["+(i+1)+"]/td["+flag+"]/a", "Pay Now link");
					MAPanelWindow = driver.getCurrentUrl();			
					break;
				}else if(i==tblbodyCount.size()-1){
					if(Assert.isElementDisplayed(driver, "//a[contains(text(),'Next')]", "Next")){
						Assert.Clickbtn(driver, "//a[contains(text(),'Next')]", "Next Button");
						Manage_invoicePayNowLink();					
					}else{
						Extent_Reporting.Log_Fail("There are no records in pay Now Status", "Failed", driver);
					}
				}
			}			
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail(ImageVerify+" Logo does not exist",	"Failed",driver);
			Log.error(ImageVerify+" Logo does not exist does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to Logo does not displayed");
		}
	}
	public static String MAPanelWindow = "";
	public  static String[] browser =null;
	public static String child = null;

	public void TermsAndCondition()throws Exception{
		try{

			Log.info("Navigating To Net Banking Page");	
			Assert.waitForPageToLoad(driver);
			//((JavascriptExecutor) driver).executeScript("window.open();");
			Set<String> handles = driver.getWindowHandles();
			browser =	handles.toArray(new String[0]);
			System.out.println("Number of browsers opened are"+ browser.length);
			for (int i=0; i<handles.size();i++)
			{
				try
				{
					driver.switchTo().window(browser[i]);
					System.out.println(driver.getCurrentUrl());
					child =driver.getCurrentUrl();
					System.out.println("child:"+child);
					if(child.contains(Excel_Handling.Get_Data(TC_ID, "Invoice_Link").trim())){
						//if(child.contains("https://sotc.nowpay.co.in/invoice/")){
						System.out.println(driver.getCurrentUrl()+"found");
						Verify_SCOT_Logo();
						Assert.isElementDisplayed(driver, "//*[contains(text(),'Booking/Invoice No')]", "Booking Invoice Page");
						Extent_Reporting.Log_Pass("Pay Now Invoice page is exist", "passed");
						Extent_Reporting.Log_report_img("Invoice Page", "Passed", driver);
						WebElement hitLocation = driver.findElement(By.className(AcceptTermsAndCondition));
						new Actions(driver)
						.moveToElement(hitLocation, 1, 1)
						.click()
						.perform();
						Assert.Clickbtn(driver, CASubmitBtn, "Submit button");
						Card_Details_Options();
						Verify_SCOT_MerchantLogo();						
						driver.getWindowHandle();						
						Assert.waitForPageToLoad(driver);						
						break;
					}
				}
				catch(Throwable t)
				{
					System.out.println("Browser not opened");
				}
			}		





		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("SCOT Logo does not exist",	"Failed",driver);
			Log.error("SCOT Logo does not exist does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to Logo does not displayed");
		}







	}


	public void Card_Details_Options() throws Exception {
		try{ 
			Assert.waitForPageToLoad(driver);
			Thread.sleep(5000);
			Assert.waitForPageToLoad(driver);		
			List<WebElement> Channels = driver.findElements(By.xpath(AirpayChannals));
			int ChannelsCnt = Channels.size();
			System.out.println("Channels count is:"+ChannelsCnt);
			for(int i=0; i<ChannelsCnt;i++)
			{
				WebElement ChannelsName = Channels.get(i);
				String name = ChannelsName.getText();
				System.out.println("Channel Name: "+name);

			}
			Extent_Reporting.Log_report_img("All channels are exist as expected", "Passed", driver);  
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Test failed due to Page not redirecting to Merchant site", "Failed", driver);
			Log.error("Test failed due to Page not redirecting to Merchant site");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	public void CA_Panel_Logout() throws Exception
	{
		try{
			Thread.sleep(5000);
			if(Assert.isElementDisplay(driver, LogoutIcon)){
				Assert.clickButton(driver, LogoutIcon, "profile iocn");
				Extent_Reporting.Log_report_img("Profile icon", "Passed", driver);
				Assert.Clickbtn(driver, CALogOutBtn, "Log out button");
				Extent_Reporting.Log_report_img("Successfully logout", "Passed", driver);

			}	
			Verify_SCOT_Logo();
		}catch(Exception e)	{
			Extent_Reporting.Log_Fail(" Profile logo does not exist",	"Failed",driver);
			Log.error("Profile Logo does not exist does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to Profile Logo does not displayed");
		}
	}	
	public void CAPanel_HeaderTabs() throws Exception {
		try{ 
			Log.info("Navigating To Payment Page");	
			Assert.waitForPageToLoad(driver);		
			List<WebElement> Channels = driver.findElements(By.xpath(HeaderTabsVerify));
			int ChannelsCnt = Channels.size();
			System.out.println("Channels count is:"+ChannelsCnt);
			for(int i=0; i<ChannelsCnt;i++)
			{
				WebElement ChannelsName = Channels.get(i);
				String name = ChannelsName.getText().trim();
				if(Excel_Handling.Get_Data(TC_ID, "CAHeaderTabs").trim().contains(name)){
					ChannelsName.click();
					Extent_Reporting.Log_Pass("Tab Name is :" +name, "Passed");			
					Extent_Reporting.Log_report_img("Header Tab", "ScreenShot", driver);
				}
				if(i==ChannelsCnt){
					Extent_Reporting.Log_Fail("Check the datasheet for CA paenl Header tab", "Failed", driver);
				}
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Tab does not exist",	"Failed",driver);
			Log.error("Tab does not exist");
			e.printStackTrace();
			throw new Exception("Tab does not exist");
		}
	}
	public static String geturl= "";
	@SuppressWarnings("unused")
	public void InvoicePending() throws Exception {
		try{ 
			Log.info("Navigating To Payment Page");	
			Assert.waitForPageToLoad(driver);	
			Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, InvoiceStatusDropDown, Excel_Handling.Get_Data(TC_ID, "InvoiceFilterStatus"), "Invoice Status Drop down");
			Thread.sleep(5000);
			List<WebElement> Channels = driver.findElements(By.xpath(Invoicetable));
			int ChannelsCnt = Channels.size();
			System.out.println("Channels count is:"+ChannelsCnt);
			for(int i=0; i<ChannelsCnt;i++)
			{				
				String name = driver.findElement(By.xpath(Invoicetable+"["+(i+1)+"]")).getText().trim();
				System.out.println("Table Value: "+name);	
				if(name.equalsIgnoreCase("Invoice"))
				{
					List<WebElement> TblRows = driver.findElements(By.xpath(InvoiceTblRows));
					for(int j=0;j<TblRows.size();j++)
					{							
						Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, InvoiceStatusDropDown, Excel_Handling.Get_Data(TC_ID, "InvoiceFilterStatus").trim(), "Invoice Status Drop down");
							Extent_Reporting.Log_Pass("payment Pending Records are exist", "passed");
							Extent_Reporting.Log_report_img("Screen print", "passed", driver);
							InvocieNumber = driver.findElement(By.xpath(InvoiceTblRows+"["+(j+1)+"]/td["+(i+1)+"]")).getText().trim();
							geturl = driver.getCurrentUrl();
							MAPanelWindow = geturl.concat("/"+InvocieNumber);
							break;						
										
					}	
					Extent_Reporting.Log_Pass("payment Url Pay Header ", "Passed");			
					Extent_Reporting.Log_report_img("Header Tab is exist", "ScreenShot", driver);								 
				}
				
				if(name.contains("Payment Url"))
				{
					List<WebElement> TblRows = driver.findElements(By.xpath(InvoiceTblRows));
					for(int j=0;j<TblRows.size();j++)
					{							
						Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, InvoiceStatusDropDown, Excel_Handling.Get_Data(TC_ID, "InvoiceFilterStatus"), "Invoice Status Drop down");
						String PaidLink = driver.findElement(By.xpath(InvoiceTblRows+"["+(j+1)+"]/td["+(i+1)+"]")).getText().trim();
						if(PaidLink.contains("link"))
						{
							Extent_Reporting.Log_Pass("payment Pending Records are exist", "passed");
							Extent_Reporting.Log_report_img("Screen print", "passed", driver);							
							Assert.clickButton(driver, InvoiceTblRows+"["+(j+1)+"]/td["+(i+1)+"]/a", "payment URL link");							
							break;						
						}else{
							Extent_Reporting.Log_Fail("payment url link does not exist", "Failed", driver);
						}
						
					}	
					Extent_Reporting.Log_Pass("payment Url Pay Header ", "Passed");			
					Extent_Reporting.Log_report_img("Header Tab is exist", "ScreenShot", driver);
					break;					
				}
				if(i==ChannelsCnt){
					Extent_Reporting.Log_Fail("Invoice Pay Status Header does not exist", "Failed", driver);
				}
				
				
			}
		}catch(Exception e){
			Extent_Reporting.Log_Fail("Respective Header does not exists",	"Failed",driver);
			Log.error("Tab does not exist");
			e.printStackTrace();
			throw new Exception("Tab does not exist");
		}
	}
	public static String InvocieNumber="";
	public void InvoiceCrossVerify() throws Exception {
		try{ 
			Log.info("Navigating To Payment Page");				
			Assert.waitForPageToLoad(driver);
			Thread.sleep(5000);
			//driver.close();
			driver.switchTo().window(browser[0]);
			Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, InvoiceStatusDropDown, "All", "Invoice Status Drop down");
			Thread.sleep(2000);
			List<WebElement> Channels = driver.findElements(By.xpath(Invoicetable));
			int ChannelsCnt = Channels.size();
			System.out.println("Channels count is:"+ChannelsCnt);
			for(int i=0; i<ChannelsCnt;i++)
			{				
				String name = driver.findElement(By.xpath(Invoicetable+"["+(i+1)+"]")).getText().trim();
				System.out.println("Table Value: "+name);	
				if(name.equalsIgnoreCase("Invoice"))
				{
					Assert.inputText(driver, InvoiceNumberEnter, InvocieNumber, "Invoice Entered");
					Thread.sleep(5000);
					List<WebElement> TblRows = driver.findElements(By.xpath(InvoiceTblRows));
					for(int j=0;j<TblRows.size();j++)
					{							
						Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, InvoiceStatusDropDown, Excel_Handling.Get_Data(TC_ID, "InvoiceFilterStatus"), "Invoice Status Drop down");
						String PaidLink = driver.findElement(By.xpath(InvoiceTblRows+"["+(j+1)+"]/td["+(i+1)+"]")).getText().trim();
						if(PaidLink.contains("Paid"))
						{
							Extent_Reporting.Log_Pass("Transaction Moved into paid Status as expected", "passed");
							Extent_Reporting.Log_report_img("Screen print", "passed", driver);							
							break;						
						}						
					}	
					Extent_Reporting.Log_Pass("payment Url Pay Header ", "Passed");			
					Extent_Reporting.Log_report_img("Header Tab is exist", "ScreenShot", driver);								 
				}
				
			}
		}catch(Exception e){
			Extent_Reporting.Log_Fail("Respective Transaction Not yet done or else expired",	"Failed",driver);
			Log.error("Tab does not exist");
			e.printStackTrace();
			throw new Exception("Tab does not exist");
		}
	}
	
	
	
	public void InvoiceExtendCrossVerify() throws Exception {
		try{ 
			Log.info("Navigating To Payment Page");				
			Assert.waitForPageToLoad(driver);
			Thread.sleep(5000);
			//driver.close();
			driver.switchTo().window(browser[0]);
			Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, InvoiceStatusDropDown, "All", "Invoice Status Drop down");
			Thread.sleep(2000);
			List<WebElement> Channels = driver.findElements(By.xpath(Invoicetable));
			int ChannelsCnt = Channels.size();
			System.out.println("Channels count is:"+ChannelsCnt);
			for(int i=0; i<ChannelsCnt;i++)
			{				
				String name = driver.findElement(By.xpath(Invoicetable+"["+(i+1)+"]")).getText().trim();
				System.out.println("Table Value: "+name);	
				if(name.equalsIgnoreCase("Invoice"))
				{
					Assert.inputText(driver, InvoiceNumberEnter, InvocieNumber, "Invoice Entered");
					Thread.sleep(5000);
					List<WebElement> TblRows = driver.findElements(By.xpath(InvoiceTblRows));
					Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, InvoiceStatusDropDown, "Paid", "Invoice Status Drop down");
					for(int j=0;j<TblRows.size();j++)
					{							
						String PaidLink = driver.findElement(By.xpath(InvoiceTblRows+"["+(j+1)+"]/td["+(i+1)+"]")).getText().trim();
						if(PaidLink.contains("Paid"))
						{
							Extent_Reporting.Log_Pass("Transaction Moved into paid Status as expected", "passed");
							Extent_Reporting.Log_report_img("Screen print", "passed", driver);							
							break;						
						}						
					}	
					Extent_Reporting.Log_Pass("payment Url Pay Header ", "Passed");			
					Extent_Reporting.Log_report_img("Header Tab is exist", "ScreenShot", driver);								 
				}
				
			}
		}catch(Exception e){
			Extent_Reporting.Log_Fail("Respective Transaction Not yet done or else expired",	"Failed",driver);
			Log.error("Tab does not exist");
			e.printStackTrace();
			throw new Exception("Tab does not exist");
		}
	}
	
	public void Invoicetable() throws Exception {
		try{ 
			Log.info("Navigating To Payment Page");	
			Assert.waitForPageToLoad(driver);	
			Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, InvoiceStatusDropDown, Excel_Handling.Get_Data(TC_ID, "InvoiceFilterStatus"), "Invoice Status Drop down");
			Thread.sleep(2000);
			List<WebElement> Channels = driver.findElements(By.xpath(Invoicetable));
			int ChannelsCnt = Channels.size();
			System.out.println("Channels count is:"+ChannelsCnt);
			for(int i=0; i<ChannelsCnt;i++)
			{				
				String name = driver.findElement(By.xpath(Invoicetable+"["+(i+1)+"]")).getText().trim();
				System.out.println("Table Value: "+name);
				if(name.contains("Invoice Status"))
				{
					List<WebElement> TblRows = driver.findElements(By.xpath(InvoiceTblRows));
					for(int j=0;j<TblRows.size();j++){							
						Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, InvoiceStatusDropDown, Excel_Handling.Get_Data(TC_ID, "InvoiceFilterStatus"), "Invoice Status Drop down");
						String PaidLink = driver.findElement(By.xpath(InvoiceTblRows+"["+(j+1)+"]/td["+(i+1)+"]")).getText().trim();
						System.out.println("RecordName: "+PaidLink);
						String InvoiceFilter = Excel_Handling.Get_Data(TC_ID, "InvoiceFilterStatus").trim();
						switch(InvoiceFilter){						
						case "Paid":
							        if(PaidLink.contains("Paid"))
							        {
								    Extent_Reporting.Log_Pass("Record Is in :"+PaidLink+" Status", "Passed");							
									}else{
										Extent_Reporting.Log_Fail("Filter Option Not working as expecetd", "Failed", driver);
									}	
							        break;
						case "Unpaid":
							 		if(PaidLink.contains("Pending"))
							 		{
							 			Extent_Reporting.Log_Pass("Record Is in :"+PaidLink+" Status", "Passed");							
							 		}else{
									Extent_Reporting.Log_Fail("Filter Option Not working as expecetd", "Failed", driver);
							 		}								
							 		break;	
						case "All":
									if(PaidLink.contains("Unpaid")||PaidLink.contains("paid"))
							 		{
							 			Extent_Reporting.Log_Pass("First Record Is in :"+PaidLink+" Status", "Passed");							
							 		}else{
									Extent_Reporting.Log_Fail("Filter Option Not working as expecetd", "Failed", driver);
							 		}								
							 		break;
						}
						int temp = j+1;
						if(temp==(TblRows.size()))
						{
							break;
						}
						System.out.println("j"+ j);
							
					}				
					Extent_Reporting.Log_Pass("Invoice Pay Header ", "Passed");			
					Extent_Reporting.Log_report_img("Header Tab is exist", "ScreenShot", driver);
					break;					
				}
				if(i==ChannelsCnt){
					Extent_Reporting.Log_Fail("Invoice Pay Status Header does not exist", "Failed", driver);
				}
			}
		}catch(Exception e){
			Extent_Reporting.Log_Fail("Respective Header does not exists",	"Failed",driver);
			Log.error("Tab does not exist");
			e.printStackTrace();
			throw new Exception("Tab does not exist");
		}
	}
	
	
	public void tanishqtable() throws Exception {
		try{ 
			Log.info("Navigating To Payment Page");	
			Assert.waitForPageToLoad(driver);	
			Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, InvoiceStatusDropDown, Excel_Handling.Get_Data(TC_ID, "InvoiceFilterStatus"), "Invoice Status Drop down");
			Thread.sleep(2000);
			List<WebElement> Channels = driver.findElements(By.xpath(Invoicetable));
			int ChannelsCnt = Channels.size();
			System.out.println("Channels count is:"+ChannelsCnt);
			for(int i=0; i<ChannelsCnt;i++)
			{				
				String name = driver.findElement(By.xpath(Invoicetable+"["+(i+1)+"]")).getText().trim();
				System.out.println("Table Value: "+name);
				if(name.contains("Invoice Status"))
				{
					List<WebElement> TblRows = driver.findElements(By.xpath(InvoiceTblRows));
					for(int j=0;j<TblRows.size();j++){							
						Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, InvoiceStatusDropDown, Excel_Handling.Get_Data(TC_ID, "InvoiceFilterStatus"), "Invoice Status Drop down");
						String PaidLink = driver.findElement(By.xpath(InvoiceTblRows+"["+(j+1)+"]/td["+(i+1)+"]")).getText().trim();
						System.out.println("RecordName: "+PaidLink);
						String InvoiceFilter = Excel_Handling.Get_Data(TC_ID, "InvoiceFilterStatus").trim();
						switch(InvoiceFilter){						
						case "Paid":
							        if(PaidLink.contains("Paid"))
							        {
								    Extent_Reporting.Log_Pass("Record Is in :"+PaidLink+" Status", "Passed");							
									}else{
										Extent_Reporting.Log_Fail("Filter Option Not working as expecetd", "Failed", driver);
									}	
							        break;
						case "Pending":
							 		if(PaidLink.contains("Pending"))
							 		{
							 			Extent_Reporting.Log_Pass("Record Is in :"+PaidLink+" Status", "Passed");							
							 		}else{
									Extent_Reporting.Log_Fail("Filter Option Not working as expecetd", "Failed", driver);
							 		}								
							 		break;	
						case "All":
									if(PaidLink.contains("Unpaid")||PaidLink.contains("paid"))
							 		{
							 			Extent_Reporting.Log_Pass("First Record Is in :"+PaidLink+" Status", "Passed");							
							 		}else{
									Extent_Reporting.Log_Fail("Filter Option Not working as expecetd", "Failed", driver);
							 		}								
							 		break;
						}
						int temp = j+1;
						if(temp==(TblRows.size()))
						{
							break;
						}
						if(temp==5)
						{
							break;
						}
						System.out.println("j"+ j);
							
					}				
					Extent_Reporting.Log_Pass("Invoice Pay Header ", "Passed");			
					Extent_Reporting.Log_report_img("Header Tab is exist", "ScreenShot", driver);
					break;					
				}
				if(i==ChannelsCnt){
					Extent_Reporting.Log_Fail("Invoice Pay Status Header does not exist", "Failed", driver);
				}
			}
		}catch(Exception e){
			Extent_Reporting.Log_Fail("Respective Header does not exists",	"Failed",driver);
			Log.error("Tab does not exist");
			e.printStackTrace();
			throw new Exception("Tab does not exist");
		}
	}
	
	
	
	
	
	
	
	
	public void Credit_cardProvidingValues() throws Exception{
		try{		   		   
			Assert.inputText(driver, CreditCardNoInput, Excel_Handling.Get_Data(TC_ID, "ValidCardNumber").trim(), "Credit card Number input field");
			Assert.inputText(driver, CreditCardHolderName,Excel_Handling.Get_Data(TC_ID, "CardHolderName").trim(), "Credit card Holder Name Field");		   
			Assert.inputText(driver, CreditCardExpDate,Excel_Handling.Get_Data(TC_ID, "CardExpDate").trim(), "Credit card Number Exp Date");
			Assert.inputText(driver, CreditCardCVVCode,Excel_Handling.Get_Data(TC_ID, "CardCVVCode").trim(), "Credit card Number CVVCode");
			Extent_Reporting.Log_report_img("Credit Card details entered", "Passed", driver);
			Assert.Clickbtn(driver, CreditCardMakePaymtBtn, "Credit Card make payment button");		   
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fields or else data issue is exist", "Failed", driver);   
			Log.error("Test failed due Some fields or else data issue is exist");
			e.printStackTrace();
			throw new Exception("Some fields or else data issue is exist");
		}
	}

	
	public void SinglePage_Link() throws Exception{
		try{

			String[] MA_URL = Excel_Handling.Get_Data(TC_ID, "CollectionURL").split(";");	
			for(int URL=0;URL<MA_URL.length;URL++){	
				System.out.println(MA_URL[URL]);
				Thread.sleep(2000);
				driver.get(MA_URL[URL]);
				String title=   driver.getTitle();
				Extent_Reporting.Log_Pass("Link for: "+MA_URL[URL], "Passed");
				Extent_Reporting.Log_Pass("Resoective Logo is exist for :"+title, "Passed");
				SinglePageConstant_Field();
				DynamicSinglepage_field();	
				sumbitBtn();
				SinglePager_PaymentPage_BusinessLogic AirPay_Local = new SinglePager_PaymentPage_BusinessLogic(driver, TC_ID);
				AirPay_Local.Card_Details_Options();	
				
				if(AirPay_Local.SandBoxMode("You are in Sandbox mode (Your account will not be charged)")==true)
				{
					Credit_cardProvidingValues();
					AirPay_Local.Cash_paymentSuccessMesg();
				}else{
					AirPay_Local.FooterVerify();
					AirPay_Local.FailedTransaction();					
				}
			}
		}catch(Exception t){
			t.printStackTrace();
			throw new Exception("SCOT panel page issue");
		}
	}

	
	public void InvoiceSinglePage_Link() throws Exception{
		try{
			//Create invoicepay invoice use this function
			String[] MA_URL = Excel_Handling.Get_Data(TC_ID, "CollectionURL").split(";");	
			for(int URL=0;URL<MA_URL.length;URL++){	
				System.out.println(MA_URL[URL]);
				Thread.sleep(2000);
				driver.get(MA_URL[URL]);
				String title=   driver.getTitle();
				Extent_Reporting.Log_Pass("Link for: "+MA_URL[URL], "Passed");
				Extent_Reporting.Log_Pass("Resoective Logo is exist for :"+title, "Passed");
				InvoiceSinglePageConstant_Field();
				//DynamicSinglepage_field();	
				InvoiceCreateBtn();
				InvoiceNavigatePage();
				InvoiceSinglePageCrossVerifyData();
				InvoiceTermsAndCondition_Field();
				InvoiceTermsAndConditionChkPay_Field();
				SinglePager_PaymentPage_BusinessLogic AirPay_Local = new SinglePager_PaymentPage_BusinessLogic(driver, TC_ID);
				AirPay_Local.Card_Details_Options();
				if(AirPay_Local.SandBoxMode("You are in Sandbox mode (Your account will not be charged)")==true)
				{
					Credit_cardProvidingValues();
					AirPay_Local.InvoiceCash_paymentSuccessMesg();
				}else{
					AirPay_Local.FooterVerify();
					AirPay_Local.InvoiceFailedTransaction();					
				}
			}
		}catch(Exception t){
			t.printStackTrace();
			throw new Exception("SCOT panel page issue");
		}
	}
	
	
	
	
	
	public void SinglePageConstant_Field() throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, ImgLogo, "Image Logo")){
				Assert.Verify_Image(driver, ImgLogo, "Airpay Logo");		
				Assert.inputText(driver, FirstName, Excel_Handling.Get_Data(TC_ID, "First_Name").trim(), "First Name");
				Assert.inputText(driver, LastName, Excel_Handling.Get_Data(TC_ID, "Last_Name").trim(), "Second Name");
 				Assert.inputText(driver, Email, Excel_Handling.Get_Data(TC_ID, "Email").trim(), "Email");
				Assert.inputText(driver, PhoneNumber, Excel_Handling.Get_Data(TC_ID, "PhoneNumber").trim(), "Phone Number");
				Assert.inputText(driver, SinglepageAddress, Excel_Handling.Get_Data(TC_ID, "Address").trim(), "Address");
				

				String Value = driver.findElement(By.xpath(AmountField)).getAttribute("readonly");
				System.out.println("Value Amount: "+Value);
				if(Value==null){
					Assert.inputText(driver, AmountField, Excel_Handling.Get_Data(TC_ID, "AmoutField").trim(), "Amount");
				}
				Extent_Reporting.Log_Pass("Constant Field Entered", "Passed");
				Extent_Reporting.Log_report_img("Fields Entered", "Passed", driver);
			}else{
				Extent_Reporting.Log_Fail("Image Logo does not exist", "Failed", driver);
			}
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Image Logo is exist for :"+title, "Failed", driver);
			throw new Exception("Image panel page issue");
		}
	}

	
	
	public void InvoiceSinglePageConstant_Field() throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, InvoiceLogo, "Image Logo")){
				Assert.Verify_Image(driver, InvoiceLogo, "Airpay Logo");		
				Assert.inputText(driver, InvoicePageFirstName, Excel_Handling.Get_Data(TC_ID, "First_Name").trim(), "First Name");
				Assert.inputText(driver, InvoicePageLastName, Excel_Handling.Get_Data(TC_ID, "Last_Name").trim(), "Second Name");
				Assert.inputText(driver, InvoiceEmail, Excel_Handling.Get_Data(TC_ID, "Email").trim(), "Email");
				Assert.inputText(driver, InvoiceNumber, Excel_Handling.Get_Data(TC_ID, "InVoiceNumber").trim(), "Phone Number");
				Assert.inputText(driver, InvoicePhoneNumber, Excel_Handling.Get_Data(TC_ID, "PhoneNumber").trim(), "Phone Number");
				String Value = driver.findElement(By.xpath(InvoiceTotAmount)).getAttribute("readonly");
				System.out.println("Value Amount: "+Value);
				if(Value==null){
					Assert.inputText(driver, InvoiceTotAmount, Excel_Handling.Get_Data(TC_ID, "AmoutField").trim(), "Amount");
				}
				Extent_Reporting.Log_Pass("Constant Field Entered", "Passed");
				Extent_Reporting.Log_report_img("Fields Entered", "Passed", driver);
			}else{
				Extent_Reporting.Log_Fail("Image Logo does not exist", "Failed", driver);
			}
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Image Logo is exist for :"+title, "Failed", driver);
			throw new Exception("Image panel page issue");
		}
	}
	
	
	public void InvoiceTermsAndCondition_Field() throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, termsAndConditionchkbox, "Terms and Condition check box")){
				
				Assert.Clickbtn(driver, termsAndConditionlink, "Terms and Condition link");
				Thread.sleep(2000);
				if(Assert.isElementDisplayed(driver, CloseTermsAndCondition, "Terms and Condition")){
					Extent_Reporting.Log_Pass("As expecetd", "Passed");
					Extent_Reporting.Log_report_img("Screen print", "pASSED", driver);
					Assert.Clickbtn(driver, CloseTermsAndCondition, "Close button");

				}else{
					Extent_Reporting.Log_Fail("Terms and Condition page does not exist", "failed", driver);
				}
				
			}else{
				Extent_Reporting.Log_Fail("Terms and Condition check box does not exist", "Failed", driver);
			}
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Terms and Condition check box does not exist", "Failed", driver);
			throw new Exception("Image panel page issue");
		}
	}
	
	public void InvoiceTermsAndConditionChkPay_Field() throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, termsAndConditionchkbox, "Terms and Condition check box")){
				
				Assert.Clickbtn(driver, termsAndConditionchkbox, "Terms and Condition link");
				if(Assert.isElementDisplayed(driver, "//a[@id='mkpayment']", "Terms and Condition")){
					Extent_Reporting.Log_report_img("Screen print", "pASSED", driver);
					Assert.Clickbtn(driver, "//a[@id='mkpayment']", "Close button");
					Thread.sleep(5000);
				}else{
					Extent_Reporting.Log_Fail("Terms and Condition page does not exist", "failed", driver);
				}
				
			}else{
				Extent_Reporting.Log_Fail("Terms and Condition check box does not exist", "Failed", driver);
			}
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Terms and Condition check box does not exist", "Failed", driver);
			throw new Exception("Image panel page issue");
		}
	}
	
			
			
			
	public void InvoiceNavigatePage() throws Exception{
		try{
			Thread.sleep(5000);
			if(Assert.isElementDisplayed(driver, "(//a)[1]", "Terms and Condition check box")){			
				Assert.Clickbtn(driver, "(//a)[1]", "Terms and Condition link");
				Thread.sleep(5000);
				if(Assert.isElementDisplayed(driver, InvoiceLogo, "Image Logo")){
					Extent_Reporting.Log_report_img("Screen print", "pASSED", driver);
					
				}else{
					Extent_Reporting.Log_Fail("Terms and Condition page does not exist", "failed", driver);
				}
				
			}else{
				Extent_Reporting.Log_Fail("Terms and Condition check box does not exist", "Failed", driver);
			}
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Terms and Condition check box does not exist", "Failed", driver);
			throw new Exception("Image panel page issue");
		}
	}
	
	
	
	public void InvoiceSinglePageCrossVerifyData() throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, InvoiceLogo, "Image Logo"))
			{
				Assert.Verify_Image(driver, InvoiceLogo, "Airpay Logo");		
				String InvoicNumber = driver.findElement(By.xpath(InvoiceNo)).getText().split(" ")[2].trim();			
				String AmountV = driver.findElement(By.xpath(AmountActual)).getText().trim();
				String LastnameGetVa = driver.findElement(By.xpath(LastnameGetVal)).getAttribute("value").trim();
				String FirstNameGetVa = driver.findElement(By.xpath(FirstNameGetVal)).getAttribute("value").trim();
				String EmailGetVa = driver.findElement(By.xpath(EmailGetVal)).getAttribute("value").trim();
				String PhoneNumberGetVa = driver.findElement(By.xpath(PhoneNumberGetVal)).getAttribute("value").trim();
				String AmountGetVa = driver.findElement(By.xpath(AmountGetVal)).getText().trim();
				if(InvoicNumber.contains(Excel_Handling.Get_Data(TC_ID, "InVoiceNumber").trim())&& AmountV.contains(Excel_Handling.Get_Data(TC_ID, "AmoutField").trim())
						&& AmountGetVa.contains(Excel_Handling.Get_Data(TC_ID, "AmoutField").trim())&&PhoneNumberGetVa.contains(Excel_Handling.Get_Data(TC_ID, "PhoneNumber").trim())
						&&FirstNameGetVa.contains(Excel_Handling.Get_Data(TC_ID, "First_Name").trim()) &&LastnameGetVa.contains(Excel_Handling.Get_Data(TC_ID, "Last_Name").trim())
						&& EmailGetVa.contains(Excel_Handling.Get_Data(TC_ID, "Email").trim())){					
					Assert.isElementDisplayed(driver, "//div[@id='invoice-for']/p[text()='"+Excel_Handling.Get_Data(TC_ID, "PhoneNumber").trim()+"']", "Phone number is");
					Assert.isElementDisplayed(driver, "//div[@id='invoice-for']/p[text()='"+Excel_Handling.Get_Data(TC_ID, "Email").trim()+"']", "Email is");
					Extent_Reporting.Log_Pass("All fields are exist as expected", "Passed");
					Extent_Reporting.Log_report_img("Screen Print", "Passed", driver);
				 Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, "//select[@id='paymethodselect']", Excel_Handling.Get_Data(TC_ID, "Payment_Mode").trim(), "PayMethod");
				Extent_Reporting.Log_Pass("Pay Method selected as: "+Excel_Handling.Get_Data(TC_ID, "Payment_Mode").trim(), "Passed");
				}else
				{
					Extent_Reporting.Log_Fail("Cross verify Data Went wrong", "Failed", driver);
				}
			}else{
				Extent_Reporting.Log_Fail("Image Logo does not exist", "Failed", driver);
			}
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Image Logo is exist for :"+title, "Failed", driver);
			throw new Exception("Image panel page issue");
		}
	}
	
	
	public void InvoiceSinglePendingtransaction() throws Exception{
		try{			
			Set<String> handles = driver.getWindowHandles();
			browser =	handles.toArray(new String[0]);
			System.out.println("Number of browsers opened are"+ browser.length);
			for (int i=0; i<handles.size();i++)
			{
				try
				{
					driver.switchTo().window(browser[i]);
					System.out.println(driver.getTitle());
					child =driver.getCurrentUrl();
					if(child.contains(MAPanelWindow)){
						System.out.println(driver.getTitle()+"found");
						if(Assert.isElementDisplayed(driver, InvoiceLogo, "Image Logo"))
						{
							Assert.Verify_Image(driver, InvoiceLogo, "Airpay Logo");									
							Extent_Reporting.Log_report_img("Screen Print", "Passed", driver);
							Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, "//select[@id='paymethodselect']", Excel_Handling.Get_Data(TC_ID, "Payment_Mode").trim(), "PayMethod");
							Extent_Reporting.Log_Pass("Pay Method selected as: "+Excel_Handling.Get_Data(TC_ID, "Payment_Mode").trim(), "Passed");			
							InvoiceTermsAndCondition_Field();
							InvoiceTermsAndConditionChkPay_Field();
							SinglePager_PaymentPage_BusinessLogic AirPay_Local = new SinglePager_PaymentPage_BusinessLogic(driver, TC_ID);
							AirPay_Local.Card_Details_Options();
							if(AirPay_Local.SandBoxMode("You are in Sandbox mode (Your account will not be charged)")==true)
							{
								Credit_cardProvidingValues();
								AirPay_Local.InvoiceCash_paymentSuccessMesg();
							}else{
								AirPay_Local.FooterVerify();
								AirPay_Local.InvoiceFailedTransaction();					
							}
						}else{
							Extent_Reporting.Log_Fail("Image Logo does not exist", "Failed", driver);
						}
						
						break;
					}
				}
				catch(Throwable t)
				{
					System.out.println("Browser not opened");
				}
			}		
			
			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Image Logo is exist for :"+title, "Failed", driver);
			throw new Exception("Image panel page issue");
		}
	}
	
	
	public static String TransactionName = "";
	public static String InvoicNumber="";
	public void InvoiceSingleExtendTransaction() throws Exception{
		try{			
			Set<String> handles = driver.getWindowHandles();
			browser =	handles.toArray(new String[0]);
			System.out.println("Number of browsers opened are"+ browser.length);
			for (int i=0; i<handles.size();i++)
			{
				try
				{
					driver.switchTo().window(browser[1]);
					System.out.println(driver.getTitle());
						System.out.println(driver.getTitle()+"found");
						if(Assert.isElementDisplayed(driver, InvoiceLogo, "Image Logo"))
						{
							Assert.Verify_Image(driver, InvoiceLogo, "Airpay Logo");
							TransactionName = driver.findElement(By.xpath(InvoiceActualName)).getText().trim();
							InvoicNumber = driver.findElement(By.xpath(InvoiceNo)).getText().split(" ")[2].trim();			
							Extent_Reporting.Log_report_img("Screen Print", "Passed", driver);
							if(Assert.isElementDisplay(driver, "//select[@id='paymethodselect']")){
							Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, "//select[@id='paymethodselect']", Excel_Handling.Get_Data(TC_ID, "Payment_Mode").trim(), "PayMethod");
							}
							Extent_Reporting.Log_Pass("Pay Method selected as: "+Excel_Handling.Get_Data(TC_ID, "Payment_Mode").trim(), "Passed");			
							InvoiceTermsAndCondition_Field();
							InvoiceTermsAndConditionChkPay_Field();
							SinglePager_PaymentPage_BusinessLogic AirPay_Local = new SinglePager_PaymentPage_BusinessLogic(driver, TC_ID);
							AirPay_Local.Card_Details_Options();
							if(AirPay_Local.SandBoxMode("You are in Sandbox mode (Your account will not be charged)")==true)
							{
								Credit_cardProvidingValues();
								AirPay_Local.InvoiceCashExtendPaymentCash();
								break;
							}else{
								AirPay_Local.FooterVerify();
								AirPay_Local.InvoiceFailedTransaction();
								
							}
						}else{
							Extent_Reporting.Log_Fail("Image Logo does not exist", "Failed", driver);
						}
				}
				catch(Throwable t)
				{
					System.out.println("Browser not opened");
				}
			}		
			
			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Image Logo is exist for :"+title, "Failed", driver);
			throw new Exception("Image panel page issue");
		}
	}
	
	public void DynamicSinglepage_field() throws Exception{
		try{
			if(Assert.isElementDisplay(driver, SubmitBtn))
			{	Thread.sleep(2000);
				Assert.Clickbtn(driver, SubmitBtn, "Submit Button");
			}
			Thread.sleep(600);
			List<WebElement> tblHeaderCount = driver.findElements(By.xpath(SelectDropDown));
			System.out.println("TotHeader: "+tblHeaderCount.size());			
			for(int i=1;i<=tblHeaderCount.size();i++)
			{					
				WebElement selectDropBox1 = driver.findElement(By.xpath(SelectDropDown+"["+i+"]"));
				Select select =new Select(selectDropBox1);
				List<WebElement> optionValue = select.getOptions();
				System.out.println("DropDown Size: "+optionValue.size());	
				Assert.selectDropBoxValue(driver, SelectDropDown+"["+i+"]", 1, "Drop Down");//(driver, Netbank_DropDown, value[i], value[i+1]+" Bank ");			
				Extent_Reporting.Log_Pass("Text Area Entered", "Passed");
				Extent_Reporting.Log_report_img("All fields Entered", "Passed", driver);		
			}	
			/*if(Assert.isElementDisplay(driver, SubmitBtn))
			{
				Assert.Clickbtn(driver, SubmitBtn, "Submit Button");
			}*/
			List<WebElement> tblinputField = driver.findElements(By.xpath(InputTextField));
			System.out.println("TotHeader: "+tblinputField.size());	
			for(int i=1;i<=tblinputField.size();i++)
			{													
				Assert.inputText(driver, InputTextField+"["+i+"]", Excel_Handling.Get_Data(TC_ID, "Text_Feed"), "Respective Data entered");
				Extent_Reporting.Log_Pass("Text Area Entered", "Passed");
				Extent_Reporting.Log_report_img("All fields Entered", "Passed", driver);						
			}				
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fileds are missed to feed the data",	"Failed",driver);
			Log.error("Some fileds are missed to feed the data");
			//e.printStackTrace();
			throw new Exception("Test failed due to Logo does not displayed");
		}
	}
	
	public void InvoiceCreateBtn() throws Exception{
		try{
			if(Assert.isElementDisplay(driver, InvoiceCreateBtn)){
				Assert.Clickbtn(driver, InvoiceCreateBtn, "Submit button");
				Extent_Reporting.Log_Pass("Sumbit the single page", "passed");
			}		
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fileds are missed to feed the data",	"Failed",driver);
			Log.error("Some fileds are missed to feed the data");
			//e.printStackTrace();
			throw new Exception("Test failed due to Logo does not displayed");
		}
	}
	
	
	public void sumbitBtn() throws Exception{
		try{
			if(Assert.isElementDisplay(driver, SubmitBtn))
			{
				Assert.Clickbtn(driver, SubmitBtn, "Submit button");
				Extent_Reporting.Log_Pass("Sumbit the single page", "passed");
				
			}
			
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Some fileds are missed to feed the data",	"Failed",driver);
			Log.error("Some fileds are missed to feed the data");
			//e.printStackTrace();
			throw new Exception("Test failed due to Logo does not displayed");
		}
	}
	
	public void InvoicePendingViewIcon() throws Throwable {
		try{ 
			Log.info("Navigating To Payment Page");	
			Assert.waitForPageToLoad(driver);	
			Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, InvoiceStatusDropDown, Excel_Handling.Get_Data(TC_ID, "InvoiceFilterStatus"), "Invoice Status Drop down");
			Thread.sleep(3000);
			Extent_Reporting.Log_report_img("Invoice pay is in "+Excel_Handling.Get_Data(TC_ID, "InvoiceFilterStatus"), "Passed", driver);
			Assert.Javascriptexecutor_forClick(driver, InvoiceEyeIcon, "InvoiceEyeIcon pay");
		}catch(Exception e){
			Extent_Reporting.Log_Fail("Respective Header does not exists",	"Failed",driver);
			Log.error("Tab does not exist");
			e.printStackTrace();
			throw new Exception("Tab does not exist");
		}
	}
	
	
	public void InvoiceSettingMenuLink() throws Throwable {
		try{ 
			Log.info("Navigating To Payment Page");	
			Assert.waitForPageToLoad(driver);
			Assert.Javascriptexecutor_forClick(driver, SettingsOption, "Invoice Settings");			
			Assert.inputText(driver, InvoiceExpiredField, " ", "Blank Entered");
			Assert.Javascriptexecutor_forClick(driver, ExportSubmitBtn, "Invoice expiry Submit button");
			Assert.isElementDisplayed(driver, ErrorMsg, "Error Message");		
			Extent_Reporting.Log_report_img("Invoice pay is in ", "Passed", driver);
			Assert.inputText(driver, InvoiceExpiredField, "-1", "Blank Entered");
			Assert.Javascriptexecutor_forClick(driver, ExportSubmitBtn, "Invoice expiry Submit button");
			Assert.isElementDisplayed(driver, ErrorMsg, "Error Message");
			Extent_Reporting.Log_report_img("Invoice pay is in ", "Passed", driver);
			Assert.inputText(driver, InvoiceExpiredField, Excel_Handling.Get_Data(TC_ID, "InvoiceExpiryHrs"), "Invoice Expiry in Hours");
			Thread.sleep(3000);
			Extent_Reporting.Log_report_img("Invoice pay is in ", "Passed", driver);
			Assert.Javascriptexecutor_forClick(driver, ExportSubmitBtn, "Invoice expiry Submit button");
		}catch(Exception e){
			Extent_Reporting.Log_Fail("Respective Header does not exists",	"Failed",driver);
			Log.error("Tab does not exist");
			e.printStackTrace();
			throw new Exception("Tab does not exist");
		}
	}
	
	public void InvoiceExpirySuccessMsg() throws Throwable {
		try{ 
			Log.info("Navigating To Payment Page");	
			if(Assert.isElementDisplayed(driver, ExpiryDateMsg, "Expiry Message"))
			{
				
				Extent_Reporting.Log_Pass("Success message is displayed", "passed");
				Extent_Reporting.Log_report_img("Success Message", "passed", driver);
			}else{
				Extent_Reporting.Log_Fail("Respective Success Message does not exists",	"Failed",driver);
			}
			
		}catch(Exception e){
			Extent_Reporting.Log_Fail("Respective Success Message does not exists",	"Failed",driver);
			Log.error("Tab does not exist");
			e.printStackTrace();
			throw new Exception("Tab does not exist");
		}
	}
	
	
	
	public void InvoiceUnpaidStatus() throws Exception {
		try{ 
			if(Assert.isElementDisplayed(driver, InvoicependingStatus, "Transaction Status"))
			{
				Extent_Reporting.Log_Pass("Record is in pending only", "Passed");
				Extent_Reporting.Log_report_img("Screen print", "Passed", driver);
				String CreatedDate = driver.findElement(By.xpath(InvoiceCreatedDate)).getText().split(",")[1].trim();
				String Substring = CreatedDate.substring(0, 3).trim();
				System.out.println(Substring);
				Date date = new SimpleDateFormat("MMM").parse(Substring);//put your month name here
			    Calendar cal = Calendar.getInstance();
			    cal.setTime(date);
			    int monthNumber=cal.get(Calendar.MONTH)+1;
			    System.out.println(monthNumber);		    
			    
			    String invoiceStatus = driver.findElement(By.xpath(InvoicependingStatus)).getText().trim();
			    if(invoiceStatus.equalsIgnoreCase("pending"))
			    {
			    	Extent_Reporting.Log_Pass("Status is in pending only as expecetd", "Passed"); 
			     }else if(invoiceStatus.equalsIgnoreCase("Paid"))
			     {
				    	Extent_Reporting.Log_Pass("Status is in Paid only as expecetd", "Passed"); 
			     }else{
				    	Extent_Reporting.Log_Fail("Status does not exist as expecetd", "Passed",driver); 
			     }		        
			}else{
				Extent_Reporting.Log_Fail("Record does not exist", "Failed", driver);
			}
		}catch(Exception e){
			Extent_Reporting.Log_Fail("Respective Header does not exists",	"Failed",driver);
			Log.error("Tab does not exist");
			e.printStackTrace();
			throw new Exception("Tab does not exist");
		}
	}
	
	
	public void TanishqPendingStatus() throws Exception {
		try{ 
			if(Assert.isElementDisplayed(driver, TanishqInvoiceStatus, "Transaction Status"))
			{		    
			    String invoiceStatus = driver.findElement(By.xpath(TanishqInvoiceStatus)).getText().trim();
			    if(invoiceStatus.equalsIgnoreCase("pending"))
			    {
			    	Extent_Reporting.Log_Pass("Status is in "+invoiceStatus , "Passed"); 
			     }else if(invoiceStatus.equalsIgnoreCase("Paid"))
			     {
				    	Extent_Reporting.Log_Pass("Status is in "+ invoiceStatus , "Passed"); 
			     }else{
				    	Extent_Reporting.Log_Fail("Status does not exist as expecetd", "Passed",driver); 
			     }		        
			}else{
				Extent_Reporting.Log_Fail("Record does not exist", "Failed", driver);
			}
		}catch(Exception e){
			Extent_Reporting.Log_Fail("Respective Header does not exists",	"Failed",driver);
			Log.error("Tab does not exist");
			e.printStackTrace();
			throw new Exception("Tab does not exist");
		}
	}
	
	public static String InvoiceNub="";
	public void InvoicePendingExtendUpdate() throws Throwable {
		try{ 
			if(Assert.isElementDisplayed(driver, InvoicependingStatus, "Transaction Status"))
			{
				Extent_Reporting.Log_Pass("Record is in pending only", "Passed");
				Extent_Reporting.Log_report_img("Screen print", "Passed", driver);
				String expiredYear = driver.findElement(By.xpath(expiredOnDate)).getAttribute("value").trim().split(",")[0].trim();
				System.out.println("expiredOn: "+expiredYear);								
				String CreatedDate = driver.findElement(By.xpath(expiredOnDate)).getAttribute("value").trim().split(",")[1].trim();
				String Substring = CreatedDate.substring(0, 3).trim();
				String DateSubstring = CreatedDate.substring(4, 6).trim();
				System.out.println(Substring);
				System.out.println(DateSubstring);
				Date date = new SimpleDateFormat("MMM").parse(Substring);//put your month name here
			    Calendar cal = Calendar.getInstance();
			    cal.setTime(date);
			    InvoiceNub = driver.findElement(By.xpath(InvoiceNumberpending)).getText().trim();
			    int monthNumber=cal.get(Calendar.MONTH)+1;
			    System.out.println(monthNumber);			    
				String Currentyear = new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime());
			    System.out.println("SystemDate: "+Currentyear);		    
			    if(Integer.parseInt(expiredYear)>=Integer.parseInt(Currentyear))
			    {
			    	System.out.println("Same year exis");
					String CurrentMonth = new SimpleDateFormat("MM").format(Calendar.getInstance().getTime());
					System.out.println("currentMonth: "+CurrentMonth);
					if(monthNumber>=Integer.parseInt(CurrentMonth))
					{
						System.out.println("Same month");						
						String CurrentDate = new SimpleDateFormat("dd").format(Calendar.getInstance().getTime());
						if(Integer.parseInt(DateSubstring)>=Integer.parseInt(CurrentDate)){
							
							System.out.println("Same date exis");
							InvocieLink();	
						}else{
							System.out.println("Date got expired");
							Thread.sleep(2000);
							Assert.Clickbtn(driver, expiredOnDate, "Click Date");
							
							WebDriverWait wait=new WebDriverWait(driver, 20);
							WebElement guru99seleniumlink;
							guru99seleniumlink= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DatePopup)));
							guru99seleniumlink.isDisplayed();
							
							//((JavascriptExecutor)driver).executeScript("arguments[0].value=arguments[1]", expiredOnDate, "15-Mar-2019, 12:00 am");							
							Thread.sleep(5000);
							Assert.Javascriptexecutor_forClick(driver, NextupdatedDate, "Next Extend Date");
							Assert.Clickbtn(driver, NextupdatedDateDonebtn, "Done button");	
						    InvocieLink();	        
						}	
					}else{
						System.out.println("Month got Expired");
						Thread.sleep(2000);
						Assert.Clickbtn(driver, expiredOnDate, "Click Date");
						WebDriverWait wait=new WebDriverWait(driver, 20);
						WebElement guru99seleniumlink;
						guru99seleniumlink= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DatePopup)));
						guru99seleniumlink.isDisplayed();
						
						//((JavascriptExecutor)driver).executeScript("arguments[0].value=arguments[1]", expiredOnDate, "15-Mar-2019, 12:00 am");							

						Thread.sleep(5000);
						Assert.Javascriptexecutor_forClick(driver, NextupdatedDate, "Next Extend Date");
						
						Assert.Clickbtn(driver, NextupdatedDateDonebtn, "Done button");
					    InvocieLink();	        
					}					
			    }else{
			    	System.out.println("Year got Expired");
					Thread.sleep(2000);
					//Assert.Clickbtn(driver, expiredOnDate, "Click Date");
					
					WebDriverWait wait=new WebDriverWait(driver, 20);
					WebElement guru99seleniumlink;
					guru99seleniumlink= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(DatePopup)));
					guru99seleniumlink.isDisplayed();
					//Thread.sleep(5000);
					//((JavascriptExecutor)driver).executeScript("arguments[0].value=arguments[1]", expiredOnDate, "15-Mar-2019, 12:00 am");							

			    	Assert.Javascriptexecutor_forClick(driver, NextupdatedDate, "Next Extend Date");
					Assert.Javascriptexecutor_forClick(driver, NextupdatedDateDonebtn, "Done button");
				    InvocieLink();	        
			    }
			}else{
				Extent_Reporting.Log_Fail("Record does not exist", "Failed", driver);
			}
		}catch(Exception e){
			Extent_Reporting.Log_Fail("Respective Header does not exists",	"Failed",driver);
			Log.error("Tab does not exist");
			e.printStackTrace();
			throw new Exception("Tab does not exist");
		}
	}
	
	
	public void InvocieLink() throws Exception{
		try{
			if(Assert.isElementDisplayed(driver, InvoiceTabLink, "Invoice tab")){
				
				Assert.Clickbtn(driver, InvoiceTabLink, "Invoice Tab link");
				Assert.inputText(driver, InvoiceFilter, InvoiceNub, "Invoice Number");
				Assert.clickButton(driver, InvoiceFilter, "Keys Enter");
				InvoicePending();
				InvoiceSingleExtendTransaction();
				InvoiceExtendCrossVerify();
				
			}
			
			
			
		}catch(Exception e){
			Extent_Reporting.Log_Fail("Respective Header does not exists",	"Failed",driver);
			Log.error("Tab does not exist");
			e.printStackTrace();
			throw new Exception("Tab does not exist");
		}
	}
	public void tanish_create_invoice() throws Exception{
		try{
		/*	//Create invoicepay invoice use this function
			String[] MA_URL = Excel_Handling.Get_Data(TC_ID, "CollectionURL").split(";");	
			for(int URL=0;URL<MA_URL.length;URL++){	
				System.out.println(MA_URL[URL]);
				Thread.sleep(2000);*/
			
	//			driver.get(MA_URL[URL]);
				String title=   driver.getTitle();
//				Extent_Reporting.Log_Pass("Link for: "+MA_URL[URL], "Passed");
				Extent_Reporting.Log_Pass("Resoective Logo is exist for :"+title, "Passed");
				InvoiceSinglePageConstant_Field();
				//DynamicSinglepage_field();	
				InvoiceCreateBtn();
				InvoiceNavigatePage();
				InvoiceSinglePageCrossVerifyData();
				InvoiceTermsAndCondition_Field();
				InvoiceTermsAndConditionChkPay_Field();
				SinglePager_PaymentPage_BusinessLogic AirPay_Local = new SinglePager_PaymentPage_BusinessLogic(driver, TC_ID);
				AirPay_Local.Card_Details_Options();
				if(AirPay_Local.SandBoxMode("You are in Sandbox mode (Your account will not be charged)")==true)
				{
					Credit_cardProvidingValues();
					AirPay_Local.InvoiceCash_paymentSuccessMesg();
				}else{
					AirPay_Local.FooterVerify();
					AirPay_Local.InvoiceFailedTransaction();					
				}
		//	}
		}catch(Exception t){
			t.printStackTrace();
			throw new Exception("SCOT panel page issue");
		}
	}


}