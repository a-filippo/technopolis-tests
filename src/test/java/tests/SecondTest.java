package tests;

import org.junit.Assert;
import org.junit.Test;

import core.CreatingGroupPromise;
import core.page.GroupCreatingPage;
import core.page.OneGroupPage;
import core.page.SessionPage;
import core.page.UserMainPage;
import model.TestBot;

public class SecondTest extends TestBase {

    @Test
    public void testGroupNoemptyNameCreation() throws Exception {
        new SessionPage(driver)
            .doLogin(new TestBot("technopolisBot20", "technopolis16"));

        UserMainPage userMainPage = new UserMainPage(driver);

        userMainPage.clickGroupsOnToolbar();

        GroupCreatingPage groupPage = new GroupCreatingPage(driver)
            .clickCreateGroup()
            .clickInterestGroup()
            .typeGroupName("Group")
            .clickCreateButton();

        OneGroupPage groupCreated = new CreatingGroupPromise(driver, groupPage)
            .successCreating();

        Assert.assertEquals("Названия групп не совпадают", groupCreated.getGroupName(),"Group");
    }

    @Test
    public void testGroupEmptyNameCreation() throws Exception {
        new SessionPage(driver)
                .doLogin(new TestBot("technopolisBot20", "technopolis16"));

        UserMainPage userMainPage = new UserMainPage(driver);

        userMainPage.clickGroupsOnToolbar();

        GroupCreatingPage groupPage = new GroupCreatingPage(driver)
            .clickCreateGroup()
            .clickInterestGroup()
            .clickCreateButton();

        new CreatingGroupPromise(driver, groupPage)
                .emptyNameError();
    }
}