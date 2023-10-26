
import org.junit.jupiter.api.Order;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

import static io.restassured.RestAssured.get;

public class DemoQaTests {


    String url = "https://demoqa.com/login";
    String urlBooks = "https://demoqa.com/books";
    String urlProfile = "https://demoqa.com/profile";
    By lLogin = By.xpath("//button[text()='Login']");
    By lFirstname = By.xpath("//input[@id='firstname']");
    By lLastname = By.xpath("//input[@id='lastname']");
    By lUsername = By.xpath("//input[@id='userName']");
    By lPassword = By.xpath("//input[@id='password']");
    By lNotRobotBox = By.xpath("//span[@id='recaptcha-anchor']");
    By lLogoutButton = By.xpath("//button[text()='Log out']");
    By lSearchBox = By.xpath("//input[@id='searchBox']");

    By lRows = By.xpath("//div[@class='rt-tbody']//div[@role='row']");
    By lBookStore = By.xpath("//span[text()='Book Store']");
    By lBookApplication = By.xpath("//*[*='Book Store Application']");
    By lBookStoreText = By.xpath("//div[text()='Book Store']");
    By lProfileText = By.xpath("//div[text()='Profile']");
    By lAddBookLink = By.xpath("//button[text()='Add To Your Collection']");
    By lIsbnLabel = By.cssSelector("#ISBN-label");

    String isbn = "9781449337711";
    String title = "";




    static WebDriver driver;
    static WebDriverWait wait;

    public DemoQaTests() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterClass
    public void quit() {
        //driver.quit();
    }

    @Test(priority = 1)

    public void login() throws InterruptedException {
        title = getTitle(isbn);
        driver.get(url);
        //zoom(0.9);
        sendKey(lUsername, "admin");
        sendKey(lPassword, "Admin777._&");
        click(lLogin);
        wait.until(ExpectedConditions.presenceOfElementLocated(lLogoutButton));
        Thread.sleep(2000);

    }


    @Test(priority = 2)
    public void search() throws InterruptedException {
        driver.get(urlBooks);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lBookStoreText));
        sendKey(lSearchBox, title, Keys.ENTER);
        List<WebElement> books = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(lRows));
        for (WebElement book : books) {
            System.out.println(book.getText());
        }
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    public void addBook() {
        click(getBookLink(title));

        wait.until(ExpectedConditions.visibilityOfElementLocated(lIsbnLabel));
        click(lAddBookLink);
       // driver.switchTo().alert().accept();

    }

    @Test(priority = 4)
    public void navigateToProfile() {
        driver.get(urlProfile);
        wait.until(ExpectedConditions.visibilityOfElementLocated(lProfileText));
        wait.until(ExpectedConditions.visibilityOfElementLocated(getBookLink(title)));
    }

    public String getTitle(String isbn){
        String title = get("https://demoqa.com/BookStore/v1/Books")
                .then().log().body()
                .extract().jsonPath().get("books.find{it.isbn =='" + isbn + "'}.title");

        //System.out.println(title);

        return title;
    }


    public void click(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView()", element);
        js.executeScript("window.scrollBy(0,100)");
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();

    }

    public void sendKey(By locator, CharSequence... key) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(key);

    }

  /*  public void zoom(double value) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='" + value + "'");
    }*/

    public By getBookLink(String text) {
        return By.xpath("//a[text()=\"" + text + "\"]");
    }
}
