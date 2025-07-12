package tests;

public class CategoryListingTest {
    WebDriver driver = new ChromeDriver();
    driver.manage().window().maximize();
    driver.get("https://www.doodhvalefarms.com/categories/dairy");

    CitySelection citySelection = new CitySelection(driver);
    citySelection.selectDelhiCity();

    DairyCategoryPage dairyPage = new DairyCategoryPage(driver);
    dairyPage.clickFirstAvailableProduct();
}