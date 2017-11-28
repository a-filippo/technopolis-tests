package core.wrapper;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.page.PageBase;

public class OneFeedWrapper extends PageBase {
    private WebElement wrapper;
    private static By USER_NAME = By.cssSelector(".user-link");
    private static By FEED_TEXT = By.cssSelector(".media-text_cnt_tx");

    private static By INTERVIEW_WRAPPER = By.cssSelector(".poll_lst");
    private static By INTERVIEW_ANSWERS = By.cssSelector(".poll_n_tx");

    private static By PLACE = By.cssSelector("a[class*='place-id-'][hrefattrs*='cmd=PopLayerMediaTopic']");

    public OneFeedWrapper(WebDriver driver, WebElement wrapper) {
        this.driver = driver;
        this.wrapper = wrapper;
        check();
    }

    @Override
    public void check() {
        Assert.assertNotNull("Имя пользователя не найдено", findElement(wrapper, USER_NAME));
        Assert.assertNotNull("Блок с текстом не найден", findElement(wrapper, FEED_TEXT));
    }

    public String getText(){
        return findElement(wrapper, FEED_TEXT).getText();
    }

    public String[] getInterviewAnswers(){
        WebElement interviewWrapper = findElement(wrapper, INTERVIEW_WRAPPER);
        Assert.assertNotNull("Блок опроса не найден", interviewWrapper);

        List<WebElement> interviewAnswers = interviewWrapper.findElements(INTERVIEW_ANSWERS);
        String[] interviewAnswersText = new String[interviewAnswers.size()];
        for (int i = 0; i < interviewAnswers.size(); i++){
            interviewAnswersText[i] = interviewAnswers.get(i).getText();
        }
        return interviewAnswersText;
    }

    public String getPlace(){
        WebElement place = findElement(wrapper, PLACE);
        Assert.assertNotNull("Геопозиция к посту не приложена", place);
        return place.getText();
    }
}
