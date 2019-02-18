package com.Airpay.BusinessLogic;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

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
			}
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("Respective Logo is exist for :"+title, "Failed", driver);
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

	
	
	public void DynamicSinglepage_field() throws Exception{
		try{
			if(Assert.isElementDisplay(driver, SubmitBtn))
			{
				Assert.Clickbtn(driver, SubmitBtn, "Submit Button");
			}
			Thread.sleep(2000);
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
	
	
	
	
	public void sumbitBtn() throws Exception{
		try{
			if(Assert.isElementDisplay(driver, SubmitBtn)){
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


}