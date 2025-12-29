package com.autotests.tests.mobile;

import com.autotests.core.MobileDriverManager;
import io.appium.java_client.android.AndroidDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public abstract class BaseMobileTest {

    protected AndroidDriver driver;

    @BeforeClass
    public void setUp() {
        driver = MobileDriverManager.getDriver();
    }

    @AfterClass
    public void tearDown() {
        MobileDriverManager.quitDriver();
    }
}
