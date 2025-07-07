package resources;

import base.BaseTest;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

public class PageSourceDebug extends BaseTest {

    public void save(String fileName) {
        try {
            String source = driver.getPageSource();
            FileWriter writer = new FileWriter(fileName);
            writer.write(source);
            writer.close();
            System.out.println("✅ Page source saved: " + fileName);
        } catch (IOException e) {
            System.out.println("❌ Error saving page source: " + e.getMessage());
        }
    }

    public void saveAfterNavigating(String url, String fileName) {
        try {
            driver.get(url);

            // Wait for full JS load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.until((ExpectedCondition<Boolean>) wd ->
                    {
                        assert wd != null;
                        return ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete");
                    }
            );

            String source = driver.getPageSource();
            FileWriter writer = new FileWriter(fileName);
            writer.write(source);
            writer.close();
            System.out.println("✅ Page source saved after navigating to URL: " + fileName);
        } catch (IOException e) {
            System.out.println("❌ Error saving page source: " + e.getMessage());
        }
    }
}