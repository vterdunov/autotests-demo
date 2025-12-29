package com.autotests.pages.mobile;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchScreen extends BaseScreen {

    @FindBy(id = "org.wikipedia:id/search_src_text")
    private WebElement searchInput;

    @FindBy(id = "org.wikipedia:id/search_close_btn")
    private WebElement clearSearchButton;

    @FindBy(id = "org.wikipedia:id/page_list_item_title")
    private List<WebElement> searchResultTitles;

    @FindBy(id = "org.wikipedia:id/page_list_item_container")
    private List<WebElement> searchResultItems;

    @FindBy(id = "org.wikipedia:id/search_empty_message")
    private WebElement emptySearchMessage;

    @FindBy(xpath = "//android.widget.ImageButton[@content-desc='Navigate up']")
    private WebElement backButton;

    public SearchScreen(AndroidDriver driver) {
        super(driver);
    }

    public void enterSearchQuery(String query) {
        enterText(searchInput, query);
    }

    public void clearSearch() {
        tap(clearSearchButton);
    }

    public boolean hasSearchResults() {
        waitSeconds(2);
        return !searchResultItems.isEmpty();
    }

    public int getResultsCount() {
        return searchResultItems.size();
    }

    public String getFirstResultTitle() {
        if (!searchResultTitles.isEmpty()) {
            return getElementText(searchResultTitles.get(0));
        }
        return "";
    }

    public ArticleScreen tapFirstResult() {
        if (!searchResultItems.isEmpty()) {
            tap(searchResultItems.get(0));
        }
        return new ArticleScreen(driver);
    }

    public ArticleScreen tapResultByIndex(int index) {
        if (index < searchResultItems.size()) {
            tap(searchResultItems.get(index));
        }
        return new ArticleScreen(driver);
    }

    public boolean isEmptyMessageDisplayed() {
        return isElementDisplayed(emptySearchMessage);
    }

    public void goBack() {
        tap(backButton);
    }

    public String getSearchInputText() {
        waitForVisibility(searchInput);
        return searchInput.getText();
    }
}
