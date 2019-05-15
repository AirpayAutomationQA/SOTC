package com.Airpay.BusinessLogic;

import java.io.File;
import java.io.FileInputStream;

import org.openqa.selenium.WebDriver;

import com.Airpay.PageObject.Airpay_CAPanel_PageObject;
import com.Airpay.Reporting.Extent_Reporting;
import com.Airpay.Utilities.ElementAction;
import com.Airpay.Utilities.Log;

public class Unique_Transaction_BusinessLogic extends Airpay_CAPanel_PageObject {
	public WebDriver driver;
	public String TC_ID = "";
	public static FileInputStream fis= null;
	ElementAction Assert = new ElementAction();
	public Unique_Transaction_BusinessLogic(WebDriver driver, String TC_ID)
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
	public void UniqueTransactionExcelRead(String UniqueTransactionPath) throws Exception{
		try{
			
			fis = new FileInputStream(new File(UniqueTransactionPath));
			
			
			
			
			
			
			
			
		}catch(Exception t){
			t.printStackTrace();
			Extent_Reporting.Log_Fail("SOTC Logo is exist for :"+title, "Failed", driver);
			throw new Exception("SCOT panel page issue");
		}
	}
	
}