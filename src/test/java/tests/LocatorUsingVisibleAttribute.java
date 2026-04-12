package tests;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class LocatorUsingVisibleAttribute {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://www.amazon.com/");
		if (page.locator("//button[text()='Continue shopping']").isVisible()) {
			page.locator("//button[text()='Continue shopping']").click();
		}
		page.waitForTimeout(3000);
		List<String> linksList = page.locator("a:visible").allInnerTexts();
		for (int i = 0; i < linksList.size(); i++) {
			System.out.println(linksList.get(i));
		}
		
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
		System.out.println("Execution Completed Successfully");
	}

}
