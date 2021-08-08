package test.java.uiAutomation.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import test.java.core.Base;
import test.java.uiAutomation.pagefactory.LpLoginPage;

public class login2

{
    LpLoginPage lpLoginPage=new LpLoginPage(Base.driver);

    @Test(priority = 0,dependsOnGroups = "1")
    public void loginAsDistrictuser()
    {
        lpLoginPage.gotoLp();
        lpLoginPage.enterusername("asharma@uworld.com");
        lpLoginPage.enterPassword("lpdemouser");
//        Base.driver.close();
    }

    @Test(priority = 1,dependsOnGroups = "1")
    public void loginAsCampusUser()
    {
//        Base.driver.switchTo().window(Base.driver.)
//        Base.driver.navigate().to(Base.propertiesRead("applicationUrl"));
        lpLoginPage.enterusername("in_crowleydistrictuser1@uworld.in");
        lpLoginPage.enterPassword("lpdemouser");
        lpLoginPage.clickLoginButton();
//        Base.driver.close();
    }

    @BeforeClass()
    void beforemethod()
    {
        System.out.println("Class is starting");
    }
    @AfterClass()
    void aftermethod()
    {
        System.out.println("class ended");
    }

}
