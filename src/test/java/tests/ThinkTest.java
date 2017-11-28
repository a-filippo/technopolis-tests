package tests;

import org.junit.Assert;
import org.junit.Test;

import core.wrapper.OneFeedWrapper;
import core.page.SessionPage;
import core.page.ThinksPage;
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

        UserMainPage userMainPageHelper = new UserMainPage(driver);
        userMainPageHelper.clickOpenThink();

        new ThinksPage(driver)
            .close();
    }

    @Test
    public void basePublication(){
        defaultAuthorization();

        UserMainPage userMainPageHelper = new UserMainPage(driver);
        userMainPageHelper.clickOpenThink();

        new ThinksPage(driver)
            .writeMainText("Базовая публикация")
            .submit();

        OneFeedWrapper lastFeed = userMainPageHelper.getLastFeed();
        Assert.assertEquals("Базовая публикация", lastFeed.getText());
    }

    @Test
    public void basePublicationWithExtraTextarea(){
        defaultAuthorization();

        UserMainPage userMainPageHelper = new UserMainPage(driver);
        userMainPageHelper.clickOpenThink();

        ThinksPage thinksHelper = new ThinksPage(driver)
            .writeMainText("Земля вовсе не круглая");

        thinksHelper.addTextarea()
            .type("И не квадратная");

        thinksHelper.submit();

        OneFeedWrapper lastFeed = userMainPageHelper.getLastFeed();
        Assert.assertEquals("Земля вовсе не круглая\nИ не квадратная", lastFeed.getText());
    }

    @Test
    public void appendingInterview(){
        defaultAuthorization();

        UserMainPage userMainPageHelper = new UserMainPage(driver);
        userMainPageHelper.clickOpenThink();

        ThinksPage thinksHelper = new ThinksPage(driver)
            .writeMainText("Земля вовсе не круглая (прикладываем опрос)");

        thinksHelper.addInterview()
            .typeAnswer(0, "Квадратная")
            .typeAnswer(1, "Круглая")
            .typeAnswer(2, "Шарообразная");

        thinksHelper.submit();

        OneFeedWrapper lastFeed = userMainPageHelper.getLastFeed();
        Assert.assertEquals("Земля вовсе не круглая (прикладываем опрос)", lastFeed.getText());
        Assert.assertArrayEquals(new String[]{"Квадратная", "Круглая", "Шарообразная"}, lastFeed.getInterviewAnswers());
    }

    @Test
    public void appendingPlace(){
        defaultAuthorization();

        UserMainPage userMainPageHelper = new UserMainPage(driver);
        userMainPageHelper.clickOpenThink();

        ThinksPage thinksHelper = new ThinksPage(driver)
            .writeMainText("Земля вовсе не круглая (прикладываем метку на карте)");

        ThinksSelectPlaceWrapper selectPlace = thinksHelper.selectPlace()
            .typeAddress("СПбПУ")
            .waitResultsLoading();

        String addressName = selectPlace.clickFirstResult();

        thinksHelper.submit();

        OneFeedWrapper lastFeed = userMainPageHelper.getLastFeed();
        Assert.assertEquals("Земля вовсе не круглая (прикладываем метку на карте)", lastFeed.getText());
        Assert.assertEquals(addressName, lastFeed.getPlace());
    }
}
