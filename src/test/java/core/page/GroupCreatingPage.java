package core.page;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Страница создания группы
 */
public class GroupCreatingPage extends PageBase {
    private static final By CREATE_NEW_GROUP = By.xpath(".//*[contains(@href, 'st.layer.cmd=PopLayerCreateAltGroup')]");

    public GroupCreatingPage(WebDriver driver) {
         super(driver);
    }

    @Override
    public void check() {
        boolean createNewGroupIsset = new WebDriverWait(driver, 10).until(driver -> isElementPresent(CREATE_NEW_GROUP));

        Assert.assertTrue("Кнопка создания групп не найдена", createNewGroupIsset);
        checkExistElement("Инпут поиска групп не найден", By.id("query_userAltGroupSearch"));
    }

    public GroupCreatingPage clickCreateButton() {
        click(By.id("hook_FormButton_button_create"));
        return this;
    }

    public GroupCreatingPage typeGroupName(String groupName) {
        type(By.id("field_name"), groupName);
        return this;
    }

    public GroupCreatingPage clickInterestGroup() {
        click(By.xpath("//*[contains(@class,'create-group-dialog_img __interest')]"));
        return this;
    }

    public GroupCreatingPage clickCreateGroup() {
        By locator = By.xpath(".//*[contains(@href,'st.layer.cmd=PopLayerCreateAltGroup')]");
        checkExistElement("Кнопка создания групп не найдена", locator);
        click(locator);
        return this;
    }
}