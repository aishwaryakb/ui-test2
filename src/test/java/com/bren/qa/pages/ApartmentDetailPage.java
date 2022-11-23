package com.bren.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.bren.qa.base.Base;
import com.bren.qa.helper.SwipeHelper;
import com.bren.qa.helper.TapHelper;

import io.appium.java_client.MobileBy;
import io.appium.java_client.TouchAction;
public class ApartmentDetailPage extends Base{
	
	String imageCountString; 
	String totalImages;
	String currentImagePosition;
	@FindBy(xpath = "//*[@text = 'VIEW GALLERY']")
	public WebElement viewGalleryButton;
	@FindBy(xpath = "//*[@text = 'TAKE VIRTUAL TOUR']")
	WebElement takeVirtualTourButton;
	@FindBy(xpath = "//*[@resource-id = 'apartmentName']")
	public WebElement apartmentTitle;
	@FindBy(xpath = "//*[@resource-id = 'shareCloseIconAndTitle']")
    public WebElement shareCloseIconAndTitle;
	@FindBy(xpath = "//*[@resource-id = 'shareAndDownloadIcons']")
	WebElement shareAndDownloadIcons;
	@FindBy(xpath = "//*[@resource-id = 'commonHeader']")
	WebElement commonHeader;
	@FindBy(xpath = "//*[@resource-id = 'projectImageContainer']")
	WebElement projectImageContainer;
	@FindBy(xpath = "//*[@resource-id = 'RNE__Image__children__container']")
	WebElement projectChildrenImage;
	@FindBy(xpath = "//*[@text = 'ZAHARA BY BREN HIGHLIGHTS']")
	public WebElement highLightsSection;
	@FindBy(xpath = "//*[@resource-id = 'dotsAndArrow']")
	WebElement dotsAndArrowSection;
	@FindBy(className = "android.view.ViewGroup")
	WebElement dots;
	@FindBy(xpath = "//*[@resource-id = 'projectPlanListView']")
	WebElement projectPlanListView;
	@FindBy(xpath = "//*[@resource-id = 'youtubePlayerContainer']")
	public WebElement youtubePlayerContainer;
	@FindBy(xpath = "//*[@text = 'AMENITIES']")
	WebElement amenitiesSection;
	@FindBy(xpath = "//*[@text = 'LOCATION']")
	public WebElement locationSection;
	@FindBy(xpath = "//*[@text = 'PRICE']")
	public WebElement priceSection;
	@FindBy(xpath = "//*[@text = 'SPECIFICATIONS']")
	WebElement specificationSection;
	@FindBy(xpath = "//*[@text = 'REQUEST FOR A CALLBACK']")
	public WebElement requestForACallBackButton;
	@FindBy(xpath = "//*[@text = 'Refer and earn']")
	WebElement referAndEarnSection;
	@FindBy(xpath = "//*[@text = 'REFER A FRIEND']")
	public WebElement referAndEarnButton;
	@FindBy(className = "android.widget.TextView")
	public WebElement imageCountElement;
	@FindBy(className = "android.widget.ImageView")
	public WebElement imageViewElement;
	@FindBy(xpath = "//*[@id = 'iconIcon']")
	WebElement closeIcon;
	@FindBy(className = "android.webkit.WebView")
	WebElement webView;
	@FindBy(xpath = "//*[@text = 'Share']")
	WebElement shareTitle;
	@FindBy(xpath = "//*[@resource-id = 'leftAndRightArrow']")
	public WebElement leftAndrightArrow; 
	@FindBy(xpath = "//*[@text = 'Pause']")
	public WebElement pauseBtn;
	@FindBy(xpath = "//*[@text = 'Design inspired by Mediterranean aesthetics']")
	WebElement highLightsSubtitle1;
	@FindBy(xpath = "//*[@text = '4 Residential Towers, Clubhouse Dali, Clubhouse Salsa']")
	WebElement highLightsSubtitle2;
	@FindBy(xpath = "//*[@text = 'Amenities for all ages - sports/fitness, wellness,"
			+ " entertainment/hospitality, with active green spaces']")
	WebElement highLightsSubtitle3;
	@FindBy(xpath = "//*[@text = 'An option of installing a premium German modular kitchen is offered.']")
	WebElement highLightsSubtitle4;
	@FindBy(xpath = "//*[@text = '4.975 acres with over 80% open space']")
	WebElement highLightsSubtitle5;
	@FindBy(xpath = "//*[@text = 'Built with aluminium formwork system that provides extra carpet area']")
	WebElement highLightsSubtitle6;
	@FindBy(xpath = "//*[@text = 'AMENITIES']")
	WebElement amenitiesTitle;
	@FindBy(xpath = "//*[@resource-id = 'projectSpecAndAmenities']")
	WebElement projectSpecAndAmenitiesSection;
	
	@FindBy(xpath = "//*[@resource-id = 'nameAndDownArrowContainer']")
	WebElement amenintiesName;
	@FindBy(xpath = "//*[@resource-id = 'downArrow']")
	public WebElement downArrow;
	@FindBy(xpath = "//*[@resource-id = 'details']")
	public WebElement detailsOfSpecification;
	@FindBy(xpath = "//*[@resource-id = 'projectAmenities']")
	public WebElement projectAmenitiesSection;
	@FindBy(xpath = "//*[@text = 'Full screen']")
	public WebElement fullScreenOption;
	
	
	
	
	
	
	public ApartmentDetailPage() {
		PageFactory.initElements(driver, this);
	}
	public void scrollDownUntil(String text) {
		driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0))."
				+ "scrollIntoView(new UiSelector().textMatches(\"" + text + "\").instance(0))"));
	}
	public void clickLeftAndRightArrows() {
		leftAndrightArrow.click();
	}
	public void clickDownArrows() {
        TapHelper.tapElementAt(amenintiesName, 0.8, 0.6);
    }
	public void clickViewGallery() {
		viewGalleryButton.click();
	}
	public void clickVirtualTour() {
		takeVirtualTourButton.click();
	}
	public ReferAndEarnFormPage clickReferAndEarnButton() {
		referAndEarnButton.click();
		return new ReferAndEarnFormPage();
	}
	
	public String getScreenOrientation() {
		ScreenOrientation orientation = driver.getOrientation();
		String orientationString = String.valueOf(orientation);
		return orientationString;
	}
	public int getImageCount() {
		imageCountString = imageCountElement.getText();
		System.out.println(imageCountString);
		totalImages = imageCountString.substring(2);
		return Integer.parseInt(totalImages);
		
	}
	
	public int getCurrentImagePosition() {
		imageCountString = imageCountElement.getText();
		currentImagePosition = imageCountString.substring(0,1);
		return Integer.parseInt(currentImagePosition);
	}
	public void swipeGalleryImages() {
		int numberOfImages = this.getImageCount();
		System.out.println(numberOfImages);
		while(numberOfImages > 0) {
			SwipeHelper.swipe(imageViewElement);
			numberOfImages --;
		}
		
	}
	public int getHighlightsImageCount() {
		return dotsAndArrowSection.findElements(By.className("android.view.ViewGroup")).size();
	}
	
	public void clickOnImageElement() {
		imageViewElement.click();
	}
	public void clickOnCloseIcon() {
		closeIcon.click();
	}
	public void clickShare() {
		shareAndDownloadIcons.findElements(By.xpath("//*[@resource-id ='button']")).get(0).click();
	}
	public void clickDownloadIcon() {
		shareAndDownloadIcons.findElements(By.xpath("//*[@resource-id ='button']")).get(1).click();
	}
	public void clickYoutubeVideContainer() {
	    TapHelper.tapElementAt(youtubePlayerContainer, 0.8, 0.6);
	}
	public void clickOnFullScreen() {
	    TapHelper.tapElementAt(fullScreenOption, 0.8, 0.6);
	}
	public boolean viewGalleryButtonIsDisplayed() {
		return viewGalleryButton.isDisplayed();
	}
	public boolean takeVirtualTourButtonIsDisplayed() {
		return takeVirtualTourButton.isDisplayed();
	}
	public boolean webViewIsDisplayed() {
		return webView.isDisplayed();
	}
	public boolean apartmentTitleIsDisplayed() {
		return apartmentTitle.isDisplayed();
	}
	public boolean shareAndDownloadIconsIsDisplayed() {
		return shareAndDownloadIcons.isDisplayed();
	}
	public boolean projectImageContainerIsDisplayed() {
		return projectImageContainer.isDisplayed();
	}
	public boolean projectPlanListViewIsDisplayed() {
		return projectPlanListView.isDisplayed();
	}
	public boolean youtubePlayerContainerIsDisplayed() {
		return youtubePlayerContainer.isDisplayed();
	}
	public boolean amenitiesSectionIsDisplayed() {
		return amenitiesSection.isDisplayed();
	}
	public boolean specificationSectionIsDisplayed() {
		return specificationSection.isDisplayed();
	}
	public boolean requestForACallBackButtonIsDisplayed() {
		return requestForACallBackButton.isDisplayed();
	}
	public boolean referAndEarnSectionIsDisplayed() {
		return referAndEarnSection.isDisplayed();
	}
	public boolean dotsAndArrowSectionIsDisplayed() {
		return referAndEarnSection.isDisplayed();
	}
	public boolean highLightsSectionIsDisplayed() {
		return highLightsSection.isDisplayed();
	}
	public boolean shareTitleIsDisplayed() {
		return shareTitle.isDisplayed();
	}
	public boolean pauseButtonIsDisplayed() {
		return pauseBtn.isDisplayed();
	}
	public boolean highLightsSubTitle1IsDisplayed() {
		return highLightsSubtitle1.isDisplayed();
	}
	public boolean highLightsSubTitle2IsDisplayed() {
		return highLightsSubtitle2.isDisplayed();
	}
	public boolean highLightsSubTitle3IsDisplayed() {
		return highLightsSubtitle3.isDisplayed();
	}
	public boolean highLightsSubTitle4IsDisplayed() {
		return highLightsSubtitle4.isDisplayed();
	}
	public boolean highLightsSubTitle5IsDisplayed() {
		return highLightsSubtitle5.isDisplayed();
	}
	public boolean highLightsSubTitle6IsDisplayed() {
		return highLightsSubtitle6.isDisplayed();
	}
	public boolean amenitiesTitleIsDisplayed() {
		return amenitiesTitle.isDisplayed();
	}
	public boolean projectAmenitiesSectionIsDisplayed() {
		return projectSpecAndAmenitiesSection.isDisplayed();
	}
	
	public boolean amenitiesNameIsDisplayed() {
		return amenintiesName.isDisplayed();
	}
	public boolean downArrowIsDisplayed() {
		return downArrow.isDisplayed();
	}
}
