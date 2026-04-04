package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BrowserContextTest {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));

		// Browser context 1
		BrowserContext brCntx1 = browser.newContext();
		Page page1 = brCntx1.newPage();
		page1.navigate("https://demo.automationtesting.in/Register.html");
		System.out.println(page1.title());

		// Browser context 2
		BrowserContext brCntx2 = browser.newContext();
		Page page2 = brCntx2.newPage();
		page2.navigate("https://www.therapynotes.com/");
		System.out.println(page2.title());

		// Browser context 3
		BrowserContext brCntx3 = browser.newContext();
		Page page3 = brCntx3.newPage();
		page3.navigate("https://www.verisk.com/");
		System.out.println(page3.title());
	}

}
