package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.page.OneGroupPage;
import core.page.PageBase;

/**
 * Промис создания группы
 * Кнопка создания группы кликабельна, заранее невозможно определить результат ее нажатия
 */
public class CreatingGroupPromise {
    private PageBase pageHelper;
    private WebDriver driver;
    private By INPUT_FORM_WRAPPER = By.xpath(".//*[@id='field_name']/ancestor::*[contains(@class, 'form_i')]");

    public CreatingGroupPromise(WebDriver driver, PageBase pageHelper) {
        this.driver = driver;
        this.pageHelper = pageHelper;
    }

    /**
     * Если поле ввода названия пустое, то кнопка не нажмется, появится ошибка
     */
    public void emptyNameError(){
        boolean hasErrorClass = new WebDriverWait(driver, 1)
            .until((webDriver) -> pageHelper.hasClass(driver.findElement(INPUT_FORM_WRAPPER), "form_i__error"));
        Assert.assertTrue("Название не является ошибочным", hasErrorClass);
    }

    /**
     * Успешное создание группы проверяется в момент создания экземпляра страницы
     *
     * @return экземпляр созданной группы
     */
    public OneGroupPage successCreating(){
        return new OneGroupPage(driver);
    }
}
