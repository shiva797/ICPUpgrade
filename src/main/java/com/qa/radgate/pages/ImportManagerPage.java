package com.qa.radgate.pages;

import com.microsoft.playwright.Page;

public class ImportManagerPage {

	private Page page;

	// 1. String Locators - OR(Object Repository)
	public String siteId = "(//input[@class='input-box'])[1]";
	public String siteIdOption = "//div[@class='option ']";
	public String siteNoOption = "";
	public String createSubjectBtn = "//div[@class='create-subject']";
	public String createSubjectID = "//input[@name='patientID']";
	public String createSubjectSubmitBtn = "//button[contains(text(),'submit')]";
	public String createSubjectFailMsg = "//*[@class='toast error show']";

	public String subjectId = "(//input[@class='input-box'])[2]";
	public String subjectIdOption = "(//input[@class='input-box'])[2]/../div[1]";

	public String importIcon="//*[@title='Import']";


	// 2. Page constructor:
	public ImportManagerPage(Page page) {
		this.page = page;

	}

	// 3.
	public boolean doSelectSiteId(String siteIdValue) throws InterruptedException {
		page.waitForTimeout(2000);
		/*
		 * page.locator(siteId).first().click(); page.getByText(siteIdValue).click();
		 * page.click(siteId);
		 */
		if (siteIdValue != null) {
			page.fill(siteId, siteIdValue);
			page.click(siteIdOption);
			if (page.isVisible(createSubjectBtn)) {
				System.out.println("Site ID " + siteIdValue + " selected successfully");
				return true;
			}
			System.out.println("Site ID " + siteIdValue + " is not exist, Please select the correct Site ID");
			return false;
		}
		System.out.println("No site ID provided.");
		return false;
	}

	public boolean doCreateSubject(String createSubjectValue) {
		page.waitForTimeout(2000);
		String patientId = createSubjectValue.substring(5);
		System.out.println("Subject ID: " + patientId + " creation started");
		page.click(createSubjectBtn);
		page.fill(createSubjectID, patientId);
		page.click(createSubjectSubmitBtn);
		// page.getByRole(AriaRole.BUTTON, new
		// Page.GetByRoleOptions().setName("submit")).click();//Recorded statement

		if (page.isVisible(createSubjectFailMsg)) {
			System.out.println(page.textContent(createSubjectFailMsg));
			return false;
		}
		return true;

	}

	public boolean doSelectSubjectId(String siteIdValue, String subjectIdValue) throws InterruptedException {
		page.waitForTimeout(2000);
		if (subjectIdValue != null) {
			page.fill(subjectId, subjectIdValue);
			String subjectIDSearchOption = page.textContent(subjectIdOption);
			System.out.println("Subject ID Search result is: " + subjectIDSearchOption);
			if (subjectIDSearchOption.equalsIgnoreCase("No Options")) {
				System.out.println("Subject ID " + subjectIdValue + " is not available");
				if (doCreateSubject(subjectIdValue)) {
					System.out.println("Subject ID: " + subjectIdValue + " created successfully");
				}
				page.fill(subjectId, subjectIdValue);
				page.click(subjectIdOption);
				System.out.println("Subject ID: " + subjectIdValue + " selected successfully");
				return true;
			} else if (!subjectIDSearchOption.startsWith(siteIdValue)) {
				System.out.println("Subject ID " + subjectIdValue.substring(0, siteIdValue.length())
						+ " prefix is not matched with the selected Site ID " + siteIdValue);
				return false;
			} 
			page.click(subjectIdOption);
			System.out.println(" Subject ID " + subjectIdValue + " selected successfully");
			return true;
		} else {
			System.out.println("No Subject ID provided.");
			return false;
		}
	}
	
	public ImportFormPage navigateToImportFormPage() {
		page.click(importIcon);
		System.out.println("Navigating to Import Form page");
		return new ImportFormPage(page);
		
	}

}
