package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MethodsInPlaywrightTest {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://demo.automationtesting.in/Register.html");
		//get text
		Locator fullNameLocator = page.locator("text=Full Name*");
		String fullName = fullNameLocator.textContent();
		System.out.println("fullName: "+fullName);
		
		//Assert Value
		Locator pageHeader = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Automation Demo Site"));
		assertThat(pageHeader.getByText("Automation Demo Site"));
		
		//is displayed / is Present
		Locator text_area = page.locator("textarea");
		boolean isVisible = text_area.isVisible();
		System.out.println(isVisible);
		
		//get Title
		String pageTitle = page.title();
		System.out.println(pageTitle);
		
		//get Current Url
		String currentUrl = page.url();
		System.out.println(currentUrl);
		
		//click
		Locator input_FirstName = page.locator("//input[@ng-model='FirstName']");
		input_FirstName.click();
		
	    //send Keys
		Locator input_EmailAddress = page.locator("//input[@ng-model='EmailAdress']");
		input_EmailAddress.fill("alokbarman@email.com");
		//page.waitForTimeout(3000);
		System.out.println(">>>>>"+input_EmailAddress.inputValue());
		
	    //clear
		Locator input_lastName = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First Name"));
		input_lastName.fill("Barman");
		//page.waitForTimeout(3000);
		System.out.println("====>"+input_lastName.inputValue());
		input_lastName.clear();
		//page.waitForTimeout(3000);
		System.out.println("====>"+input_lastName.inputValue());
		
		//Enabled
		Locator button_Submit = page.locator("#submitbtn");
		boolean isEnabled = button_Submit.isEnabled();
		System.out.println(isEnabled);
		
		//is selected radio button
		Locator radio_male = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Male").setExact(true));
		radio_male.click();
		boolean radioIsChecked = radio_male.isChecked();
		System.out.println(radioIsChecked);
		
		//is selected check box
		Locator checkbox_Cricket = page.locator("#checkbox1");
		checkbox_Cricket.click();
		boolean checkboxIsChecked = checkbox_Cricket.isChecked();
		System.out.println(checkboxIsChecked);
		
		//teardown
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
	}

}
