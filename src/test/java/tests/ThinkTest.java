package tests;

import org.junit.Assert;
import org.junit.Test;

import core.wrapper.OneFeedWrapper;
import core.page.SessionPage;
import core.page.ThinksPage;
import core.ThinksSelectPlace;
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

        ThinksPage thinksHelper = new ThinksPage(driver);
        thinksHelper.close();

        // проверить закрытость

    }

    @Test
    public void basePublication(){
        defaultAuthorization();

        UserMainPage userMainPageHelper = new UserMainPage(driver);
        userMainPageHelper.clickOpenThink();

        ThinksPage thinksHelper = new ThinksPage(driver)
            .writeMainText("Базовая публикация 1")
            .submit();

        OneFeedWrapper lastFeed = userMainPageHelper.getLastFeed();
        Assert.assertEquals("Базовая публикация 1", lastFeed.getText());
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
        // проверка
    }

    @Test
    public void appendingInterview(){
        defaultAuthorization();

        UserMainPage userMainPageHelper = new UserMainPage(driver);
        userMainPageHelper.clickOpenThink();

        ThinksPage thinksHelper = new ThinksPage(driver)
            .writeMainText("Земля вовсе не круглая");

        thinksHelper.addInterview()
            .typeAnswer(0, "Квадратная")
            .typeAnswer(1, "Круглая")
            .typeAnswer(2, "Шарообразная");

        thinksHelper.submit();

        OneFeedWrapper lastFeed = userMainPageHelper.getLastFeed();
        Assert.assertEquals("Земля вовсе не круглая", lastFeed.getText());
        Assert.assertArrayEquals(new String[]{"Квадратная", "Круглая", "Шарообразная"}, lastFeed.getInterviewAnswers());
        // проверка
    }

    @Test
    public void appendingPlace(){
        defaultAuthorization();

        UserMainPage userMainPageHelper = new UserMainPage(driver);
        userMainPageHelper.clickOpenThink();

        ThinksPage thinksHelper = new ThinksPage(driver)
            .writeMainText("Земля вовсе не круглая");

        ThinksSelectPlace selectPlace = thinksHelper.selectPlace()
            .typeAddress("СПбПУ")
            .waitResultsLoading();

        String addressName = selectPlace.clickFirstResult();

        thinksHelper.submit();

        OneFeedWrapper lastFeed = userMainPageHelper.getLastFeed();
        Assert.assertEquals("Земля вовсе не круглая", lastFeed.getText());
        Assert.assertEquals(addressName, lastFeed.getPlace());


    }
}
