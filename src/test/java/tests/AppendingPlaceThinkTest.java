package tests;

import org.junit.Assert;
import org.junit.Test;

import core.wrapper.OneFeedWrapper;
import core.page.SessionPage;
import core.page.ThinksLayer;
import core.wrapper.ThinksSelectPlaceWrapper;
import core.page.UserMainPage;
import model.TechnopolisBot;

public class AppendingPlaceThinkTest extends TestBase {

    @Test
    public void appendingPlace(){
        UserMainPage userMainPage = new SessionPage(driver)
            .doLogin(new TechnopolisBot());

        ThinksLayer thinksLayer = userMainPage.clickOpenThink()
            .writeMainText("Земля вовсе не круглая (докажем картой)");

        ThinksSelectPlaceWrapper selectPlace = thinksLayer.selectPlace()
            .typeAddress("СПбПУ")
            .waitResultsLoading();

        String addressName = selectPlace.clickFirstResult();

        thinksLayer
            .submit()
            .checkLayerUnvisible();

        OneFeedWrapper lastFeed = userMainPage.getLastFeed();
        Assert.assertEquals("Земля вовсе не круглая (докажем картой)", lastFeed.getText());
        Assert.assertEquals(addressName, lastFeed.getPlace());
    }
}
