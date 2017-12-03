package tests;

import org.junit.Assert;
import org.junit.Test;

import core.page.SessionPage;
import core.page.UserMainPage;
import core.wrapper.OneFeedWrapper;
import model.TechnopolisBot;

public class BasePublicationThinkTest extends TestBase {

    @Test
    public void basePublication(){
        UserMainPage userMainPage = new SessionPage(driver)
            .doLogin(new TechnopolisBot());

        userMainPage.clickOpenThink()
            .writeMainText("Базовая публикация")
            .submit()
            .checkLayerUnvisible();

        OneFeedWrapper lastFeed = userMainPage.getLastFeed();
        Assert.assertEquals("Базовая публикация", lastFeed.getText());
    }
}
