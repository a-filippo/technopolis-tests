package core.page;

import org.openqa.selenium.WebDriver;

import core.ContextBase;

abstract public class PageBase extends ContextBase {

    public PageBase(WebDriver driver) {
        super(driver, driver);
    }
}
