package test.java.bdd.cucumberTestng.stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import test.java.bdd.cucumberTestng.testmethods.login;

import java.io.IOException;

public class LoginPositive extends login
{
    @Given("^Goto Google page \"(.*)\"$")
    public void goto_Google_page(String url) throws IOException {
        goto_google_psge(url);
    }

    @When("^The page is loaded$")
    public void the_page_is_loaded() throws IOException {
        check_page_is_loaded();
    }

    @And("^Search keyword \"(.*)\"$")
    public void search_keyword(String keyword) throws IOException {
        searchKeyword(keyword);
    }
}
