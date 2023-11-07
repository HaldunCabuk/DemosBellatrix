package jdbc.odev_03112023;

import org.openqa.selenium.By;

public class Locators {

   static By lMyAccount = By.xpath("//span[text()='My Account']");
    static By lRegisterLink = By.xpath("//a[text()='Register']");
    static By lLoginLink = By.xpath("//a[text()='Login']");
    static By lEmailBox = By.cssSelector("#input-email");
    static  By lPwdBox = By.cssSelector("#input-password");
    static  By lLoginButton = By.xpath("//input[@type='submit']");
    static  By lFirstNameInput = By.cssSelector("#input-firstname");
    static By lLastNameInput = By.cssSelector("#input-lastname");
    static By lCompanyInput = By.cssSelector("#input-company");
    static By lEmailInput = By.cssSelector("#input-email");
    static By lTelInput = By.cssSelector("#input-telephone");
    static  By lPwdInput = By.cssSelector("#input-password");
    static  By lConfrmPwdInput = By.cssSelector("#input-confirm");
    static By lConfrmPolicy = By.xpath("//input[@type='checkbox']");
    static  By lSubmitButton = By.xpath("//input[@type='submit']");
    static By lRegPageText = By.xpath("//h1[text()='Account']");
    static  By lAddressBook = By.xpath("//div[@class='list-group']//*[text()='Address Book']");
    static  By lContinueButton = By.xpath("//a[text()='Continue']");
    static By lNewAddress = By.xpath("//a[text()='New Address']");
    static  By lAddress1Input = By.cssSelector("#input-address-1");
    static  By lAddress2Input = By.cssSelector("#input-address-2");
    static By lCityInput = By.cssSelector("#input-city");
    static By lPostcodeInput = By.cssSelector("#input-postcode");
    static By lCountryInput = By.cssSelector("#input-country");
    static  By lZoneInput = By.cssSelector("#input-zone");







}
