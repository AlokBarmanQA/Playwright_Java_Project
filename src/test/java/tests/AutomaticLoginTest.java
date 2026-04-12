package tests;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType.LaunchOptions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class AutomaticLoginTest {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser
				.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get("applogin.json")));
		Page page = browserContext.newPage();
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		//page.waitForTimeout(5000);
		//title validation
		String actualTitle = page.title();
		System.out.println(actualTitle);
		assertThat(page).hasTitle("OrangeHRM");
		//Current URL validation
		assertThat(page).hasURL("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		//text validation
		Locator pageheader_dashboard = page.locator("xpath=//h6[normalize-space()='Dashboard']");
		System.out.println(pageheader_dashboard.textContent());
		assertThat(page.locator("xpath=//h6[normalize-space()='Dashboard']")).containsText("Dashboard");
		assertThat(pageheader_dashboard).hasText("Dashboard");

		// clean up
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
		System.out.println("Execution ompleted Successfully");
	}

}
