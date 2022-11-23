package com.bren.qa.utils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.bren.qa.base.Base;

public final class ScreenshotUtil {
	public static String getBase64Image() {
		try{
			return ((TakesScreenshot)Base.driver).getScreenshotAs(OutputType.BASE64);
		}catch(Exception e){
			System.out.println(e);
		}
		
	}
}

