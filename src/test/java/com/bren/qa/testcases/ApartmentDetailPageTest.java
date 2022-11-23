package com.bren.qa.testcases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.helper.ScrollHelper;
import com.bren.qa.pages.ApartmentDetailPage;
import com.bren.qa.pages.ApartmentsListPage;
import com.bren.qa.pages.LaunchPage;
import com.bren.qa.pages.LoginPage;
import com.bren.qa.pages.MultipleApartmentHomePage;
import com.bren.qa.pages.OtpVerificationPage;
import com.bren.qa.pages.ReferAndEarnFormPage;
import com.bren.qa.pages.SingleApartmentHomePage;
import com.bren.qa.report.ExtentManager;
import com.bren.qa.report.ExtentReport;


public class ApartmentDetailPageTest extends Base {
    int count = 0;
    int maxTries = 3;
	String portraitMode ="PORTRAIT";
	String referAndEarnDescreption = "Refer people to buy an apartment with Bren and "
			+ "earn a reward when the referred person buys an apartment";
	LaunchPage launchPage;
	LoginPage loginPage;
	OtpVerificationPage otpVerificationPage;
	SingleApartmentHomePage myHomePage;
	MultipleApartmentHomePage multipleApartmentHomePage;
	ApartmentsListPage apartmentsListPage;
	ApartmentDetailPage apartmentDetailPage;
	ReferAndEarnFormPage referAndEarnFormPage;
	
	
	public ApartmentDetailPageTest() {
		super();
	}
	@BeforeMethod(alwaysRun=true)
	public void setup(Method m) throws MalformedURLException, InterruptedException {
	    while(true) {
	        try{
	            ExtentReport.testInitialization(m);
        		initialization();
        		launchPage = new LaunchPage();
        		loginPage = launchPage.clickSignInButton();
        		otpVerificationPage = loginPage.enterNumber(prop.get("number").toString());
        		Thread.sleep(8000);
        		driver.manage().timeouts().implicitlyWait(240, TimeUnit.SECONDS);
        		driver.findElementByXPath("//*[@text = 'Enter OTP']");
        		myHomePage = otpVerificationPage.inputOtp(prop.getProperty("otp").toString());
        		Thread.sleep(8000);
        		driver.findElementByXPath("//*[@text = 'About this property']");
        		apartmentsListPage = myHomePage.clickApartmentsTab();
        		Thread.sleep(5000);
        		apartmentDetailPage = apartmentsListPage.clickOnZaharaByBrenProject();
        		multipleApartmentHomePage = new MultipleApartmentHomePage();
        		Thread.sleep(4000);
        		return;
	        }
            catch(Exception e){
                System.out.println(e);
                if (++count == maxTries) throw e;
            }
        }
	}
//  	@Test(priority = 1, alwaysRun=true)
	public void verifySectionsInTheApartmentDetailPage() {
		
		Assert.assertTrue(apartmentDetailPage.takeVirtualTourButtonIsDisplayed(), "Take Virtual Tour Button isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Take Virtual Tour Button is displayed");
		
		Assert.assertTrue(apartmentDetailPage.viewGalleryButtonIsDisplayed(), "View Gallery Button isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "View Gallery Button is displayed");
		
		Assert.assertTrue(apartmentDetailPage.apartmentTitleIsDisplayed(), "Apartment Title isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Apartment Title is displayed");
			
		Assert.assertTrue(apartmentDetailPage.shareAndDownloadIconsIsDisplayed(), "Share And Download Icons isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Share And Download Icons is displayed");
		
		Assert.assertTrue(apartmentDetailPage.projectImageContainerIsDisplayed(), "Project Image Container isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Project Image Container is displayed");
		
		ScrollHelper.scrollUntil("LOCATION");
		Assert.assertTrue(apartmentDetailPage.locationSection.isDisplayed(), "Location section isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Location section is displayed");
		
		ScrollHelper.scrollUntil("PRICE");
		Assert.assertTrue(apartmentDetailPage.priceSection.isDisplayed(), "Price section isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Price section is displayed");
		
		ScrollHelper.scrollUntil("ZAHARA BY BREN HIGHLIGHTS");
		Assert.assertTrue(apartmentDetailPage.highLightsSectionIsDisplayed(), "HighLights Section isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "HighLights Section is displayed");
		 
		ScrollHelper.scrollUntil("WALKTHROUGH");
		ScrollHelper.scrollDown();
		Assert.assertTrue(apartmentDetailPage.youtubePlayerContainerIsDisplayed(), "Youtube Player Container isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Youtube Player Container is displayed");
		
		ScrollHelper.scrollUntil("AMENITIES");
        apartmentDetailPage.downArrow.click();
        Assert.assertTrue(apartmentDetailPage.amenitiesNameIsDisplayed(), "Clicking on drop down arrow for each amenities isnt opening the detailed list");
        ExtentManager.getExtentTest().log(Status.PASS, "Clicking on drop down arrow for each amenities isnt opening the detailed list");
        
		
		ScrollHelper.scrollUntil("Refer people to buy an apartment with Bren and earn a reward when the referred person buys an apartment");
		Assert.assertTrue(apartmentDetailPage.requestForACallBackButtonIsDisplayed(), "REQUEST FOR A CALLBACK isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "REQUEST FOR A CALLBACK section is displayed");
		
		Assert.assertTrue(apartmentDetailPage.referAndEarnSectionIsDisplayed(), "Refer And Earn Section isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Refer And Earn section is displayed");	
		
		
	}
	
//  	@Test(priority = 2, alwaysRun=true)
	public void verifyThatTheUserCanSeeTheListOfAllSpecificationsByClickingOnTheViewMoreOption() {
		ScrollHelper.scrollUntil("SPECIFICATIONS");
		try {
			driver.findElementByXPath("//*[@text = 'View more']").click();
		}
		catch(NoSuchElementException e) {
			ScrollHelper.scrollDown();
			Assert.assertTrue(driver.findElementByXPath("//*[@text = 'View less']").isDisplayed(), "User Can't See List of all Specifications By Clicking On The ViewMore Option");
			ExtentManager.getExtentTest().log(Status.PASS, "verified that The User can See The List Of All Specifications by clicking On The View more Option");
		}
		
	}
 	@Test(priority = 3, alwaysRun=true)
	public void verifySeeAllImagesClickingInViewGallery() throws InterruptedException {
		apartmentDetailPage.clickViewGallery();
		Thread.sleep(5000);
		System.out.println(driver.getPageSource());
		Thread.sleep(20000);
		System.out.println(driver.getPageSource());
		apartmentDetailPage.swipeGalleryImages();
		Assert.assertEquals(apartmentDetailPage.getCurrentImagePosition(), apartmentDetailPage.getImageCount(), "All images in the gallery section is viewed ");
	}
	
//  	@Test(priority = 4, alwaysRun=true)
	public void verifyUserCanReturnToApartmentDetailScreenByClickOnTheCloseIconOnImages() throws InterruptedException {
		apartmentDetailPage.clickViewGallery();
		Thread.sleep(5000);
		apartmentDetailPage.clickOnImageElement();
		Thread.sleep(3000);
		driver.findElementByXPath("//*[@resource-id = 'iconIcon']").click();

		Assert.assertTrue(apartmentDetailPage.viewGalleryButtonIsDisplayed(), "Doesn't return back to detail page after clicking on close icon");
		ExtentManager.getExtentTest().log(Status.PASS, "Returns back to Apartment detail Page after clicking on Close Icon");
	}
//  	@Test(priority =5, alwaysRun=true)
	public void verifyImagesAreInPortraitMode() throws InterruptedException {
		apartmentDetailPage.clickViewGallery();
		Thread.sleep(5000);
		Assert.assertEquals(apartmentDetailPage.getScreenOrientation(),portraitMode, "Images are not In Portrait Mode");
		ExtentManager.getExtentTest().log(Status.PASS, "Images are in Portrait Mode");
	}
	
//  	@Test(priority =6, alwaysRun=true)
	public void verifyTakeVirtualTourOpensVirtualTour() throws InterruptedException {
		apartmentDetailPage.clickVirtualTour();
		Thread.sleep(5000);
		Assert.assertTrue(apartmentDetailPage.webViewIsDisplayed(), "Not Opening Virtual Tour");
		ExtentManager.getExtentTest().log(Status.PASS, "Opened Virtual Tour");
	}
	
//  	@Test(priority = 7, alwaysRun=true)
	public void verifyShareButton() throws InterruptedException {
		apartmentDetailPage.clickShare();
		driver.findElementByXPath("//*[@text = 'ALLOW']").click();
		driver.manage().timeouts().implicitlyWait(240, TimeUnit.SECONDS);
		Assert.assertTrue(apartmentDetailPage.shareTitleIsDisplayed(), "Not able to share the docs");
		
	}
//  	@Test(priority = 8, alwaysRun=true)
	public void documentDownloadVerification() throws IOException, InterruptedException {
		Thread.sleep(5000);
		String expectedToastMessage = "Downloading please wait..";
		apartmentDetailPage.clickDownloadIcon();
		driver.manage().timeouts().implicitlyWait(180, TimeUnit.SECONDS);
		driver.findElementByXPath("//*[@text = 'ALLOW']").click();
		String actualToastMessage = driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
	    Assert.assertEquals(actualToastMessage, expectedToastMessage);
	    ExtentManager.getExtentTest().log(Status.PASS, "Document downloaded successfully"); 
	}
	
//  	@Test(priority = 9, alwaysRun=true)
	public void verifyUserCanSlideAllImagesAddedinTheHighlights() throws InterruptedException {
        ScrollHelper.scrollUntil("WALKTHROUGH");
	ScrollHelper.scrollDown();
        ScrollHelper.scrollUp();
		Assert.assertTrue(apartmentDetailPage.highLightsSubTitle1IsDisplayed(), "First Highlights section is not displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "First HighLight Is Displayed");
		apartmentDetailPage.clickLeftAndRightArrows();
		
		ExtentManager.getExtentTest().log(Status.INFO, "Right arrow in the highlight section is clicked");
		Assert.assertTrue(apartmentDetailPage.highLightsSubTitle2IsDisplayed(), "second Highlight isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "second Highlight Is Displayed");
		apartmentDetailPage.clickLeftAndRightArrows();
		Thread.sleep(4000);
		ExtentManager.getExtentTest().log(Status.INFO, "Right arrow in the highlight section is clicked");
		Assert.assertTrue(apartmentDetailPage.highLightsSubTitle3IsDisplayed(), "Third Highlight isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Third Highlight Is Displayed");
		apartmentDetailPage.clickLeftAndRightArrows();
		Thread.sleep(4000);
		ExtentManager.getExtentTest().log(Status.INFO, "Right arrow in the highlight section is clicked");
		Assert.assertTrue(apartmentDetailPage.highLightsSubTitle4IsDisplayed(), "Fourth Highlight isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Fourth Highlight Is Displayed");
		apartmentDetailPage.clickLeftAndRightArrows();
		Thread.sleep(4000);
		ExtentManager.getExtentTest().log(Status.INFO, "Right arrow in the highlight section is clicked");
		Assert.assertTrue(apartmentDetailPage.highLightsSubTitle5IsDisplayed(), "Fifth Highlight isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Fifth Highlight Is Displayed");
		apartmentDetailPage.clickLeftAndRightArrows();
		Thread.sleep(4000);
		ExtentManager.getExtentTest().log(Status.INFO, "Right arrow in the highlight section is clicked");
		Assert.assertTrue(apartmentDetailPage.highLightsSubTitle6IsDisplayed(), "Sixth Highlight isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Sixth HighLight Is Displayed");	
	}
	
//  	@Test(priority = 10, alwaysRun=true)
	public void verifyWalkthroughVideoAddedInTheDetailScreenIsPlayingWhenClickingOnIt() {
	    ScrollHelper.scrollUntil("WALKTHROUGH");
	    ScrollHelper.scrollDown();
		apartmentDetailPage.clickYoutubeVideContainer();
		Assert.assertTrue(apartmentDetailPage.pauseButtonIsDisplayed(), "Walkthrough Video Added In The Detail Screen Is Playing When Clicking On It");
		ExtentManager.getExtentTest().log(Status.PASS, "Verified Walkthrough Video Added In The Detail Screen is Playing When Clicking On It");
	}
 	@Test(priority = 11, alwaysRun=true)
	public void verifyThatUserCanViewTheWalkthroughVideoInFullScreen() throws InterruptedException {
		System.out.println(driver.getOrientation());
		ScrollHelper.scrollUntil("WALKTHROUGH");
		ScrollHelper.scrollDown();
		apartmentDetailPage.clickYoutubeVideContainer();
		driver.findElementByXPath("//*[@text = 'Full screen']").click();
		Thread.sleep(3000);
		System.out.println(driver.getOrientation());
		Assert.assertEquals(apartmentDetailPage.getScreenOrientation(), "LANDSCAPE", "Video is playing in full screen mode");
	}
//  	@Test(priority = 12, alwaysRun=true)
	public void verifyAmenitiesSection() {
		
	    ScrollHelper.scrollUntil("AMENITIES");
		ScrollHelper.scrollDown();
		Assert.assertTrue(apartmentDetailPage.amenitiesTitleIsDisplayed(), "Amenities Title isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Amenities Title Is Displayed");	
	
		Assert.assertTrue(apartmentDetailPage.amenitiesSectionIsDisplayed(), "Amenities Section isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Amenities Section Is Displayed");	
	
		Assert.assertTrue(apartmentDetailPage.amenitiesNameIsDisplayed(), "Amenities Name isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Amenities Name Is Displayed");	
		Assert.assertTrue(apartmentDetailPage.downArrowIsDisplayed(), "Down Arrow isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Down Arrow is Displayed");		
	}
// 	@Test(priority = 13, alwaysRun=true)
	public void verifyUserCanClickOnDropDownArrowOnSpecificationForADetailedList() {
	    	ScrollHelper.scrollUntil("SPECIFICATIONS");
		ScrollHelper.scrollDown();
		driver.findElementByXPath("//*[@text = 'ELEVATORS']").click();
		System.out.println(driver.getPageSource());
		Assert.assertTrue(apartmentDetailPage.detailsOfSpecification.isDisplayed(), "Clicking on Drop Down Arrow on a Specification isn't opening a DetailedList");
		ExtentManager.getExtentTest().log(Status.PASS, "Clicking on Drop Down Arrow on a Specification opens a Detailed List");	
		
	}
//  	@Test(priority = 14, alwaysRun=true)
	public void verifyUserWillGetPopupMessageWhenClickOnRequestForACallBackFromApartmentDetailScreen() throws InterruptedException {
		String  actualtoastMessage;
		String expectedToastMessage = "The contact information that you shared already exists in our system. We appreciate your time";
		ScrollHelper.scrollUntil("Refer and earn");
		apartmentDetailPage.requestForACallBackButton.click();
		Thread.sleep(60000);
		apartmentDetailPage.requestForACallBackButton.click(); 
		actualtoastMessage = driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
	
		Assert.assertEquals(actualtoastMessage, expectedToastMessage, "Pop-up message isn't displayed after clicking on Request For CallBack");
	    ExtentManager.getExtentTest().log(Status.PASS, "Pop-up message is verified");
	}
//  	@Test(priority = 15, alwaysRun=true)
	public void verifyReferAFriendFormIsOpeningWhenUserClicksOnTheReferAFriendOptionFromApartmentDetailsScreen() {
	    ScrollHelper.scrollUntil(referAndEarnDescreption);
		ScrollHelper.scrollDown();
		referAndEarnFormPage = apartmentDetailPage.clickReferAndEarnButton();
		Assert.assertTrue(referAndEarnFormPage.formDescreption.isDisplayed(), "Refer And Earn Form is not opening");
		ExtentManager.getExtentTest().log(Status.PASS, "Refer and Earn Form is opened When User Clicks On The Refer A Friend option");
		
	}
//  	@Test(priority = 16, alwaysRun=true)
	public void verifyThatReferAFriendFormShouldBePreselectedWithCorrespondingApartment() throws InterruptedException {
		String projectTitle = driver.findElementByXPath("//android.widget.TextView[@index ='0']").getAttribute("text");
		System.out.println(projectTitle);
		ScrollHelper.scrollUntil("REFER A FRIEND");
		referAndEarnFormPage = apartmentDetailPage.clickReferAndEarnButton();
		ScrollHelper.scrollDownUntil("Refer friend");
		String preSelectedApartment = driver.findElementByXPath("//*[@resource-id = 'text_input']").getAttribute("text");
		System.out.println(preSelectedApartment);
		Assert.assertEquals(preSelectedApartment, projectTitle, "Refer A Friend Form isn't Preselected With Corresponding Apartment");
		ExtentManager.getExtentTest().log(Status.PASS, "Verified that Refer A Friend Form is Preselected With Corresponding Apartment");
		
	}
	
//  	@Test(priority = 17, alwaysRun=true)
	public void verifyUserIsGettingConfirmationScreenOrAlreadyExistingReferalAfterClickingOnTheReferFriendOption() throws InterruptedException {
	    ScrollHelper.scrollUntil(referAndEarnDescreption);
		ScrollHelper.scrollDown();
		referAndEarnFormPage = apartmentDetailPage.clickReferAndEarnButton();
		Thread.sleep(5000);
		referAndEarnFormPage.firstNameField.sendKeys(prop.getProperty("referAFriendFirstName"));
		Thread.sleep(5000);
		String firstNameFieldValue = referAndEarnFormPage.firstNameField.getAttribute("text");
		referAndEarnFormPage.lastNameField.sendKeys(prop.getProperty("referAFriendLastName"));
		Thread.sleep(5000);
		referAndEarnFormPage.emailAddressField.sendKeys(prop.getProperty("referAFriendEmailAddress"));
		Thread.sleep(5000);
		referAndEarnFormPage.mobileNumberField.sendKeys(prop.getProperty("referAFriendMobileNumber"));
		ScrollHelper.scrollDown();
		Assert.assertTrue(referAndEarnFormPage.isConfirmationScreenOrExistingReferalMessageIsDisplayed(firstNameFieldValue), "User isn't Getting Confirmation Screen after clicking on the Refer Friend option");
		ExtentManager.getExtentTest().log(Status.PASS, "User is Getting Confirmation Screen after clicking on the Refer Friend option");
		
	}
//  	@Test(priority = 18, alwaysRun=true)
	public void verifyThatTheUserIsNavigatingToTheHomeScreenWhenClickingOnTheBrensIconFromSingleApartmentDetailsScreen() throws InterruptedException {

		driver.findElementByXPath("//*[@resource-id ='RNE__Image']").click();
		myHomePage.verifySingleApartmentHomePage();
		ExtentManager.getExtentTest().log(Status.PASS, "Verified that the User is Navigating to the Home"
				+ " Screen When clicking on the Brens Icon from Single Apartment Details screen");
	}
	

}
