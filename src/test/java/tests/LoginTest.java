package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void verifyValidLogin() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.navigateToLoginPage();
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLogin();

        String message = loginPage.getFlashMessage();

        Assert.assertTrue(message.contains("You logged into a secure area!"));
    }

    @Test

    public void verifyInvalidLogin() {

        LoginPage loginPage = new LoginPage(page);

        loginPage.navigateToLoginPage();
        loginPage.enterUsername("wrongUser");
        loginPage.enterPassword("wrongPassword");
        loginPage.clickLogin();

        String message = loginPage.getFlashMessage();

//        Assert.assertTrue(message.contains("Your username is invalid!"),
//                "Error message validation failed");

        Assert.assertTrue(false);



    }

}
