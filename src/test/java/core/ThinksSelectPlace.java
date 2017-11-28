package core;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import core.page.PageBase;

public class ThinksSelectPlace extends PageBase {
    private WebElement container;

    private static By INPUT_ADDRESS = By.cssSelector(".pform_map_search .it.search-input_it");
    private static By CONTAINER_MAP = By.cssSelector(".pform_map_search .search-input");
    private static By SUGGESTS = By.cssSelector(".pform_map_cnt .suggest .suggest_li");
    private static By SUGGEST_TEXT = By.cssSelector(".ucard_info_name");


    public ThinksSelectPlace(WebDriver driver, By by){
        this.driver = driver;
        this.container = findElement(by);
        check();
    }

    @Override
    public void check() {
        Assert.assertNotNull("Инпут ввода адреса не найден", container.findElement(INPUT_ADDRESS));
    }

    public ThinksSelectPlace typeAddress(String address){
        Assert.assertNotNull("Инпут ввода адреса не найден", container.findElement(INPUT_ADDRESS));
        container.findElement(INPUT_ADDRESS).sendKeys(address);
        return this;
    }

    public ThinksSelectPlace waitResultsLoading(){
        Assert.assertNotNull("Инпут ввода адреса не найден", container.findElement(INPUT_ADDRESS));

        boolean loaded = new WebDriverWait(driver, 7)
                .until((webDriver) -> this.hasClass(container.findElement(CONTAINER_MAP), "search-input_searching"));

        Assert.assertTrue("Элементы не загрузились", loaded);
        return this;
    }

    /**
     *
     * @return - Название выбранной точки
     */
    public String clickFirstResult(){
        for (WebElement suggest : container.findElements(SUGGESTS)){
            if (!hasClass(suggest, "invisible")){
                String name = suggest.findElement(SUGGEST_TEXT).getText();
                suggest.click();
                return name;
            }
        }
        Assert.fail("Результатов не найдено");
        return null;
    }
}
