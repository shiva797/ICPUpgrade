package com.qa.radgate.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.radgate.base.BaseTest;
import com.qa.radgate.constants.AppConstants;
import com.qa.radgate.pages.ImportManagerPage;
import com.qa.radgate.pages.RadgateTrialSelectPage;

public class LoginPageTest extends BaseTest {

	SoftAssert softAssert = new SoftAssert();

	@Test(priority = 1)
	public void loginPageTitleTest() {
		String actualTitle = loginPage.getLoginPageTitle();
		Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}

	@DataProvider
	public Object[][] getDataProvider() {
		return new Object[][] { { "rsvqc1" }, { "rsvqc2" } };
	}

	@Test(priority = 2)
	public void loginTest() throws InterruptedException {
		loginPage.doLogin("rsvsite", "User@123");
		String currentTitle = loginPage.getLoginPageTitle();
		
		Assert.assertEquals(currentTitle, "RADGATE : : Import Web App");
		
	}
	
	@Test(priority = 3)
	@Parameters("trialNameValue")
	public void trialSelectPageTest(String trialNameValue) throws InterruptedException {
		radgateTrialSelectPage=loginPage.navigateToRadgatePage();
		boolean isTrialSelect = radgateTrialSelectPage.doTrialSelect(trialNameValue);
		Assert.assertEquals(true, isTrialSelect);
	}
	
	@Test(priority = 4)
	@Parameters("siteIdValue")
	public void selectSiteIdTest(String siteIdValue) throws InterruptedException {
		importManagerPage=radgateTrialSelectPage.navigateToImportManagerPage();
		boolean isSiteIdSelect=importManagerPage.doSelectSiteId(siteIdValue);
		Assert.assertTrue(isSiteIdSelect);
		
	}
	
	@Test(priority = 5)
	@Parameters({"siteIdValue", "subjectIdValue"})
	public void selectSubjectIdTest(String siteIdValue, String subjectIdValue) throws InterruptedException {
		//importManagerPage=radgateTrialSelectPage.navigateToImportManagerPage();
		boolean isSubjectIdSelect=importManagerPage.doSelectSubjectId(siteIdValue, subjectIdValue);
		Assert.assertTrue(isSubjectIdSelect);
		
	}
	
	@Test(priority = 6)
	public void importFormNavigationTest() throws InterruptedException {
		importFormPage=importManagerPage.navigateToImportFormPage();
		Assert.assertTrue(importFormPage.isImportFormPageOpen());
		
	}
	
	@Test(priority = 7)
	@Parameters({"subjectIdValue", "timepointDescription", "modality", "cmiTime"})
	public void webImportPageNavigationTest(String subjectIdValue, String timepointDescription, String modality, String cmiTime) throws InterruptedException {
		webImportPage=importFormPage.navigateToWebImportPage(timepointDescription, modality, cmiTime);
		String importToolTitle=webImportPage.getWebImportPageTitle();
		Assert.assertEquals(importToolTitle, AppConstants.IMPORTTOOL_PAGE_TITLE);
		
	}
	
	

}
