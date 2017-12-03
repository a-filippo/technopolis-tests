package core.page;

import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import core.wrapper.OneFeedWrapper;

/**
 * Главная страница пользователя
 */
public class UserMainPage extends PageBase {
    private static final By MIDDLE_COLUMN_TOP_CARD = By.id("hook_Block_MiddleColumnTopCard");
    private static final By POSTING_THINKS_BLOCK = By.id("hook_Block_PostingForm");
    private static final By LEFT_USER_TOPCARD = By.id("hook_Block_LeftColumnTopCardUser");

    private static final By POSTING_THINKS_INPUT = By.cssSelector("#hook_Block_PostingForm .input_placeholder");

    private static final By FEEDS = By.cssSelector(".feed-list .feed");

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void check() {
        waitPresentElement("Средняя колонка карточки пользователя не найдена", MIDDLE_COLUMN_TOP_CARD, 3);
        waitPresentElement("Левый блок пользователя не найден", LEFT_USER_TOPCARD, 3);
        waitPresentElement("Форма постинга не найдена", POSTING_THINKS_BLOCK, 3);
    }

    public ThinksLayer clickOpenThink(){
        checkVisibilityElement("Блок постинга не найден", POSTING_THINKS_INPUT);
        click(POSTING_THINKS_INPUT);
        return new ThinksLayer(driver);
    }

    public OneFeedWrapper getLastFeed(){
        List<WebElement> oneFeed = findElements(FEEDS);
        Assert.assertTrue("Запись не найдена", !oneFeed.isEmpty());
        return new OneFeedWrapper(driver, oneFeed.get(0));
    }
}
