package tests;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class AutomaticLoginAuth {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		Locator input_username = page.locator("[name='username']");
		Locator input_password = page.locator("[name='password']");
		Locator button_login = page.locator("xpath=//button[normalize-space()='Login']");
		
		input_username.fill("Admin");
		input_password.fill("admin123");
		button_login.click();
		
		browserContext.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get("applogin.json")));
		
		//clean up
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
	}

}
