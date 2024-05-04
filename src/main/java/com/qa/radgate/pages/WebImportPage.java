package com.qa.radgate.pages;

import com.microsoft.playwright.Page;

public class WebImportPage {

	private Page page;
	
	

	public WebImportPage(Page page) {
		this.page = page;

	}
	
	public String getWebImportPageTitle() {
		return page.title();
		
	}

}
