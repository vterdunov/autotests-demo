package com.autotests.pages.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(css = ".central-textlogo__image")
    private WebElement wikipediaLogo;

    @FindBy(id = "searchInput")
    private WebElement searchInput;

    @FindBy(css = "button[type='submit']")
    private WebElement searchButton;

    @FindBy(css = ".central-featured")
    private WebElement centralFeaturedSection;

    @FindBy(id = "js-lang-list-button")
    private WebElement languageListButton;

    @FindBy(css = "[data-el-section='primary links'] a[href*='ru.wikipedia']")
    private WebElement russianLanguageLink;

    @FindBy(css = "[data-el-section='primary links'] a[href*='en.wikipedia']")
    private WebElement englishLanguageLink;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLogoDisplayed() {
        return isDisplayed(wikipediaLogo);
    }

    public boolean isSearchInputDisplayed() {
        return isDisplayed(searchInput);
    }

    public void enterSearchQuery(String query) {
        sendKeys(searchInput, query);
    }

    public void clickSearchButton() {
        click(searchButton);
    }

    public SearchResultsPage searchFor(String query) {
        enterSearchQuery(query);
        clickSearchButton();
        return new SearchResultsPage(driver);
    }

    public boolean isCentralFeaturedDisplayed() {
        return isDisplayed(centralFeaturedSection);
    }

    public void clickLanguageButton() {
        click(languageListButton);
    }

    public void selectRussianLanguage() {
        click(russianLanguageLink);
    }

    public void selectEnglishLanguage() {
        click(englishLanguageLink);
    }

    public String getSearchInputPlaceholder() {
        waitForVisibility(searchInput);
        return searchInput.getAttribute("placeholder");
    }

    public void clearSearchInput() {
        waitForVisibility(searchInput);
        searchInput.clear();
    }

    public String getSearchInputValue() {
        waitForVisibility(searchInput);
        return searchInput.getAttribute("value");
    }
}
