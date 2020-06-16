	package com.dotcom.testcases;

import org.testng.annotations.Test;

import com.dotcom.keyword.engine.KeyWordEngine;

public class SingIn {
	
	public KeyWordEngine KeyWordEngine;
	
	@Test (groups = "sanity" )
	
	public void SignIn() throws Throwable
	
	{
		KeyWordEngine=new KeyWordEngine();
		KeyWordEngine.startExecution("Regression");
	}

	 
}
