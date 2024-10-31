package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pages.HomePage;
import utils.DriverManager;

public class HomeSteps {
    private final HomePage homePage = new HomePage(DriverManager.getDriver());

    @Given("the user navigates to the home page")
    public void navigateToHomePage() {
        homePage.navigateToHomePage();
    }

    @When("the user goes to a product {string}")
    public void goToProduct(String productName) {
        homePage.goToProduct(productName);
    }

    @And("go to the cart")
    public void goToTheCart() {
        homePage.goToCart();
    }
}
