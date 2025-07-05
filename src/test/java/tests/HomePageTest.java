package tests;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Test;
import pages.CitySelection;

import java.util.List;

public class HomePageTest extends BaseTest {

    @Test
    public void testClickEachCategoryWithScrollFix() throws InterruptedException {
        CitySelection cityPage = new CitySelection(driver);
        cityPage.selectDelhiCity();

        // Wait for homepage to load
        Thread.sleep(3000);

        for (int i = 0; i < 10; i++) { // Adjust max number of categories
            List<WebElement> categories = driver.findElements(By.cssSelector("h3.category-title"));

            if (i >= categories.size()) break;

            WebElement category = categories.get(i);
            String categoryName = category.getText().trim();
            if (categoryName.isEmpty()) continue;

            System.out.println("➡ Clicking on category: " + categoryName);

            // Scroll into view before clicking
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", category);
            Thread.sleep(500); // Optional slight pause

            // Click using parent <a> element
            WebElement anchor = category.findElement(By.xpath("./ancestor::a"));
            anchor.click();

            Thread.sleep(2000); // Wait on category page

            // Optionally capture page source here if needed

            driver.navigate().back(); // Return to homepage
            Thread.sleep(2000); // Wait for homepage to reload
        }
    }
}