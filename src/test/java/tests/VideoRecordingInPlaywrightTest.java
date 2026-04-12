package tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class VideoRecordingInPlaywrightTest {
	
	@Test
	public void loginToApplicatopn() {
		
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(1000));
		BrowserContext browserContext = browser.newContext(new Browser.NewContextOptions().setRecordVideoDir(Paths.get("./videos/")));;
		
		Page page = browserContext.newPage();
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		
		Locator usernameTextbox=page.locator("input[name='username']");
		Locator passwordTextbox=page.locator("input[name='password']");
		Locator loginButton=page.locator("//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button");
		
		usernameTextbox.fill("Admin");
		passwordTextbox.fill("admin123");
		loginButton.click();
		
		assertThat(page.getByRole(AriaRole.HEADING)).containsText("Dashboard");
		
		
		//Teardown mandatory
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
	}

}
