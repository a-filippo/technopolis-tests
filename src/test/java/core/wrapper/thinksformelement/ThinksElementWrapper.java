package core.wrapper.thinksformelement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.page.PageBase;

public abstract class ThinksElementWrapper extends PageBase {
    protected WebElement wrapper;

    public ThinksElementWrapper(WebDriver driver, By by){
        this.driver = driver;
        this.wrapper = findElement(by);
        check();
    }

    protected WebElement findElementInWrapper(By by){
        return findElement(wrapper, by);
    }
}
