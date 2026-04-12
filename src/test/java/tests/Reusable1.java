package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;

public class Reusable1 {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium()
				.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(3000));
		BrowserContext browserContext = browser.newContext();
		// Start tracing before creating/navigating a page
		browserContext.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));
		// Open new page
		Page page = browserContext.newPage();
		page.navigate("https://demo.automationtesting.in/Register.html");
		System.out.println(page.title());
		
		
		browser.close();
		playwright.close();
	}

}
