package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private final WebDriver driver;

    private final By loginBtn = By.xpath("//button[contains(text(),'Login') or contains(text(),'Sign In')]");
    private final By phoneInput = By.id("phoneNumber"); // update this after inspecting
    private final By sendOtpBtn = By.xpath("//button[contains(text(),'Send OTP')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLogin() {
        driver.findElement(loginBtn).click();
    }

    public void enterPhoneNumber(String phone) {
        driver.findElement(phoneInput).sendKeys(phone);
    }

    public void clickSendOtp() {
        driver.findElement(sendOtpBtn).click();
    }
}