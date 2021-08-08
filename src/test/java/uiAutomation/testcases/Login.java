package test.java.uiAutomation.testcases;

import org.testng.annotations.*;
import test.java.core.Base;
import test.java.uiAutomation.pagefactory.LpLoginPage;

public class Login


{
    LpLoginPage lpLoginPage=new LpLoginPage(Base.driver);

    @Test(priority = 0,groups = "1")
    public void loginAsDistrictuser()
    {
        lpLoginPage.gotoLp();
        lpLoginPage.enterusername("asharma@uworld.com");
        lpLoginPage.enterPassword("lpdemouser");
//        Base.driver.close();
    }

    @Test(priority = 1,groups = "1")
    public void loginAsCampusUser()
    {
//        Base.driver.switchTo().window(Base.driver.)
//        Base.driver.navigate().to(Base.propertiesRead("applicationUrl"));
        lpLoginPage.enterusername("in_crowleydistrictuser1@uworld.in");
        lpLoginPage.enterPassword("lpdemouser");
        lpLoginPage.clickLoginButton();
//        Base.driver.close();
    }
    @BeforeMethod()
    void beforemethod()
    {
        System.out.println("Test is starting");
    }
    @AfterMethod()
    void aftermethod()
    {
        System.out.println("Test ended");
    }
    @BeforeClass()
    void beforeclass()
    {
        System.out.println("Class is starting");
    }
    @AfterClass()
    void afterclass()
    {
        System.out.println("class ended");
    }

}
