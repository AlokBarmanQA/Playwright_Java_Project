package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.Tracing;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.nio.file.Paths;

public class TraceViewerTest {

	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			// Start tracing before creating / navigating a page.
			context.tracing()
					.start(new Tracing.StartOptions().setScreenshots(true).setSnapshots(true).setSources(true));
			Page page = context.newPage();
			page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
			assertThat(page.getByRole(AriaRole.HEADING)).containsText("Login");
			assertThat(page.locator("form")).containsText("Username");
			assertThat(page.locator("form")).containsText("Password");
			assertThat(page.locator("form")).containsText("Forgot your password?");
			page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).click();
			page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).fill("Admin");
			page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).click();
			page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("admin123");
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
			assertThat(page.getByRole(AriaRole.HEADING)).containsText("Dashboard");
			assertThat(page.getByLabel("Sidepanel").getByRole(AriaRole.LIST)).containsText("Admin");
			assertThat(page.getByLabel("Sidepanel").getByRole(AriaRole.LIST)).containsText("Dashboard");
			page.getByRole(AriaRole.BANNER).getByRole(AriaRole.IMG, new Locator.GetByRoleOptions().setName("profile picture")).click();
		      page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Logout")).click();

			// Stop tracing and export it into a zip archive.
			context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("trace.zip")));
			page.close();
			playwright.close();
			System.out.println("Execution Ended Successfully");

			// Opening Trace Viewer Command:
			// mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="show-trace trace.zip"
			// Upload zip file to see Trace Viewer: trace.playwright.dev
		}
	}
}
