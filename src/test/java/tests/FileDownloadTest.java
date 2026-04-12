package tests;

import java.nio.file.Paths;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Download;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
//import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class FileDownloadTest {

	public static void main(String[] args) {
		Playwright playwright = Playwright.create();
		Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
		BrowserContext browserContext = browser.newContext();
		Page page = browserContext.newPage();
		page.navigate("https://chromedriver.storage.googleapis.com/index.html?path=114.0.5735.90/");

		Download download = page.waitForDownload(() -> {
			page.click("a:text('chromedriver_win32.zip')");
		});
		// download.cancel();
		System.out.println(download.url());
		String downloadPath = download.path().toString();
		System.out.println(downloadPath);
		download.saveAs(Paths.get("./download/chromedriver_win32.zip"));
		System.out.println(download.suggestedFilename());
		

		// clean up
		page.close();
		browserContext.close();
		browser.close();
		playwright.close();
		System.out.println("Execution ompleted Successfully");
	}

}
