package odevJdbcWithCucumber.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Select {

    WebElement element;
    WebDriverWait wait;
    public Select(By locator){
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        this.element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public Select(WebElement element){
        wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        this.element = element;
    }

    public void selectByIndex(int index){
        element.findElements(By.cssSelector("li")).get(index).click();
    }

    public void selectByValue(String value){
        element.findElement(By.cssSelector("li[value='" + value+ "']")).click();
    }


}
