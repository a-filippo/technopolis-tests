package tests;

import org.junit.Assert;
import org.junit.Test;

import core.page.SessionPage;
import core.page.ThinksLayer;
import core.page.UserMainPage;
import core.wrapper.OneFeedWrapper;
import model.TechnopolisBot;

public class AppendingInterviewThinkTest extends TestBase {

    @Test
    public void appendingInterview(){
        UserMainPage userMainPage = new SessionPage(driver)
            .doLogin(new TechnopolisBot());

        ThinksLayer thinksLayer = userMainPage.clickOpenThink()
            .writeMainText("Земля круглая?");

        thinksLayer.addInterview()
            .typeAnswer(0, "Квадратная")
            .typeAnswer(1, "Круглая")
            .typeAnswer(2, "Шарообразная");

        thinksLayer
            .submit()
            .checkLayerUnvisible();

        OneFeedWrapper lastFeed = userMainPage.getLastFeed();
        Assert.assertEquals("Земля круглая?", lastFeed.getText());
        Assert.assertArrayEquals(new String[]{"Квадратная", "Круглая", "Шарообразная"}, lastFeed.getInterviewAnswers());
    }
}
