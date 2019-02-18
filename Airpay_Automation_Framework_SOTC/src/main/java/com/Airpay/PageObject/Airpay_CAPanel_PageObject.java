package com.Airpay.PageObject;

public class Airpay_CAPanel_PageObject {
	

	
	//*************************CA Panel Login *******************************************************
	
			public static final  String  CAloginID = "//input[@id='LoginForm_username']";
			public static final  String  CAPwd = "//input[@id='LoginForm_password']";			
			public static final  String  CASubmitBtn = "//input[@class='btn btn-primary' and @type='submit']";					
			public static final String LogoPaymentPage = "//*[@src='resources/images/airpay-payment-processing-services-logo.png' or @class='mer-logo-txt']";
			public static final String ImgLogo = "(//img)[1]";			
			public static final  String  SOTCLogo = "//img[contains(@src,'sotc.png')]";	
			public static final String AirpayChannals = "//*[@class='menu-link']";

			
			public static final  String  LogoutIcon = "//*[@class='userbox dropdown']/a";	
			public static final  String  CALogOutBtn = "//a[@href='/site/logout']";
			public static final String AcceptTermsAndCondition ="css-label";

			
			public static final String AAdharField ="//*[@id='aadhar']";
			public static final String PANField ="//*[@id='pan']";
			public static final String ProfileSubmtBtn ="//*[@id='profile-submit-button']";
			public static final String ProfileCAErrorMsg ="(//*[@id='aadhar']//following::span[@class='help-block with-errors'])[1]";
			public static final String AadharCadInvalidErMsg ="//*[text()='Aadhar number is invalid']";

			
			
	//************************************SinglePage Links Locators ****************************************
			
			public static final String FirstName = "//input[@id='paymentmaster-first_name']";
			public static final String LastName = "//input[@id='paymentmaster-last_name']";
			public static final String Email = "//input[@id='paymentmaster-email']";
			public static final String PhoneNumber = "//input[@id='paymentmaster-phone']";
			public static final String AmountField = "//input[@id='paymentmaster-amount']";

			public static final String TotalErrorField = "(//div[contains(@class,'required has-error')]/*[contains(@name,'PaymentMaster')])";
			public static final String SelectDropDown = "(//div[contains(@class,'required has-error')]/select[contains(@name,'PaymentMaster')])";
			public static final String InputTextField ="(//div[contains(@class,'required has-error')]/input[contains(@name,'PaymentMaster')])";
			public static final String SubmitBtn = "//button[@id='subm']";
			
			
			public static final String CancelTransaction = "//div[@class='sorry sorrytransaction']";
			public static final String SandboxModeMsg ="//div[@class='sandbox-msg']";
			
			
			
			
			
			public static final  String  HeaderTabsVerify = "//ul[@class='r-tabs-nav']/li";
			
			public static final  String  payHerebtn= "//button[@type='submit']";
	
	
}
