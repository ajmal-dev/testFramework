package test.java.uiAutomation.testcases;

import test.java.uiAutomation.core.Base;
import org.apache.commons.mail.EmailException;
import org.testng.annotations.*;

import java.io.IOException;

public class suite

{
    @Parameters({"browser"})
    @BeforeTest()
    public void beforetest(@Optional String browser) throws IOException, InterruptedException
    {
//        TestCaseLibrary testcaseutility=new TestCaseLibrary();
        Base.setLog4j();
        String date=Base.getDate();
        Base.log.info("The Test Suite is  starting :  "+ date);
        Base.setEnvironment(browser);
//        Base.extentConfiguration();

    }

    @AfterTest()
    void endTest() throws EmailException, IOException
    {

        Base.driver.quit();
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
