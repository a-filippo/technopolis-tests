package core;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract public class ContextBase {
    protected SearchContext searchContext;
    protected WebDriver driver;

    public ContextBase(WebDriver driver, SearchContext searchContext) {
        this.searchContext = searchContext;
        this.driver = driver;
        check();
    }

    protected WebElement findElement(By by){
        return findElement(searchContext, by);
    }

    protected WebElement findElement(SearchContext searchContext, By by){
        try {
            return searchContext.findElement(by);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    protected List<WebElement> findElements(By by){
        return findElements(searchContext, by);
    }

    private List<WebElement> findElements(SearchContext searchContext, By by) {
        return searchContext.findElements(by);
    }

    public void type(By by, String text){
        type(by, text, false);
    }

    public void type(By by, String text, boolean append){
        if (!append){
            searchContext.findElement(by).clear();
        }
        searchContext.findElement(by).sendKeys(text);
    }

    public void click(By by){
        searchContext.findElement(by).click();
    }

    public boolean isElementPresent(By by) {
        try {
            searchContext.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementVisible(By by) {
        try {
            return searchContext.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected void checkPresentElement(String messageIfNone, By locator){
        Assert.assertTrue(messageIfNone, isElementPresent(locator));
    }

    protected void checkPresentElement(String messageIfNone, WebElement webElement){
        Assert.assertNotNull(messageIfNone, webElement);
    }

    protected void checkVisibilityElement(String messageIfUnvisible, By locator){
        Assert.assertTrue(messageIfUnvisible, isElementVisible(locator));
    }

    protected void checkVisibilityElement(String messageIfUnvisible, WebElement webElement){
        Assert.assertTrue(messageIfUnvisible, webElement != null && webElement.isDisplayed());
    }

    protected void checkUnvisibilityElement(String messageIfVisible, By locator){
        Assert.assertFalse(messageIfVisible, isElementVisible(locator));
    }

    protected void waitPresentElement(String messageIfNone, By by, int seconds){
        Assert.assertTrue(
            messageIfNone,
            (new WebDriverWait(driver, seconds)
                .until(webDriver -> isElementPresent(by)))
        );
    }

    protected void waitVisibilityElement(String messageIfNone, WebElement webElement, int seconds){
        Assert.assertNotNull(
            messageIfNone,
            (new WebDriverWait(driver, seconds)
                .until(ExpectedConditions.visibilityOf(webElement)))
        );
    }

    protected void waitVisibilityElement(String messageIfNone, By by, int seconds){
        waitVisibilityElement(messageIfNone, findElement(by), seconds);
    }

    public boolean hasClass(WebElement element, String searchingClass){
        String classes = element.getAttribute("class");
        for (String c : classes.split("\\s+")) {
            if (c.equals(searchingClass)) {
                return true;
            }
        }

        return false;
    }

    public abstract void check();
}
