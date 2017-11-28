package core.wrapper.thinksformelement;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InterviewThinksElement extends ThinksElementWrapper {
    private static By INPUT_QUESTION_PLACEHOLDER = By.cssSelector(".itx.itx__placeholder");
    private static By INPUT_QUESTION_TEXTAREA = By.cssSelector(".itx.posting-form_poll__question");

    private static By INPUT_ANSWERS_PLACEHOLDER = By.cssSelector(".it.it__placeholder");
    private static By INPUT_ANSWERS_TEXT = By.cssSelector(".it.posting-form_poll__answer");

    public InterviewThinksElement(WebDriver driver, By by) {
        super(driver, by);
    }

    @Override
    public void check() {
        Assert.assertNotNull("Плэйсхолдер текстареи не найден", findElementInWrapper(INPUT_QUESTION_PLACEHOLDER));
        Assert.assertNotNull("Текстарея не найдена", findElementInWrapper(INPUT_QUESTION_TEXTAREA));
    }

    public void typeQuestion(String text){
        WebElement textareaPlaceholder = findElementInWrapper(INPUT_QUESTION_PLACEHOLDER);
        WebElement textarea = findElementInWrapper(INPUT_QUESTION_TEXTAREA);
        Assert.assertNotNull("Плэйсхолдер текстареи не найден", textareaPlaceholder);
        Assert.assertNotNull("Текстарея не найдена", textarea);
        textareaPlaceholder.sendKeys("");
        textarea.sendKeys(text);
    }

    public InterviewThinksElement typeAnswer(int index, String answer){
        List<WebElement> inputAnswersPlaceholder = wrapper.findElements(INPUT_ANSWERS_PLACEHOLDER);
        List<WebElement> inputAnswersText = wrapper.findElements(INPUT_ANSWERS_TEXT);
        Assert.assertTrue("Нет такого числа плейсхолдеров инпутов для ответа в опросе", inputAnswersPlaceholder.size() > index);
        Assert.assertTrue("Нет такого числа инпутов для ответа в опросе", inputAnswersText.size() > index);
        inputAnswersPlaceholder.get(index).click();
        inputAnswersText.get(index).sendKeys(answer);
        return this;
    }
}
