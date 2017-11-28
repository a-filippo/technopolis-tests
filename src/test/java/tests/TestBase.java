package tests;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.fail;

abstract public class TestBase {

    protected ChromeDriver driver;

    private void init() {
        driver = new ChromeDriver();
        driver.manage().window().setSize(new Dimension(1440, 900));
        String baseUrl = "https://ok.ru/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get(baseUrl);
    }

    private void stop() {
        driver.quit();
    }

    @Before
    public void setUp() throws Exception {
        init();
    }

    @After
    public void tearDown() throws Exception {
        stop();
    }

}
