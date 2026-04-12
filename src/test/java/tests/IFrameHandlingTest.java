package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class IFrameHandlingTest {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://www.formsite.com/templates/registration-form-templates/vehicle-registration-form/");
		
		//click on the image
		Locator image = page.locator("//img[@title='Vehicle-Registration-Forms-and-Examples']");
		image.click();
		//navigate to iframe
		String mainFrameLocator = "//iframe[contains(@id, 'frame-one')]";
		Locator textarea_description = page.locator("textarea#RESULT_TextArea-5");
		Locator input_FirstName = page.locator("input#RESULT_TextField-8");
		//enter information
		page.frameLocator("//iframe[contains(@id, 'frame-one')]").locator("input#RESULT_TextField-1").fill("Proposal Title");
		page.frameLocator("//iframe[contains(@id, 'frame-one')]").locator("input#RESULT_TextField-3").fill("Location");
		page.frameLocator(mainFrameLocator).locator(textarea_description).fill("Page Object Model");
		page.frameLocator(mainFrameLocator).locator(input_FirstName).fill("POM");
		
		//cleanup
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
		System.out.println("Execution Completed Successfully");
	}

}
