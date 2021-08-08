package test.java.bdd.cucumberTestng.stepdefinitions;

import io.cucumber.java.en.Given;
import test.java.core.Base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class BuildEnvironment

{
    public void setEnvironment() throws IOException, InterruptedException
    {
        String date= Base.getDate();
        Base.log.info("The Test Suite is  starting :  "+ date);
        Base.setEnvironment();
    }

}
