package core.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Страница группы
 */
public class OneGroupPage extends PageBase {
    private static final By GROUP_NAME_H1 = By.cssSelector("h1.mctc_name_tx");
    private static final By TOP_CARD_BLOCK = By.id("hook_Block_MiddleColumnTopCardAltGroup");

    public OneGroupPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void check() {
        checkExistElement("Название группы не найдено", GROUP_NAME_H1);
        checkExistElement("Один из важных элементов групп не найден", TOP_CARD_BLOCK);
    }

    public String getGroupName(){
        return driver.findElement(GROUP_NAME_H1).getText();
    }
}
