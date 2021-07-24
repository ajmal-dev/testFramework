package test.java.uiAutomation.testcases;

import org.testng.annotations.Test;
import test.java.uiAutomation.core.Base;
import test.java.uiAutomation.pagefactory.LpLoginPage;

public class DistrictUserLogin


{
    LpLoginPage lpLoginPage=new LpLoginPage(Base.driver);

    @Test
    public void loginAsDistrictuser()
    {
        lpLoginPage.gotoLp();
        lpLoginPage.enterusername("asharma@uworld.com");
        lpLoginPage.enterPassword("lpdemouser");
    }

}
