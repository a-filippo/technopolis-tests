package tests;

import org.junit.Test;

import core.page.SessionPage;
import core.page.UserMainPage;
import model.TechnopolisBot;

public class OpenCloseThinkTest extends TestBase {
    @Test
    public void openCloseThink(){
        UserMainPage userMainPage = new SessionPage(driver)
            .doLogin(new TechnopolisBot());

        userMainPage.clickOpenThink()
            .close()
            .checkLayerUnvisible();
    }
}
