package cucumberTests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Stepdefs {
	private WebDriver browser;
	
	@Test
	@Given("^The web browser is opened on google home$")
	public void startChromeWithGoogle() throws Exception{
		WebDriverManager.chromedriver().setup();
		browser = new ChromeDriver();
		browser.get("http://localhost:8080/users");
		
		String pageSource = browser.getPageSource();
		
		System.out.println(pageSource);
//		Boolean isPageTitleOk = pageTitle.equals("Google");
//		assertTrue(isPageTitleOk);
		
		browser.close();
	}
	
	
}
