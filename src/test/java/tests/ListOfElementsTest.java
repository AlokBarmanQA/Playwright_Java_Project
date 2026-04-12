package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.List;

public class ListOfElementsTest {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://orangehrm.com/orangehrm-30-day-trial/");
		assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Allow all"))).isVisible();
		assertThat(page.locator("#CybotCookiebotDialogBodyContentTitle")).containsText("This website uses cookies");
		assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Close banner"))).isVisible();
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Close banner")).click();
		
		//Store Single element
		Locator contactSales = page.locator("//button[contains(text(),'Contact Sales')]");
		System.out.println(contactSales.count());
		contactSales.click();
		
		//Handle multiple elements
		Locator countryOptions = page.locator("select#Form_getForm_Country option");
		System.out.println(countryOptions.count());
		//Way-1
		for(int i=0; i<countryOptions.count(); i++) {
			String actualCountry = countryOptions.nth(i).textContent();
			System.out.println(actualCountry);
		}
		System.out.println("==============================================");
		//Way-2
		List<String> countryOptionsList = countryOptions.allTextContents();
		for(String country : countryOptionsList) {
			System.out.println(country);
		}
		System.out.println("==============================================");
		//Way-3
		countryOptionsList.forEach(country -> System.out.println(country));
		
		//closing
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
		System.out.println("Execution Ended Successfully");
	}
}
