package com.Airpay.BusinessLogic;


import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.Airpay.PageObject.Airpay_CAPanel_PageObject;
import com.Airpay.PageObject.Airpay_PaymentPage_PageObject;
import com.Airpay.Reporting.Extent_Reporting;
import com.Airpay.TestData.Excel_Handling;
import com.Airpay.Utilities.ElementAction;
import com.Airpay.Utilities.Log;

/**
 * This is Business Logic having the methods of login and verifying the fields.
 * @author psakole
 * 
 */	
public class SinglePager_PaymentPage_BusinessLogic extends Airpay_PaymentPage_PageObject {
	public WebDriver driver;
	public String TC_ID = "";
	ElementAction Assert = new ElementAction();
	//Log log = new Log();	
	public static String OrderID = "";
	public SinglePager_PaymentPage_BusinessLogic(WebDriver driver, String TC_ID)
	{
		Log.info("AirPay_payment_Mode_Wallet_BusinessLogic");
		this.driver = driver;
		this.TC_ID=TC_ID;
		//log = new Log();
	}
	/**
	 * @author parmeshwar Sakole
	 * Following method is used for Filling up the local host details page.
	 * @throws Throwable
	 */
	public static String GetOrderID = null;
	public void LocalHostDetailPage() throws Exception {
		try{ 
			Log.info("Navigating To Local Host page of Payment");	   
			if(Assert.isElementDisplay(driver, BuyerMailId))
			{ 
				Log.debug("Local Host page");
				Assert.inputText(driver, BuyerMailId, Excel_Handling.Get_Data(TC_ID, "BuyerMailID"), "Buyer Mail ID");
				Assert.inputText(driver, BuyerPhoneNumber, Excel_Handling.Get_Data(TC_ID, "BuyerPhoneNumber"), "Buyer Phone Number");
				Assert.inputText(driver, BuyerFirstName, Excel_Handling.Get_Data(TC_ID, "BuyerFirstName"), "Buyer First Name");
				Assert.inputText(driver, BuyerLastName, Excel_Handling.Get_Data(TC_ID, "BuyerLastName"), "Buyer Last Name");
				//Assert.inputText(driver, Order_Id, Excel_Handling.Get_Data(TC_ID, "Order_Id"), "Order_Id");			
				String  string = RandomStringUtils.randomAlphabetic(8);		
				System.out.println("Random 1 = " + string);				
				Assert.inputText(driver, Order_Id, string, "Order_Id");
				GetOrderID = driver.findElement(By.xpath(Order_Id)).getAttribute("value");
				Assert.inputText(driver, Amount, Excel_Handling.Get_Data(TC_ID, "Amount"), "Amount");
				//Assert.inputText(driver, Order_Id, Excel_Handling.Get_Data(TC_ID, "Order_Id"), "Order_Id");				Assert.inputText(driver, Amount, Excel_Handling.Get_Data(TC_ID, "Amount"), "Amount");
				Extent_Reporting.Log_report_img("Local Host page required field filled", "Passed", driver);
				Assert.Clickbtn(driver, payHerebtn, "Pay Here");
				Assert.waitForPageToLoad(driver);
			}
			else{
				Extent_Reporting.Log_Fail("Local Host page not exist ", "Local Host page not displayed", driver);   
				Log.error("Local Host page not successfully displayed");
				throw new Exception(" Test failed due to local host page not displayed");
			}
		}                     
		catch(Exception e)	
		{
			Log.error("Test failed due to local host page not displayed");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}


	public void LocalHostDetailPage_Merchant_Wallet() throws Exception {
		try{ 
			Log.info("Navigating To Local Host page of Payment");	   
			if(Assert.isElementDisplay(driver, BuyerMailId))
			{ 
				Log.debug("Local Host page");
				Assert.inputText(driver, BuyerMailId, Excel_Handling.Get_Data(TC_ID, "BuyerMailID"), "Buyer Mail ID");
				Assert.inputText(driver, BuyerPhoneNumber, Excel_Handling.Get_Data(TC_ID, "BuyerPhoneNumber"), "Buyer Phone Number");
				Assert.inputText(driver, BuyerFirstName, Excel_Handling.Get_Data(TC_ID, "BuyerFirstName"), "Buyer First Name");
				Assert.inputText(driver, BuyerLastName, Excel_Handling.Get_Data(TC_ID, "BuyerLastName"), "Buyer Last Name");
				//Assert.inputText(driver, Order_Id, Excel_Handling.Get_Data(TC_ID, "Order_Id"), "Order_Id");			
				String  string = RandomStringUtils.randomAlphabetic(8);		
				System.out.println("Random 1 = " + string);				
				Assert.inputText(driver, Order_Id, string, "Order_Id");
				GetOrderID = driver.findElement(By.xpath(Order_Id)).getAttribute("value");
				Assert.inputText(driver, Amount, Excel_Handling.Get_Data(TC_ID, "Amount"), "Amount");
				//Assert.inputText(driver, Order_Id, Excel_Handling.Get_Data(TC_ID, "Order_Id"), "Order_Id");
				Assert.inputText(driver, Amount, Excel_Handling.Get_Data(TC_ID, "Amount"), "Amount");
				Extent_Reporting.Log_report_img("Local Host page required field filled", "Passed", driver);
				Assert.inputText(driver, "//*[@name='wallet' and @id='wallet']", Excel_Handling.Get_Data(TC_ID, "Transaction_Wallet"), "Transaction Wallet");
				Assert.Clickbtn(driver, payHerebtn, "Pay Here");
				Assert.waitForPageToLoad(driver);
			}
			else{
				Extent_Reporting.Log_Fail("Local Host page not exist ", "Local Host page not displayed", driver);   
				Log.error("Local Host page not successfully displayed");
				throw new Exception(" Test failed due to local host page not displayed");
			}
		}                     
		catch(Exception e)	
		{
			Log.error("Test failed due to local host page not displayed");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}

	
	
	/**
	 * @author parmeshwar Sakole
	 * Following method is used for Filling up the local host details page.
	 * @throws Throwable
	 */
	public void LocalHostDetailPage(String AmountVal) throws Exception {
		try{ 
			Log.info("Navigating To Local Host page of Payment");	   
			if(Assert.isElementDisplay(driver, BuyerMailId))
			{ 
				Log.debug("Local Host page");
				Assert.inputText(driver, BuyerMailId, Excel_Handling.Get_Data(TC_ID, "BuyerMailID"), "Buyer Mail ID");
				Assert.inputText(driver, BuyerPhoneNumber, Excel_Handling.Get_Data(TC_ID, "BuyerPhoneNumber"), "Buyer Phone Number");
				Assert.inputText(driver, BuyerFirstName, Excel_Handling.Get_Data(TC_ID, "BuyerFirstName"), "Buyer First Name");
				Assert.inputText(driver, BuyerLastName, Excel_Handling.Get_Data(TC_ID, "BuyerLastName"), "Buyer Last Name");
				//Assert.inputText(driver, Order_Id, Excel_Handling.Get_Data(TC_ID, "Order_Id"), "Order_Id");			
				String  string = RandomStringUtils.randomAlphabetic(8);		
				System.out.println("Random 1 = " + string);				
				Assert.inputText(driver, Order_Id, string, "Order_Id");
				GetOrderID = driver.findElement(By.xpath(Order_Id)).getAttribute("value");
				Assert.inputText(driver, Amount, AmountVal, "Amount");
				//Assert.inputText(driver, Order_Id, Excel_Handling.Get_Data(TC_ID, "Order_Id"), "Order_Id");				Assert.inputText(driver, Amount, Excel_Handling.Get_Data(TC_ID, "Amount"), "Amount");
				Extent_Reporting.Log_report_img("Local Host page required field filled", "Passed", driver);
				Assert.Clickbtn(driver, payHerebtn, "Pay Here");
				Assert.waitForPageToLoad(driver);
			}
			else{
				Extent_Reporting.Log_Fail("Local Host page not exist ", "Local Host page not displayed", driver);   
				Log.error("Local Host page not successfully displayed");
				throw new Exception(" Test failed due to local host page not displayed");
			}
		}                     
		catch(Exception e)	
		{
			Log.error("Test failed due to local host page not displayed");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	
	/**
	 * @author parmeshwar Sakole
	 * Following method is used for Filling up the local host details page.
	 * @throws Throwable
	 */
	
	
	
	
	
	
	
	
	
	private static final String CHAR_LIST = 
			"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	private static final int RANDOM_STRING_LENGTH = 10;

	private int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CHAR_LIST.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}
	/**
	 * This method generates random string
	 * @return
	 */
	public static char ch ;
	public void Order_ID_Genreate() throws Exception{
		try{			    				   			         
			StringBuffer randStr = new StringBuffer();
			for(int i=0; i<RANDOM_STRING_LENGTH; i++)
			{
				int number = getRandomNumber();
				ch = CHAR_LIST.charAt(number);
				randStr.append(ch);
			}


		}catch(Exception e)	{
			Log.error("Test failed due to local host page not displayed");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}

	}		

	/**
	 * @author parmeshwar Sakole
	 * Following method is used for Filling up the local host details page.
	 * @throws Throwable
	 */
	public void LocalHostDetailPage_ErrorVerify() throws Exception {
		try{ 
			Log.info("Navigating To Local Host page of Payment");	   
			if(Assert.isElementDisplay(driver, BuyerMailId))
			{ 
				Log.debug("Local Host page");
				Assert.inputText(driver, BuyerMailId, Excel_Handling.Get_Data(TC_ID, "BuyerMailID"), "Buyer Mail ID");
				Assert.inputText(driver, BuyerPhoneNumber, Excel_Handling.Get_Data(TC_ID, "BuyerPhoneNumber"), "Buyer Phone Number");		
				String  string = RandomStringUtils.randomAlphabetic(8);		
				System.out.println("Random 1 = " + string);				
				Assert.inputText(driver, Order_Id, string, "Order_Id");
				//Assert.inputText(driver, Order_Id, Excel_Handling.Get_Data(TC_ID, "Order_Id"), "Order_Id");
				Assert.inputText(driver, Amount, Excel_Handling.Get_Data(TC_ID, "Amount"), "Amount");
				Extent_Reporting.Log_report_img("Local Host page required field filled", "Passed", driver);
				Assert.Clickbtn(driver, payHerebtn, "Pay Here");
				Assert.waitForPageToLoad(driver);
			}
			else{
				Extent_Reporting.Log_Fail("Local Host page not exist ", "Local Host page not displayed", driver);   
				Log.error("Local Host page not successfully displayed");
				throw new Exception(" Test failed due to local host page not displayed");
			}
		}                     
		catch(Exception e)	
		{
			Log.error("Test failed due to local host page not displayed");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	/**
	 * @author parmeshwar Sakole
	 * @Method Name: Card Selection method.
	 * Following method is used Handling Multiple Card options
	 * @throws Exception
	 */
	public static int temp;
	public static boolean flag=false;
	public void Card_Details_Options() throws Exception {
		try{ 
			Assert.waitForPageToLoad(driver);
			Thread.sleep(2000);
			Log.info("Navigating To Payment Page");	 
			//Assert.waitForFrameAndSwitch(driver, "tranframe");
			String Card = Excel_Handling.Get_Data(TC_ID, "Payment_Mode").trim();
			Assert.waitForPageToLoad(driver);		
			List<WebElement> Channels = driver.findElements(By.xpath(AirpayChannals));
			int ChannelsCnt = Channels.size();
			System.out.println("Channels count is:"+ChannelsCnt);
			for(int i=0; i<ChannelsCnt;i++)
			{
				WebElement ChannelsName = Channels.get(i);
				String name = ChannelsName.getText();
				System.out.println("Channel Name: "+name);
				if(name.equalsIgnoreCase(Card))
				{
					OrderID = driver.findElement(By.xpath(orderIDfetch)).getText().trim();
					ChannelsName.click();
					Extent_Reporting.Log_report_img(" payment mode option choosen as: "+name, "Passed", driver);
					flag = false;
					break;					
				}
				temp = i;
				if(temp==ChannelsCnt-1){
					flag = true;
					Extent_Reporting.Log_report_img(Card+" Channel Mode OFF or else Amount Range Issue ", "Failed", driver);					
				}
			}
			Extent_Reporting.Log_report_img("All channels are exist as expected", "Passed", driver);  
		}                     
		catch(Exception e)	
		{
			Log.error("Test failed due to card does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	
	/**
	 * @author parmeshwar Sakole
	 * @Method Name: Card Selection method.
	 * Following method is used Handling Multiple Card options
	 * @throws Exception
	
	/**
	 * @author parmeshwar Sakole
	 * @Method Name: Card Selection method.
	 * Following method is used Handling Multiple Card options
	 * @throws Exception
	 */
	public static String bankName = null;
	
	

	/**
	 * @author parmeshwar Sakole
	 * @Method Name: Card Selection method.
	 * Following method is used Handling Multiple Card options
	 * @throws Exception
	 */
	public void NetBank_AllBanks_Verify_to_Select() throws Exception {
		try{ 
			Log.info("Navigating To Net Banking Page");	 
			if(Assert.isElementDisplayed(driver, SelectBank_DropDown, "Select Bank Drop Down" ))
			{         	
				WebElement selectDropBox = driver.findElement(By.xpath(Netbank_DropDown));
				Select select =new Select(selectDropBox);
				List<WebElement> optionValue = select.getOptions();
				for(int i =1;i<optionValue.size();i++)
				{				
					WebElement selectDropBox1 = driver.findElement(By.xpath(Netbank_DropDown));
					Select select1 =new Select(selectDropBox1);
					Assert.selectDropBoxValue(driver, Netbank_DropDown, i, " Bank Name");//(driver, Netbank_DropDown, value[i], value[i+1]+" Bank ");			
					//Assert.selectDropBoxValuebyVisibleTextwithoutClick(driver, Netbank_DropDown, "LAXMI VILAS BANK", " Bank Name");//(driver, Netbank_DropDown, value[i], value[i+1]+" Bank ");			
					bankName =  select1.getFirstSelectedOption().getText();
					Extent_Reporting.Log_report_img(bankName+" Bank Selected", "Passed", driver);

				}   		
				Assert.waitForPageToLoad(driver);
			}
			else{
				Extent_Reporting.Log_Fail(" option does not exis",	"Failed",driver);
				Log.error("Local Host page not successfully displayed");
				throw new Exception("option does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail(" option does not exis",	"Failed",driver);
			Log.error("Test failed due to card does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}




	/**
	 * @author parmeshwar Sakole
	 * @Method Name: Card Selection method.
	 * Following method is used Handling Multiple Card options
	 * @throws Exception
	 */
	public void Popular_bank_verification() throws Exception {
		try{ 
			Log.info("Navigating To Net Banking Page");	 
			if(Assert.isElementDisplayed(driver, Popularbanks, "Select Bank Drop Down" ))
			{         	
				List<WebElement> popular1 = driver.findElements(By.xpath(Popularbanks));			
				for(int i =0;i<popular1.size();i++)
				{
					String popularbackName = driver.findElement(By.xpath(Popularbanks+"["+(i+1)+"]")).getText();
					driver.findElement(By.xpath(Popularbanks+"["+(i+1)+"]")).click();
					WebElement selectDropBox1 = driver.findElement(By.xpath(Netbank_DropDown));
					Select select1 =new Select(selectDropBox1);
					bankName =  select1.getFirstSelectedOption().getText();					
					if(popularbackName.contains(bankName)){					
						Extent_Reporting.Log_Pass("Popular Bank Logo is exist as "+bankName, "Drop Down Name is exist as "+popularbackName);												
						Extent_Reporting.Log_report_img("Bank Name Drop Down and Popular Logo name is exist", "Passed", driver);
					}else{
						Extent_Reporting.Log_Fail("Popular bank name does not exist", "Failed", driver);					
					}
				}   		
			}
			else{
				Extent_Reporting.Log_Fail(" option does not exis",	"Failed",driver);
				Log.error("Local Host page not successfully displayed");
				throw new Exception("option does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail(" option does not exis",	"Failed",driver);
			Log.error("Test failed due to card does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}


	/**
	 * @author parmeshwar Sakole
	 * @Method Name: Card Selection method.
	 * Following method is used Handling Multiple Card options
	 * @throws Exception
	 */
	public void Popular_bank_verification_previous_selectedBank() throws Exception {
		try{ 
			Log.info("Navigating To Net Banking Page");	 
			if(Assert.isElementDisplayed(driver, Popularbanks, "Select Bank Drop Down" ))
			{         	
				List<WebElement> popular1 = driver.findElements(By.xpath(Popularbanks));			
				for(int i =0;i<popular1.size();i++)
				{
					String popularbackName = driver.findElement(By.xpath(Popularbanks+"["+(i+1)+"]")).getText();
					driver.findElement(By.xpath(Popularbanks+"["+(i+1)+"]")).click();
					WebElement selectDropBox1 = driver.findElement(By.xpath(Netbank_DropDown));
					Select select1 =new Select(selectDropBox1);
					bankName =  select1.getFirstSelectedOption().getText();					
					if(popularbackName.contains(bankName)){					

						Extent_Reporting.Log_Pass("Popular Bank Logo is exist as"+bankName, "Drop Down Name is exist as"+popularbackName);												
						Extent_Reporting.Log_report_img("Bank Name Drop Down and Popular Logo name is exist", "Passed", driver);
						Assert.selectDropBoxValue(driver, Netbank_DropDown, i+1, " Bank Name");
						Extent_Reporting.Log_Pass("NetBanking Drop Down selected", "Passed");												
						Extent_Reporting.Log_report_img("Bank Name Drop Down and Popular Logo name is exist", "Passed", driver);

					}else{
						Extent_Reporting.Log_Fail("Popular bank name does not exist", "Failed", driver);					
					}
				}   		
			}
			else{
				Extent_Reporting.Log_Fail(" option does not exis",	"Failed",driver);
				Log.error("Local Host page not successfully displayed");
				throw new Exception("option does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail(" option does not exis",	"Failed",driver);
			Log.error("Test failed due to card does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}



	public void NetbankingErrDropDropRedLine() throws Exception {
		try{ 
			Log.info("Navigating To Net Banking Page");	 
			if(Assert.isElementDisplay(driver, NetbankingErrDropDropRedLine))
			{         				
				Assert.isElementDisplayed(driver, NetbankingErrDropDropRedLine, "Net banking ErrDropDrop Red Line");
				Extent_Reporting.Log_report_img("Error drop down is exist", "Passed", driver);
			}
			else{
				Extent_Reporting.Log_Fail("Net Banking Error Drop down red line is not exist",	"Failed",driver);
				Log.error("Local Host page not successfully displayed");
				throw new Exception("option does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Net Banking Error Drop down red line is not exist",	"Failed",driver);
			Log.error("Test failed due to card does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	public boolean SandBoxMode(String SuccessMsg) throws Exception {
		try{ 
			Log.info("Navigating To Net Banking Page");	 
			WebElement hiddenDiv = driver.findElement(By.xpath(Airpay_CAPanel_PageObject.SandboxModeMsg));
			String errMsg = hiddenDiv.getText(); 
			String script = "return arguments[0].innerText";
			errMsg = (String) ((JavascriptExecutor) driver).executeScript(script, hiddenDiv);			
			System.out.println(errMsg);
			if(errMsg.contains(SuccessMsg))
			{
				Extent_Reporting.Log_Pass("Repective  Message is exist", " Msg is:"+errMsg);
				Extent_Reporting.Log_report_img("Respective  Message is exist", "Passed", driver);	
				
			}else
			{	
				Extent_Reporting.Log_Fail("Repective  Message does not exist", " Msg is:"+errMsg, driver);
			}		
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail(" Make payment button does not exist for net banking",	"Failed",driver);
			Log.error("Test failed due to card does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
		return true;
	}
	public void Verify_PaymentPageFields() throws Exception {
		try{ 
			Log.info("Navigating To Payment Page");	
			Assert.waitForPageToLoad(driver);
			Thread.sleep(2000);
			if(Assert.isElementDisplayed(driver, LogoPaymentPage, "Logo payment page" ))
			{         	
				Assert.Verify_Image(driver, ImgLogo, "Airpay Logo");
				//Assert.isElementDisplayed(driver, airPayFavIcon, "Airpay Fav icon");
				Extent_Reporting.Log_report_img("Respective Details is exist", "Passed", driver);
			}else{
				Extent_Reporting.Log_Fail("Logo payment page does not exis",	"Failed",driver);
				Log.error("Local Host page not successfully displayed");
				throw new Exception("option does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Airpay Logo does not exist",	"Failed",driver);
			Log.error("Airpay Logo does not exist does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to Airpay Logo does not displayed");
		}
	}
	public void Verify_All_Channels() throws Exception {
		try{ 
			Log.info("Navigating To Payment Page");	
			Assert.waitForPageToLoad(driver);		
			List<WebElement> Channels = driver.findElements(By.xpath(AirpayChannals));
			int ChannelsCnt = Channels.size();
			System.out.println("Channels count is:"+ChannelsCnt);
			for(int i=0; i<ChannelsCnt;i++)
			{
				WebElement ChannelsName = Channels.get(i);
				String name = ChannelsName.getText();
				Extent_Reporting.Log_Pass("Channel Name is :" +name, "Passed");			
			}
			Extent_Reporting.Log_report_img("All channels are exist as expected", "Passed", driver);        
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Channenls does not exist",	"Failed",driver);
			Log.error("Channenls does not exist");
			e.printStackTrace();
			throw new Exception("Channenls does not exist");
		}
	}
	/**
	 * @author parmeshwar.sakole
	 * @throws Exception
	 * Method use: verify the Channels fields.
	 */
	public void SummerSectionVerify_indianCurrency() throws Exception {
		try{ 
			if(Assert.isElementDisplayed(driver, SummerySecOrdID, "Summery section"))
			{			
				String OrderID = driver.findElement(By.xpath(SummerySecOrdID)).getText();
				String Amt = driver.findElement(By.xpath(AmtValueBlock)).getText();
				System.out.println("amtone:"+ Amt);
				String Amtval[] = Amt.split("₹");
				//String MerchanName = driver.findElement(By.xpath(MerchantName)).getText();			
			  System.out.println("Amount Value:"+ Amtval[1]);				
				if(OrderID.trim().equalsIgnoreCase(GetOrderID)
						&& Amtval[1].trim().equalsIgnoreCase(Excel_Handling.Get_Data(TC_ID, "Amount").trim())){
					Extent_Reporting.Log_Pass("Actual Order id is:"+OrderID, "Expected Order ID Is :"+GetOrderID);
					Extent_Reporting.Log_Pass("Actual Amount is :"+Amtval[1], "Expected Amount Is :"+Excel_Handling.Get_Data(TC_ID, "Amount"));				
					//Assert.isElementDisplayed(driver, MerchantName, "Merchant name is exist as :"+MerchanName); //Removed this functionality
					Extent_Reporting.Log_report_img("Summery Section is exist as expected", "Passed", driver);        
				}else{				
					Extent_Reporting.Log_Fail("Actual Order id is:"+OrderID, "Expected Order ID Is :"+GetOrderID,driver);
					Extent_Reporting.Log_Fail("Actual Amount is :"+Amtval[1], "Expected Amount Is :"+Excel_Handling.Get_Data(TC_ID, "Amount"),driver);
					throw new Exception("There is an something issue with order id and amount summery");
				}						
			}else{			
				Extent_Reporting.Log_Fail("Summery sectioin does not exist", "Failed",driver);
				throw new Exception("There is an something issue with order id and amount summery");
			}			
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Summery sectioin does not exist", "Failed",driver);
			Log.error("Channenls does not exist");
			e.printStackTrace();
			throw new Exception("Channenls does not exist");
		}
	}



	public void FooterVerify() throws Exception {
		try{ 
			Log.info("Navigating To Payment Page");	
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, footerVerifyLink, "Footer text" ))
			{ 
				Assert.isElementDisplayed(driver, airPayFavIcon, "Airpay Fav icon");
				String textFooter = Assert.getInputTextValue(driver, footerVerifyLink, "");
				Extent_Reporting.Log_Pass("Footer Message is :"+textFooter, "Passed");
				Assert.isElementDisplayed(driver, CancelpaymentPage, "Cancel link");
				Extent_Reporting.Log_report_img("Cancel link is exist as expected", "Passed", driver);
				Assert.Clickbtn(driver, CancelpaymentPage, "Cancel link");
				Extent_Reporting.Log_report_img("Footer is exist as expected", "Passed", driver);
			}else{
				Extent_Reporting.Log_Fail(" option does not exis",	"Failed",driver);
				Log.error("Local Host page not successfully displayed");
				throw new Exception("option does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Airpay Logo does not exist",	"Failed",driver);
			Log.error("Airpay Logo does not exist does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to Airpay Logo does not displayed");
		}
	}

	
	public void FailedTransaction() throws Exception {
		try{ 
			Log.info("Navigating To Payment Page");	
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, Airpay_CAPanel_PageObject.FailedTransactionErrMsg, "Footer text" ))
			{ 
				
				Extent_Reporting.Log_report_img("Failed transaction is exist", "Passed", driver);
			}else{
				Extent_Reporting.Log_report_img("Failed transaction not does is exist", "Passed", driver);
				Log.error("Local Host page not successfully displayed");
				throw new Exception("option does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Airpay Logo does not exist",	"Failed",driver);
			Log.error("Airpay Logo does not exist does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to Airpay Logo does not displayed");
		}
	}
	
	public void InvoiceFailedTransaction() throws Exception {
		try{ 
			Log.info("Navigating To Payment Page");	
			Assert.waitForPageToLoad(driver);
			if(Assert.isElementDisplayed(driver, Airpay_CAPanel_PageObject.InvoiceLogo, "Footer text" ))
			{ 
				Assert.isElementDisplayed(driver, Airpay_CAPanel_PageObject.InvoiceFailedTransactions, "Failed");
				Extent_Reporting.Log_report_img("Failed transaction message is exist", "Passed", driver);
			}else{
				Extent_Reporting.Log_report_img("Failed transaction message not does is exist", "Passed", driver);
				Log.error("Local Host page not successfully displayed");
				throw new Exception("option does not exist displayed");
			}
		}                     
		catch(Exception e)	
		{
			Extent_Reporting.Log_Fail("Airpay Logo does not exist",	"Failed",driver);
			Log.error("Airpay Logo does not exist does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to Airpay Logo does not displayed");
		}
	}
	

	
	

	public void BankPage_validation() throws Exception {
		try{ 
			Log.info("Navigating To Net Banking Page");	
			JavascriptExecutor js = (JavascriptExecutor) driver;
			String domain = (String) js.executeScript("return document.domain");
			driver.manage().timeouts().implicitlyWait(2000, TimeUnit.MILLISECONDS);			 			 
			if(domain.equals("") || domain.equals("payments.airpay.co.in")||(driver.getPageSource().contains("Error Page Exception"))==true
					||(driver.getPageSource().contains("Internal Server Error"))==true|| driver.getTitle().contains("HTTP Status - 400"))
			{			 
				Extent_Reporting.Log_Fail("Its not navigated to Respective Bank as", "Error Snap", driver);
				Log.error("Its not navigated to Respective Bank as :"+bankName);
			}else{
				Extent_Reporting.Log_Pass("Its Navigated to :"+bankName, "Passed");
				Extent_Reporting.Log_report_img("Its Navigated to respective bank" , "Passed", driver);
				Thread.sleep(2000);
			}
		}catch(Exception e)	
		{
			Extent_Reporting.Log_Fail(" Make payment button does not exist for net banking",	"Failed",driver);
			Log.error("Test failed due to card does not exist");
			e.printStackTrace();
		}
	} 

	public void NavigateToLocalHostPage() throws Exception {
		try{ 
			Log.info("Navigating To Net BankingPage");	
			Assert.waitForPageToLoad(driver);
			driver.get(Excel_Handling.Get_Data(TC_ID, "PaymentPage_URL").trim());
			Assert.waitForPageToLoad(driver);
			LocalHostDetailPage();	
			Card_Details_Options();
		}catch(Exception e){
			Log.error("Test failed due to card does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	
	
	public void NavigateToLocalHostPageNetBank() throws Exception {
		try{ 
			Log.info("Navigating To Net BankingPage");	
			Assert.waitForPageToLoad(driver);
			driver.get(Excel_Handling.Get_Data(TC_ID, "PaymentPage_URL").trim());
			Assert.waitForPageToLoad(driver);
			LocalHostDetailPage();	
			Thread.sleep(2000);
			Log.info("Navigating To Payment Page");	 
			Assert.waitForFrameAndSwitch(driver, "tranframe");
			Card_Details_Options();
		}catch(Exception e){
			Log.error("Test failed due to card does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}
	public void NavigateToInlineKit() throws Exception {
		try{ 
			Log.info("Navigating To Net BankingPage");	
			Assert.waitForPageToLoad(driver);
			driver.get(Excel_Handling.Get_Data(TC_ID, "PaymentPage_URL").trim());
			Assert.waitForPageToLoad(driver);
			LocalHostDetailPage();
			Thread.sleep(2000);
			Log.info("Navigating To Payment Page");	 
			Assert.waitForFrameAndSwitch(driver, Excel_Handling.Get_Data(TC_ID, "FrameName").trim());
			
			Extent_Reporting.Log_report_img("Airpay payment page is exist as expected", "Passed", driver);
			//Card_Details_Options();
		}catch(Exception e){
			Log.error("Test failed due to card does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}

	public void Cash_ChannelVerification() throws Exception {
		try{ 			
			if(flag==true)
			{
				Extent_Reporting.Log_Pass("Respective Channel Does not exist as expected", "Passed");			
			}else{
				Extent_Reporting.Log_Fail("Respective Channel is exist", "Failed", driver);			
			}
		}catch(Exception e){
			Extent_Reporting.Log_Fail("Respective Channel is exist", "Failed", driver);
			Log.error("Test failed due to card does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}

	public void Cash_ChannelVerificationExist() throws Exception {
		try{ 			
			if(flag==false)
			{
				Extent_Reporting.Log_Pass("Respective Channel is exist as expected", "Passed");			
			}else{
				Extent_Reporting.Log_Fail("Respective Channel does not exist", "Failed", driver);			
			}
		}catch(Exception e){
			Extent_Reporting.Log_Fail("Respective Channel is exist", "Failed", driver);

			Log.error("Test failed due to card does not exist");
			e.printStackTrace();
			throw new Exception("Test failed due to local host page not displayed");
		}
	}

	public void NavigateToLocalHostPage_WithAmount(String Amt) throws Exception {
		try{ 
			Log.info("Navigating To Net Banking Page");	
			Assert.waitForPageToLoad(driver);
			driver.get(Excel_Handling.Get_Data(TC_ID, "PaymentPage_URL").trim());
			Assert.waitForPageToLoad(driver);
			LocalHostDetailPage(Amt);	
			Card_Details_Options();
		}catch(Exception e){
			Log.error("Test failed due to card does not exist");
			throw new Exception("Test failed due to local host page not displayed");
		}
	}

	public static String MAPanelWindow = null;
	public static String child = null;
	public  static String[] browser =null;
	public void NavigateToLocalHostPage_WithAmountAnotherWindow(String Amt) throws Exception {
		try{ 
		
			Log.info("Navigating To Net Banking Page");	
			Assert.waitForPageToLoad(driver);
			MAPanelWindow = driver.getTitle();
			
			((JavascriptExecutor) driver).executeScript("window.open();");
			Set<String> handles = driver.getWindowHandles();
			browser =	handles.toArray(new String[0]);
			System.out.println("Number of browsers opened are"+ browser.length);
			for (int i=0; i<handles.size();i++)
			{
				try
				{
					driver.switchTo().window(browser[i]);
					System.out.println(driver.getTitle());
					child =driver.getTitle();
					if(!child.contains(MAPanelWindow)){
						System.out.println(driver.getTitle()+"found");
						driver.navigate().to(Excel_Handling.Get_Data(TC_ID, "PaymentPage_URL").trim());
						driver.getWindowHandle();						
						Assert.waitForPageToLoad(driver);
						LocalHostDetailPage(Amt);	
						Card_Details_Options();
						break;
					}
				}
				catch(Throwable t)
				{
					System.out.println("Browser not opened");
				}
			}		
		}catch(Exception e){
			Log.error("Test failed due to card does not exist");
			throw new Exception("Test failed due to local host page not displayed");
		}
	}

   public void SearchTransaction() throws Exception{
	   try{		  
		   if(Assert.isElementDisplayed(driver, "//*[@class='table']/tbody/tr/td/a[contains(text(),'"+GetOrderID+"')]", "Transaction Records"))
		   {
			 Assert.Clickbtn(driver, "//*[@class='table']/tbody/tr/td/a[contains(text(),'"+GetOrderID+"')]", "Respective Transaction");
			 Extent_Reporting.Log_report_img("Transaction Records", "Passed", driver);  
		   }else{
			  Extent_Reporting.Log_Fail("Respective Transaction records does not exist", "Failed", driver); 
		   }   
	   }catch(Exception t){
			  Extent_Reporting.Log_Fail("Respective Transaction records does not exist", "Failed", driver); 
			  t.printStackTrace();
		   
	   }
   }
   
   
   public static String  AirPaytransactionID = null;

	public void Cash_paymentSuccessMesg() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			Thread.sleep(10000);			
			if(Assert.isElementDisplay(driver, "(//td[@class='titletd']/b)")){
				List<WebElement> tblcontent = driver.findElements(By.xpath("(//td[@class='titletd']/b)"));
				for(int i=1;i<=tblcontent.size();i++)
				{				
					String tblrowVal = driver.findElement(By.xpath("(//td[@class='titletd']/b)["+i+"]")).getText().trim();
					System.out.println("rowValue: "+tblrowVal);
					if(tblrowVal.equalsIgnoreCase("Name:")){
						//Extent_Reporting.Log_Pass("Actual Transaction ID Name Is: "+tblrowVal, "Expected Transaction Id Name is: "+"TRANSACTIONID:");
						String FirstName = driver.findElement(By.xpath("(//td[@class='subtd'])["+i+"]")).getText().split(" ")[0].trim();
						String LastName = driver.findElement(By.xpath("(//td[@class='subtd'])["+i+"]")).getText().split(" ")[1].trim();
						if(FirstName.equalsIgnoreCase(Excel_Handling.Get_Data(TC_ID, "First_Name").trim())&& LastName.equalsIgnoreCase(Excel_Handling.Get_Data(TC_ID, "Last_Name").trim()) ){
							Extent_Reporting.Log_Pass("First name is : "+FirstName, "Last Name is :"+LastName);
							Extent_Reporting.Log_report_img("Success payment Transaction ID is as expected", "Passed", driver);
							break;
						}
					}
					if(i==tblcontent.size()){
						Extent_Reporting.Log_Fail("Transaction Id Field row Does not exist", "Failed", driver);
					}					
				}
				List<WebElement> tblAmout = driver.findElements(By.xpath("(//td[@class='titletd']/b)"));
				for(int i=1;i<=tblAmout.size();i++)
				{				
					String tblrowVal = driver.findElement(By.xpath("(//td[@class='titletd']/b)["+i+"]")).getText().trim();
					System.out.println("rowValue: "+tblrowVal);
					if(tblrowVal.equalsIgnoreCase("Amount:")){
						//Extent_Reporting.Log_Pass("Actual Amount field Name Is: "+tblrowVal, "Expected Amount field Name is: "+"AMOUNT:");
						String TxnAmt = driver.findElement(By.xpath("(//td[@class='subtd'])["+i+"]")).getText().split("/")[0];
						String ActualAmt = TxnAmt.split("Rs.")[1];
						System.out.println("dd: "+ActualAmt);
						if(ActualAmt.contains(Excel_Handling.Get_Data(TC_ID, "AmoutField").trim())){
							Extent_Reporting.Log_Pass("Actual Transaction Amount is: "+TxnAmt, "Passed");
							Extent_Reporting.Log_report_img("Success payment Transaction Amount is as expected", "Passed", driver);
							break;
						}
					}
					if(i==tblcontent.size()){
						Extent_Reporting.Log_Fail("Transaction Amount Field row Does not exist", "Failed", driver);
					}					
				}				
				List<WebElement> TranMesg = driver.findElements(By.xpath("(//td[@class='titletd']/b)"));
				for(int i=1;i<=TranMesg.size();i++)
				{				
					String tblrowVal = driver.findElement(By.xpath("(//td[@class='titletd']/b)["+i+"]")).getText().trim();
					System.out.println("rowValue: "+tblrowVal);
					if(tblrowVal.equalsIgnoreCase("Payment reference number:")){
						String TxnMessage = driver.findElement(By.xpath("(//td[@class='subtd'])["+i+"]")).getText().trim();
						if(TxnMessage.equalsIgnoreCase(OrderID)){
							Extent_Reporting.Log_Pass("Actual Transaction TxnMessage is: "+TxnMessage, "Passed");
							Extent_Reporting.Log_report_img("Success payment Transaction Message is displayed", "Passed", driver);
							break;
						}
					}
					if(i==tblcontent.size()){
						Extent_Reporting.Log_Fail("Transaction Amount Field row Does not exist", "Failed", driver);
					}					
				}
			}else{
				Extent_Reporting.Log_Fail("Cash Payment Transaction success Message does not exist", "Failed", driver);
				throw new Exception("Test failed due to local host page not displayed");
			}

		}catch(Exception e) 
		{
			Extent_Reporting.Log_Fail("Cash Payment Transaction success Message does not exist", "Failed", driver);
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Failed due to Transaction Success message does not exist");
		}
	}

	public void InvoiceCash_paymentSuccessMesg() throws Exception{
		try{
			Assert.waitForPageToLoad(driver);
			Thread.sleep(10000);			
			if(Assert.isElementDisplay(driver, "//img[@class='main-logo']")){			
				String FetchOrderN0 = driver.findElement(By.xpath(Airpay_CAPanel_PageObject.SerialNumber)).getText().trim();
				String FirstNa    = driver.findElement(By.xpath(Airpay_CAPanel_PageObject.AnnumitantName)).getText().split(" ")[0].trim();
				String LastNa    = driver.findElement(By.xpath(Airpay_CAPanel_PageObject.AnnumitantName)).getText().split(" ")[1].trim();
				if(FetchOrderN0.contains(Excel_Handling.Get_Data(TC_ID, "InVoiceNumber").trim())&& FirstNa.contains(Excel_Handling.Get_Data(TC_ID, "First_Name").trim())
						&& LastNa.contains(Excel_Handling.Get_Data(TC_ID, "Last_Name").trim())){
					Extent_Reporting.Log_report_img("Screen print", "Passed", driver);
					Extent_Reporting.Log_Pass("All data is exist as expected", "Passed");
					
				}else{
					Extent_Reporting.Log_Fail("Transaction Success Data is wrong", "Failed", driver);
				}
			}else{
				Extent_Reporting.Log_Fail("Logo Does not Exist", "Failed", driver);
				throw new Exception("Test failed due to local host page not displayed");
			}
		}catch(Exception e) 
		{
			Extent_Reporting.Log_Fail("Cash Payment Transaction success Message does not exist", "Failed", driver);
			Log.error("Test failed due to page is navigating to payment page");
			e.printStackTrace();
			throw new Exception("Failed due to Transaction Success message does not exist");
		}
	}
	
   
   
}