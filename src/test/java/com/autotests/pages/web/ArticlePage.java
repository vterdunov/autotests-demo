package com.autotests.pages.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ArticlePage extends BasePage {

    @FindBy(id = "firstHeading")
    private WebElement articleTitle;

    @FindBy(css = "#mw-content-text")
    private WebElement articleContent;

    @FindBy(css = "#toc")
    private WebElement tableOfContents;

    @FindBy(css = ".mw-body-content p")
    private List<WebElement> articleParagraphs;

    @FindBy(css = "#p-lang .interlanguage-link a")
    private List<WebElement> languageLinks;

    @FindBy(css = ".mw-logo")
    private WebElement wikipediaLogo;

    public ArticlePage(WebDriver driver) {
        super(driver);
    }

    public String getArticleTitle() {
        return getText(articleTitle);
    }

    public boolean isArticleTitleDisplayed() {
        return isDisplayed(articleTitle);
    }

    public boolean isContentDisplayed() {
        return isDisplayed(articleContent);
    }

    public boolean hasTableOfContents() {
        try {
            waitForVisibility(tableOfContents);
            return tableOfContents.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public int getParagraphsCount() {
        return articleParagraphs.size();
    }

    public String getFirstParagraphText() {
        if (!articleParagraphs.isEmpty()) {
            return getText(articleParagraphs.get(0));
        }
        return "";
    }

    public boolean hasLanguageLinks() {
        return !languageLinks.isEmpty();
    }

    public int getLanguageLinksCount() {
        return languageLinks.size();
    }

    public HomePage clickLogo() {
        click(wikipediaLogo);
        return new HomePage(driver);
    }
}
