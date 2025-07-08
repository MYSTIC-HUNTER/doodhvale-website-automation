package tests;

import base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CitySelection;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class PDPScreenTest extends BaseTest {

    @org.testng.annotations.Test
    public void captureScreenshotsForAllPDPs() throws Exception {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // City Selection flow starts
        CitySelection cityPage = new CitySelection(driver);
        cityPage.selectDelhiCity();
        By homepageReady = By.cssSelector("h3.category-title");
        wait.until(ExpectedConditions.visibilityOfElementLocated(homepageReady));
        Thread.sleep(2000);
        System.out.println("✅ Homepage rendered. Proceeding to scroll and click.");

        for (int i = 0; i < 10; i++) {
            WebElement category = driver.findElements(By.cssSelector("h3.category-title")).get(i);
            String name = category.getText().trim();
            if (name.equalsIgnoreCase("Dairy")) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", category);
                Thread.sleep(4000);
                WebElement anchor = category.findElement(By.xpath("./ancestor::a"));
                anchor.click();
                break;
            }
        }
        //City Selection Flow ends

        By productSelector = By.cssSelector(".swiper-slide a");
        List<WebElement> products = driver.findElements(productSelector);
        int totalCaptured = products.size();
        System.out.println("✅ Found total products: " + totalCaptured);

        for (int i = 0; i < totalCaptured; i++) {
            products = driver.findElements(productSelector);
            if (i >= products.size()) break;

            WebElement product = products.get(i);

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", product);
            Thread.sleep(500);
            String productName = "";
            try {
                WebElement title = product.findElement(By.cssSelector(".product_title_name"));
                productName = title.getText().trim().replaceAll("[^a-zA-Z0-9]", "_");
            } catch (StaleElementReferenceException e) {
                System.out.println("⚠️ Stale element while extracting title. Skipping index: " + i);
                continue;
            }

            product.click();

            // Wait for PDP title to confirm navigation
            By pdpTitle = By.cssSelector(".product_title_name");
            wait.until(ExpectedConditions.visibilityOfElementLocated(pdpTitle));

            // Take screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File targetFile = new File("screenshots/pdp/" + productName + ".png");
            targetFile.getParentFile().mkdirs();
            screenshot.renameTo(targetFile);

            System.out.println("📸 Captured: " + productName);

            driver.navigate().back();
            wait.until(ExpectedConditions.visibilityOfElementLocated(productSelector));
        }
    }
}