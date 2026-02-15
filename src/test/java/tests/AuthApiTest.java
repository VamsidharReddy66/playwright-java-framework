package tests;

import base.ApiBaseTest;
import com.microsoft.playwright.APIResponse;
import io.qameta.allure.Allure;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.*;



import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.Map;

import utils.ApiLogger;
import utils.ConfigReader;
import utils.TestContext;
import com.microsoft.playwright.options.RequestOptions;
import utils.TestDataReader;


public class AuthApiTest extends ApiBaseTest {



        @Test
        @Severity(SeverityLevel.CRITICAL)
        @Owner("Vamsi")
        @Feature("Authentication API")
        @Story("User Login")
        @Description("Verify that user can login and token is generated successfully")
        public void verifyLoginAndGetToken () throws Exception {

            // 1️⃣ Get test data
            String username = TestDataReader.get("validUser", "username");
            String password = TestDataReader.get("validUser", "password");

            // 2️⃣ Build request body
            String requestBody = """
                    {
                        "username": "%s",
                        "password": "%s"
                    }
                    """.formatted(username, password);

            String apiBaseUrl = ConfigReader.getProperty("apiBaseUrl");

            // 3️⃣ Send request
            APIResponse response = request.post(
                    apiBaseUrl + "/post",
                    RequestOptions.create()
                            .setHeader("Content-Type", "application/json")
                            .setData(requestBody)
            );

            // 4️⃣ Validate status
            Assert.assertEquals(response.status(), 200);

            // 5️⃣ Parse response
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(response.text());

            JsonNode dataNode = jsonNode.get("data");

            // 6️⃣ Extract values
            String responseUsername = dataNode.get("username").asText();

            // 7️⃣ Generate fake token
            String fakeToken = "TOKEN_" + responseUsername;
            TestContext.setAuthToken(fakeToken);

            System.out.println("Token Generated: " + fakeToken);
        }
    }






