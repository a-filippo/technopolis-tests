package core.wrapper.thinksformelement;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.wrapper.thinksformelement.ThinksElementWrapper;

public class TextThinksElement extends ThinksElementWrapper {
    private static final By TEXTAREA = By.cssSelector(".posting-form_sctn__text .textWrap");

    public TextThinksElement(WebDriver driver, By by) {
        super(driver, by);
    }

    @Override
    public void check() {
        Assert.assertNotNull("Текстарея не найдена", findElementInWrapper(TEXTAREA));
    }

    public void type(String text){
        WebElement textarea = findElementInWrapper(TEXTAREA);
        Assert.assertNotNull("Текстарея не найдена", textarea);
        textarea.sendKeys(text);
    }
}
