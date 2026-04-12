package tests;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class SelectDropDownValueTest {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://demo.automationtesting.in/Register.html");
		
		//Method-1
		Locator selectElement = page.locator("select#Skills");
		// Find and click the select element
		selectElement.click();

		// Wait for the dropdown to open
		page.waitForLoadState();

		// Use selectOption to select by label
		selectElement.selectOption("Java");
		
		page.waitForTimeout(3000);
		
		//Method-2
		// Click to open the dropdown
		selectElement.click();
		// Get all options and find the one matching "Certifications"
		Locator skillOptions = selectElement.locator("option");
		for(int i = 0; i < skillOptions.count(); i++) {
		    String skill = skillOptions.nth(i).textContent();
		    System.out.println(">>>>" + skill);
		    if(skill.equalsIgnoreCase("Certifications")) {
		        // Use selectOption instead of click
		        selectElement.selectOption(skillOptions.nth(i).getAttribute("value"));
		        break;
		    }
		}
		page.locator("#Skills").selectOption("Java");
		page.waitForTimeout(3000);
		//Country
		Locator dd_country = page.locator("span[role='combobox']");
		Locator select_country = page.locator("select#country");
		Locator label_SelectCountry = page.locator("//label[normalize-space()='Select Country :']");
		dd_country.click();
		//Method-3
		page.waitForLoadState();
		select_country.selectOption("Bangladesh");
		dd_country.click();
		
		page.waitForTimeout(5000);
		
		//Method-4
		Locator countryOptions = select_country.locator("option");
		List<String> countryList = countryOptions.allTextContents();
		for(int i=0; i<countryList.size(); i++) {
			String country = countryList.get(i);
			System.out.println(">>>>"+country);
			if(country.equalsIgnoreCase("South Africa")) {
				select_country.selectOption("South Africa");
				break;
			}
		}
		label_SelectCountry.click();
		page.waitForTimeout(3000);
	    
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
	    
	}

}
