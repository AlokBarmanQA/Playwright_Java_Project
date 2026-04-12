package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class CreateMultipleBrowserContexts {

	public static void main(String[] args) {
		
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext1 = browser.newContext();
		Page page1 = browserContext1.newPage();
		page1.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		page1.fill("//input[@placeholder='Username']", "Admin");
		page1.fill("//input[@placeholder='Password']", "admin123");
		page1.click("//button[normalize-space()='Login']");
		
		BrowserContext browserContext2 = browser.newContext();
		Page page2 = browserContext2.newPage();
		page2.navigate("https://demo.automationtesting.in/Register.html");
		page2.fill("//input[@placeholder='First Name']", "Alok");
		page2.fill("//input[@placeholder='Last Name']", "Barman");
		page2.fill("//input[@type='email']", "alokbarman@email.com");
		
		page1.close();
		browserContext1.close();
		page2.close();
		browserContext2.close();
		browser.close();
		playwright.close();
		System.out.println("Execution Ended Successfully");
	}

}
