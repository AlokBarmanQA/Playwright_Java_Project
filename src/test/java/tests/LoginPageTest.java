package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.DashboardPageObject;
import pages.LoginPageObject;

public class LoginPageTest extends BaseTest {
	
	@Test
	public void loginToApplicatopn() {
		
		LoginPageObject loginPageObject = new LoginPageObject(page);
		DashboardPageObject dashboardPageObject = new DashboardPageObject(page);
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		loginPageObject.doLogin("Admin", "admin123");
		dashboardPageObject.verifyPageNameLabel();
	}

}
