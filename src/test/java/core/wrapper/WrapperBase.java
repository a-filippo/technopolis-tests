package core.wrapper;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.ContextBase;

abstract public class WrapperBase extends ContextBase {

    public WrapperBase(WebDriver driver, WebElement webElement) {
        super(driver, webElement);
    }
}
