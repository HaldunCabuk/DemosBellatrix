package stepdefs;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class CartSteps extends BaseSteps{


    @When("user add the product {string} to the cart")
    public void userAddTheProductToTheCart(String text) {

        getInput("Search products…").sendKeys(text);
        new Actions(driver).keyDown(Keys.ENTER).perform();

        click(addToCarts.get(0));

    }

    @And("user click to Cart")
    public void userClickToCart() {
    }

    @Then("the product {string} should be in Cart")
    public void theProductShouldBeInCart(String arg0) {
    }
}
