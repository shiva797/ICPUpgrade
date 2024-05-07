package com.qa.radgate.pages;

import java.nio.file.Paths;

import com.microsoft.playwright.Page;
import com.qa.radgate.factory.PlaywrightFactory;

public class WebImportPage {

	private Page page;
	
	private String fileUpload="//input[@id='file-input']";
	private String folderUpload="//input[@id='folder-input']";
	
	public WebImportPage(Page page) {
		this.page = page;

	}
	
	
	
	public String getWebImportPageTitle() {
		return page.title();
		
	}
	
	public String webImportFileBrowse(String uploadFilePath) {
		PlaywrightFactory.dicomModifier("dcmodify.bat");
		page.getByText("Browse Files").click();
		page.setInputFiles(fileUpload, Paths.get(uploadFilePath));
		page.waitForTimeout(2000);
		return page.url();
		
	}

}
