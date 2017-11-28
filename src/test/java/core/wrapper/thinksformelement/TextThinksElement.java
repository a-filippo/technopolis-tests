package core.wrapper.thinksformelement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextThinksElement extends ThinksFormElementWrapper {
    private static final By TEXTAREA = By.cssSelector(".posting-form_sctn__text .textWrap");

    public TextThinksElement(WebDriver driver, WebElement wrapper) {
        super(driver, wrapper);
    }

    @Override
    public void check() {
        checkPresentElement("Текстарея не найдена", TEXTAREA);
    }

    public void type(String text){
        WebElement textarea = findElement(TEXTAREA);
        checkPresentElement("Текстарея не найдена", textarea);
        textarea.sendKeys(text);
    }
}
