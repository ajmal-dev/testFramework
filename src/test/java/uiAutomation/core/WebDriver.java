/*  @Author = Mohammed Ajmal */

package test.java.uiAutomation.core;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.uiAutomation.core.Base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class WebDriver
{
    public static RemoteWebDriver driver;
    public static RemoteWebDriver launchwebdriver(String  browser) throws IOException
    {
        try {

            if
            (
                    browser.equalsIgnoreCase("chrome")&& Base.propertiesRead("os").equalsIgnoreCase("linux")&&Base.propertiesRead("parallelexecution").equalsIgnoreCase("false")) {
                Base.log.info("Setting chrome browser to selenium webdriver");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
                Base.log.info("Environment set successfully in Chrome browser for linux os success!!!");
            }
            else if (browser.equalsIgnoreCase("firefox")&& Base.propertiesRead("os").equalsIgnoreCase("linux")&&Base.propertiesRead("parallelexecution").equalsIgnoreCase("false"))
            {
                Base.log.info("Setting firefox browser to selenium webdriver");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
                Base.log.info("Environment set successfully in Firefox browser for linux os success!!!");
            }
            else if((browser.equalsIgnoreCase("chrome")&& Base.propertiesRead("os").equalsIgnoreCase("windows"))&&Base.propertiesRead("parallelexecution").equalsIgnoreCase("false"))
            {
                Base.log.info("Setting chrome browser to selenium webdriver");
                WebDriverManager.chromedriver().setup();
//                System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
                driver = new ChromeDriver();
//                driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
                Base.log.info("Environment set successfully in Chrome browser for windows os success!!!");
            }
            else if(browser.equalsIgnoreCase("firefox")&& Base.propertiesRead("os").equalsIgnoreCase("windows")&&Base.propertiesRead("parallelexecution").equalsIgnoreCase("false"))
            {
                Base.log.info("Setting firefox browser to selenium webdriver");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
                Base.log.info("Environment set successfully in firefox browser for windows os success!!!");
            }
            else if (browser.equalsIgnoreCase("chrome")&&Base.propertiesRead("os").equalsIgnoreCase("windows")&&Base.propertiesRead("parallelexecution").equalsIgnoreCase("true"))
            {
                Base.log.info("Setting chrome browser to selenium webdriver");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
                Base.log.info("Environment set successfully in Chrome browser for windows os success!!!");
            }
            else if(browser.equalsIgnoreCase("chrome")&&Base.propertiesRead("os").equalsIgnoreCase("linux")&&Base.propertiesRead("parallelexecution").equalsIgnoreCase("true"))
            {
                Base.log.info("Setting chrome browser to selenium webdriver");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
                Base.log.info("Environment set successfully in Chrome browser for linux os success!!!");
            }
            else if(browser.equalsIgnoreCase("firefox")&&Base.propertiesRead("os").equalsIgnoreCase("windows")&&Base.propertiesRead("parallelexecution").equalsIgnoreCase("true"))
            {
                Base.log.info("Setting firefox browser to selenium webdriver");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
                Base.log.info("Environment set successfully in Chrome browser for linux os success!!!");
            }
            else if(browser.equalsIgnoreCase("firefox")&&Base.propertiesRead("os").equalsIgnoreCase("linux")&&Base.propertiesRead("parallelexecution").equalsIgnoreCase("true"))
            {
                Base.log.info("Setting firefox browser to selenium webdriver");
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
                Base.log.info("Environment set successfully in firefox browser for linux os success!!!");
            }
        }
        catch (NullPointerException  e)
        {
            e.printStackTrace();
            Base.log.error("The driver is not configured properly or Update the drivers");
            Base.log.error("The  Environment setup failed !!!!!");
        }
        catch(IllegalArgumentException e)
        {
            e.printStackTrace();
            Base.log.error("Check the drivers, it is not configured properly");
            Base.log.error("The  Environment setup failed !!!!!");
        }
        return driver;
    }
}
