package core.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import model.TestBot;

/**
 * Страница авторизации
 */
public class SessionPage extends PageBase {
    private static final By FIELD_EMAIL = By.id("field_email");
    private static final By FIELD_PASSWORD = By.id("field_password");
    private static final By BUTTON_SUBMIT = By.cssSelector(".form-actions input.button-pro[data-l*='loginButton']");

    public SessionPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void check() {
        waitPresentElement("Поле ввода емэйла не найдено",  FIELD_EMAIL, 2);
        waitPresentElement("Поле ввода пароля не найдено",  FIELD_PASSWORD, 2);
        waitPresentElement("Кнопка отправки формы не найдена",  BUTTON_SUBMIT, 2);
    }

    public UserMainPage doLogin(TestBot testBot) {
        checkVisibilityElement("Поле ввода емэйла не найдено",  FIELD_EMAIL);
        type(FIELD_EMAIL, testBot.getLogin());

        checkVisibilityElement("Поле ввода пароля не найдено",  FIELD_PASSWORD);
        type(FIELD_PASSWORD, testBot.getPassword());

        checkVisibilityElement("Кнопка отправки формы не найдена",  BUTTON_SUBMIT);
        click(BUTTON_SUBMIT);

        return new UserMainPage(driver);
    }
}
