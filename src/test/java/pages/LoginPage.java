package pages;

import com.microsoft.playwright.Page;
import utils.ConfigReader;


public class LoginPage {

    private Page page;



    public LoginPage(Page page) {
        this.page = page;
    }

    // Locators
    private String usernameInput = "#username";
    private String passwordInput = "#password";
    private String loginButton = "button[type='submit']";
    private String flashMessage = "#flash";

    // Actions
    public void navigateToLoginPage() {
        page.navigate(ConfigReader.getProperty("baseUrl"));
    }


    public void enterUsername(String username) {
        page.locator(usernameInput).fill(username);
    }

    public void enterPassword(String password) {
        page.locator(passwordInput).fill(password);
    }

    public void clickLogin() {
        page.locator(loginButton).click();
        page.waitForSelector(flashMessage);
    }

    public String getFlashMessage() {
        return page.locator(flashMessage).textContent();
    }
}
