package com.autotests.core;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class MobileDriverManager {

    private static final ThreadLocal<AndroidDriver> driverThreadLocal = new ThreadLocal<>();

    public static AndroidDriver getDriver() {
        if (driverThreadLocal.get() == null) {
            initDriver();
        }
        return driverThreadLocal.get();
    }

    private static void initDriver() {
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName(ConfigReader.getProperty("mobile.platform.name", "Android"));
        options.setDeviceName(ConfigReader.getProperty("mobile.device.name", "Android Emulator"));
        options.setAutomationName(ConfigReader.getProperty("mobile.automation.name", "UiAutomator2"));
        options.setAppPackage(ConfigReader.getProperty("mobile.app.package", "org.wikipedia"));
        options.setAppActivity(ConfigReader.getProperty("mobile.app.activity", "org.wikipedia.main.MainActivity"));

        String apkPath = ConfigReader.getProperty("mobile.apk.path");
        if (apkPath != null && !apkPath.isEmpty()) {
            options.setApp(System.getProperty("user.dir") + "/" + apkPath);
        }

        options.setNoReset(false);
        options.setFullReset(false);

        String appiumUrl = ConfigReader.getProperty("mobile.appium.url", "http://127.0.0.1:4723");

        try {
            AndroidDriver driver = new AndroidDriver(new URL(appiumUrl), options);
            int timeout = ConfigReader.getIntProperty("mobile.timeout", 15);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));
            driverThreadLocal.set(driver);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Invalid Appium URL: " + appiumUrl, e);
        }
    }

    public static void quitDriver() {
        AndroidDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}
