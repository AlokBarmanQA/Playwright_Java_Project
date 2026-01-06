package pages;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class DashboardPageObject {

	private Page page;
	
	public DashboardPageObject(Page page) {
		this.page=page;
	}
	
	public void verifyPageNameLabel() {
		
		assertThat(page.getByRole(AriaRole.HEADING)).containsText("Dashboard");
	}
}
