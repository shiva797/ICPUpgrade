package com.qa.radgate.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.radgate.base.BaseTest;
import com.qa.radgate.constants.AppConstants;

public class RadgateTrialSelectPageTest extends BaseTest {

	SoftAssert softAssert = new SoftAssert();

	@Test(priority = 1)
	public void radgateTrialSelectPageNavigationTest() throws InterruptedException {
		radgateTrialSelectPage = loginPage.navigateToRadgatePage();
		String afterLoginRadgateURL=radgateTrialSelectPage.getRadgatePageURL();
		System.out.println(radgateTrialSelectPage+"*************");
		Assert.assertEquals(afterLoginRadgateURL, AppConstants.RADGATE_PAGE_URL);
		
	}

}
