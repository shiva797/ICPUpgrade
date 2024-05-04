package com.qa.radgate.pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class RadgateTrialSelectPage {
	private Page page;
	

	// 1. String Locators:
	public String trilaName = "//h2[normalize-space()='Bayer_21197_OBR']";
	public String importManagerTab = "//a[@class='nav-link active']";
	

	// 2. Page constructor:
	public RadgateTrialSelectPage(Page page) {
		this.page = page;

	}

	// 3. Page Actions/methods:
	public boolean doTrialSelect(String trialNameValue) throws InterruptedException {

		// page.click(trilaName);
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName(trialNameValue)).click();

		Thread.sleep(2000);
		if (page.isVisible(importManagerTab)) {
			System.out.println(trialNameValue + " trial selected Successfully");
		}
		return true;

	}
	
	public String getRadgatePageURL() {
		String url = page.url();
		System.out.println("Current URL is: " + url);
		return page.url();
	}
	
	public ImportManagerPage navigateToImportManagerPage() throws InterruptedException {
		Thread.sleep(2000);
		//page.click("importManagerTab");
		System.out.println("Navigated to Import Manager page");
		return new ImportManagerPage(page);
	}

}
