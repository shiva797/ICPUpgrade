package com.qa.radgate.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class ImportFormPage {

	private Page page;

	public String importFormHeader = "//h2[contains(text(),'Import Form')]";
	public String iFormSiteId = "//*[@name='jhove:ClinicalTrialSiteID']";
	public String iFormSubjectId = "//*[@name='jhove:ClinicalTrialSubjectID']";
	public String iFormTpDescription = "//*[@name='jhove:ClinicalTrialTimePointDescription']";
	public String iFormModality = "//*[@name='jhove:Modality']";
	public String iFormCmitime = "//*[@type='regex']";
	public String iFormSubmitBtn = "//button[contains(text(),'submit')]";

	public ImportFormPage(Page page) {
		this.page = page;
	}

	public boolean isImportFormPageOpen() {
		page.locator(importFormHeader).waitFor();
		if (page.isVisible(importFormHeader)) {
			System.out.println(page.textContent(importFormHeader) + " Launched Successfully");
			return true;
		}
		System.out.println("Import Form unable to Launch");
		return false;
	}

	public boolean isImportFormSubjectIdExist(String subjectIdValue) {
		Locator subjectIdElement = page.locator(iFormSubjectId);
		String fieldValue = subjectIdElement.inputValue();
		if (subjectIdValue.equalsIgnoreCase(fieldValue)) {
			System.out.println(
					"Import Form Subject ID: " + fieldValue + " matched with required Subject ID: " + subjectIdValue);
			return true;

		}
		System.out.println("Import Form Subject ID: " + fieldValue + " is not matched with required Subject ID: "
				+ subjectIdValue);
		return false;

	}

	public WebImportPage navigateToWebImportPage(String timepointDescription, String modality, String cmiTime)
			throws InterruptedException {
		page.locator(iFormTpDescription).selectOption(timepointDescription);
		page.locator(iFormModality).selectOption(modality);
		page.click(iFormCmitime);
		page.locator(iFormCmitime).fill(cmiTime);
	    Page popupPage = page.waitForPopup(() -> page.click(iFormSubmitBtn));
		return new WebImportPage(popupPage);

	}

}
