package core.wrapper.thinksformelement;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InterviewThinksElement extends ThinksFormElementWrapper {
    private static final By INPUT_QUESTION_PLACEHOLDER = By.cssSelector(".itx.itx__placeholder");
    private static final By INPUT_QUESTION_TEXTAREA = By.cssSelector(".itx.posting-form_poll__question");

    private static final By INPUT_ANSWERS_PLACEHOLDER = By.cssSelector(".it.it__placeholder");
    private static final By INPUT_ANSWERS_TEXT = By.cssSelector(".it.posting-form_poll__answer");

    public InterviewThinksElement(WebDriver driver, WebElement wrapper) {
        super(driver, wrapper);
    }

    @Override
    public void check() {
        checkPresentElement("Плэйсхолдер текстареи не найден", INPUT_QUESTION_PLACEHOLDER);
        checkPresentElement("Текстарея не найдена", INPUT_QUESTION_TEXTAREA);
    }

    public InterviewThinksElement typeAnswer(int index, String answer){
        List<WebElement> inputAnswersPlaceholder = findElements(INPUT_ANSWERS_PLACEHOLDER);
        List<WebElement> inputAnswersText = findElements(INPUT_ANSWERS_TEXT);

        Assert.assertTrue("Нет такого числа плейсхолдеров инпутов для ответа в опросе", inputAnswersPlaceholder.size() > index);
        Assert.assertTrue("Нет такого числа инпутов для ответа в опросе", inputAnswersText.size() > index);

        WebElement inputAnswerPlaceholder = inputAnswersPlaceholder.get(index);
        WebElement inputAnswerText = inputAnswersText.get(index);

        checkVisibilityElement("Плейсхолдер ответа интервью не найден", inputAnswerPlaceholder);
        inputAnswerPlaceholder.click();

        checkVisibilityElement("Инпут ответа интервью не найден", inputAnswerText);
        inputAnswerText.sendKeys(answer);

        return this;
    }
}
