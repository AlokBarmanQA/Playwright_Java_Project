package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RecordPlayDebugTest {

	public static void main(String[] args) {
		// https://playwright.dev/java/docs/codegen-intro
		// mvn exec:java -e -D exec.mainClass=com.microsoft.playwright.CLI -D exec.args="codegen demo.playwright.dev/todomvc"

		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();
			page.navigate("https://demo.playwright.dev/todomvc/#/");
			page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("What needs to be done?"))
					.fill("Java");
			page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("What needs to be done?"))
					.press("Enter");
			assertThat(page.getByTestId("todo-title")).isVisible();
			assertThat(page.getByTestId("todo-title")).containsText("Java");
			page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("What needs to be done?")).click();
			page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("What needs to be done?"))
					.fill("Playwright");
			page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("What needs to be done?"))
					.press("Enter");
			assertThat(page.locator("body")).containsText("Playwright");
			page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("What needs to be done?")).click();
			page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("What needs to be done?"))
					.fill("Cucumber");
			page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("What needs to be done?"))
					.press("Enter");

			assertThat(page.locator("body")).containsText("Cucumber");
			assertThat(page.locator("body")).containsText("All");
			assertThat(page.locator("body")).containsText("Active");
			assertThat(page.locator("body")).containsText("Completed");
			page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("Java"))
					.getByLabel("Toggle Todo").check();
			assertThat(page.locator("body")).containsText("2 items left");
			page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("Playwright"))
					.getByLabel("Toggle Todo").check();
			page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("Cucumber"))
					.getByLabel("Toggle Todo").check();
			assertThat(page.getByText("All Active Completed")).isVisible();

			page.getByText("Java").click();
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete")).click();
			page.getByText("Playwright").click();
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete")).click();
			page.getByTestId("todo-title").click();
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete")).click();
			
			assertThat(page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("What needs to be done?")))
					.isEmpty();
			System.out.println("Execution Ended Successfully");
		}
	}
}
