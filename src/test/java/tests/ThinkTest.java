package tests;

import org.junit.Assert;
import org.junit.Test;

import core.wrapper.OneFeedWrapper;
import core.page.SessionPage;
import core.page.ThinksLayer;
import core.wrapper.ThinksSelectPlaceWrapper;
import core.page.UserMainPage;
import model.TestBot;

public class ThinkTest extends TestBase {
    private void defaultAuthorization(){
        new SessionPage(driver)
            .doLogin(new TestBot("technopolisBot20", "technopolis16"));
    }

    @Test
    public void openCloseThink(){
        defaultAuthorization();

        UserMainPage userMainPage = new UserMainPage(driver);
        userMainPage.clickOpenThink();

        new ThinksLayer(driver)
            .close();
    }

    @Test
    public void basePublication(){
        defaultAuthorization();

        UserMainPage userMainPage = new UserMainPage(driver);
        userMainPage.clickOpenThink();

        new ThinksLayer(driver)
            .writeMainText("Базовая публикация")
            .submit();

        OneFeedWrapper lastFeed = userMainPage.getLastFeed();
        Assert.assertEquals("Базовая публикация", lastFeed.getText());
    }

    @Test
    public void basePublicationWithExtraTextarea(){
        defaultAuthorization();

        UserMainPage userMainPage = new UserMainPage(driver);
        userMainPage.clickOpenThink();

        ThinksLayer thinksLayer = new ThinksLayer(driver)
            .writeMainText("Земля вовсе не круглая");

        thinksLayer.addTextarea()
            .type("И не квадратная");

        thinksLayer.submit();

        OneFeedWrapper lastFeed = userMainPage.getLastFeed();
        Assert.assertEquals("Земля вовсе не круглая\nИ не квадратная", lastFeed.getText());
    }

    @Test
    public void appendingInterview(){
        defaultAuthorization();

        UserMainPage userMainPage = new UserMainPage(driver);
        userMainPage.clickOpenThink();

        ThinksLayer thinksLayer = new ThinksLayer(driver)
            .writeMainText("Земля круглая?");

        thinksLayer.addInterview()
            .typeAnswer(0, "Квадратная")
            .typeAnswer(1, "Круглая")
            .typeAnswer(2, "Шарообразная");

        thinksLayer.submit();

        OneFeedWrapper lastFeed = userMainPage.getLastFeed();
        Assert.assertEquals("Земля круглая?", lastFeed.getText());
        Assert.assertArrayEquals(new String[]{"Квадратная", "Круглая", "Шарообразная"}, lastFeed.getInterviewAnswers());
    }

    @Test
    public void appendingPlace(){
        defaultAuthorization();

        UserMainPage userMainPage = new UserMainPage(driver);
        userMainPage.clickOpenThink();

        ThinksLayer thinksLayer = new ThinksLayer(driver)
            .writeMainText("Земля вовсе не круглая (докажем картой)");

        ThinksSelectPlaceWrapper selectPlace = thinksLayer.selectPlace()
            .typeAddress("СПбПУ")
            .waitResultsLoading();

        String addressName = selectPlace.clickFirstResult();

        thinksLayer.submit();

        OneFeedWrapper lastFeed = userMainPage.getLastFeed();
        Assert.assertEquals("Земля вовсе не круглая (докажем картой)", lastFeed.getText());
        Assert.assertEquals(addressName, lastFeed.getPlace());
    }
}
