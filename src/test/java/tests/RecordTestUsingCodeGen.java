package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class RecordTestUsingCodeGen {

	public static void main(String[] args) {
		try (Playwright playwright = Playwright.create()) {
			Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
			BrowserContext context = browser.newContext();
			Page page = context.newPage();
			page.navigate("https://demo.playwright.dev/todomvc/#/");
			page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("What needs to be done?")).click();
			page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("What needs to be done?"))
					.fill("Playwright Java Project develop in Visual Studio Code");
			page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("What needs to be done?"))
					.press("Enter");
			page.getByTestId("todo-title").click();
			page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("What needs to be done?")).click();
			page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("What needs to be done?"))
					.fill("Playwright and selenium");
			page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("What needs to be done?"))
					.press("Enter");
			assertThat(page.locator("body")).containsText("Completed");
			assertThat(page.getByText("Playwright and selenium")).isVisible();
			assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete"))).isVisible();
			assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete"))).isVisible();
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete")).click();
			page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Delete")).click();
		}
	}
}
