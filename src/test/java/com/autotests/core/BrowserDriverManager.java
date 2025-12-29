package com.autotests.core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class BrowserDriverManager {

    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    public static WebDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            initDriver();
        }
        return driverThreadLocal.get();
    }

    private static void initDriver() {
        String browser = ConfigReader.getProperty("web.browser", "chrome").toLowerCase();
        WebDriver driver;

        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                driver = new FirefoxDriver(firefoxOptions);
                break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                driver = new ChromeDriver(chromeOptions);
                break;
        }

        int timeout = ConfigReader.getIntProperty("implicit.wait", 10);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
        driver.manage().window().maximize();

        driverThreadLocal.set(driver);
    }

    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}
