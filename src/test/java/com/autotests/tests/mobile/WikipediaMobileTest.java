package com.autotests.tests.mobile;

import com.autotests.pages.mobile.ArticleScreen;
import com.autotests.pages.mobile.MainScreen;
import com.autotests.pages.mobile.SearchScreen;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class WikipediaMobileTest extends BaseMobileTest {

    private MainScreen mainScreen;

    @BeforeMethod
    public void initScreen() {
        mainScreen = new MainScreen(driver);
        mainScreen.skipOnboarding();
    }

    @Test(priority = 1, description = "Verify main screen elements are displayed")
    public void testMainScreenElements() {
        Assert.assertTrue(mainScreen.isSearchContainerDisplayed(),
                "Search container should be displayed on main screen");
    }

    @Test(priority = 2, description = "Verify search functionality")
    public void testSearchFunctionality() {
        SearchScreen searchScreen = mainScreen.tapSearchContainer();
        searchScreen.enterSearchQuery("Java");

        Assert.assertTrue(searchScreen.hasSearchResults(),
                "Search should return results for 'Java'");
    }

    @Test(priority = 3, description = "Verify article opens after search")
    public void testOpenArticleFromSearch() {
        SearchScreen searchScreen = mainScreen.tapSearchContainer();
        searchScreen.enterSearchQuery("Python programming language");

        Assert.assertTrue(searchScreen.hasSearchResults(),
                "Search should return results");

        ArticleScreen articleScreen = searchScreen.tapFirstResult();

        Assert.assertTrue(articleScreen.isArticleWebViewDisplayed(),
                "Article WebView should be displayed");
    }

    @Test(priority = 4, description = "Verify article scroll functionality")
    public void testArticleScroll() {
        SearchScreen searchScreen = mainScreen.tapSearchContainer();
        searchScreen.enterSearchQuery("Wikipedia");

        Assert.assertTrue(searchScreen.hasSearchResults(),
                "Search should return results");

        ArticleScreen articleScreen = searchScreen.tapFirstResult();

        Assert.assertTrue(articleScreen.isArticleWebViewDisplayed(),
                "Article should be displayed before scroll");

        articleScreen.scrollDown();
        articleScreen.scrollDown();

        Assert.assertTrue(articleScreen.isBackButtonDisplayed(),
                "Back button should still be visible after scroll");
    }

    @Test(priority = 5, description = "Verify search results count is positive")
    public void testSearchResultsCount() {
        SearchScreen searchScreen = mainScreen.tapSearchContainer();
        searchScreen.enterSearchQuery("Artificial Intelligence");

        Assert.assertTrue(searchScreen.hasSearchResults(),
                "Search should return results");

        int resultsCount = searchScreen.getResultsCount();
        Assert.assertTrue(resultsCount > 0,
                "Should have at least one search result");
    }

    @Test(priority = 6, description = "Verify first search result title is not empty")
    public void testFirstResultTitle() {
        SearchScreen searchScreen = mainScreen.tapSearchContainer();
        searchScreen.enterSearchQuery("Machine Learning");

        Assert.assertTrue(searchScreen.hasSearchResults(),
                "Search should return results");

        String firstResultTitle = searchScreen.getFirstResultTitle();
        Assert.assertFalse(firstResultTitle.isEmpty(),
                "First result title should not be empty");
    }

    @DataProvider(name = "mobileSearchQueries")
    public Object[][] mobileSearchQueries() {
        return new Object[][]{
                {"Java"},
                {"Android"},
                {"iPhone"}
        };
    }

    @Test(dataProvider = "mobileSearchQueries", priority = 7,
            description = "Verify search with different queries on mobile")
    public void testSearchWithDifferentQueries(String query) {
        SearchScreen searchScreen = mainScreen.tapSearchContainer();
        searchScreen.enterSearchQuery(query);

        Assert.assertTrue(searchScreen.hasSearchResults(),
                "Search for '" + query + "' should return results");

        searchScreen.goBack();
    }

    @Test(priority = 8, description = "Verify navigation back from article")
    public void testNavigationBackFromArticle() {
        SearchScreen searchScreen = mainScreen.tapSearchContainer();
        searchScreen.enterSearchQuery("Tesla");

        Assert.assertTrue(searchScreen.hasSearchResults(),
                "Search should return results");

        ArticleScreen articleScreen = searchScreen.tapFirstResult();
        Assert.assertTrue(articleScreen.isArticleWebViewDisplayed(),
                "Article should be displayed");

        articleScreen.goBack();

        Assert.assertTrue(searchScreen.hasSearchResults() || mainScreen.isSearchContainerDisplayed(),
                "Should return to search or main screen");
    }
}
