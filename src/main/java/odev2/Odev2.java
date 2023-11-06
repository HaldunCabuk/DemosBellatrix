package odev2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Odev2 {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeTest
    public void setUp(){
        driver =new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }



    @Test
    public void  Scenario_2() throws InterruptedException {

//        1.  https://www.saucedemo.com/  sitesine git
        String url = "https://www.saucedemo.com/";
        driver.get(url);


//        2.  sayfada belirtilen standart user ile login olun

        WebElement username = driver.findElement(By.xpath("//input[@placeholder='Username']"));
        username.sendKeys("standard_user");


        WebElement password = driver.findElement(By.xpath("//input[@placeholder='Password']"));
        password.sendKeys("secret_sauce");

        WebElement loginButton = driver.findElement(By.xpath("//input[@id='login-button']"));
        loginButton.click();



// 3.  Sayfada Listelenen ürünlerin isimlerini List olarak alin ve consola yazdirin.

        Thread.sleep(3000);

        WebElement element = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        wait.until(ExpectedConditions.visibilityOf(element));


        List<WebElement> listOfProduct = driver.findElements(By.xpath("//div[@id='root'] //div[@class='inventory_item_name ']"));


        for (WebElement each : listOfProduct) {
            System.out.println(each.getText());
        }


//        4.  "Sauce Labs Bike Light" ve "Sauce Labs Bolt T-Shirt" ürünlerini sepete ekleyin

//div[text()='Sauce Labs Bike Light']
        //div[text()='Sauce Labs Bolt T-Shirt']

//        WebElement product_BikeLight = driver.findElement(By.xpath("//div[text()='Sauce Labs Bike Light']"));
//        product_BikeLight.click();
//        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bike-light']")).click();
//
//        WebElement product_BoltTShirt = driver.findElement(By.xpath("  //div[text()='Sauce Labs Bolt T-Shirt']"));
//        product_BoltTShirt.click();
//        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bolt-t-shirt']")).click();


//        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bike-light']")).click();


        // 5.  Sayfada Listelenen ürünlerin "Add To Cart"'dan  "Remove"'a dönen buton sayisinin 2 oldugunu assert edin.
        //     sepete gidin

        List<WebElement> listOfRemoweProducts = driver.findElements(By.xpath("//button[text()='Remove']"));
        Assert.assertTrue(listOfRemoweProducts.size()==2);


        // 6.  sepette 2 ürünün oldugunu assert edin.
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        Thread.sleep(2000);
        List<WebElement> cartProducts = driver.findElements(By.xpath("//div[@class='cart_list']//div[@class='cart_item']"));
        Assert.assertEquals(cartProducts.size(),2);



        //  7.  Checkout yapin
        driver.findElement(By.cssSelector("#checkout")).click();



        // 8.  Acilan sayfadaki formu doldurun
        WebElement firstName =  driver.findElement(By.cssSelector("#first-name"));
        //firstName.sendKeys("trgt");

        new Actions(driver)
                .clickAndHold(firstName)
                .sendKeys("trgt")
                .keyDown(Keys.TAB)
                .sendKeys("aaaaa")
                .keyDown(Keys.TAB)
                .sendKeys("06165")
                .keyDown(Keys.TAB)
                .pause(2000)
                .keyDown(Keys.ENTER)
                .release()
                .perform();

    }


    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
