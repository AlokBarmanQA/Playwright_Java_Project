package tests;

import com.microsoft.playwright.*;

public class Reusable2 {

    public static void main(String[] args) {
        try (Playwright playwright = Playwright.create()) {
            Browser browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setSlowMo(2000));
            Page page = browser.newPage();
            page.navigate("https://playwright.dev");
            
            System.out.println(page.title());
            

            
        }
    }
}
