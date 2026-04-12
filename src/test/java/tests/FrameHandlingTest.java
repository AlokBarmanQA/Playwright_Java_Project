package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FrameHandlingTest {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://www.londonfreelance.org/courses/frames/index.html");
		
		//Find frame locator as String
		String mainFrame = "frame[name='main']";
		Locator titleBar = page.locator("h2");
		String actualTitleBar = page.frameLocator(mainFrame).locator(titleBar).textContent();
		System.out.println(actualTitleBar);
		
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
		System.out.println("Execution Completed Successfully");
	}

}
