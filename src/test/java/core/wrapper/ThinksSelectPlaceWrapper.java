package core.wrapper;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ThinksSelectPlaceWrapper extends WrapperBase {
    private static final By INPUT_ADDRESS = By.cssSelector(".pform_map_search .it.search-input_it");
    private static final By CONTAINER_MAP = By.cssSelector(".pform_map_search .search-input");
    private static final By SUGGESTS = By.cssSelector(".pform_map_cnt .suggest .suggest_li");
    private static final By SUGGEST_TEXT = By.cssSelector(".ucard_info_name");


    public ThinksSelectPlaceWrapper(WebDriver driver, WebElement wrapper){
        super(driver, wrapper);
    }

    @Override
    public void check() {
        checkPresentElement("Инпут ввода адреса не найден", INPUT_ADDRESS);
        checkPresentElement("Контейнер карты не найден", CONTAINER_MAP);
    }

    public ThinksSelectPlaceWrapper typeAddress(String address){
        WebElement inputAddress = findElement(INPUT_ADDRESS);
        checkVisibilityElement("Инпут ввода адреса не найден", inputAddress);
        inputAddress.sendKeys(address);
        return this;
    }

    public ThinksSelectPlaceWrapper waitResultsLoading(){
        WebElement mapContainer = findElement(CONTAINER_MAP);
        checkVisibilityElement("Блок с данными адресов не найден", mapContainer);

        boolean loaded = new WebDriverWait(driver, 7)
                .until((webDriver) -> hasClass(mapContainer, "search-input_searching"));

        Assert.assertTrue("Элементы не загрузились", loaded);
        return this;
    }

    /**
     *
     * @return - Название выбранной точки
     */
    public String clickFirstResult(){
        for (WebElement suggest : findElements(SUGGESTS)){
            if (!hasClass(suggest, "invisible")){
                String name = findElement(suggest, SUGGEST_TEXT).getText();
                suggest.click();
                return name;
            }
        }
        Assert.fail("Результатов не найдено");
        return null;
    }
}
