package tests;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleTest extends BaseTest {

    @Test
    public void verifyGoogleTitle() {
        page.navigate("https://www.google.com");

        String title = page.title();

        System.out.println("Page Title: " + title);

        Assert.assertTrue(title.contains("Google"));

    }
}
