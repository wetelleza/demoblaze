package steps;

import io.cucumber.java.en.And;
import pages.ProductPage;
import utils.DriverManager;

public class ProductSteps {
    private final ProductPage productPage = new ProductPage(DriverManager.getDriver());

    @And("add to cart")
    public void addToCart() {
        productPage.addToCart();
    }
}
