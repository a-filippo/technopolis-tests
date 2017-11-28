package core.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import core.wrapper.OneFeedWrapper;

/**
 * Главная страница пользователя
 */
public class UserMainPage extends PageBase {
    private static By FEEDS_BLOCK = By.cssSelector(".feed-list");
    private static By LAST_FEED = By.cssSelector(".feed-list .feed:first-child");

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void check() {
        checkExistElement("Форма постинга не найдена", By.id("hook_Block_PostingForm"));
    }

    public void clickGroupsOnToolbar() {
        click(By.xpath(".//*[contains(@hrefattrs, 'st.cmd=userAltGroup')]"));
    }

    public void clickOpenThink(){
        click(By.cssSelector("#hook_Block_PostingForm .input_placeholder"));
    }

    public OneFeedWrapper getLastFeed(){
        Assert.assertNotNull("Блок записей не найден", findElement(FEEDS_BLOCK));
        Assert.assertNotNull("Запись не найдена", findElement(LAST_FEED));
        return new OneFeedWrapper(driver, findElement(LAST_FEED));
    }
}
