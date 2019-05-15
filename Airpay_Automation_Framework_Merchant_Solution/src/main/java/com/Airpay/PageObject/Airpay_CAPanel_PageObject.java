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
			public static final String FailedTransactionErrMsg ="//div[@class='sorry sorrytransaction']/p";

			public static final String CreditCardNoInput ="(//div[@class='blockMain blockCards credit-tab']//following::input[contains(@class,'form-control cardNumber')])[1]";
			public static final String CreditCardExpDate = "(//div[@class='blockMain blockCards credit-tab']//following::input[contains(@class,'form-control exp_date')])[1]";
			public static final String CreditCardCVVCode ="(//div[@class='blockMain blockCards credit-tab']//following::input[contains(@class,'form-control cvv')])[1]";
			public static final String CreditCardMakePaymtBtn = "//input[@class='btn submit-credit-dtls']";
			public static final String CreditCardHolderName = "(//div[@class='blockMain blockCards credit-tab']//following::input[contains(@class,'form-control cardname')])[1]";
			public static final String CreditCardErrField = "//div[@class='formDom form-group errorvalue']//label";
			public static final String CardInvalidErrMsgVerify = "(//*[@class='generic-error'])[1]";
			public static final String GenericSuccessMessage = "(//*[@class='generic-success'])[1]";
			public static final String PopupErrmShbBtn = "(//*[@class='msgclosebtn'])[1]";
			
				
			public static final  String  HeaderTabsVerify = "//ul[@class='r-tabs-nav']/li";
			
			
			
			public static final  String  payHerebtn= "//button[@type='submit']";
	
			
    //******************************************** Invoice pay ***************************************
			public static final String InvoiceLogo ="//img[@class='img_style' or @src='https://payments.invoicepay.co.in/uploads/531/logo_1549967156.png' or @class='main-logo']";	
			public static final String TanishqLogo ="//img[@alt='Nowpay']";	

			
			
			
			public static final String InvoicePageFirstName ="//input[@class='form-control' and @id='FIRST_NAME']";	
			public static final String InvoicePageLastName ="//input[@class='form-control' and @id='LAST_NAME' and @name='LAST_NAME']";	
			public static final String InvoiceEmail ="//input[@class='form-control' and @id='LAST_NAME' and @name='EMAIL']";	
			public static final String InvoiceNumber ="//input[@class='form-control' and @id='INVOICE_NUMBER' and @name='INVOICE_NUMBER']";	
			public static final String InvoicePhoneNumber ="//input[@class='form-control' and @id='PHONE' and @name='PHONE']";	

			public static final String InvoiceTotAmount ="//input[@class='form-control' and @id='TOTAL_AMOUNT' and @name='TOTAL_AMOUNT']";	
			//public static final String InvoicePhoneNumber ="//input[@class='form-control' and @id='PHONE' and @name='PHONE']";	
			public static final String InvoiceCreateBtn ="//input[ @id='invoice-create']";	

			public static final String InvoiceNo = "(//div[@id='invoiceno']/h4)[1]";
			public static final String Invoicedetails = "//div[@id='invoice-for']";
			public static final String InvoiceActualName = "//div[@id='invoice-for']/h4";
			public static final String AmountActual = "//td[@id='amountdue']";
			
			
			
			public static final String PhoneNumberGetVal = "//input[@id='Customer_PHONE']";
			public static final String EmailGetVal = "//input[@id='Customer_EMAIL']";
			public static final String FirstNameGetVal = "//input[@id='Customer_FIRST_NAME']";
			public static final String LastnameGetVal = "//input[@id='Customer_LAST_NAME']";
			public static final String AmountGetVal = "//span[@id='payamount2']";
			public static final String termsAndConditionchkbox = "//input[@id='Invoice_iagree']";
			public static final String termsAndConditionlink = "//a[text()='terms and conditions.']";			
			public static final String CloseTermsAndCondition = "//a[@title='Close']";

			
			public static final String SerialNumber ="//span[@class='placeholder-serial']";
			public static final String AnnumitantName ="//*[@class='input-name']";
			public static final String InvoiceFailedTransactions ="//div[@class='tran_error']";
			
			public static final String InvoiceLoginID ="//input[@id='LoginForm_username']";
			public static final String InvoiceLoginPWD ="//input[@id='LoginForm_password']";

			public static final String InvoiceImportBtn ="//input[@id='import-button']";
			public static final String InvoiceExportBtn ="//input[@id='export-button']";
			public static final String ChooseLink ="//a[@class='file-input-wrapper btn btn-default ']";
			public static final String InvoiceSendMail ="//select[@id='InvoiceImport_send_email']";
			public static final String InvoiceSendSMS ="//select[@id='InvoiceImport_send_sms']";

			public static final String Invoicetable ="(//table/thead/tr/th)";
			public static final String InvoiceStatusDropDown ="//select[@id='Invoice_INVOICE_STATUS']";
			public static final String InvoiceEmpty ="//span[@class='empty']";
			public static final String InvoiceTblRows ="(//table/tbody/tr)";
			
			public static final String InvoiceNumberEnter ="//input[@id='Invoice_INVOICE_ID' and @class='form-control searchid']";
			public static final String InvoiceEyeIcon ="(//*[@class='glyphicon glyphicon-eye-open'])[1]";

			
			public static final String InvoicependingStatus ="(//*[text()='Invoice Status']//following::td)[1]";
			public static final String InvoiceCreatedDate ="(//*[text()='Created On']//following::td)[1]";

			
			
			public static final String InvoiceExpiredOn ="//input[@class='form-control hasDatepicker']";
			public static final String InvoiceSettingLink ="//a[@href='/site/settings']";
			
			public static final String InvoiceExpiredField ="//input[@id='Company_EXPIRED_ON']";
			public static final String ExportSubmitBtn ="//input[@class='btn btn-primary lg-btn']";
			public static final String ExpiryDateMsg ="//*[text()='Expiry date in hours updated successfully.']";
			public static final String SettingsOption ="//a[text()='Settings']";
			public static final String ErrorMsg ="//div[@class='errorMessage']";
			public static final String expiredOnDate ="//input[@id='expired_on']";
			public static final String NextupdatedDate ="(//a[contains(@class,'ui-state-default ui-state-highlight ui-state-active')]//following::td/a)[1]";
			public static final String NextupdatedDateDonebtn ="//button[text()='Done']";
			public static final String InvoiceNumberpending ="(//*[text()='Invoice Number']//following::td)[1]";
			public static final String InvoiceTabLink ="//a[text()='Invoices']";
			public static final String InvoiceFilter ="//input[@id='Invoice_INVOICE_NUMBER' and @class='form-control searchid']";
			public static final String DatePopup ="//div[@id='ui-datepicker-div']";

			//***************************************** Tanishq pay test cases *****************************
			public static final String TanishqDateOfGeneration ="(//*[text()='Date of Generation']//following::td)[1]";
			public static final String TanishqExpiryLinkDate ="(//*[text()='Payment Link Valid Upto']//following::td)[1]";
			public static final String TanishqInvoiceStatus ="(//*[text()='Invoice Status']//following::td)[1]";
			public static final String ManualTanishqBtn ="//*[@class='dropdown-toggle btn btn-primary md-btn']";
			public static final String DownLoadManual ="//a[text()='Download Manual']";
			public static final String ViewManual="//a[text()='View Manual']";


			
			
			
			
			
			

			

			
			
			
			
			

			

			

			
			

			
			
			
			
			
			
			
			
			
			
			
			
			

			
			
			
			
			
			
			
			
			
			
			
}
