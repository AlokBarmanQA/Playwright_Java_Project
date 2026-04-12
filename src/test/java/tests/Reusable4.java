package tests;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class Reusable4 {

	private Page page;
	
	public Reusable4(Page page) {
		this.page=page;
	}
	
	public void verifyPageNameLabel() {
		
		assertThat(page.getByRole(AriaRole.HEADING)).containsText("Dashboard");
	}
}
