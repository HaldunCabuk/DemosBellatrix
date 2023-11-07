package odevJdbcWithCucumber.model;

import org.openqa.selenium.By;

public interface Locators {


    By lUsername = By.id("input-email");
    By lPassword = By.id("input-password");
    By lLoginButton = By.cssSelector("input[value='Login']");

    By[] adressFormLocators = {
            getByAttribute("First Name"),
            getByAttribute("Last Name"),
            getByAttribute("Company"),
            getByAttribute("Address 1"),
            getByAttribute("Address 2")
    };

    static By getByText(String text){
        return getByText(text, 1);
    }

    static By getByText(String text, int index){
        return By.xpath("//*[text()='" + text + "'][" + index +"]");
    }

    static By getByAttribute(String attr){
        return getByAttribute(attr, 1);
    }

    static By getByAttribute(String attr, int index){
        return By.xpath("//*[@*[.='" + attr + "']][" + index + "]");
    }



}
