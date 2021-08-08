package test.java.bdd.cucumberTestng.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import org.apache.velocity.tools.view.BrowserTool;
import test.java.bdd.cucumberTestng.testmethods.login;
import test.java.core.Base;
import test.java.core.BrowserFunctions;

import java.io.IOException;

public class LoginPositive extends login
{
    @Before
    public void launchBrowser() throws IOException, InterruptedException {
        BuildEnvironment buildEnvironment=new BuildEnvironment();
        buildEnvironment.setEnvironment();
    }
    @After
    public void closeBrowser()
    {
        BrowserFunctions browserFunctions=new BrowserFunctions();
        Base.driver.quit();
    }
    @Given("^Goto Google page \"(.*)\"$")
    public void goto_Google_page(String url) throws IOException
    {
        goto_google_psge(url);
    }

    @When("^The page is loaded$")
    public void the_page_is_loaded() throws IOException
    {
        check_page_is_loaded();
    }

    @And("^Search keyword \"(.*)\"$")
    public void search_keyword(String keyword) throws IOException
    {
        searchKeyword(keyword);
    }
}
