package base;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.ConfigReader;
import utils.TestContext;


public class ApiBaseTest {

    protected Playwright playwright;
    protected APIRequestContext request;

    @BeforeMethod
    public void setUpApi() {

        playwright = Playwright.create();

        APIRequest.NewContextOptions options = new APIRequest.NewContextOptions();
        options.setBaseURL(ConfigReader.getProperty("apiBaseUrl"));

        request = playwright.request().newContext(options);

    }


    @AfterMethod
    public void tearDownApi() {

        TestContext.clear();   // 👈 VERY IMPORTANT

        if (request != null) {
            request.dispose();
        }

        if (playwright != null) {
            playwright.close();
        }
    }

}
