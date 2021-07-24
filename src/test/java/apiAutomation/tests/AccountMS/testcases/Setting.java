package test.java.apiAutomation.tests.AccountMS.testcases;


import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import test.java.apiAutomation.corefunctions.BaseFunctions;

import java.io.IOException;

public class Setting
{

    @BeforeSuite
    public void setting() throws IOException

    {

        BaseFunctions.setLog4jForRestAssured();
        BaseFunctions.extentConfiguration();
    }

    @AfterSuite
    public void tearDown()
    {
        BaseFunctions.extentapi.flush();
    }
}
