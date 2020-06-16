package com.dotcom.testcases;

import org.testng.annotations.Test;

import com.dotcom.keyword.engine.KeyWordEngine;

public class AddInternetToCart {

	
public KeyWordEngine KeyWordEngine;
	
	@Test (groups = "sanity" )
	
	public void AddInternetTOCart() throws Throwable
	
	{
		KeyWordEngine=new KeyWordEngine();
		KeyWordEngine.startExecution("TestSteps");
	}

}
