package com.qa.radgate.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class LoginPage {

	private Page page;

	// 1. String Locators - OR(Object Repository) ICP Upgrade
	private String userName = "input[id='username']";
	private String password = "Password";
	private String submitButton = ". Sign In";
	public String logoutIcon = "//img[@alt='Logout']";

	// 2. Page constructor:
	public LoginPage(Page page) {
		this.page = page;

	}

	// 3. Page Actions/methods: ICP
	public String getLoginPageTitle() {
		String title = page.title();
		System.out.println("Current Title is: " + title);
		return page.title();

	}

	public String getLoginPageURL() {
		String url = page.url();
		System.out.println("Current URL is: " + url);
		return page.url();

	}

	public boolean doLogin(String userNameValue, String passwordValue) throws InterruptedException {

		String title = page.title();
		page.fill(userName, userNameValue);
		// page.fill(password, passwordValue);
		page.getByLabel(password, new Page.GetByLabelOptions().setExact(true)).click();
		page.getByLabel(password, new Page.GetByLabelOptions().setExact(true)).fill(passwordValue);
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(submitButton)).click();
		System.out.println(userNameValue + " clicked on Submit button");
		String currentTitle = page.title();
		if (title.equalsIgnoreCase(currentTitle)) {
			System.out.println(userNameValue + " already signed in in different machine");
			page.getByLabel(password, new Page.GetByLabelOptions().setExact(true)).click();
			page.getByLabel(password, new Page.GetByLabelOptions().setExact(true)).fill(passwordValue);
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName(submitButton)).click();
		}
		Thread.sleep(2000);
		if (page.isHidden(logoutIcon)) {
			System.out.println(userNameValue + " is not able to login please check user credentials");
			return false;
		}

		System.out.println(userNameValue + " is Logged in Successfully");
		return true;

	}

	public RadgateTrialSelectPage navigateToRadgatePage() {
		/*page.click("");
		page.click("");*/
		System.out.println(page+"Login Page Page class >>>>>>>>>>>>>>>>");
		return new RadgateTrialSelectPage(page);
	}
	
/*	public String importManagerTab = "//a[@class='nav-link active']";
	public boolean doTrialSelect(String trialNameValue) throws InterruptedException {

		// page.click(trilaName);
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(trialNameValue)).click();

		Thread.sleep(2000);
		if (page.isVisible(importManagerTab)) {
			System.out.println(trialNameValue + " trial selected Successfully");
		}
		return true;

	}*/

}