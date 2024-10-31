package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import utils.DriverManager;

public class ProductSteps {
    private final HomePage homePage = new HomePage(DriverManager.getDriver());
    private final ProductPage productPage = new ProductPage(DriverManager.getDriver());
    private final CartPage cartPage = new CartPage(DriverManager.getDriver());

    private String homePagePrice;
    private String productPagePrice;
    private String cartPagePrice;

    @And("the user adds the product to the cart")
    public void addToCart() {
        productPage.addToCart();
    }

    @When("the user verify the price in home of {string}")
    public void verifyPriceInHome(String productName) {
        homePagePrice = homePage.getProductPrice(productName);
        Assert.assertNotNull(homePagePrice, "Price not found on the home page.");
    }

    @And("the user goes to a product {string}")
    public void goToProduct(String productName) {
        homePage.goToProduct(productName);
    }

    @And("the user captures the price of the product")
    public void captureProductPrice() {
        productPagePrice = productPage.getProductPrice();
        Assert.assertEquals(productPagePrice, homePagePrice, "The price on the home page and product page do not match.");
    }

    @Then("the user verifies that the product price in the cart matches the captured price")
    public void verifyPriceInCart() {
        cartPagePrice = cartPage.getProductPrice();
        Assert.assertEquals(cartPagePrice, productPagePrice, "The price on the product page and cart do not match.");
    }

    @Given("the user navigates to the home page")
    public void navigateToHomePage() {
        homePage.navigateToHomePage();
    }

    @And("the user goes to the cart")
    public void goToCart() {
        homePage.goToCart();
    }

}
