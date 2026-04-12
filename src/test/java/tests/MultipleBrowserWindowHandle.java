package tests;

import com.microsoft.playwright.*;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class MultipleBrowserWindowHandle {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
            BrowserContext browserContext = browser.newContext();
            Page page = browserContext.newPage();
            
            page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
            System.out.println("Parent Current URL: "+page.url());
            System.out.println("Parent Window Title: "+page.title());
            assertThat(page).hasTitle(page.title());
            
            Page popup = page.waitForPopup(() -> {
            	Locator twitter = page.locator("//a[@href='https://twitter.com/orangehrm?lang=en']");
                twitter.click();
            });
            popup.waitForLoadState();
            popup.waitForTimeout(5000);
            System.out.println("Popup Current URL: "+popup.url());
            System.out.println("Popup Window Title: "+popup.title());
            assertThat(popup).hasTitle(popup.title());
            System.out.println("Parent Current URL: "+page.url());
            System.out.println("Parent Window Title: "+page.title());
            
            popup.close();
            page.waitForTimeout(5000);
            
            //Open a blank tab and enter a url
            Page blankPopup = page.waitForPopup(() -> {
            	page.click("a[target='_blank']");
            });
            blankPopup.waitForLoadState();
            blankPopup.navigate("https://alokbarmanqa.github.io/website/");
            page.waitForTimeout(5000);
            System.out.println("Popup Window Title: "+blankPopup.title());
        }
    }
}
