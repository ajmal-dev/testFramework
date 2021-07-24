package test.java.bdd.cucumberTestng.stepdefinitions;
import cucumber.api.java.en.Given;
import test.java.bdd.cucumberTestng.coreSelenium.Base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BuildEnvironment

{
    @Given("^setup environmental settings$")
    public void setEnvironment() throws IOException, InterruptedException
    {
        String date= Base.getDate();
        Base.log.info("The Test Suite is  starting :  "+ date);
        Base.setEnvironment();
        Base.driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);
    }

}
