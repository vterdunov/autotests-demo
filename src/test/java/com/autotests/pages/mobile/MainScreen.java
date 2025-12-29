package com.autotests.pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainScreen extends BaseScreen {

    @FindBy(id = "org.wikipedia:id/fragment_onboarding_skip_button")
    private WebElement skipButton;

    @FindBy(id = "org.wikipedia:id/search_container")
    private WebElement searchContainer;

    @FindBy(xpath = "//android.widget.TextView[@text='Search Wikipedia']")
    private WebElement searchPlaceholder;

    @FindBy(id = "org.wikipedia:id/main_toolbar_wordmark")
    private WebElement wikipediaWordmark;

    @FindBy(id = "org.wikipedia:id/nav_more_container")
    private WebElement moreOptionsButton;

    @FindBy(id = "org.wikipedia:id/nav_tab_explore")
    private WebElement exploreTab;

    @FindBy(id = "org.wikipedia:id/nav_tab_reading_lists")
    private WebElement savedTab;

    public MainScreen(AndroidDriver driver) {
        super(driver);
    }

    public void skipOnboarding() {
        try {
            if (isElementDisplayed(skipButton)) {
                tap(skipButton);
            }
        } catch (Exception e) {
            // Onboarding already skipped
        }
    }

    public boolean isSearchContainerDisplayed() {
        return isElementDisplayed(searchContainer);
    }

    public SearchScreen tapSearchContainer() {
        tap(searchContainer);
        return new SearchScreen(driver);
    }

    public boolean isWordmarkDisplayed() {
        return isElementDisplayed(wikipediaWordmark);
    }

    public void tapMoreOptions() {
        tap(moreOptionsButton);
    }

    public void tapExploreTab() {
        tap(exploreTab);
    }

    public void tapSavedTab() {
        tap(savedTab);
    }

    public boolean isExploreTabDisplayed() {
        return isElementDisplayed(exploreTab);
    }
}
