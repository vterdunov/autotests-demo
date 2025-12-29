package com.autotests.pages.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SearchResultsPage extends BasePage {

    @FindBy(css = ".mw-search-results .mw-search-result")
    private List<WebElement> searchResults;

    @FindBy(css = ".mw-search-results .mw-search-result-heading a")
    private List<WebElement> searchResultLinks;

    @FindBy(css = ".searchresults")
    private WebElement searchResultsContainer;

    @FindBy(css = ".mw-search-nonefound")
    private WebElement noResultsMessage;

    @FindBy(id = "firstHeading")
    private WebElement pageHeading;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public boolean hasResults() {
        try {
            waitForVisibility(searchResultsContainer);
            return !searchResults.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }

    public int getResultsCount() {
        return searchResults.size();
    }

    public ArticlePage clickFirstResult() {
        if (!searchResultLinks.isEmpty()) {
            click(searchResultLinks.get(0));
        }
        return new ArticlePage(driver);
    }

    public ArticlePage clickResultByIndex(int index) {
        if (index < searchResultLinks.size()) {
            click(searchResultLinks.get(index));
        }
        return new ArticlePage(driver);
    }

    public String getFirstResultTitle() {
        if (!searchResultLinks.isEmpty()) {
            return getText(searchResultLinks.get(0));
        }
        return "";
    }

    public boolean isNoResultsMessageDisplayed() {
        try {
            waitForVisibility(noResultsMessage);
            return noResultsMessage.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPageHeadingDisplayed() {
        return isDisplayed(pageHeading);
    }

    public String getPageHeading() {
        return getText(pageHeading);
    }
}
