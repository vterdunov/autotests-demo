package com.autotests.tests.web;

import com.autotests.pages.web.ArticlePage;
import com.autotests.pages.web.HomePage;
import com.autotests.pages.web.SearchResultsPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WikipediaWebTest extends BaseWebTest {

    private HomePage homePage;

    @BeforeMethod
    public void initPage() {
        driver.get("https://www.wikipedia.org");
        homePage = new HomePage(driver);
    }

    @Test(description = "Verify Wikipedia logo is displayed on home page")
    public void testLogoIsDisplayed() {
        Assert.assertTrue(homePage.isLogoDisplayed(),
                "Wikipedia logo should be displayed on the home page");
    }

    @Test(description = "Verify central featured section is displayed")
    public void testCentralFeaturedSectionDisplayed() {
        Assert.assertTrue(homePage.isCentralFeaturedDisplayed(),
                "Central featured section should be displayed");
    }

    @Test(description = "Verify search input has placeholder text")
    public void testSearchInputPlaceholder() {
        String placeholder = homePage.getSearchInputPlaceholder();
        Assert.assertNotNull(placeholder, "Search input should have placeholder");
        Assert.assertFalse(placeholder.isEmpty(), "Placeholder should not be empty");
    }

    @Test(description = "Verify search functionality returns results")
    public void testSearchReturnsResults() {
        homePage.enterSearchQuery("Java programming");
        homePage.clickSearchButton();

        SearchResultsPage resultsPage = new SearchResultsPage(driver);

        Assert.assertTrue(resultsPage.hasResults() || resultsPage.isPageHeadingDisplayed(),
                "Search should return results or redirect to article");
    }

    @Test(description = "Verify article page opens after search")
    public void testArticlePageOpens() {
        SearchResultsPage resultsPage = homePage.searchFor("Albert Einstein");

        if (resultsPage.hasResults()) {
            ArticlePage articlePage = resultsPage.clickFirstResult();
            Assert.assertTrue(articlePage.isArticleTitleDisplayed(),
                    "Article title should be displayed");
        } else {
            Assert.assertTrue(resultsPage.isPageHeadingDisplayed(),
                    "Page heading should be displayed (direct article redirect)");
        }
    }

    @Test(description = "Verify article title matches search query")
    public void testArticleTitleMatchesSearch() {
        String searchQuery = "Python";
        SearchResultsPage resultsPage = homePage.searchFor(searchQuery);

        if (resultsPage.hasResults()) {
            ArticlePage articlePage = resultsPage.clickFirstResult();
            String articleTitle = articlePage.getArticleTitle().toLowerCase();
            Assert.assertTrue(articleTitle.contains(searchQuery.toLowerCase()),
                    "Article title should contain search query");
        } else {
            String pageHeading = resultsPage.getPageHeading().toLowerCase();
            Assert.assertTrue(pageHeading.contains(searchQuery.toLowerCase()),
                    "Page heading should contain search query");
        }
    }

    @Test(description = "Verify article content is displayed")
    public void testArticleContentDisplayed() {
        SearchResultsPage resultsPage = homePage.searchFor("Wikipedia");

        ArticlePage articlePage;
        if (resultsPage.hasResults()) {
            articlePage = resultsPage.clickFirstResult();
        } else {
            articlePage = new ArticlePage(driver);
        }

        Assert.assertTrue(articlePage.isContentDisplayed(),
                "Article content should be displayed");
    }

    @Test(description = "Verify search input can be cleared")
    public void testSearchInputClear() {
        homePage.enterSearchQuery("Test query");
        homePage.clearSearchInput();
        String inputValue = homePage.getSearchInputValue();
        Assert.assertTrue(inputValue == null || inputValue.isEmpty(),
                "Search input should be empty after clearing");
    }

    @DataProvider(name = "searchQueries")
    public Object[][] searchQueries() {
        return new Object[][]{
                {"Java"},
                {"Python"},
                {"Artificial Intelligence"}
        };
    }

    @Test(dataProvider = "searchQueries", description = "Verify search with different queries")
    public void testSearchWithDifferentQueries(String query) {
        SearchResultsPage resultsPage = homePage.searchFor(query);
        Assert.assertTrue(resultsPage.hasResults() || resultsPage.isPageHeadingDisplayed(),
                "Search for '" + query + "' should return results or article page");
    }

    @Test(description = "Verify page title contains Wikipedia")
    public void testPageTitleContainsWikipedia() {
        String title = homePage.getPageTitle();
        Assert.assertTrue(title.toLowerCase().contains("wikipedia"),
                "Page title should contain 'Wikipedia'");
    }
}
