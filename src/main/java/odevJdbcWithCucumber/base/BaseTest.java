package odevJdbcWithCucumber.base;

import odevJdbcWithCucumber.utils.Driver;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseTest {

    protected WebDriver driver;
    protected WebDriverWait wait;


    protected BaseTest(){
        driver = Driver.getDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void click(By locator){
        click(getElement(locator));
    }

    public void click(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void sendKeys(By locator, CharSequence...keys){
        sendKeys(getElement(locator), keys);
    }

    public void sendKeys(WebElement element, CharSequence...keys){
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(keys);
    }

    public void TAB(){
        new Actions(driver).sendKeys(Keys.TAB).perform();
    }

    public void ENTER(){
        new Actions(driver).sendKeys(Keys.ENTER).perform();
    }

    public void hover(By locator){
        hover(getElement(locator));
    }

    public void hover(WebElement element){
        new Actions(driver).moveToElement(element).perform();
    }

    public WebElement getElement(By locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public String getRandomString(int len){
        return RandomStringUtils.randomAlphabetic(len);
    }

    public String getRandomNumber(int len){
        return RandomStringUtils.randomNumeric(len,len);
    }

    public void waitForVisibility(By locator){
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


}
