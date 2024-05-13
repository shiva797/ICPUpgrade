package com.qa.radgate.base;

import java.io.FileNotFoundException;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.microsoft.playwright.Page;
import com.qa.radgate.factory.PlaywrightFactory;
import com.qa.radgate.pages.ImportFormPage;
import com.qa.radgate.pages.ImportManagerPage;
import com.qa.radgate.pages.LoginPage;
import com.qa.radgate.pages.RadgateTrialSelectPage;
import com.qa.radgate.pages.WebImportDtfPage;
import com.qa.radgate.pages.WebImportPage;

/**
 * 
 * 
 * 
 * @author Shivakumar
 *
 */
public class BaseTest {

	PlaywrightFactory pf;
	Page page;
	Properties prop;

	protected LoginPage loginPage;
	protected RadgateTrialSelectPage radgateTrialSelectPage;
	protected ImportManagerPage importManagerPage;
	protected ImportFormPage importFormPage;
	protected WebImportPage webImportPage;
	protected WebImportDtfPage webImportDtfPage;

	@Parameters({ "browser" })
	@BeforeTest
	public void setUp(String browserName) throws FileNotFoundException {

		System.out.println("beforetest Browser Name"+browserName);
		pf = new PlaywrightFactory();
		prop=pf.init_prop();
		
		if (browserName != null) {
			prop.setProperty("browser", browserName);
		}
		
		page = pf.initBrowser(prop);
		loginPage = new LoginPage(page);
		

	}
	
	@AfterTest
	public void tearDown() {
		// page.context().browser().close();

	}

}
