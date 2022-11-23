package com.bren.qa.base;
import org.testng.asserts.SoftAssert;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.model.Test;
import com.bren.qa.report.ExtentReport;
import com.bren.qa.utils.TestUtil;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

public class Base {
	public static AndroidDriver<AndroidElement> driver;
	public static Properties prop;
	public static SoftAssert softAssert;
	@BeforeSuite
	public void setupSuite() {
		ExtentReport.initReport();
	}
	@AfterSuite
	public void tearDownSuite() {
		ExtentReport.teardownReports();	
	}
	@AfterMethod(alwaysRun=true)
    public void tearDown() {
        try {
            driver.quit();
        }
        catch(Exception e) {
            System.out.println(e);
            driver.quit();
        }
    }
	public Base() {
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+
									"/src/test/java/com/bren/qa/properties/config.properties");
			prop.load(ip);
		}
		catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
	public static void initialization() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Nexus 6");
		File appdir = new File("src");
		File app = new File(appdir, "bren-test.apk");
		cap.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
		cap.setCapability("appPackage","com.brencorp.play.mybren");
		cap.setCapability("appActivity","com.brencorp.play.mybren.MainActivity");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
		cap.setCapability("newCommandTimeout", 1000);
		driver = new AndroidDriver<AndroidElement>(new URL(prop.getProperty("url")),cap);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
	}
}
