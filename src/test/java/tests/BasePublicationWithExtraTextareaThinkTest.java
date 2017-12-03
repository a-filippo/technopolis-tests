package tests;

import org.junit.Assert;
import org.junit.Test;

import core.page.SessionPage;
import core.page.ThinksLayer;
import core.page.UserMainPage;
import core.wrapper.OneFeedWrapper;
import model.TechnopolisBot;

public class BasePublicationWithExtraTextareaThinkTest extends TestBase {

    @Test
    public void basePublicationWithExtraTextarea(){
        UserMainPage userMainPage = new SessionPage(driver)
            .doLogin(new TechnopolisBot());

        ThinksLayer thinksLayer = userMainPage.clickOpenThink()
            .writeMainText("Земля вовсе не круглая");

        thinksLayer.addTextarea()
            .type("И не квадратная");

        thinksLayer
            .submit()
            .checkLayerUnvisible();

        OneFeedWrapper lastFeed = userMainPage.getLastFeed();
        Assert.assertEquals("Земля вовсе не круглая\nИ не квадратная", lastFeed.getText());
    }
}
