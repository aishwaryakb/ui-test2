package com.bren.qa.testcases;

import java.lang.reflect.Method;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import java.io.File;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.InvalidElementStateException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.bren.qa.base.Base;
import com.bren.qa.pages.LaunchPage;
import com.bren.qa.pages.LoginPage;
import com.bren.qa.pages.MultipleApartmentHomePage;
import com.bren.qa.pages.OtpVerificationPage;
import com.bren.qa.pages.OwnerProfilePage;
import com.bren.qa.pages.SingleApartmentHomePage;
import com.bren.qa.report.ExtentManager;
import com.bren.qa.report.ExtentReport;

public class OwnerProfilePageTest extends Base {
    int count = 0;
    int maxTries = 3;
	LaunchPage launchPage;
	LoginPage loginPage;
	OtpVerificationPage otpVerificationPage;
	MultipleApartmentHomePage myHomePage;
	SingleApartmentHomePage singleApartmentHomePage;
	OwnerProfilePage ownerProfilePage;
	
	public OwnerProfilePageTest() {
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
        		otpVerificationPage = loginPage.enterNumber(prop.get("multpleApartmentsOwnerNumber").toString());
        		Thread.sleep(8000);
        		driver.manage().timeouts().implicitlyWait(240, TimeUnit.SECONDS);
                driver.findElementByXPath("//*[@text = 'Enter OTP']");
        		myHomePage = otpVerificationPage.inputOtpForMultupleApartmentAccount(prop.getProperty("multpleApartmentsOwnerOtp").toString());
        		ownerProfilePage = myHomePage.clickProfileTab();
        		singleApartmentHomePage = new SingleApartmentHomePage();
        		return;
            }
            catch(Exception e){
                System.out.println(e);
                if (++count == maxTries) throw e;
            }
        }
		
	}
// 	@Test(priority = 1, alwaysRun=true)
	public void verifyDetailsInProfilePage() throws InterruptedException {
		Thread.sleep(5000);
		Assert.assertTrue(ownerProfilePage.profilePicture.isDisplayed(), "Profile Picture isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Profile Picture is displayed");
		Assert.assertTrue(ownerProfilePage.nameTitle.isDisplayed(), "Name Title isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Name Title is displayed");
		Assert.assertTrue(ownerProfilePage.nameInputField.isDisplayed(), "Name Input Field isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Name Input Field is displayed");
		
		Assert.assertTrue(ownerProfilePage.emailAddressTitle.isDisplayed(), "Email Address Title isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Email Address Title is displayed");
		Assert.assertTrue(ownerProfilePage.emailInputField.isDisplayed(), "Email Input Field isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Email Input Field is displayed");
		
		Assert.assertTrue(ownerProfilePage.phoneNumberTitle.isDisplayed(), "Phone Number Title isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Phone Number Title is displayed");
		Assert.assertTrue(ownerProfilePage.mobileInputField.isDisplayed(), "Mobile Input Field isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Mobile Input Field  is displayed");
		Assert.assertTrue(ownerProfilePage.countryCodeInputField.isDisplayed(), "Country code Input field isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "Country code Input field is displayed");
		
		Assert.assertTrue(ownerProfilePage.logOutButton.isDisplayed(), "Log Out Button isn't displayed");
		ExtentManager.getExtentTest().log(Status.PASS, "LogOut Button is displayed");
	}
	
// 	@Test(priority = 2, alwaysRun=true)
	public void verifyThatTheNameEmailAddressAndPhoneNumberFieldsAreNonEditable() throws InterruptedException {
		Thread.sleep(5000);
		String preSetFieldValue;
		String currentFieldValue = "";
		
		preSetFieldValue = ownerProfilePage.nameInputField.getAttribute("text");
		try {
			ownerProfilePage.nameInputField.sendKeys(prop.getProperty("referAFriendFirstName"));
		}
		catch(InvalidElementStateException e) {
			currentFieldValue = ownerProfilePage.nameInputField.getAttribute("text");
		}
		Assert.assertTrue(currentFieldValue.equals(preSetFieldValue), "Name Input field is Editable");
		ExtentManager.getExtentTest().log(Status.PASS, "Name Input field non editable");
		
		
		preSetFieldValue = ownerProfilePage.emailInputField.getAttribute("text");
		try {
			ownerProfilePage.emailInputField.sendKeys(prop.getProperty("referAFriendEmailAddress"));
		}
		catch(InvalidElementStateException e) {
			currentFieldValue = ownerProfilePage.emailInputField.getAttribute("text");
		}
		Assert.assertTrue(currentFieldValue.equals(preSetFieldValue), "Email Input field is Editable");
		ExtentManager.getExtentTest().log(Status.PASS, "Email Input field non editable");
		
		
		
		
		preSetFieldValue = ownerProfilePage.mobileInputField.getAttribute("text");
		try {
			ownerProfilePage.mobileInputField.sendKeys(prop.getProperty("referAFriendMobileNumber"));
		}
		catch(InvalidElementStateException e) {
			currentFieldValue = ownerProfilePage.mobileInputField.getAttribute("text");
		}
		Assert.assertTrue(currentFieldValue.equals(preSetFieldValue), "Mobile Input field is Editable");
		ExtentManager.getExtentTest().log(Status.PASS, "Mobile Input field non editable");
		
	}
	@Test(priority = 3, alwaysRun=true)
	public void verifyThatTheUserCanLogOutTheAccountByClickingOnTheLogoutButton()throws IOException {
		try{
			File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(file, new File("screen.jpg"));
			ownerProfilePage.logOutButton.click();
			ExtentManager.getExtentTest().log(Status.INFO, "clicked on logout button");
			Assert.assertTrue(launchPage.continueAsGuestButton.isDisplayed(), "Logout wasn't successfull");
			ExtentManager.getExtentTest().log(Status.PASS, "Logout was successful");
		}
		catch(Exception e){
			System.out.println(e);
		}
		
	}
// 	@Test(priority = 5, alwaysRun=true)
	public void verifyThatTheUserIsNavigatingToTheHomeScreenWhenClickingOnTheBrensIconFromOwnerProfilePage() throws InterruptedException {
		driver.findElementByXPath("//*[@resource-id ='RNE__Image']").click();
		myHomePage.homePageVerification();
		ExtentManager.getExtentTest().log(Status.PASS, "Verified that the User is Navigating to the Home"
				+ " Screen When clicking on the Brens Icon from Owner Profile Page");
	}
	
// 	@Test(enabled=false)
	public void verifyThatUserCanUpdateProfilePictureFromTheProfileScreen() throws InterruptedException {
		String expectedToastMessage = "Customer's Profile Photo updated successfully";
		Thread.sleep(4000);
		WebElement parrentScrollView = driver.findElementByClassName("android.widget.ScrollView");
		parrentScrollView.findElement(By.xpath("//*[@resource-id = 'RNE__Image']")).click();
		driver.findElementByXPath("//*[@text = 'Camera']").click();
		driver.findElementByXPath("//*[@text = 'WHILE USING THE APP']").click();
		Thread.sleep(5000);
		driver.findElementByXPath("//*[@content-desc = 'Shutter']");
		driver.findElementByXPath("//*[@content-desc = 'Shutter']").click();
		driver.findElementByXPath("//*[@content-desc = 'Done']").click();
		String actualToastMessage = driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
		Assert.assertEquals(actualToastMessage, expectedToastMessage);
	}
// 	@Test(enabled=false)
	public void verifyThatUserCanDeleteProfilePictureFromTheProfileScreen() throws InterruptedException {
		String expectedToastMessage = "Profile image deleted";
		Thread.sleep(4000);
		WebElement parrentScrollView = driver.findElementByClassName("android.widget.ScrollView");
		parrentScrollView.findElement(By.xpath("//*[@resource-id = 'RNE__Image']")).click();
		driver.findElementByXPath("//*[@text = 'Camera']").click();
        	driver.findElementByXPath("//*[@text = 'WHILE USING THE APP']").click();
        	Thread.sleep(5000);
        	driver.findElementByXPath("//*[@content-desc = 'Shutter']");
        	driver.findElementByXPath("//*[@content-desc = 'Shutter']").click();
        	driver.findElementByXPath("//*[@content-desc = 'Done']").click();
        	driver.findElementByXPath("//*[@text = 'Name']");
        	parrentScrollView.findElement(By.xpath("//*[@resource-id = 'RNE__Image']")).click();
        	driver.findElementByXPath("//*[@text = 'Delete Photo']").click();
        	String actualToastMessage = driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
        	Assert.assertEquals(actualToastMessage, expectedToastMessage);
        
		
	}
}
