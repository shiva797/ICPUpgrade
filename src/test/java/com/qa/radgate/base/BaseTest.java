package com.qa.radgate.base;

import java.io.FileNotFoundException;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.microsoft.playwright.Page;
import com.qa.radgate.factory.PlaywrightFactory;
import com.qa.radgate.pages.ImportFormPage;
import com.qa.radgate.pages.ImportManagerPage;
import com.qa.radgate.pages.LoginPage;
import com.qa.radgate.pages.RadgateTrialSelectPage;
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

	@BeforeTest
	public void setUp() throws FileNotFoundException {

		System.out.println("beforetest ");
		pf = new PlaywrightFactory();
		prop=pf.init_prop();
		page = pf.initBrowser(prop);
		loginPage = new LoginPage(page);
		System.out.println(loginPage+"Base Test?>>>>>>>>>>>>>>>>>>>>>>");
		

	}
	
	@AfterTest
	public void tearDown() {
		// page.context().browser().close();

	}

}
