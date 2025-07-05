package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    private final WebDriver driver;

    private final By categoryTitles = By.cssSelector("h3.category-title");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public List<WebElement> getAllCategoryElements() {
        return driver.findElements(categoryTitles);
    }

    public void clickCategoryByName(String categoryName) {
        By category = By.xpath("//h3[text()='" + categoryName + "']/ancestor::a");
        driver.findElement(category).click();
    }
}