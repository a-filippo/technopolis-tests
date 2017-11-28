package core.wrapper.thinksformelement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.wrapper.WrapperBase;

public abstract class ThinksFormElementWrapper extends WrapperBase {
    public ThinksFormElementWrapper(WebDriver driver, WebElement wrapper){
        super(driver, wrapper);
    }
}
