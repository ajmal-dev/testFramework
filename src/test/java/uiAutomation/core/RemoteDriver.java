/*  @Author = Mohammed Ajmal */
package test.java.uiAutomation.core;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import test.java.uiAutomation.core.Base;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class RemoteDriver
{
// ****************************** RemoteDriver for Chrome and firefox **********************************************
    public  static RemoteWebDriver launching(String browser) throws IOException, InterruptedException
    {
        Thread.sleep(5000);
        org.openqa.selenium.remote.RemoteWebDriver driver=null;
        if(browser.equalsIgnoreCase("chrome"))
        {
            DesiredCapabilities dc = DesiredCapabilities.chrome();
            URL url = new URL("http://" + Base.propertiesRead("ipaddress_chrome") + "/wd/hub");
            try
            {
                Base.log.info("Setting the selenium driver ..............");
                driver = new RemoteWebDriver(url, dc);
            }
            catch (Exception e)
            {
                Base.log.error("Check the standalone browser server is running on the machine");
                e.printStackTrace();
            }
        }
        else if (browser.equalsIgnoreCase("firefox"))
        {
            DesiredCapabilities dc = DesiredCapabilities.firefox();
            URL url = new URL("http://" + Base.propertiesRead("ipaddress_firefox") + "/wd/hub");
            try
            {
                Base.log.info("Setting the selenium driver ..............");
                driver = new org.openqa.selenium.remote.RemoteWebDriver(url, dc);
            }
            catch (Exception e)
            {
                Base.log.error("Check the standalone browser server is running on the machine");
                System.out.println("Check the standalone server is running on the machine");
                e.printStackTrace();
            }
        }
        return driver;
    }
    public RemoteDriver() throws IOException {
    }
}
