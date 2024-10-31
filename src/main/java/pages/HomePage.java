package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By cartLink = By.id("cartur");

    public void navigateToHomePage() {
        driver.get("https://www.demoblaze.com/");
        wait.until(ExpectedConditions.titleContains("STORE"));
    }

    public void goToProduct(String productName) {
        By productLink = By.linkText(productName);
        wait.until(ExpectedConditions.elementToBeClickable(productLink)).click();
    }

    public void goToCart() {
        WebElement cart = driver.findElement(cartLink);
        cart.click();
    }
}
