package base;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.ITestResult;
import utils.ConfigReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class BaseTest {

    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);


    @BeforeMethod
    public void setUp() {

        playwright = Playwright.create();

        String browserType = ConfigReader.getProperty("browser");
        boolean headless = Boolean.parseBoolean(ConfigReader.getProperty("headless"));

        logger.info("Launching browser: {}", browserType);
        logger.info("Headless mode: {}", headless);

        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(headless);

        switch (browserType.toLowerCase()) {
            case "firefox":
                browser = playwright.firefox().launch(options);
                break;
            case "webkit":
                browser = playwright.webkit().launch(options);
                break;
            default:
                browser = playwright.chromium().launch(options);
                break;
        }

        context = browser.newContext();
        page = context.newPage();
    }

    @AfterMethod
    public void tearDown(ITestResult result) {

        if (result.getStatus() == org.testng.ITestResult.FAILURE) {
            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(java.nio.file.Paths.get("screenshots/" + result.getName() + ".png"))
                    .setFullPage(true));
        }
        if (context != null) {
            context.close();
        }

        if (browser != null) {
            browser.close();
        }

        if (playwright != null) {
            playwright.close();
        }
    }
}
