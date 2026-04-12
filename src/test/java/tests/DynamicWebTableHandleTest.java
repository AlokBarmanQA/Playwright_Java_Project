package tests;

import java.util.List;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class DynamicWebTableHandleTest {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		BrowserContext brCx2 = browser.newContext();
		Page p2 = brCx2.newPage();
		
		page.navigate("https://datatables.net/extensions/select/examples/checkbox/checkbox.html");
		p2.navigate("https://primeng.org/");
		
		Locator rows =page.locator("table#example tr");
		//action on particular element
		rows.locator(":scope", new Locator.LocatorOptions().setHasText("Caesar Vance"))
								.locator(".dt-select-checkbox")
								.click();
		
		//Print all the elements
		List<String> tables = rows.locator(":scope").allInnerTexts();
		for(String row:tables) {
			System.out.println(row);
		}
		
		//2nd way
		rows.locator(":scope").allInnerTexts().forEach(e -> System.out.println(e));
		
		//Angular application
		//p2.getByRole(AriaRole.ROW, new Page.GetByRoleOptions().setName("Art Venere country flag")).getByRole(AriaRole.CHECKBOX).click();
		Locator obj = p2.locator("table#pn_id_1-table tr");
		obj.locator(":scope", new Locator.LocatorOptions().setHasText("Art Venere"))
								.locator("//td//input[@class='p-checkbox-input']").click();
		
		List<String> allrows = obj.locator(":scope").allInnerTexts();
		for(String row:allrows) {
			System.out.println(row);
		}
		
		page.close();
		p2.close();
		browserContext.close();
		brCx2.close();
		browser.close();
		playwright.close();
		System.out.println("Execution Completed Successfully");
	}

}
