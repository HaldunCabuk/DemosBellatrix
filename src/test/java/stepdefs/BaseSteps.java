package stepdefs;

import driver.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BaseSteps {

    protected WebDriver driver;
    protected static WebDriverWait wait;

    String url = "https://demos.bellatrix.solutions/";
    String title = "Bellatrix Demos – Bellatrix is a cross-platform," +
            " easily customizable and extendable " +
            ".NET test automation framework that increases tests’ reliability.";
    protected static final String ATTRIBUTE = "//*[@*[.='%s']]";
    protected static final String NAME = "//a[text()='%s']";

    @FindBy(xpath = "//ul[@class='products columns-4' and .//*[text()='Add to cart']]//a[.='Add to cart']")
    public List<WebElement> addToCarts;

    @FindBy(xpath = "//ul[@class='products columns-4']//h2")
    public List<WebElement> productNames;
    @FindBy(xpath = "//ul[@class='products columns-4']//li")
    public List<WebElement> products;

    By lViewCartButton = By.xpath("//a[@class='added_to_cart wc-forward']");
    By lFalcon9 = By.xpath("//a[text()='Falcon 9']");

    protected BaseSteps() {
        driver = Driver.getDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        click(element);
    }

    public void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void sendKey(By locator, CharSequence... texts) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        sendKey(element, texts);
    }

    public void sendKey(WebElement element, CharSequence... texts) {
        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(texts);
    }

    public WebElement getInput(String text) {

        String attr = String.format(ATTRIBUTE, text);

        String xpath = attr;
        By locator = By.xpath(xpath);
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }


    public void productNums(By locator) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        String nums = element.getSize().toString();

    }

    public String isVisible(By locator){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        String name = element.getText().toLowerCase();
        return name;
    }
}
