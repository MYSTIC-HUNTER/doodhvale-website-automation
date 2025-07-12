package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategoryListing {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By firstAddButton = By.xpath("(//button[contains(text(),'Add')])[1]");

    public CategoryListing(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void clickFirstAvailableProduct() {
        scrollToProductSection();
        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(firstAddButton));
        addBtn.click();
    }

    private void scrollToProductSection() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 500)");
    }
}