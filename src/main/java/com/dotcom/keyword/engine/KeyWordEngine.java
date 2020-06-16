package com.dotcom.keyword.engine;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

//import org.apache.poi.hssf.model.Workbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.dotcom.keyword.base.Base;

public class KeyWordEngine {
	
	public WebDriver driver;
	public Properties prop;
	public Base base;
	public WebElement element;
	
	public static Workbook book;
	public static org.apache.poi.ss.usermodel.Sheet sheet;	
	
	public final String SCENARIO_SHEET_PATH="/Users/apple/git/auto/DotComKeyword/src/main/java/com/dotcom/keyword/scenarios/Test_Scenarios.xlsx";
	
	public void startExecution(String sheetName) throws Throwable 
	
	{
		String locatorName="NA";
		String locatorValue="NA";
		 
		FileInputStream file= null;
		try {
			file =new FileInputStream(SCENARIO_SHEET_PATH);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		sheet = book.getSheet(sheetName);
		
		int k=1;
		for(int i=0; i<sheet.getLastRowNum(); i++) 
		{
			
			
				
				String locatorColValue=sheet.getRow(i+1).getCell(k+1).toString().trim();
				if(!locatorColValue.equals("NA"))
				
				{
					locatorName=locatorColValue.split("%%")[0].trim();
					locatorValue=locatorColValue.split("%%")[1].trim();
				} 
				
				String action=sheet.getRow(i+1).getCell(k+2).toString().trim();	
				String value=sheet.getRow(i+1).getCell(k+3).toString().trim();	
				
				
				
				switch (action) {
				
				//open browser
				case "open browser":
					base = new Base();
					prop=base.init_properties();
					//base.init_driver(prop);
					if(value.isEmpty()||value.equals("NA"))
					{
						driver=base.init_driver(prop.getProperty("browser"));
						//Log.info("open browser");
					}
					else 
					{
					driver=base.init_driver(value);
					//Log.info("open browser");
					}
					break;
					
				//enter URL
				
				case "Navigate to site":
					if(value.isEmpty()||value.equals("NA"))
					{
						driver.get(prop.getProperty("url"));
						//Log.info("Nav to site");
					}
					else 
					{
						driver.get(value);
						//Log.info("Nav to site");
					}
					break;
					
				//quit browser
				
				case "quit":
					driver.quit();
					break;
					

				default:
					break;
				}
				
				
				
				//key
				
				switch (locatorName) {
				
				case "id":
					
					element=driver.findElement(By.id(locatorValue));
					
					//sendkeys
					
					
					if(action.equalsIgnoreCase("sendKeys")) 
					try
					{  
						element.clear();
					element.sendKeys(value);
					}
					
					catch (NoSuchElementException e) 
					{
						System.out.println("Send Keys failed");
						System.out.println("Error is " + e);
						}
					
					//click
					else if(action.equalsIgnoreCase("click")) 
					{
						
						element.click();
					}
					
					break;
					
				case "Xpath":
					
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
					element=driver.findElement(By.xpath(locatorValue));
					
					//click
					
					if(action.equalsIgnoreCase("click"))
					{
					 try {
						 try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						element.click();}
					 catch (NoSuchElementException e) 
						{
							System.out.println("click action failed");
							System.out.println("Error is " + e);
							}
					}
					
					//send keys
					
					else if(action.equalsIgnoreCase("sendKeys"))
						try
					{  
						element.clear();
					element.sendKeys(value);
					}
					
					catch (NoSuchElementException e) 
					{
						System.out.println("Send Keys failed");
						System.out.println("Error is " + e);
						}
					//download
					
	
					// Verify link text
					
					else if(action.equalsIgnoreCase("verifyLinkText"))
						
					{
						element.getText().equals(value);
					}
					
					// click button
					else if(action.equalsIgnoreCase("clickButton"))
					{
						element.click(); 
					}
					
					//verify button text
					
					else if(action.equalsIgnoreCase("verifyButtonText"))
					{

						element.getText().equals(value);	
					}
					//select list 
					//verify all list elements
					//verify all list selection
					//SelectRadio
					
					else if(action.equalsIgnoreCase("selectRadio"))
					{
						element.click();
					}
					
					//verifyRadioSelected
					
					else if(action.equalsIgnoreCase("verifyRadioSelected"))
					{
						element.getAttribute("checked");
					}
					
					//check check box
					
					else if(action.equalsIgnoreCase("checkCheckBox"))
					{
						element.getAttribute("checked");
						
					}
					
					//uncheck check box
					
					else if(action.equalsIgnoreCase("uncheckCheckBox")) 
					{
						element.getAttribute("checked");	
					}
					
					// verify checkbox selected
					
					else if(action.equalsIgnoreCase("verifyCheckBoxSelected"))
					{
						element.getAttribute("checked");	
					}
					break;
					
					
					
				case "PLinkText":
					
					element=driver.findElement(By.partialLinkText(locatorValue));
					
					if(action.equalsIgnoreCase("click"))
					{
						element.click();
					}
					
					break;
					
					
					
				case "LinkText":
					
					element=driver.findElement(By.partialLinkText(locatorValue));
					
					if(action.equalsIgnoreCase("click"))
					{
						Thread.sleep(3000);
						element.click();
					}
					
					break;
					

				default:
					break;
				}

				
				
			
			
		}
	}

}
