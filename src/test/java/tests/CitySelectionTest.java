package tests;

import base.BaseTest;
import org.testng.annotations.Test;
import pages.CitySelection;

public class CitySelectionTest extends BaseTest {

    @Test
    public void testSelectDelhiAndSavePageSource() throws InterruptedException {
        CitySelection cityPage = new CitySelection(driver);
        cityPage.selectDelhiCity();

        Thread.sleep(4000); // Replace with WebDriverWait if necessary

//        PageSourceDebug pageSourceSaver = new PageSourceDebug();
//        pageSourceSaver.driver = this.driver;  // Ensure WebDriver is passed
//        pageSourceSaver.save("homepage_after_city_selection.html");
    }
}
