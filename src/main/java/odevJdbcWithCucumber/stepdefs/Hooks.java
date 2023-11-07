package odevJdbcWithCucumber.stepdefs;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import odevJdbcWithCucumber.utils.Driver;

public class Hooks {


    @Before
    public void before(){
        Driver.getDriver();
    }


    @After
    public void after(){
        //Driver.quit();
    }
}
