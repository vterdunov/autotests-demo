package com.autotests.pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.Collections;

public class ArticleScreen extends BaseScreen {

    @FindBy(id = "org.wikipedia:id/page_web_view")
    private WebElement articleWebView;

    @FindBy(xpath = "//android.view.View[@resource-id='pcs-edit-section-title-description']")
    private WebElement articleTitleView;

    @FindBy(xpath = "//android.widget.TextView[@resource-id='org.wikipedia:id/page_toolbar_button_show_overflow_menu']")
    private WebElement overflowMenuButton;

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
    private WebElement backButton;

    @FindBy(id = "org.wikipedia:id/page_save")
    private WebElement saveButton;

    @FindBy(id = "org.wikipedia:id/page_toolbar_button_search")
    private WebElement searchInArticleButton;

    @FindBy(xpath = "//android.view.View[contains(@text, '')]")
    private WebElement articleContent;

    public ArticleScreen(AndroidDriver driver) {
        super(driver);
    }

    public boolean isArticleWebViewDisplayed() {
        waitSeconds(3);
        return isElementDisplayed(articleWebView);
    }

    public String getArticleTitle() {
        waitSeconds(2);
        try {
            return getElementText(articleTitleView);
        } catch (Exception e) {
            return "";
        }
    }

    public void tapOverflowMenu() {
        tap(overflowMenuButton);
    }

    public void goBack() {
        tap(backButton);
    }

    public void tapSaveButton() {
        tap(saveButton);
    }

    public void tapSearchInArticle() {
        tap(searchInArticleButton);
    }

    public void scrollDown() {
        int screenHeight = driver.manage().window().getSize().getHeight();
        int screenWidth = driver.manage().window().getSize().getWidth();

        int startX = screenWidth / 2;
        int startY = (int) (screenHeight * 0.8);
        int endY = (int) (screenHeight * 0.2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 1);

        scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(scroll));
    }

    public void scrollUp() {
        int screenHeight = driver.manage().window().getSize().getHeight();
        int screenWidth = driver.manage().window().getSize().getWidth();

        int startX = screenWidth / 2;
        int startY = (int) (screenHeight * 0.2);
        int endY = (int) (screenHeight * 0.8);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence scroll = new Sequence(finger, 1);

        scroll.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
        scroll.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        scroll.addAction(finger.createPointerMove(Duration.ofMillis(500), PointerInput.Origin.viewport(), startX, endY));
        scroll.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Collections.singletonList(scroll));
    }

    public boolean isBackButtonDisplayed() {
        return isElementDisplayed(backButton);
    }
}
