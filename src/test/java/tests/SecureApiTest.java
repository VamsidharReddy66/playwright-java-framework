package tests;

import base.ApiBaseTest;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.options.RequestOptions;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestContext;

import java.io.ByteArrayInputStream;

public class SecureApiTest extends ApiBaseTest {

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Owner("Vamsi")
    @Feature("Secure API")
    @Story("Authorized Access")
    @Description("Verify secure API works using stored auth token")
    public void verifySecureApiWithToken() {

        String token = TestContext.getAuthToken();

        System.out.println("Using Token: " + token);

        APIResponse response = request.get(
                "https://postman-echo.com/get",
                RequestOptions.create()
                        .setHeader("Authorization", "Bearer " + token)
        );

        Allure.addAttachment("Secure API Response",
                new ByteArrayInputStream(response.text().getBytes()));

        Assert.assertEquals(response.status(), 200);
    }
}
