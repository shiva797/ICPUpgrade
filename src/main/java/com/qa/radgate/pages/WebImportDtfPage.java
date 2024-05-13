package com.qa.radgate.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class WebImportDtfPage {

	private Page page;
	
	private String dtfNumber="//p[contains(text(),'DTF# Bayer')]";
	private String dtfCmiTime="//b[contains(text(),'Contrast Media Injection Time')]/../..//span";
	private String dtfNotes="//textarea[@placeholder='Notes/Comments...']";
	private String nextBtn="//a[contains(text(),'NEXT')]";

	public WebImportDtfPage(Page page) {
		this.page = page;
	}
	
	public String getWebImportDtfPageURL() {
		String url =  page.url();
		System.out.println("Web Import Dtf page url : " + url);
		return url;
	}
	
	public String getWebImportDtfNumber() {
		page.locator(dtfCmiTime).waitFor();
		String dtfNumElement = page.textContent(dtfNumber);
		return dtfNumElement;
		
	}
	
	public boolean isInDtfCmiTimeValueExist(String cmiTime) {
		//Locator dtfCmiTimeElement = page.locator(dtfCmiTime);
		String fieldValue = page.textContent(dtfCmiTime);
		if (cmiTime.equalsIgnoreCase(fieldValue)) {
			System.out.println("Import Form CMI Time: " + cmiTime + " is matched with DTF CMI Time : "
					+ fieldValue);
			return true;

		}
		System.out.println("Import Form CMI Time: " + cmiTime + " is not matched with DTF CMI Time : "
				+ fieldValue);
		return false;

	}
	
	public WebImportSigninPage navigateToWebImportSigninPage(String dtfComment) {
		page.fill(dtfNotes, dtfComment);
		page.click(nextBtn);
		return new WebImportSigninPage(page);
	}
}
