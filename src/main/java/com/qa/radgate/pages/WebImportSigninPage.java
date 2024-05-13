package com.qa.radgate.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class WebImportSigninPage {

	private Page page;
	
	private String userName = "input[id='username']";
	private String password = "Password";
	private String submitButton = ". Sign In";
	
	public WebImportSigninPage(Page page) {
		this.page=page;

	}
	
	public boolean doSignin(String userNameValue, String passwordValue) throws InterruptedException {

		page.fill(userName, userNameValue);
		// page.fill(password, passwordValue);
		page.getByLabel(password, new Page.GetByLabelOptions().setExact(true)).click();
		page.getByLabel(password, new Page.GetByLabelOptions().setExact(true)).fill(passwordValue);
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(submitButton)).click();
		System.out.println(userNameValue + " clicked on Signin button");
		

		System.out.println(userNameValue + " is Logged in Successfully");
		return true;

	}

}
