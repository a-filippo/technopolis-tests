package core.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

abstract public class PageBase {
    protected WebDriver driver;

    public PageBase(WebDriver driver) {
        this.driver = driver;
        check();
    }

    protected PageBase(){

    }

    protected WebElement findElement(By by){
        return findElement(driver, by);
    }

    protected WebElement findElement(SearchContext searchContext, By by){
        try {
            return searchContext.findElement(by);
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public void type(By by, String text){
        type(by, text, false);
    }

    public void type(By by, String text, boolean append){
        if (!append){
            driver.findElement(by).clear();
        }
        driver.findElement(by).sendKeys(text);
    }

    public void click(By by){
        driver.findElement(by).click();
    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public boolean isElementVisible(By by) {
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected void checkExistElement(String messageIfNone, By locator){
        Assert.assertTrue(messageIfNone, isElementPresent(locator));
    }

    protected void checkNoExistElement(String messageIfIs, By locator){
        Assert.assertFalse(messageIfIs, isElementPresent(locator));
    }

    protected void checkVisibilityElement(String messageIfUnvisible, By locator){
        Assert.assertTrue(messageIfUnvisible, isElementVisible(locator));
    }

    protected void checkUnvisibilityElement(String messageIfVisible, By locator){
        Assert.assertFalse(messageIfVisible, isElementVisible(locator));
    }

    public void markCheckbox(By by, boolean mark){
        if (driver.findElement(by).isSelected() != mark){
            click(by);
        }
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
