package com.Airpay.PageObject;

public class Create_Activity_PageObject
{
	public static final String userid="//*[@id='uname']";
	public static final String password="//*[@id='pass']";
	public static final String submit_button="//input[@type='submit' and @value='SIGN IN']";
	public static final String activity_menu="//*[text()='Activity']";
	public static final String add_activity_sub_menu="//a[text()='Add Activity']";
	
	public static final String new_activity_button="//a[@class='btn btn-primary' and text()=' New Activity']";
	
	public static final String activity_title="//input[@class='input-xlarge' and @id='activity_title']";
	public static final String activity_code="//input[@class='input-xlarge' and @id='code']";
	public static final String start_date="//input[@class='input-xlarge' and @id='code']";

	public static final String cal_day="//a[@class='ui-state-default']";
	public static final String cal_month="//select[@class='ui-datepicker-month']";
	public static final String cal_year="//select[@class='ui-datepicker-year']";
	
	public static final String cal_done_button="//button[@class='ui-datepicker-close ui-state-default ui-priority-primary ui-corner-all']";
	
	public static final String end_date="//input[@class='input-xlarge hasDatepicker' and @id='ed_date']";

	public static final String upload_logo="//input[@class='upload_logo' and @id='upload_logo']";

	public static final String choose_file_button="//input[@class='input-xlarge' and @id='logo']";
	
	public static final String school_receipt_heading="//input[@class='input-xlarge' and @id='receipt_heading']";
	
	public static final String meal_show_yes="//input[@class='meal_show' and @value='Y']";
	public static final String meal_show_no="//input[@class='meal_show' and @value='N']";
	public static final String receipt_show_yes="//input[@name='receipt_show' and @value='1']";
	public static final String receipt_show_no="//input[@name='receipt_show' and @value='0']";
	
	public static final String show_email_yes="//input[@class='mailtxt' and @value='1']";
	public static final String show_email_no="//input[@class='mailtxt' and @value='0']";

	public static final String classes="//span[@class='select2-selection select2-selection--multiple']";
	
	public static final String select_class="//li[text()='STD1']";
	
	public static final String activity="//input[@class='input-xlarge' and @id='activity']";

	public static final String fees="//input[@class='input-xlarge' and @id='fees']";

	public static final String add_more_button="//button[@class='btn btn-warning']";
	public static final String pos_required="//input[@class='pos_req' and @id='pos_req']";
	public static final String save_button="//i[@class='icon-save']";
	
	public static final String activity_title_error="//label[@class='error' and @id='activity_title-error']";
	public static final String code_error="//label[@class='error' and @id='code-error']";
	public static final String start_date_error="//label[@class='error' and @id='st_date-error']";
	public static final String end_date_error="//label[@class='error' and @id='ed_date-error']";
	




}
