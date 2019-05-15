package com.Airpay.TestData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.Airpay.BusinessLogic.Student_Report_BuninessLogic;
import com.Airpay.Reporting.Extent_Reporting;
import com.Airpay.Utilities.Log;


public class OpenCSVReader
{	
   public WebDriver driver;
   public String TC_ID = "";
   public static int PayementPosition;
   public static int foundPosition;
   public static boolean flag= true;
   public static String csvFile =System.getProperty("user.dir")+"\\AirPayTestData\\config\\"; 
   public OpenCSVReader(WebDriver driver, String TC_ID)
	{
		Log.info("Airpay_School_Pay_BusinessLogic");
		this.driver = driver;
		this.TC_ID=TC_ID;
	}
	
   
   public static void cSVFileVerify(String Pid) throws Exception{ 
    boolean paymentID = true;  	
    BufferedReader br = null;
    String line = "";   
     try {
             br = new BufferedReader(new FileReader(csvFile+Student_Report_BuninessLogic.FileName));             
             while ((line = br.readLine()) != null) 
             {          	 
            	   System.out.println();          	 
            	   String payid[] = line.split(",");
            	   System.out.println(payid.length);         	           	   
            	   for(int i=0;i<payid.length;i++)
            	   {          		   
            		if(paymentID)
            		{ 
            			System.out.println(payid[i].trim());           			
            		   if(payid[i].trim().contains("Payment Id"))
            		   {
            			   PayementPosition= i;
            			   System.out.println("got it at "+ PayementPosition +" Position");
            			   System.out.println();
            			   paymentID=false;
            			   break;
            		   }
            		   if(i==payid.length){
                		   Extent_Reporting.Log_Pass("Payment id Column does not exist", "passed");
                		   break;
                	   }           		   
            		}    		
            		if(paymentID==false)
            		{	
	            		System.out.println(payid[PayementPosition]);
	            		if(payid[PayementPosition].equalsIgnoreCase(Pid))
	            		{	            			
	            			String RecordsNum= payid[PayementPosition-1];
	            			Extent_Reporting.Log_Pass("Actaul PID is "+payid[PayementPosition] , "Excel PID is "+Pid);
	            			Extent_Reporting.Log_Pass("Transaction record found at "+RecordsNum+" Position", "Passed");
	            			flag= false;
	            			break;
	            		}else{
	            			break;
	            		}
            		}         				   
            	   }
            	   if(flag){
            		   System.out.println("Not founded");
            	   }else{
            		   break;
            	   }
            	     
             }                       
           }catch(Exception e){
            	 e.printStackTrace();
           }
    	
    }
           
}

         