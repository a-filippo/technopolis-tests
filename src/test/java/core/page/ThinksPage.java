package core.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.wrapper.thinksformelement.InterviewThinksElement;
import core.wrapper.thinksformelement.TextThinksElement;
import core.ThinksSelectPlace;

public class ThinksPage extends PageBase {
    private static final By MAIN_LAYER = By.id("mtLayerMain");
    private static final By MAIN_TEXTAREA = By.id("posting_form_text_field");
    private static final By CLOSE_ELEMENT = By.cssSelector(".media-layer_close");
    private static final By CHECKBOX_TO_STATUS = By.cssSelector(".posting-form_ac-status");
    private static final By BUTTON_SUBMIT = By.cssSelector("#mtLayerMain .form-actions input[type='submit']");
    private static final By BUTTON_ADD_TEXT = By.id("opentext");
    private static final By BUTTON_ADD_INTERVIEW = By.id("openpoll");

    private static final By BUTTON_SELECT_PLACE = By.cssSelector(".tico.lp");
    private static final By SELECT_PLACE_BLOCK = By.cssSelector(".pform_map");

    private static final By LAST_THINKS_FORM_ELEMENT = By.cssSelector(".posting-form_sctn_w:last-child");

    public ThinksPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void check() {
        new WebDriverWait(driver, 10)
            .until(ExpectedConditions.visibilityOfElementLocated(MAIN_LAYER));
    }

    public void close(){
        click(CLOSE_ELEMENT);
        checkLayerUnvisible();
    }

    public ThinksPage writeMainText(String text){
        type(MAIN_TEXTAREA, text);
        return this;
    }

    public ThinksPage submit(){
        checkVisibilityElement("Кнопка \"Поделиться\" невидима", BUTTON_SUBMIT);
        Assert.assertFalse("Кнопка \"Поделиться\" неактивна", hasClass(driver.findElement(BUTTON_SUBMIT), "__disabled"));
        click(BUTTON_SUBMIT);
        new WebDriverWait(driver, 3)
            .until(ExpectedConditions.invisibilityOfElementLocated(MAIN_LAYER));
        checkLayerUnvisible();
        return this;
    }

    public TextThinksElement addTextarea(){
        checkVisibilityElement("Элемент добавления текстареи не найден", BUTTON_ADD_TEXT);
        click(BUTTON_ADD_TEXT);
        return new TextThinksElement(driver, LAST_THINKS_FORM_ELEMENT);
    }

    public InterviewThinksElement addInterview(){
        checkVisibilityElement("Элемент добавления опроса не найден", BUTTON_ADD_INTERVIEW);
        click(BUTTON_ADD_INTERVIEW);
        return new InterviewThinksElement(driver, LAST_THINKS_FORM_ELEMENT);
    }

    public ThinksSelectPlace selectPlace(){
        checkVisibilityElement("Элемент добавления места не найден", BUTTON_SELECT_PLACE);
        click(BUTTON_SELECT_PLACE);
        return new ThinksSelectPlace(driver, SELECT_PLACE_BLOCK);
    }

    private void checkLayerUnvisible() {
        checkUnvisibilityElement("Леер не закрылся", MAIN_LAYER);
    }
}
