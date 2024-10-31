package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//a[contains(@onclick, 'addToCart') and contains(@class, 'btn-success')]")
    private WebElement addToCartButton;

    @FindBy(xpath = "//h3[@class='price-container']")
    private WebElement priceElement;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void addToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton)).click();

        try {
            wait.until(ExpectedConditions.alertIsPresent());
            driver.switchTo().alert().accept();
        } catch (Exception e) {
            System.out.println("No confirmation alert found.");
        }
    }

    public String getProductPrice() {
        String priceText = wait.until(ExpectedConditions.visibilityOf(priceElement)).getText();
        return priceText.replace("$", "").split(" ")[0].trim();
    }
}
