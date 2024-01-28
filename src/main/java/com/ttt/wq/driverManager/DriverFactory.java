package com.ttt.wq.driverManager;

import org.openqa.selenium.WebDriver;

import com.ttt.wq.driverManager.browserTypes.ChromeDriverManager;
import com.ttt.wq.driverManager.browserTypes.FirefoxDriverManager;

public class DriverFactory {
    public static ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<WebDriver>();

    public static WebDriver createDriver() {
        WebDriver driver = null;

        String browserType = "chrome";
        switch (browserType.toLowerCase()) {
            case "chrome":
                driver = new ChromeDriverManager().getDriver();
                break;
            case "firefox":
                driver = new FirefoxDriverManager().getDriver();
                break;
            default:
                throw new IllegalArgumentException("Unsupported browser type provided");
        }

        driverThreadLocal.set(driver);
        driver.manage().window().maximize();

        return driver;
    }

    public static WebDriver getDriverInstance() {
        return driverThreadLocal.get();
    }
}
