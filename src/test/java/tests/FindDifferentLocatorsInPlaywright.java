package tests;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.util.List;
import java.util.regex.Pattern;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;

public class FindDifferentLocatorsInPlaywright {

	static Page page;
	
	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		
		// ==> attibutes to inspect locators in Playwright
		
		// ==> Developer & Technical Attributes
		// 1****Test ID (getByTestId): Specifically for elements with a data-testid attribute (the default, though this is configurable).
				//<button data-testid="directions">Itinéraire</button> OR <button data-pw="directions">Itinéraire</button>
				page.getByTestId("directions").click();
				page.getByTestId("login-submit-btn").click();
				page.getByTestId("nav-menu").getByTestId("settings-icon").click();
				
				//1. Basic Usage
				//<button data-testid="login-button">Login</button>:
				// Clicks the element with data-testid="login-button"
				page.getByTestId("login-button").click();
				
				//2. Chaining for Scope
				// Finds the "submit" button specifically inside the "sidebar" container
				page.getByTestId("sidebar").getByTestId("submit").click();
				
				//3. Customizing the Attribute
				// In your Playwright initialization
				playwright.selectors().setTestIdAttribute("data-qa");

				// Now this will look for data-qa="submit-btn"
				page.getByTestId("submit-btn").click();
				
				//4. Handling Multiple Elements
				//If several elements share the same test ID (like items in a list), use .nth(), .first(), or .last().
				// Gets the 3rd item (index 2) in a list tagged with 'product-card'
				page.getByTestId("product-card").nth(2).click();
				
				//5. Assertions
				// Check if the success banner is visible
				assertThat(page.getByTestId("success-banner")).isVisible();
				
				
		// 2****XPath: The "last resort" for complex DOM traversal when no other unique identifier exists. 
				/*Example: playwright.$$("//a[@data-csa-c-content-id='nav_cs_bestsellers' and text()='Best Sellers']")
				Example: playwright.inspect("//a[@data-csa-c-content-id='nav_cs_bestsellers' and text()='Best Sellers']")
				Example: Using XPATH: playwright.$$("//a[text()='Amazon Devices']")*/
				
				//1. Basic XPath Locators
					/*Target		XPath Syntax		Java Example
					 Anywhere		//tag				page.locator("xpath=//button")
					 By Attribute	//*[@attr='val']	page.locator("xpath=//*[@id='login-btn']")
					 By Text		//*[text()='val']	page.locator("xpath=//*[text()='Submit']")
					*/
				
				//2. Using contains() (Most Common)
				// Find a button where text contains "Deals" (like "Today's Deals")
				page.locator("xpath=//a[contains(text(), 'Deals')]").click();

				// Find an element where the class contains "active"
				page.locator("xpath=//div[contains(@class, 'active')]").isVisible();
				
				//3. Navigating Parents and Siblings
				// 1. Move to Parent: Find a span with text, then go to its parent div
				page.locator("xpath=//span[text()='Username']/parent::div").click();

				// 2. Move to Sibling: Find a label, then find the input next to it
				page.locator("xpath=//label[text()='Email']/following-sibling::input").fill("test@test.com");
				
				//4. Indexing (Choosing one of many)
				// Clicks the first button found on the page
				page.locator("xpath=(//button)[1]").click();

				// Clicks the second row in a specific table
				page.locator("xpath=//table[@id='results']//tr[2]").click();
				
				//5. Multiple Conditions (and / or)
				// Find a button that has the class 'btn' AND the type 'submit'
				page.locator("xpath=//button[contains(@class, 'btn') and @type='submit']").click();
				
				//6. Using it for "Today's Deals" (Quote Handling)
				// The simplest way to handle the apostrophe in "Today's"
				page.locator("xpath=//a[contains(., \"Today's Deals\")]").click();
				page.locator("xpath=//button");
				page.locator("button");
				page.locator("button").filter(new Locator.FilterOptions().setVisible(true));
				
		// 3****CSS Selectors: Accessed via page.locator(), using standard CSS syntax like .class-name, #id, or custom attributes like [data-status="active"].
				/*Example: Using ID: playwright.$$("a#nav-logo-sprites")
				Example: Using Multiple Classes: playwright.$$(".nav-a.nav-a-2.a-popover-trigger.a-declarative.nav-progressive-attribute")
				Example: Using Text: playwright.$$("text=Amazon Basics")
				Example: Using has-text: playwright.$$('a:has-text("Today\'s Deals")')
				Example: Using backticks to handle the apostrophe in "Today's": playwright.$$(`a:has-text("Today's Deals")`)*/
				Locator contact_sales = page.locator("text=CONTACT SALES");
				contact_sales.click();
				page.locator(".nav-a.nav-a-2.nav-progressive-attribute").click();
				page.locator("a:has-text(\"Today's Deals\")").click();
				page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Today's Deals")).click();
				//Partial Attribute Matches (CSS)
				// Matches <div id="user_profile_123">
				page.locator("[id*='user_profile']").click();
				
				//1. The Basics
					/*To find by...			CSS Syntax	Java Example
						ID					#id			page.locator("#login-btn")
						Class				.class		page.locator(".primary-button")
						Multiple Classes	.c1.c2		page.locator(".btn.btn-large")
						Tag					tag			page.locator("input")
						Attribute			[attr]		page.locator("[name='email']")
					 */
				//2. Matching Parts of an Attribute (Very Useful)
				//If an ID or Class changes slightly (dynamic IDs), use these "wildcard" selectors:
					/* Starts with (^=): [id^='user_'] (Matches user_1, user_2)
						Ends with ($=): [id$='_submit'] (Matches login_submit)
						Contains (*=): [class*='nav-item'] (Matches top-nav-item-active)
					*/
				// Example: Find a button where the ID starts with "guest_"
				page.locator("button[id^='guest_']").click();
				
				//3. Parent-Child Relationships
				//Direct Child (>): Finds an element immediately inside another.
				//Any Descendant (Space): Finds an element anywhere inside the parent.
				// Find an <li> that is a direct child of a <ul> with class 'menu'
				page.locator("ul.menu > li").first().click();

				//4. Selecting by Index (:nth-child)
				// Clicks the 3rd item in a list
				page.locator("ul > li:nth-child(3)").click();
				
				//5. Playwright-Specific CSS Extensions
				//Playwright adds special "pseudo-classes" that aren't in standard CSS but are incredibly helpful:
				//:has(): Selects a parent based on its children.
				//:visible: Selects only elements that are actually showing on screen.
				// Select the card <div> that contains an <img> tag inside it
				page.locator("div.card:has(img)").click();

				// Select only the visible "Submit" button if there are hidden ones
				page.locator("button.submit:visible").click();
				
				//6. Combining Selectors
				//You can be as specific as you need to be by chaining tags, classes, and attributes together:
				// Tag 'input', with class 'form-control', and attribute name 'zip'
				page.locator("input.form-control[name='zip']").fill("10001");
				page.locator("css=button");
				page.locator("button");
				
		// ==> Recommended User-Facing Attributes 
		// These built-in methods target specific semantic and accessibility attributes:
		// 1****Role (getByRole): Locates elements by their ARIA role (e.g., button, checkbox, heading) and accessible name.
		
				page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit")).click();
				page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email")).fill("user@example.com");
				page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("I agree to terms")).check();
				page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Welcome").setLevel(1)).isVisible();
				page.getByRole(AriaRole.BUTTON);
				page.getByRole(AriaRole.CHECKBOX);
				page.getByRole(AriaRole.LISTITEM);
				page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("orange"));
				page.getByRole(AriaRole.LISTITEM).nth(1);
				//1. Basic Syntax
				// Finds <button>Login</button>
				page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();

				// Finds <a href="/home">Home</a>
				page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Home")).click();

				//2. Form Elements (Inputs & Selects)
				// Textbox: Matches <input type="text"> or <textarea> labeled "Email"
				page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email")).fill("test@test.com");

				// Checkbox: Matches <input type="checkbox">
				page.getByRole(AriaRole.CHECKBOX, new Page.GetByRoleOptions().setName("Subscribe")).check();

				// Combobox: Matches <select> dropdowns
				page.getByRole(AriaRole.COMBOBOX, new Page.GetByRoleOptions().setName("Country")).selectOption("USA");
				
				//3. Headings by Level
				// Matches <h1>Welcome</h1>
				page.getByRole(AriaRole.HEADING, new Page.GetByRoleOptions().setName("Welcome").setLevel(1)).isVisible();
				
				//4. Matching Exactly
				// Matches "Login" ONLY
				page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login").setExact(true)).click();
				
				//5. Images (by Alt Text)
				// Matches <img alt="User Avatar">
				page.getByRole(AriaRole.IMG, new Page.GetByRoleOptions().setName("User Avatar")).isVisible();
				
				//6. Common AriaRoles in Java
					/*	UI Element 			Java Enum (AriaRole.)
						Button				BUTTON
						Link				LINK
						Input / Textarea	TEXTBOX
						Checkbox			CHECKBOX
						Radio Button		RADIO
						Dropdown			COMBOBOX
						Table Row			ROW
						Navigation Bar		NAVIGATION
					*/
				
		// 2****Text (getByText): Finds elements based on their visible text content.
				//1. Basic Text Match (Partial)
				// Matches <p>Welcome, John</p>
				page.getByText("Welcome").click();

				//2. Exact Text Match
				// Matches "Login" but NOT "Login Now" or "login"
				page.getByText("Welcome, John");
				page.getByText("orange");
				page.getByText("Login", new Page.GetByTextOptions().setExact(true)).click();

				//3. Combining with a Specific Tag
				// Finds a <button> that contains the text "Submit"
				page.locator("button").getByText("Submit").click();

				//4. Handling Quotes (Like "Today's Deals")
				// No special escaping needed for single quotes in Java strings
				page.getByText("Today's Deals").click();

				// If the text actually contains double quotes: He said "Hello"
				page.getByText("He said \"Hello\"").isVisible();

				//5. Using it for Assertions
				// Verify a success message is visible
				assertThat(page.getByText("Account created successfully!")).isVisible();

				//6. Finding one item in a list
				// Clicks the second "Add to Cart" button found on the page
				page.getByText("Add to Cart").nth(1).click();

				
		// 3****Label (getByLabel): Identifies form controls (like inputs) using the text of their associated <label> element.
				//1. Basic Input Field	
				// Finds the input associated with the "Username" label
				page.getByLabel("User Name").fill("Alok");
				page.getByLabel("password").fill("secret");
				page.getByLabel("Sign On").click();

				//2. Exact vs. Partial Match
				// Matches "Password" exactly, will NOT match "Confirm Password"
				page.getByLabel("Password", new Page.GetByLabelOptions().setExact(true)).fill("12345");

				//3. Checkboxes and Radio Buttons
				// Clicks the checkbox next to "I accept the terms"
				page.getByLabel("I accept the terms").check();

				// Selects a radio button labeled "Credit Card"
				page.getByLabel("Credit Card").check();

				//4. Select Dropdowns
				// Finds the <select> labeled "Country" and chooses "USA"
				page.getByLabel("Country").selectOption("USA");

				//5. Using aria-label
				/* <button aria-label="Close dialog">X</button> */
				page.getByLabel("Close dialog").click();

				//6. Dealing with Multiple Labels
				// Finds the "Zip Code" input specifically inside the "Shipping" section
				page.locator("#shipping-section").getByLabel("Zip Code").fill("90210");
				
				
		// 4****Placeholder (getByPlaceholder): Targets input fields using their placeholder attribute.
				//1. Basic Input Field
				/* <input placeholder="Search products..."> */
				// Fills the search bar with "laptop"
				page.getByPlaceholder("Search products...").fill("laptop");

				//2. Exact vs. Partial Match
				// Matches "Email" exactly, will NOT match "Email Address"
				page.getByPlaceholder("Email", new Page.GetByPlaceholderOptions().setExact(true)).fill("test@example.com");

				//3. Login Forms (Email & Password)
				// Filling out a simple login form
				page.getByPlaceholder("Enter your email").fill("user@example.com");
				page.getByPlaceholder("Password").fill("Secret123!");
				page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
				
				//4. Textareas
				// Matches <textarea placeholder="Tell us about yourself"></textarea>
				page.getByPlaceholder("Tell us about yourself").fill("I am a QA Automation Engineer.");
				
				//5. Combining with a Specific Container
				// Finds the placeholder inside the footer specifically
				page.locator("footer").getByPlaceholder("Your Email").fill("newsletter@me.com");
				
				//6. Checking Visibility (Assertions)
				// Asserts that the search bar exists with the correct hint text
				assertThat(page.getByPlaceholder("Search...")).isVisible();
				
				
		// 5****Alt Text (getByAltText): Primarily used for images (<img>) and area elements to locate them via their alt attribute.
				//1. Basic Image Interaction
				page.getByAltText("playwright logo").click();
				// Check if the logo is visible
				boolean isLogoVisible = page.getByAltText("Company Logo").isVisible();
				System.out.println(isLogoVisible);
				// Click an image that acts as a button
				page.getByAltText("Add to Cart").click();

				//2. Exact vs. Partial Match
				// Matches "User Avatar" exactly, ignoring "User Avatar 2"
				page.getByAltText("User Avatar", new Page.GetByAltTextOptions().setExact(true)).click();
				
				//3. Handling Dynamic Text (Regex)
				// Matches alt text like "Photo taken on 2024-01-01" or "Photo taken on 2024-05-15"
				page.getByAltText(Pattern.compile("Photo taken on .*")).isVisible();
				
				//4. Combining with Assertions
				// Verify the "Verified Account" badge is shown
				assertThat(page.getByAltText("Verified Profile Checkmark")).isVisible();

				//5. Finding an image in a specific list. If you have a grid of products, use .first(), .last(), or .nth() to pick one.
				// Clicks the 3rd product image on the page
				page.getByAltText("Product Thumbnail").nth(2).click();
				
				
		// 6****Title (getByTitle): Selects elements with a matching title attribute, often used for tooltips.
				//<span title='Issues count'>25 issues</span>
				page.getByTitle("Issues count");
				//1. Basic Icon Button
				// Matches <button title="Delete Item"> or <svg title="Delete Item">
				page.getByTitle("Delete Item").click();
				
				//2. Exact vs. Partial Match
				// Matches "Close" exactly, will not match "Close Window"
				page.getByTitle("Close", new Page.GetByTitleOptions().setExact(true)).click();

				//3. Verifying Tooltips (Assertions)
				// Asserts that the help icon has the correct tooltip text
				assertThat(page.getByTitle("Click for more information")).isVisible();

				//4. Using Regular Expressions
				// Matches titles like "Last updated: 10:00 AM" or "Last updated: 11:30 PM"
				page.getByTitle(Pattern.compile("Last updated: .*")).isVisible();

				//5. Chaining with Other Locators
				// Finds the "Edit" icon specifically inside the row for "User #45"
				page.locator("tr").filter(new Locator.FilterOptions().setHasText("User #45"))
				    .getByTitle("Edit User")
				    .click();
				//Multiple elements
				Locator login_button = page.locator("text=Login");
				int numberOfLoginButtons = login_button.count();
				System.out.println(numberOfLoginButtons);
				login_button.first().click();
				
		//*********How to Print multiple elements
				Locator countryOptions = page.locator("select#Form_submitForm_Country option");
				for(int i=0; i<countryOptions.count(); i++) {
					String country = countryOptions.nth(i).textContent();
					System.out.println(country);
				}
				//Second way to print
				List<String> countryOptionList = countryOptions.allTextContents();
				for(String str:countryOptionList) {
					System.out.println(str);
				}
				//Third way to print
				countryOptionList.forEach(ele -> System.out.println(ele));
	}

}
