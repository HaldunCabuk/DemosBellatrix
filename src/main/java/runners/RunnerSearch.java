package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/main/resources/FeatureSearch.feature"}
        , glue = {"stepdefs"}
        , plugin = {"pretty",
        "json:target/cucumber/cucumber.json",
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
}

)
public class RunnerSearch {
}
