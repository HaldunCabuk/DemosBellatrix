package stepdefs;

import driver.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CucumberHooks {


    @After
    public void afterScenario(){
        //Driver.quit();
    }



}
