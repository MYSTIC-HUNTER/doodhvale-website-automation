package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginFlow() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickLogin();
        loginPage.enterPhoneNumber("0303020201");
        loginPage.clickSendOtp();
//        loginPage.
    }
}