package tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

//import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.regex.Pattern;

public class SelectorsInPlaywrightRecordingTest {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://demo.automationtesting.in/Register.html");
		//locator text
		Locator pageHeader_AutomationDemoSite = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Automation Demo Site"));
		Locator link_Home_blue_navbar = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Home"));
		Locator link_Register_blue_navbar = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Register"));
		Locator link_WebTable_blue_navbar = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("WebTable"));
		Locator dd_SwitchTo_blue_navbar = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("SwitchTo"));
		Locator dd_Widgets_blue_navbar = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Widgets"));
		Locator dd_Interactions_blue_navbar = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Interactions"));
		Locator dd_Video_blue_navbar = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Video"));
		Locator dd_WYSIWYG_blue_navbar = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("WYSIWYG"));
		Locator dd_More_blue_navbar = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("More"));
		Locator link_PracticeSite_blue_navbar = page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Practice Site"));
		Locator label_Register = page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Register"));
		Locator label_FullName = page.getByText("Full Name*");
		Locator input_FirstName = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("First Name"));
		Locator input_LastName = page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Last Name"));
		Locator label_Address = page.getByText("Address", new Page.GetByTextOptions().setExact(true));
		Locator input_Address = page.locator("textarea");
		Locator label_EmailAddress = page.getByText("Email address*");
		Locator input_EmailAddress = page.locator("input[type=\"email\"]");
		Locator label_Phone = page.getByText("Phone*");
		
		Locator input_Phone = page.locator("input[type=\"tel\"]");
		Locator label_Gender = page.getByText("Gender*");
		Locator radiobutton_Male_Gender = page.getByText("Male", new Page.GetByTextOptions().setExact(true));
		Locator option_Male_Gender = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("Male").setExact(true));
		Locator radiobutton_FeMale_Gender = page.getByText("FeMale");
		Locator option_FeMale_Gender = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("FeMale"));
		Locator label_Hobbies = page.getByText("Hobbies");
		Locator checkbox_Cricket_Hobbies = page.locator("#checkbox1");
		Locator option_Cricket_Hobbies = page.getByText("Cricket");
		Locator checkbox_Movies_Hobbies = page.locator("#checkbox2");
		
		Locator option_Movies_Hobbies = page.getByText("Movies");
		Locator checkbox_Hockey_Hobbies = page.locator("#checkbox3");
		Locator option_Hockey_Hobbies = page.getByText("Hockey");
		Locator label_Languages = page.getByText("Languages");
		Locator textField_Languages = page.locator("#msdd");
		Locator label_Skills = page.getByText("Skills", new Page.GetByTextOptions().setExact(true));
		Locator dd_Skills = page.locator("#Skills");
		Locator label_Country = page.getByText("Country*");
		Locator readOnly_dd_SelectCountry = page.locator("#countries");
		Locator label_SelectCountry = page.getByText("Select Country :");
		
		Locator dd_SelectCountry = page.getByLabel("", new Page.GetByLabelOptions().setExact(true));
		Locator searchField_SelectCountry = page.locator("input[type=\"search\"]");
		Locator label_DateOfBirth = page.getByText("Date Of Birth");
		Locator dd_year_DateOfBirth = page.locator("#yearbox");
		Locator dd_month_DateOfBirth = page.getByRole(AriaRole.COMBOBOX).nth(4);
		Locator dd_day_DateOfBirth = page.locator("#daybox");
		Locator label_Password = page.getByText("Password", new Page.GetByTextOptions().setExact(true));
		Locator textfield_Password = page.locator("#firstpassword");
		Locator label_ConfirmPassword = page.getByText("Confirm Password");
		Locator textfield_ConfirmPassword = page.locator("#secondpassword");
		
		Locator button_submit = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit"));
		Locator button_refresh = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Refresh"));
		Locator photoLabel = page.getByText("Photo", new Page.GetByTextOptions().setExact(true));
		Locator appLogo = page.locator("#imagetrgt");
		Locator chooseFile = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Choose File"));
		Locator bottomPopup = page.locator(".grippy-host");
		Locator facebook = page.getByRole(AriaRole.LINK).filter(new Locator.FilterOptions().setHasText(Pattern.compile("^$"))).first();
		Locator tweeter = page.getByRole(AriaRole.LINK).filter(new Locator.FilterOptions().setHasText(Pattern.compile("^$"))).nth(1);
		Locator linkedin = page.getByRole(AriaRole.LINK).filter(new Locator.FilterOptions().setHasText(Pattern.compile("^$"))).nth(2);
		Locator googleEorkspace = page.getByRole(AriaRole.LINK).filter(new Locator.FilterOptions().setHasText(Pattern.compile("^$"))).nth(3);
		Locator youtube = page.getByRole(AriaRole.LINK).filter(new Locator.FilterOptions().setHasText(Pattern.compile("^$"))).nth(4);
		
		System.out.println(pageHeader_AutomationDemoSite.textContent());
		System.out.println(link_Home_blue_navbar.textContent());
		System.out.println(link_Register_blue_navbar.textContent());
		System.out.println(link_WebTable_blue_navbar.textContent());
		System.out.println(dd_SwitchTo_blue_navbar.textContent());
		System.out.println(dd_Widgets_blue_navbar.textContent());
		System.out.println(dd_Interactions_blue_navbar.textContent());
		System.out.println(dd_Video_blue_navbar.textContent());
		System.out.println(dd_WYSIWYG_blue_navbar.textContent());
		System.out.println(dd_More_blue_navbar.textContent());
		
		System.out.println(link_PracticeSite_blue_navbar.textContent());
		System.out.println(label_Register.textContent());
		System.out.println(label_FullName.textContent());
		System.out.println(input_FirstName.textContent());
		System.out.println(input_LastName.textContent());
		System.out.println(label_Address.textContent());
		System.out.println(input_Address.textContent());
		System.out.println(label_EmailAddress.textContent());
		System.out.println(input_EmailAddress.textContent());
		System.out.println(label_Phone.textContent());
		
		System.out.println(input_Phone.textContent());
		System.out.println(label_Gender.textContent());
		System.out.println(radiobutton_Male_Gender.textContent());
		System.out.println(option_Male_Gender.textContent());
		System.out.println(radiobutton_FeMale_Gender.textContent());
		System.out.println(option_FeMale_Gender.textContent());
		System.out.println(label_Hobbies.textContent());
		System.out.println(checkbox_Cricket_Hobbies.textContent());
		System.out.println(option_Cricket_Hobbies.textContent());
		System.out.println(checkbox_Movies_Hobbies.textContent());
		
		System.out.println(option_Movies_Hobbies.textContent());
		System.out.println(checkbox_Hockey_Hobbies.textContent());
		System.out.println(option_Hockey_Hobbies.textContent());
		System.out.println(label_Languages.textContent());
		System.out.println(textField_Languages.textContent());
		System.out.println(label_Skills.textContent());
		System.out.println(dd_Skills.textContent());
		System.out.println(label_Country.textContent());
		System.out.println(readOnly_dd_SelectCountry.textContent());
		System.out.println(label_SelectCountry.textContent());
		
		System.out.println(dd_SelectCountry.textContent());
		System.out.println(searchField_SelectCountry.isVisible());
		System.out.println(label_DateOfBirth.textContent());
		System.out.println(dd_year_DateOfBirth.textContent());
		System.out.println(dd_month_DateOfBirth.textContent());
		System.out.println(dd_day_DateOfBirth.textContent());
		System.out.println(label_Password.textContent());
		System.out.println(textfield_Password.textContent());
		System.out.println(label_ConfirmPassword.textContent());
		System.out.println(textfield_ConfirmPassword.textContent());
		
		System.out.println(button_submit.textContent());
		System.out.println(button_refresh.textContent());
		System.out.println(photoLabel.textContent());
		System.out.println(appLogo.textContent());
		System.out.println(chooseFile.textContent());
		System.out.println(bottomPopup.textContent());
		System.out.println(facebook.textContent());
		System.out.println(tweeter.textContent());
		System.out.println(linkedin.textContent());
		System.out.println(googleEorkspace.textContent());
		
		System.out.println(youtube.textContent());
		
		//teardown
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
	}

}
