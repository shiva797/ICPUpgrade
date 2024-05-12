package com.qa.radgate.pages;

import java.nio.file.Paths;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.qa.radgate.factory.PlaywrightFactory;

public class WebImportPage {

	private Page page;
	
	private String fileUpload="//input[@id='file-input']";
	private String folderUpload="//input[@id='folder-input']";
	//private String uploadSeriesFilesTableHeaderCheckbox="(//*[@type='checkbox'])[1]";
	private String nextBtn="//a[contains(text(),'NEXT')]";
	
	
	public WebImportPage(Page page) {
		this.page = page;

	}
	
	public String getWebImportPageTitle() {
		return page.title();
		
	}
	
	public String webImportFileBrowse(String uploadFilePath) {
		PlaywrightFactory.dicomModifier("dcmodify.bat");
		System.out.println("Dicom Properties modified successfully");
		if (uploadFilePath.contains(".dcm")) {
			System.out.println("File path to Upload Dicom images is: "+Paths.get(uploadFilePath));
			page.setInputFiles(fileUpload, Paths.get(uploadFilePath));

		} else {
			System.out.println("File path to Upload Dicom images folder: "+Paths.get(uploadFilePath));
			page.setInputFiles(folderUpload, Paths.get(uploadFilePath));

		}
		page.waitForTimeout(1000);
		return page.url();
		
	}
	
	public boolean isDcmCheckboxSelected() {
		//page.click(uploadSeriesFilesTableHeaderCheckbox);
		page.getByRole(AriaRole.CHECKBOX).nth(1).click();
		if (page.isVisible(nextBtn)) {
			System.out.println("DCM Files selected");
			page.click(nextBtn);
			return true;
		}
		return false;
		
	}
	
	

}
