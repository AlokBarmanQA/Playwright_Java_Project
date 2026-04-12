package tests;

import java.nio.charset.StandardCharsets;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.FilePayload;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FileUploadInRunTimeVerifyContent {

	public static void main(String[] args) {
		// condition required: input type = file
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://cgi-lib.berkeley.edu/ex/fup.html");

		Locator choose_uploadFile = page.locator("input[name='upfile']");
		Locator button_press = page.locator("//input[@value='Press']");
		
		Locator header_file_upload_result = page.locator("body > h1");//File Upload Results
		Locator message_file_upload_result = page.locator("body > p:nth-child(2)");//You've uploaded a file.  Your notes on the file were:
		Locator header_file_content_result = page.locator("//html/body/p[2]");//The file's contents are:
		Locator message_content = page.locator("//html/body/pre");//This file is created in run time
		
		// Upload SINGLE file
		choose_uploadFile.setInputFiles(new FilePayload(
				"RunTimeCreatedFile.txt", 
				"text/plain", 
				"This file is created in run time".getBytes(StandardCharsets.UTF_8)));
		page.waitForTimeout(5000);
		button_press.click();
		page.waitForTimeout(9000);
		System.out.println(header_file_upload_result.textContent());
		System.out.println(message_file_upload_result.textContent());
		System.out.println(header_file_content_result.textContent());
		System.out.println(message_content.textContent());
		
		assertThat(header_file_upload_result).hasText("File Upload Results");
		assertThat(message_file_upload_result).hasText("You've uploaded a file.  Your notes on the file were:");
		assertThat(header_file_content_result).hasText("The file's contents are:");
		assertThat(message_content).hasText("This file is created in run time");

		// clean up
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
		System.out.println("Execution Completed Successfully");
		
	}

}
