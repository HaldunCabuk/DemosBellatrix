package stepdefs;

import driver.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class SearchSteps extends BaseSteps{


    @Then("homepage should be opened")
    public void homepageShouldBeOpened() {
        wait.until(ExpectedConditions.titleIs(title));

    }


    @When("user search for {string}")
    public void userSearchFor(String searchText) throws InterruptedException {
        getInput("Search products…").sendKeys(searchText);
        new Actions(driver).keyDown(Keys.ENTER).perform();
        Thread.sleep(2000);
        
    }

    @Then("there must be {int} listed product")
    public void thereMustBeProductNumberListedProduct(int productNumber) {
        Assert.assertEquals(productNames.size(),productNumber);
    }
}
