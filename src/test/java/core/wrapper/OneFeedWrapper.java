package core.wrapper;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OneFeedWrapper extends WrapperBase {
    private static final By USER_NAME = By.cssSelector(".user-link");
    private static final By FEED_TEXT = By.cssSelector(".media-text_cnt_tx");

    private static final By INTERVIEW_WRAPPER = By.cssSelector(".poll_lst");
    private static final By INTERVIEW_ANSWERS = By.cssSelector(".poll_n_tx");

    private static final By PLACE = By.cssSelector("a[class*='place-id-'][hrefattrs*='cmd=PopLayerMediaTopic']");

    public OneFeedWrapper(WebDriver driver, WebElement wrapper) {
        super(driver, wrapper);
    }

    @Override
    public void check() {
        checkPresentElement("Имя пользователя не найдено", USER_NAME);
        checkPresentElement("Блок с текстом не найден", FEED_TEXT);
    }

    public String getText(){
        return findElement(FEED_TEXT).getText();
    }

    public String[] getInterviewAnswers(){
        WebElement interviewWrapper = findElement(INTERVIEW_WRAPPER);
        checkPresentElement("Блок опроса не найден", interviewWrapper);

        List<WebElement> interviewAnswers = interviewWrapper.findElements(INTERVIEW_ANSWERS);
        String[] interviewAnswersText = new String[interviewAnswers.size()];
        for (int i = 0; i < interviewAnswers.size(); i++){
            interviewAnswersText[i] = interviewAnswers.get(i).getText();
        }
        return interviewAnswersText;
    }

    public String getPlace(){
        WebElement place = findElement(PLACE);
        checkPresentElement("Геопозиция к посту не приложена", place);
        return place.getText();
    }
}
