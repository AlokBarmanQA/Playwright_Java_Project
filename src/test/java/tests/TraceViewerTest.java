package tests;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.options.AriaRole;

public class TraceViewerTest {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium()
				.launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(3000));
		BrowserContext browserContext = browser.newContext();
		// Start tracing before creating/navigating a page
		browserContext.tracing().start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true));
		// Open new page
		Page page = browserContext.newPage();
		page.navigate("https://demo.automationtesting.in/Register.html");
		System.out.println(page.title());
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Home")).click();
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("logo")).click();
		page.pause();
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("WebTable")).click();
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Practice Site")).click();
		page.navigate("https://demo.automationtesting.in/WebTable.html");
		page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Register")).click();

		// Stop tracing and export as zip archive
		browserContext.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("trace.zip")));
		
		browser.close();
		playwright.close();
	}

}
