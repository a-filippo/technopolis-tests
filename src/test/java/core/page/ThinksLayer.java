package core.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.wrapper.thinksformelement.InterviewThinksElement;
import core.wrapper.thinksformelement.TextThinksElement;
import core.wrapper.ThinksSelectPlaceWrapper;

public class ThinksLayer extends PageBase {
    private static final By MAIN_LAYER = By.id("mtLayerMain");
    private static final By MAIN_TEXTAREA = By.id("posting_form_text_field");
    private static final By CLOSE_ELEMENT = By.cssSelector(".media-layer_close");
    private static final By BUTTON_SUBMIT = By.cssSelector("#mtLayerMain .form-actions input[type='submit']");

    private static final By BUTTON_ADD_TEXT = By.id("opentext");
    private static final By BUTTON_ADD_INTERVIEW = By.id("openpoll");

    private static final By BUTTON_SELECT_PLACE = By.cssSelector(".tico.lp");
    private static final By SELECT_PLACE_BLOCK = By.cssSelector(".pform_map");

    private static final By LAST_THINKS_FORM_ELEMENT = By.cssSelector(".posting-form_sctn_w:last-child");

    public ThinksLayer(WebDriver driver) {
        super(driver);
    }

    @Override
    public void check() {
        new WebDriverWait(driver, 10)
            .until(ExpectedConditions.visibilityOfElementLocated(MAIN_LAYER));

        checkVisibilityElement("Леер невидим", MAIN_LAYER);
        checkVisibilityElement("Текстарея невидима", MAIN_TEXTAREA);
        checkVisibilityElement("Кнопка закрыть невидима", CLOSE_ELEMENT);
    }

    public void close(){
        checkVisibilityElement("Кнопка закрыть невидима", CLOSE_ELEMENT);
        click(CLOSE_ELEMENT);
        checkLayerUnvisible();
    }

    public ThinksLayer writeMainText(String text){
        checkVisibilityElement("Текстарея невидима", MAIN_TEXTAREA);
        type(MAIN_TEXTAREA, text);
        return this;
    }

    public ThinksLayer submit(){
        checkVisibilityElement("Кнопка \"Поделиться\" невидима", BUTTON_SUBMIT);
        Assert.assertFalse("Кнопка \"Поделиться\" неактивна", hasClass(driver.findElement(BUTTON_SUBMIT), "__disabled"));
        checkVisibilityElement("Леер невидим", MAIN_LAYER);
        click(BUTTON_SUBMIT);
        new WebDriverWait(driver, 3)
            .until(ExpectedConditions.invisibilityOfElementLocated(MAIN_LAYER));
        checkLayerUnvisible();
        return this;
    }

    public TextThinksElement addTextarea(){
        checkVisibilityElement("Элемент добавления текстареи не найден", BUTTON_ADD_TEXT);
        click(BUTTON_ADD_TEXT);
        WebElement textThinksElement = findElement(LAST_THINKS_FORM_ELEMENT);
        checkPresentElement("Элемент формы не найден", textThinksElement);
        return new TextThinksElement(driver, textThinksElement);
    }

    public InterviewThinksElement addInterview(){
        checkVisibilityElement("Элемент добавления опроса не найден", BUTTON_ADD_INTERVIEW);
        click(BUTTON_ADD_INTERVIEW);
        WebElement interviewThinksElement = findElement(LAST_THINKS_FORM_ELEMENT);
        checkPresentElement("Элемент формы не найден", interviewThinksElement);
        return new InterviewThinksElement(driver, interviewThinksElement);
    }

    public ThinksSelectPlaceWrapper selectPlace(){
        checkVisibilityElement("Элемент добавления места не найден", BUTTON_SELECT_PLACE);
        click(BUTTON_SELECT_PLACE);
        WebElement selectPlaceBlock = findElement(SELECT_PLACE_BLOCK);
        checkPresentElement("Элемент формы не найден", selectPlaceBlock);
        return new ThinksSelectPlaceWrapper(driver, selectPlaceBlock);
    }

    private void checkLayerUnvisible() {
        checkUnvisibilityElement("Леер не закрылся", MAIN_LAYER);
    }
}
