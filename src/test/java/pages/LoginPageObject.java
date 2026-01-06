package pages;

import com.microsoft.playwright.Page;

public class LoginPageObject {

	private Page page;
	private final String usernameTextbox="input[name='username']";
	private final String passwordTextbox="input[name='password']";
	private final String loginButton="//*[@id='app']/div[1]/div/div[1]/div/div[2]/div[2]/form/div[3]/button";
	
	
	public LoginPageObject(Page page) {
		this.page=page;
	}
	
	public void doLogin(String username, String password) {
		page.fill(usernameTextbox, username);
		page.fill(passwordTextbox, password);
		page.click(loginButton);
	}
}
