package tests;

import base.ApiBaseTest;
import com.microsoft.playwright.APIResponse;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;

public class UserApiTest extends ApiBaseTest {

    @Test
    public void verifyUserDetails() {

        APIResponse response = request.get(
                "https://jsonplaceholder.typicode.com/users/1"
        );

        // Attach response to Allure
        Allure.addAttachment("API Response",
                new ByteArrayInputStream(response.text().getBytes()));

        Assert.assertEquals(response.status(), 200);

        String body = response.text();
        Assert.assertTrue(body.contains("Leanne Graham"));
    }
}
