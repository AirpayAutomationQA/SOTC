package com.Airpay.BusinessLogic;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import com.Airpay.Reporting.Extent_Reporting;

public class LinksActive 
{
	
	public static void main(String []args) throws InterruptedException
	{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\shishir.shah\\git\\SOTC\\Airpay_Automation_Framework_SOTC\\AirPayTestData\\Drivers\\chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		String url="https://staging-ma.airpay.co.in/";
		driver.get(url);
		driver.findElement(By.id("username")).sendKeys("41833502");
		driver.findElement(By.id("password")).sendKeys("RupenLead123");
		
		
		List<WebElement> list=driver.findElements(By.tagName("a"));
		
		for(WebElement ls: list)
		{
			String menu=ls.getText();
			driver.navigate().to(url+menu);
			Thread.sleep(2000);

			Extent_Reporting.Log_report_img(ls.getText(), "Menu name", driver);
		}
		
	}
}
