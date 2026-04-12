package tests;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.regex.Pattern;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;

public class PlaywrightAssertionUsingassertThat {

	public static void main(String[] args) {
		
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://playwright.dev");
		
		Locator locator = page.locator("[name='username']");
		Locator getStarted = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Get Started"));
		
		//Locator Assertions: Used to check the state of specific UI elements.
		//Expects page to have a heading with the name of Installation.
        assertThat(page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Installation"))).isVisible();
        
        //Custom Timeouts: You can override the default wait time for a specific assertion if an element is known to be slow.
      	assertThat(locator).isVisible(new LocatorAssertions.IsVisibleOptions().setTimeout(10000));
      	
		//Verifies an element is visible.
		assertThat(locator).isVisible();
		
		//Negated Assertions: You can check for the opposite of a condition by adding .not().
		assertThat(locator).not().isVisible();
		
		//Confirms a button or input is interactable.
		assertThat(locator).isEnabled();
		
		//========================================NOTE======================================
				/*	Assertion 	Use for...							Examples
					hasText()	Labels, headers, divs, buttons		<h2>Dashboard</h2>
					hasValue()	Text boxes, textareas, dropdowns	<input value="Dashboard">
				*/
		
		//Checks if an element contains specific text.
		assertThat(locator).hasText("text");
		
		//Checks the current value of an input field.
		assertThat(locator).hasValue("expected");
		
		//Page Assertions: Used for broad page-level checks.
		//Verifies the current URL.
		assertThat(page).hasURL("regex_or_string");
		
		//Confirms the page title is correct.
		System.out.println(page.title());
		assertThat(page).hasTitle("Expected Title");
		// Expect a title "to contain" a substring.
        assertThat(page).hasTitle(Pattern.compile("Playwright"));
        assertThat(page).hasTitle(Pattern.compile("Fast and reliable end-to-end testing for modern web apps | Playwright"));
		
        // Expect an attribute "to be strictly equal" to the value.
        assertThat(getStarted).hasAttribute("href", "/docs/intro");
        
		//===API Response Assertions: Specifically for testing API calls.=======
		//Asserts that a network response has a status code in the 200-299 range.
		APIResponse response = page.request().get("https://example.com");
		assertThat(response).isOK();
		
		
		
		//Key Features of Playwright Assertions
		//Auto-Retrying: Unlike traditional assertions that fail immediately, assertThat will keep polling the page for up to 5 seconds (by default) to wait for the UI to catch up.
		
	}

}
