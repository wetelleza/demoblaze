package hooks;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestHooks {
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeMethod
    public void setUp() {
        driver.set(new ChromeDriver());
    }

    @AfterMethod
    public void tearDown() {
        driver.get().quit();
        driver.remove();
    }

    public WebDriver getDriver() {
        return driver.get();
    }
}
