package jdbc.odev_03112023;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static jdbc.odev_03112023.Locators.*;

import java.sql.*;
import java.time.Duration;
import java.util.List;

/*
https://opencart.abstracta.us/ sitesinde
    a. yeni bir kullanici register edin
    b. login olun
    c. Adress Book'a gidin
    d. Veritabanina eklediginiz kaydi SELECT komutu ile okuyun
    e. Aldiginiz bu veriler ile "Adress Book" kaydi yapin.
    f. Kayit yapildigindan emin olun.
 */
public class Opencart_odev05112023 extends CreateAddressTable{

    WebDriver driver;
    WebDriverWait wait;

    String url = "https://opencart.abstracta.us/";
    CreateAddressTable cat;


    @BeforeTest
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterTest
    public void afterTest() {
        //driver.quit();
    }

    @Test
    public void openAndReg() {

        driver.get(url);
        wait.until(ExpectedConditions.titleIs("Your Store"));
        click(lMyAccount);
        click(lRegisterLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lRegPageText));
        sendKey(lFirstNameInput, "Ali");
        sendKey(lLastNameInput, "Veli");
        sendKey(lEmailInput, "a1b2c3@az.de");
        sendKey(lTelInput, "123456789");
        sendKey(lPwdInput, "admin");
        sendKey(lConfrmPwdInput, "admin");
        click(lConfrmPolicy);
        click(lSubmitButton);

    }

    @Test
    public void loginAndAddAddress() throws InterruptedException {
        driver.get(url);
        wait.until(ExpectedConditions.titleIs("Your Store"));
        click(lMyAccount);
        click(lLoginLink);
        sendKey(lEmailInput, "a1b2c3@az.de");
        sendKey(lPwdBox, "admin");
        click(lLoginButton);
        click(lAddressBook);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Address Book Entries']")));
        click(lNewAddress);
        List<List<String>> lists =
        sendKey(lFirstNameInput, firstName);
        sendKey(lLastNameInput, lastName);
        sendKey(lAddress1Input, address1);
        sendKey(lCityInput, city);
        sendKey(lPostcodeInput, postCode);
        //new Actions(driver).keyDown(Keys.TAB).sendKeys("tur").pause(8000).perform();
        select(lCountryInput, 231);
        Thread.sleep(3000);
        select(lZoneInput, 6);
        click(lSubmitButton);
    }




    /*@Test
    public void getTable() throws SQLException {

       bc.rs = bc.stmnt.executeQuery("select First Name, Last Name, Address 1, City, Post Code, Country, Region / State from " + url);

        while (bc.rs.next()) {
            String first_name = bc.rs.getString(1);
            String last_name =bc.rs.getString(2);
            String address1 =bc.rs.getString(3);
            String city = bc.rs.getString(4);
            String postCode =bc.rs.getString(5);
            String country =bc.rs.getString(6);
            String state =bc.rs.getString(7);

            System.out.printf("%-5d%-15s%-15s%-15s%-3s\n", first_name, last_name, address1, city, postCode, country, state);
        }


    }*/





    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void sendKey(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.sendKeys(text);


    }

    public void select(By locator, int index) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(element);
        select.selectByIndex(index);


    }

}
