package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By placeOrderButton = By.xpath("//button[text()='Place Order']");
    private final By modalTitle = By.id("orderModalLabel");
    private final By nameField = By.id("name");
    private final By cardField = By.id("card");
    private final By monthField = By.id("month");
    private final By yearField = By.id("year");
    private final By purchaseButton = By.xpath("//button[@onclick='purchaseOrder()']");
    private final By successModal = By.className("sweet-alert");
    private final By successTitle = By.tagName("h2");
    private final By details = By.className("lead");
    private final By confirmButton = By.className("confirm");

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void placeOrder() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(placeOrderButton)).click();
    }

    public void completePaymentData(String name, String cardNumber, String month, String year) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(modalTitle));
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(cardField).sendKeys(cardNumber);
        driver.findElement(monthField).sendKeys(month);
        driver.findElement(yearField).sendKeys(year);

        driver.findElement(purchaseButton).click();
    }

    public boolean isPurchaseSuccessful() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successModal)).isDisplayed();
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successModal))
                .findElement(successTitle).getText();
    }

    public String getPurchaseDetails() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(details)).getText();
    }

    public void confirmPurchase() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmButton)).click();
    }

    public boolean isProductDisplayed(String productName) {
        By productNameLocator = By.xpath("//tbody[@id='tbodyid']//td[contains(text(), '" + productName + "')]");
        return wait.until(ExpectedConditions.visibilityOfElementLocated(productNameLocator)).isDisplayed();
    }

    public void clickDeleteButton(String productName) {
        By deleteButtonLocator = By.xpath("//tbody[@id='tbodyid']//td[contains(text(), '" + productName + "')]/following-sibling::td/a[contains(@onclick, 'deleteItem')]");
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(deleteButtonLocator));
        deleteButton.click();
    }

    public boolean isProductDeleted(String productName) {
        By productLocator = By.xpath("//tbody[@id='tbodyid']//td[contains(text(), '" + productName + "')]");
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(productLocator));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getProductPrice() {
        By cartPriceLocator = By.xpath("//tr[@class='success']//td[3]");
        String priceText = wait.until(ExpectedConditions.visibilityOfElementLocated(cartPriceLocator)).getText();
        return priceText.replace("$", "").trim();
    }

}
