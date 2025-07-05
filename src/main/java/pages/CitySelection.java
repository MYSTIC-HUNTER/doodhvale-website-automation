package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CitySelection {
    private final WebDriverWait wait;

    private final By delhiNCRTile = By.xpath("//img[contains(@alt, 'Delhi NCR Icon')]");

    public CitySelection(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void selectDelhiCity() {
        WebElement cityIcon = wait.until(ExpectedConditions.elementToBeClickable(delhiNCRTile));
        cityIcon.click();
    }
}