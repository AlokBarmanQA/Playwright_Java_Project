package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class ShadowDomHandlingTest {
	
	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext1 = browser.newContext();
		Page page1 = browserContext1.newPage();
		BrowserContext browserContext2 = browser.newContext();
		Page page2 = browserContext2.newPage();

		//Page -- DOM --> Shadow DOM --> elements
		page1.navigate("https://books-pwakit.appspot.com");
		//Inspect locator under shadow-root
		Locator mainBooksInputField = page1.locator("book-app[apptitle='BOOKS'] #input");
		Locator Search___booksLabel = page1.locator("book-app[apptitle='BOOKS'] .books-desc");
		System.out.println(Search___booksLabel.textContent());
		mainBooksInputField.fill("Testing Book");
		
		//Page -- DOM --> iFrame --> Shadow DOM --> elements
		page2.navigate("https://selectorshub.com/shadow-dom-in-iframe/");
		//Inspect locator under shadow-root
		page2.frameLocator("//div[@class='elementor-element elementor-element-db7f98c elementor-widget elementor-widget-html']//iframe[@id='pact']")
		.locator("div#app2 input#pizza").fill("Salmon");
		
		page1.close();
		browserContext1.close();
		page2.close();
		browserContext2.close();
		browser.close();
		playwright.close();
		System.out.println("Execution Completed Successfully");
	}
}
