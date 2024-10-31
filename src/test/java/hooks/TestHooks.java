package hooks;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;

public class TestHooks {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        driver.set(new ChromeDriver(options));
    }

    @AfterMethod
    public void tearDown() {
        driver.get().quit();
        driver.remove();
    }

    @AfterSuite
    public void tearDownSuite() {
        if (driver.get() != null) {
            driver.get().quit();
        }
    }

    public WebDriver getDriver() {
        return driver.get();
    }
}
