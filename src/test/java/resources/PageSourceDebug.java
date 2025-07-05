package resources;

import base.BaseTest;
import java.io.FileWriter;
import java.io.IOException;

public class PageSourceDebug extends BaseTest {

    public void save(String fileName) {
        try {
            String pageSource = driver.getPageSource();
            FileWriter writer = new FileWriter(fileName);
            writer.write(pageSource);
            writer.close();
            System.out.println("✅ Page source saved: " + fileName);
        } catch (IOException e) {
            System.out.println("❌ Error saving page source: " + e.getMessage());
        }
    }
}