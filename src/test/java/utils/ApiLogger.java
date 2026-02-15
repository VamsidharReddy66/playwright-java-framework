package utils;

import io.qameta.allure.Allure;
import com.microsoft.playwright.APIResponse;

public class ApiLogger {

    public static void logResponse(APIResponse response) {
        Allure.addAttachment(
                "Status Code",
                String.valueOf(response.status())
        );

        Allure.addAttachment(
                "Response Body",
                response.text()
        );
    }
}
