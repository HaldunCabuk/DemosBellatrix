package driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver {

private static ThreadLocal<WebDriver> drivers = new ThreadLocal<>();

public static WebDriver getDriver(){
    return getDriver("chrome");

}

public static WebDriver getDriver(String browser){
    if (drivers.get() == null){
        switch (browser.toLowerCase()){
            case "firefox":
                drivers.set(new FirefoxDriver());
                break;
            case "edge":
                drivers.set(new EdgeDriver());
                break;
            default:
                drivers.set(new ChromeDriver());
        }
    }
    return drivers.get();
}


public static void quit(){

    if (drivers.get() != null){
        drivers.get().quit();
        drivers.set(null);
    }
}




}
