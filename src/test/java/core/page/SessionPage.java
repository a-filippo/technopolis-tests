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
        checkPresentElement("Поле ввода емэйла не найдено",  FIELD_EMAIL);
        checkPresentElement("Поле ввода пароля не найдено",  FIELD_PASSWORD);
        checkPresentElement("Кнопка отправки формы не найдена",  BUTTON_SUBMIT);
    }

    public void doLogin(TestBot testBot) {
        checkPresentElement("Поле ввода емэйла не найдено",  FIELD_EMAIL);
        type(FIELD_EMAIL, testBot.getLogin());

        checkPresentElement("Поле ввода пароля не найдено",  FIELD_PASSWORD);
        type(FIELD_PASSWORD, testBot.getPassword());

        checkPresentElement("Кнопка отправки формы не найдена",  BUTTON_SUBMIT);
        click(BUTTON_SUBMIT);
    }
}
