package com.bren.qa.testcases;
import java.net.MalformedURLException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.pages.DocumentsPage;
import com.bren.qa.pages.InteriorDesignDetailPage;
import com.bren.qa.pages.InteriorDesignPackagesListPage;
import com.bren.qa.pages.LaunchPage;
import com.bren.qa.pages.LoginPage;
import com.bren.qa.pages.OtpVerificationPage;
import com.bren.qa.pages.SingleApartmentHomePage;
import com.bren.qa.report.ExtentManager;
public class InteriorDesignPackagesListPageTest extends Base {
	LaunchPage launchPage;
	LoginPage loginPage;
	OtpVerificationPage otpVerificationPage;
	SingleApartmentHomePage myHomePage;
	DocumentsPage docsPage;
	InteriorDesignPackagesListPage interiorDesignPackagesListPage;
	InteriorDesignDetailPage interiorDesignDetailPage;
	String referAndEarnTitle = "Refer and earn";
	String interiorDesignTitle = "Explore interior design furnishings for your home";
	String expectedPageHeading = "Interior design packages";
	
	public InteriorDesignPackagesListPageTest() {
		super();
	}
	@BeforeMethod
	public void setup() throws MalformedURLException, InterruptedException {
		initialization();
		launchPage = new LaunchPage();
		loginPage = launchPage.clickSignInButton();
		otpVerificationPage = loginPage.enterNumber(prop.get("number").toString());
		Thread.sleep(8000);
		myHomePage = otpVerificationPage.inputOtp(prop.getProperty("otp").toString());
		myHomePage.scrollDownUntil(referAndEarnTitle);
		interiorDesignPackagesListPage = myHomePage.clickViewOptions();
	}
	
	@Test(priority = 1)
	public void verifyInteriorDesignPackageCardContents() { 
		Assert.assertEquals(interiorDesignPackagesListPage.pageHeading.getAttribute("text"), expectedPageHeading);
		ExtentManager.getExtentTest().log(Status.PASS, "Heading verified");
		Assert.assertTrue(interiorDesignPackagesListPage.interiorPackageListCardIsDisplayed(), "Interior Design Card isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Interior Design Card is displayed");
		
		Assert.assertTrue(interiorDesignPackagesListPage.interiorNameAndDescriptionSectionIsDisplayed(), "Interior-Name-And-Description Section isn't Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Interior-Name-And-Description Section Displayed");
		
		Assert.assertTrue(interiorDesignPackagesListPage.interiorImagesSectionIsDisplayed(), "Interior-Images Section is'nt Displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Interior-Images Section Displayed");
		
		Assert.assertTrue(interiorDesignPackagesListPage.interiorDetailsSectionIsDisplayed(), "Interior-Details Section isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Interior Design Card is displayed");
		
		Assert.assertTrue(interiorDesignPackagesListPage.interiorNameIsDisplayed(), "Interior-Name isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Interior-Name is displayed");
	
		Assert.assertTrue(interiorDesignPackagesListPage.viewMoreDetailsLinkIsDisplayed(), "View-More-Details-Link isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "View-More-Details-Link is displayed");
	}
	
	@Test(priority = 2)
	public void clickOnInteriorDesignCardOpensThePackage() {
	
		interiorDesignDetailPage = interiorDesignPackagesListPage.clickInteriorPackageListCard();
		Assert.assertTrue(interiorDesignDetailPage.iamInterestedBtnIsDisplayed(), "Click on Interior design cards isn't opening the package");
		ExtentManager.getExtentTest().log(Status.PASS, "Click on Interior design cards, displaying the design package");
	}
	
	@Test(priority = 3)
	public void clickOnViewMoreDetailsOpensThePackage() {
		interiorDesignDetailPage = interiorDesignPackagesListPage.clickViewMoreDetails();
		Assert.assertTrue(interiorDesignDetailPage.iamInterestedBtnIsDisplayed(), "Click on View-More-Details isn't opening the package");
		ExtentManager.getExtentTest().log(Status.PASS, "click on View-More-Details, opening the design package");
	}
	
	@AfterMethod()
	public void tearDown() {
		driver.quit();
	}
}