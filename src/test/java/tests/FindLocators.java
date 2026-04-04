package tests;

import java.util.List;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

public class FindLocators {

	static Page page;
	
	public static void main(String[] args) {
		//These are the recommended built-in locators in Playwright
		
		//1. page.getByRole() to locate by explicit and implicit accessibility attributes.
		page.getByRole(AriaRole.BUTTON);
		page.getByRole(AriaRole.LISTITEM);
		page.getByRole(AriaRole.CHECKBOX);
		page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("orange"));
		page.getByRole(AriaRole.LISTITEM).nth(1);
		
		//2. page.getByText() to locate by text content.
		page.getByText("Welcome, John");
		page.getByText("orange");
		
		//3. page.getByAltText() to locate an element, usually image, by its text alternative.
		//All images should have an alt attribute that describes the image. <img alt="playwright logo" src="/img/playwright-logo.svg" width="100" />
		page.getByAltText("playwright logo").click();
		
		//4. page.getByLabel() to locate a form control by associated label's text.
		page.getByLabel("User Name").fill("Alok");
		page.getByLabel("password").fill("secret");
		page.getByLabel("Sign On").click();
		
//		//5. page.getByPlaceholder() to locate an input by placeholder.

		//6. page.getByTitle() to locate an element by its title attribute.
		//Locate an element with a matching title attribute using Page.getByTitle(). <span title='Issues count'>25 issues</span>
		page.getByTitle("Issues count");
		
		//7. page.getByTestId() to locate an element based on its data-testid attribute (other attributes can be configured).
		//<button data-testid="directions">Itinéraire</button> OR <button data-pw="directions">Itinéraire</button>
		page.getByTestId("directions");
		
		//======== Locate by CSS or XPath ===========
		page.locator("css=button");
		page.locator("button");
		page.locator("xpath=//button");
		page.locator("button");
		page.locator("button").filter(new Locator.FilterOptions().setVisible(true));
		Locator contact_sales = page.locator("text=CONTACT SALES");
		contact_sales.click();
		//Multiple elements
		Locator login_button = page.locator("text=Login");
		int numberOfLoginButtons = login_button.count();
		System.out.println(numberOfLoginButtons);
		login_button.first().click();
		//How to Print multiple elements
		Locator countryOptions = page.locator("select#Form_submitForm_Country option");
		for(int i=0; i<countryOptions.count(); i++) {
			String country = countryOptions.nth(i).textContent();
			System.out.println(country);
		}
		//Second way to print
		List<String> countryOptionList = countryOptions.allTextContents();
		for(String str:countryOptionList) {
			System.out.println(str);
		}
		//Third way to print
		countryOptionList.forEach(ele -> System.out.println(ele));
	}

}
