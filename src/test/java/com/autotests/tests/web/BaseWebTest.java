package com.autotests.tests.web;

import com.autotests.core.BrowserDriverManager;
import com.autotests.core.ConfigReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseWebTest {

    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = BrowserDriverManager.getDriver();
        String baseUrl = ConfigReader.getProperty("web.base.url", "https://www.wikipedia.org");
        driver.get(baseUrl);
    }

    @AfterClass
    public void tearDown() {
        BrowserDriverManager.quitDriver();
    }

    protected void navigateTo(String url) {
        driver.get(url);
    }
}
