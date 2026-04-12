package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class HandleJavascriptBasedPopups {

	public static void main(String[] args) {
		// alert, prompt, confirm
		// navigate to DOM > Console > enter -> alert('I am alert'), prompt('This is prompt'), confirm('are you sure?')
		
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://the-internet.herokuapp.com/javascript_alerts");
		
		Locator button_ClickforJSAlert = page.getByText("Click for JS Alert");
		Locator button_ClickforJSConfirm = page.getByText("Click for JS Confirm");
		Locator button_ClickforJSPrompt = page.getByText("Click for JS Prompt");
		Locator result = page.locator("p#result");
		
		button_ClickforJSAlert.click();
		page.waitForTimeout(3000);
		assertThat(result).hasText("You successfully clicked an alert");
		System.out.println(result.textContent());
		
		button_ClickforJSConfirm.click();
		page.waitForTimeout(3000);
		assertThat(result).hasText("You clicked: Cancel");
		System.out.println(result.textContent());
		
		button_ClickforJSPrompt.click();
		page.waitForTimeout(3000);
		assertThat(result).hasText("You entered: null");
		System.out.println(result.textContent());
		
		// clean up
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
		System.out.println("Execution ompleted Successfully");
	}

}
