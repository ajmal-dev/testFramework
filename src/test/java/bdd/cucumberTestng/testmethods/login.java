package test.java.bdd.cucumberTestng.testmethods;

import com.aventstack.extentreports.ExtentTest;
import org.junit.Assert;
import test.java.bdd.cucumberTestng.pageobjects.google.GoogleHomePage;
import test.java.core.Base;

import java.io.IOException;

public class login

{
    GoogleHomePage googleHomePage = new GoogleHomePage(Base.driver);
    public void goto_google_psge(String url) throws IOException {
        ExtentTest testcase = null;

            googleHomePage.gotoGooglehomePage(url);

    }



    public void check_page_is_loaded() throws IOException {
        ExtentTest testcase = null;
        try
        {
            // Write code here that turns the phrase above into concrete actions
            Assert.assertTrue(googleHomePage.checkGoogleHomepageIsLoaded());
        }
        catch (AssertionError | Exception e)
        {
           e.printStackTrace();
        }
    }



    public void searchKeyword(String searchKeyWord) throws IOException {
        ExtentTest testcase = null;
        try
        {
            // Write code here that turns the phrase above into concrete actions
            googleHomePage.enterKeywordForSearch(searchKeyWord);
            googleHomePage.clickSubmitButton();
        }
        catch (AssertionError | Exception e)
        {
            e.printStackTrace();
        }

    }
}
