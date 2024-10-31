package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    // Using @FindBy annotations to locate elements
    @FindBy(xpath = "//button[text()='Place Order']")
    private WebElement placeOrderButton;

    @FindBy(id = "orderModalLabel")
    private WebElement modalTitle;

    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "card")
    private WebElement cardField;

    @FindBy(id = "month")
    private WebElement monthField;

    @FindBy(id = "year")
    private WebElement yearField;

    @FindBy(xpath = "//button[@onclick='purchaseOrder()']")
    private WebElement purchaseButton;

    @FindBy(className = "sweet-alert")
    private WebElement successModal;

    @FindBy(xpath = "//div[contains(@class, 'sweet-alert')]//h2[text()='Thank you for your purchase!']")
    private WebElement successTitle;

    @FindBy(className = "lead")
    private WebElement details;

    @FindBy(className = "confirm")
    private WebElement confirmButton;

    @FindBy(id = "tbodyid")
    private WebElement cartTableBody;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this); // Initialize elements
    }

    public void placeOrder() {
        wait.until(ExpectedConditions.visibilityOf(placeOrderButton)).click();
    }

    public void completePaymentData(String name, String cardNumber, String month, String year) {
        wait.until(ExpectedConditions.visibilityOf(modalTitle));
        nameField.sendKeys(name);
        cardField.sendKeys(cardNumber);
        monthField.sendKeys(month);
        yearField.sendKeys(year);

        purchaseButton.click();
    }

    public boolean isPurchaseSuccessful() {
        return wait.until(ExpectedConditions.visibilityOf(successModal)).isDisplayed();
    }

    public String getSuccessMessage() {
        wait.until(ExpectedConditions.visibilityOf(successModal));
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return successTitle.getText();
    }

    public String getPurchaseDetails() {
        return wait.until(ExpectedConditions.visibilityOf(details)).getText();
    }

    public void confirmPurchase() {
        wait.until(ExpectedConditions.visibilityOf(confirmButton)).click();
    }

    public boolean isProductDisplayed(String productName) {
        WebElement productNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tbody[@id='tbodyid']//td[contains(text(), '" + productName + "')]")));
        return productNameElement.isDisplayed();
    }

    public void clickDeleteButton(String productName) {
        WebElement deleteButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//tbody[@id='tbodyid']//td[contains(text(), '" + productName + "')]/following-sibling::td/a[contains(@onclick, 'deleteItem')]")));
        deleteButton.click();
    }

    public boolean isProductDeleted(String productName) {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(
                    By.xpath("//tbody[@id='tbodyid']//td[contains(text(), '" + productName + "')]")));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getProductPrice() {
        WebElement cartPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//tr[@class='success']//td[3]")));
        String priceText = cartPriceElement.getText();
        return priceText.replace("$", "").trim();
    }

    public boolean isPaymentModalVisible() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            return modalTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCartEmpty() {
        try {
            wait.until(ExpectedConditions.visibilityOf(cartTableBody));
            return cartTableBody.findElements(By.tagName("tr")).isEmpty();
        } catch (Exception e) {
            return true;
        }
    }
}
