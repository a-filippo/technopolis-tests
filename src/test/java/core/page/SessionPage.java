package core.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import model.TestBot;

/**
 * Страница авторизации
 */
public class SessionPage extends PageBase {
    public SessionPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void check() {
        checkExistElement("Поле ввода емэйла не найдено",  By.id("field_email"));
        checkExistElement("Поле ввода пароля не найдено",  By.id("field_password"));
    }

    public void doLogin(TestBot testBot) {
        type(By.id("field_email"), testBot.getLogin());
        type(By.id("field_password"), testBot.getPassword());
        click(By.cssSelector("div.form-actions > div > input.button-pro"));
    }
}
