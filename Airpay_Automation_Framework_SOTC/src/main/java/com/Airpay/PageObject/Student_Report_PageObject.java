package com.Airpay.PageObject;

public class Student_Report_PageObject
{
	public static final String report_menu="//a[@class='nav-header collapsed' and @href='#report-menu']";
	public static final String report_sub_menu="//ul[@class='nav nav-list in collapse']";
	public static final String student_report_sub_menu="//a[@href='schoolpayreport.php' and text()='Student Reports']";
	
	public static final String username_string="//a[@class='dropdown-toggle']";
	public static final String  table_headers="(//table[@class='table table-hover']/thead/tr/th)";
	public static final String  table_data="//table[@class='table table-hover']/tbody/tr/td";
	public static final String table_row_count="(//table[@class='table table-hover']/tbody/tr)";
	public static final String table_row_checkbox="//input[@class='select ']";
	public static final String Report_pay_now="//button[@id='pay_now_button' or text()='PAY NOW']";
	public static final String invoice_details_student_name="//div[@class='cust_name1 box']";
	public static final String invoice_details_description="(//div[@class='tb-description'])";
	public static final String invoice_details_headers="//div[@class='particular-table']";
	public static final String invoice_details_table_data="(//div[@class='particular-table'])[1]/div";
	public static final String invoice_roll_no="//div[text()='Serial No.']//following::div[1]";
	public static final String invoice_details_amount="//div[@class='tb-price']";
	public static final String invoice_details_total_amount="(//div[@class='sub-total'])[2]/p";
	public static final String invoice_details_late_fees="(//div[@class='sub-total'])[1]";
	public static final String invoice_details_pay_now_button="//input[@class='paynow-btn btn btn-danger btn-large' and @id='btnpay']";
	public static final String invoice_details_pay_now_button_multi="//input[@class='paynow-btn btn btn-danger btn-large' and @id='btnpay']";
	public static final String payment_page_see_surcharge_button="//div[@class='sumbtn desksumbtn iplus']";
	public static final String payment_page_total="//div[@class='main-amount-block show-amnt']//following::span[@id='total_amount']";
	public static final String payment_page_surcharge_amt="(//*[@class='surcharge_amount'])[1]";
	public static final String payment_page_amt_with_surcharge="//span[@class='amount-value-block']";
	//public static final String 
	public static final String CreditCardNoInput ="(//div[@class='blockMain blockCards credit-tab']//following::input[contains(@class,'form-control cardNumber')])[1]";
	public static final String CreditCardExpDate = "(//div[@class='blockMain blockCards credit-tab']//following::input[contains(@class,'form-control exp_date')])[1]";
	public static final String CreditCardCVVCode ="(//div[@class='blockMain blockCards credit-tab']//following::input[contains(@class,'form-control cvv')])[1]";
	public static final String CreditCardMakePaymtBtn = "//input[@class='btn submit-credit-dtls']";

	
	public static final String logout_link="//*[@class='dropdown-toggle']";
	public static final String logout_button="//a[contains(@href,'logout.php')]";
	
	public static final String roll_no_textbox="//input[@name='roll_no']";
	public static final String Search_button="//input[@class='btn']";
	public static final String admin_table_header="//table[@class='table table-hover']/thead/tr/th";
	public static final String admin_table_data="//table[@class='table table-hover']/tbody/tr";
	public static final String admin_next_btn="//a[text()='Next']";
	public static final String invoce_link="//img[@src='resources/images/pdf_icon.png']";
	public static final String payment_page_make_payment_btn="//input[@class='btn submit-credit-dtls']";
	public static final String payment_page_cancel_btn="//div[@class='tnc']//a";
	public static final String receipt_message_text="(//div[@class='school_app_wrap'])[1]";
	public static final String receipt_success_param="(//div[@class='school_app_wrap'])[2]";
	public static final String receipt_message="(//div[@class='school_class_text'])";
	public static final String receipt_param="(//div[@class='school_class_no'])";
	public static final String receipt_amount="(//div[@class='school_app_wrap'])[2]";
	public static final String cancel_receipt_text="(//div[@class='school_app_wrap'])";
	public static final String receipt_header="//h2";

	public static final String sandbox_text="//div[@class='sandbox-msg']";

	public static final String roll_no_text="//input[@id='roll_no']";// Class is not defined for the element.
	public static final String advance_search="//a[@class='span12']";
	public static final String status ="//select[@class=' span2' and @name='paytype']";
	public static final String search_button="//input[@class='btn']";

//*********************************************************** Individaul Invoice pay *******************************************
	
	public static final String ApprovedSchoolTab="//a[text()='Approved School']";
	public static final String totalSchoolNames = "//div[@id='approved']/table/tbody/tr/td/a";
	public static final String RegisteredMailID ="//div[@id='approved']/table/tbody/tr/td[4]";
	public static final String InvoiceTab = "//a[@href='#invoices']";
	public static final String IndividualRadioBtn = "//input[@type='radio' and @value='IF']";
	public static final String MergedRadioBtn = "//input[@type='radio' and @value='MF']";
	public static final String SaveInvoiceDetails = "(//input[@type='radio' and @value='MF'])[1]//following::button[text()=' Save'][1]";
	public static final String Frameswitch = "//iframe[@class='fancybox-iframe']";

	public static final String FancyBoxClosebtn ="//a[@class='fancybox-item fancybox-close']";
	public static final String ConfigurationMenu ="//a[@href='#config-menu']";
	public static final String SchoolPayTab ="//a[@href='schoolconfig.php']";
	public static final String SelectInvoiceDayDrop ="//select[@id='uniqueSelect' and @name='select_invoice']";
	public static final String SetDateSaveBtn ="//select[@id='uniqueSelect' and @name='select_invoice']//following::button[text()=' Save'][1]";
	public static final String DetailsSaveToSetDate ="//*[@class='alert alert-success']";
	public static final String FeesStructureMenu ="//*[@href='#error-menu' and @class='nav-header collapsed']";
	public static final String GenerateinvoiceBtn ="(//label[text()='Generate invoices']//following::button)[1]";

	
	public static final String CreateFessMenu ="//a[@href='createfees.php']";
	public static final String FeesDetailspage ="//*[text()='Fees Details']";
	
	public static final String SelectClassClick ="//input[@class='select2-search__field']";
	public static final String SelectfeeHeading ="//select[@id='selectfeeheading']";
	public static final String FeeNameVal ="//input[@id='feename']";
	public static final String FeeValueVal ="//input[@id='feevalue']";
	public static final String paymentDuration ="//select[@id='selpayduration']";
	public static final String QuarterDrop ="//select[@id='selpayquarter']";
	public static final String DatePopup ="//div[@id='ui-datepicker-div']";
	public static final String PreviouseDate = "//a[contains(@class,'ui-state-default ui-state-highlight')]//preceding::td[1]/a";

	public static final String PmtStartDate = "//input[@id='psd']";
	public static final String PmtEndDate = "//input[@id='ped']";
	public static final String PaymentLateFeesRadiobtn = "//input[@class='late_fee' and @value='Y']";
	public static final String lateFeesInput = "//input[@id='late_fee_amount']";
	public static final String FormSubmitBtn = "//input[@id='frmfeesubmit']";
	public static final String GenerateInvoice ="//a[@href='generateinvoice.php']";
	public static final String PaytypeDropDown ="//select[@id='paytype']";
	public static final String PaytypesubmitBtn ="//input[@type='submit']";

	

	
	


	
	
	
	
	
	
	
	

	

	
	
	

	

	
	


	
	
	

	
	
	
}
