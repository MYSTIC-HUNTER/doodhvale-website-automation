package tests;

import base.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.CitySelection;
import resources.PageSourceDebug;

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
        js = (JavascriptExecutor) driver;

        int scrollStep = 500;
        int maxScroll = 4000;
        boolean productsLoaded = false;

        for (int pos = 0; pos <= maxScroll; pos += scrollStep) {
            js.executeScript("window.scrollTo(0, arguments[0]);", pos);
            Thread.sleep(800);

            List<WebElement> products = driver.findElements(productSelector);
            if (products.size() > 0) {
                System.out.println("✅ Found " + products.size() + " products at scroll position: " + pos);
                productsLoaded = true;
                break;
            }
        }

        if (!productsLoaded) {
            System.out.println("❌ Failed to load any products.");
            PageSourceDebug debugger = new PageSourceDebug();
            debugger.save("failed_to_load_products.html");
            return;
        }

        // ✅ Save page source
        PageSourceDebug saver = new PageSourceDebug();
        saver.driver = this.driver;
        saver.save("pagesource/category_dairy.html");

        System.out.println("✅ Category page source saved.");
        List<WebElement> products = driver.findElements(productSelector);
        int totalCaptured = products.size();
        System.out.println("✅ Found total products: " + totalCaptured);

        for (int i = 0; i < totalCaptured; i++) {
            products = driver.findElements(productSelector);
            if (i >= products.size()) break;

            WebElement product = products.get(i);

            try {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", product);
                Thread.sleep(500); // wait for swiper to mount content

                WebElement title = product.findElement(By.cssSelector(".product_title_name"));
                String productName = title.getText().trim().replaceAll("[^a-zA-Z0-9]", "_");
                product.click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".product_title_name")));

                File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                File targetFile = new File("screenshots/pdp/" + productName + ".png");
                targetFile.getParentFile().mkdirs();
                screenshot.renameTo(targetFile);

                System.out.println("📸 Captured: " + productName);

                driver.navigate().back();
                wait.until(ExpectedConditions.visibilityOfElementLocated(productSelector));

            } catch (StaleElementReferenceException | NoSuchElementException e) {
                System.out.println("⚠️ Skipping index " + i + " due to stale element.");
            }
        }
    }
}