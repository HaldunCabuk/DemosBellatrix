package stepdefs;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseSteps {

    String url = "https://demos.bellatrix.solutions/";

    protected WebDriver driver;
    protected WebDriverWait wait;

    protected static final String ATTRIBUTE = "//*[@*[.='%s']]";

    protected BaseSteps(){
        driver = Driver.getDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void click(By locator){
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        click(element);
    }

    public void click(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }
    public void sendKey(By locator, CharSequence...texts){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        sendKey(element, texts);
    }

    public void sendKey(WebElement element, CharSequence...texts){
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(texts);
    }

public WebElement getInput(String text){
        String attr = String.format(ATTRIBUTE, text);

        String xpath = attr;
        By locator = By.xpath(xpath);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
}

}
