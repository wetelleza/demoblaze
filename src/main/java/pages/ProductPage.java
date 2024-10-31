package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

public class ProductPage {
    private final WebDriver driver;
    private final org.openqa.selenium.support.ui.WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new org.openqa.selenium.support.ui.WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By addToCartButton = By.xpath("//a[contains(@onclick, 'addToCart') and contains(@class, 'btn-success')]");

    public void addToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        button.click();

        try {
            WebDriverWait alertWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            alertWait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            System.out.println("No se encontró ninguna alerta de confirmación.");
        }
    }
}
