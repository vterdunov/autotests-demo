package com.autotests.pages.mobile;

import com.autotests.core.ConfigReader;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BaseScreen {

    protected AndroidDriver driver;
    protected WebDriverWait wait;

    public BaseScreen(AndroidDriver driver) {
        this.driver = driver;
        int timeout = ConfigReader.getIntProperty("explicit.wait", 15);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
        PageFactory.initElements(driver, this);
    }

    protected void waitForVisibility(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void tap(WebElement element) {
        waitForClickable(element);
        element.click();
    }

    protected void enterText(WebElement element, String text) {
        waitForVisibility(element);
        element.clear();
        element.sendKeys(text);
    }

    protected String getElementText(WebElement element) {
        waitForVisibility(element);
        return element.getText();
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            waitForVisibility(element);
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void waitForElementsPresence(int timeoutSeconds) {
        try {
            Thread.sleep(timeoutSeconds * 100L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    protected boolean waitForListNotEmpty(java.util.List<WebElement> elements, int timeoutSeconds) {
        int attempts = 0;
        int maxAttempts = timeoutSeconds * 2;
        while (attempts < maxAttempts) {
            if (!elements.isEmpty()) {
                return true;
            }
            waitForElementsPresence(5);
            attempts++;
        }
        return !elements.isEmpty();
    }
}
