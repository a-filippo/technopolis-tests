package core.page;

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

    private static final By LAST_FEED = By.cssSelector(".feed-list .feed:first-child");

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void check() {
        checkPresentElement("Средняя колонка карточки пользователя не найдена", MIDDLE_COLUMN_TOP_CARD);
        checkPresentElement("Левый блок пользователя не найден", LEFT_USER_TOPCARD);
        checkPresentElement("Форма постинга не найдена", POSTING_THINKS_BLOCK);
    }

    public void clickOpenThink(){
        checkPresentElement("Блок постинга не найден", POSTING_THINKS_INPUT);
        click(POSTING_THINKS_INPUT);
    }

    public OneFeedWrapper getLastFeed(){
        WebElement oneFeed = findElement(LAST_FEED);
        Assert.assertNotNull("Запись не найдена", oneFeed);
        return new OneFeedWrapper(driver, oneFeed);
    }
}
