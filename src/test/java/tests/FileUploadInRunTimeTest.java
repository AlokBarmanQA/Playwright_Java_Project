package tests;

import java.nio.charset.StandardCharsets;
import java.nio.file.Path;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.FilePayload;

public class FileUploadInRunTimeTest {

	public static void main(String[] args) {
		// condition required: input type = file
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://davidwalsh.name/demo/multiple-file-upload.php");

		Locator choose_uploadFile = page.locator("input#filesToUpload");
		Locator first_upload_message = page.locator("ul#fileList li");

		// Upload SINGLE file
		choose_uploadFile.setInputFiles(new FilePayload(
				"RunTimeCreatedFile.txt", 
				"text/plain", 
				"This file is creaded in run time".getBytes(StandardCharsets.UTF_8)));
		page.waitForTimeout(5000);
		for(int i=0; i<first_upload_message.count(); i++) {
			String uploadedFileName = first_upload_message.nth(i).textContent();
			System.out.println(uploadedFileName);
		}
		// De-select all file
		choose_uploadFile.setInputFiles(new Path[0]);
		page.waitForTimeout(5000);
		for(int i=0; i<first_upload_message.count(); i++) {
			String uploadedFileName = first_upload_message.nth(i).textContent();
			System.out.println(uploadedFileName);
		}

		// clean up
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
		System.out.println("Execution Completed Successfully");
	}

}
