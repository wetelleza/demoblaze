package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.testng.Assert;
import pages.CartPage;
import utils.DriverManager;

public class CartSteps {

    private final CartPage cartPage = new CartPage(DriverManager.getDriver());


    @And("place order")
    public void placeOrder() {
        cartPage.placeOrder();
    }

    @And("complete the data of payment")
    public void completeTheDataOfPayment() {
        cartPage.completePaymentData("Wilson Tellez", "4111-1111-1111-1111", "12", "26");
    }

    @Then("the user verify the purchase")
    public void verifyPurchase() {
        Assert.assertTrue(cartPage.isPurchaseSuccessful(), "Purchase was not successful");
        Assert.assertEquals("Thank you for your purchase!", cartPage.getSuccessMessage());

        String detailsText = cartPage.getPurchaseDetails();
        Assert.assertTrue(detailsText.contains("Id:"));
        Assert.assertTrue(detailsText.contains(" USD"));
        Assert.assertTrue(detailsText.contains("Card Number:"));
        Assert.assertTrue(detailsText.contains("Name:"));
        Assert.assertTrue(detailsText.contains("Date:"));

        cartPage.confirmPurchase();
    }

    @And("the user verifies that the product {string} is displayed in the cart")
    public void verifyProductIsDisplayed(String productName) {
        Assert.assertTrue(cartPage.isProductDisplayed(productName), "The product " + productName + " is not present on the list.");
    }

    @Then("the user deletes the item {string} from the cart")
    public void deleteUserItem(String productName) {
        cartPage.clickDeleteButton(productName);
    }

    @And("the product {string} is removed from the cart")
    public void verifyProductIsDeleted(String productName) {
        Assert.assertTrue(cartPage.isProductDeleted(productName), "The product " + productName + " was not removed.");
    }


}
