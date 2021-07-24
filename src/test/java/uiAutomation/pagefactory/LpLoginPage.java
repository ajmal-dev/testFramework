package test.java.uiAutomation.pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import test.java.uiAutomation.core.Base;
import test.java.uiAutomation.core.BrowserFunctions;

public class LpLoginPage


{
    BrowserFunctions browserFunctions=new BrowserFunctions();
    WebDriver driver;
    public  LpLoginPage (WebDriver driver)
    {
        this.driver=driver;
    }

    private By username_field=By.xpath("//input[@id='login_username']");
    private By password_field=By.xpath("//input[@id='login_password']");
    private By login_button=By.xpath("//button[@id='login-button']");


    public void enterusername(String username)
    {
        browserFunctions.enterData(username_field,username);
    }

    public void enterPassword(String password)
    {
            browserFunctions.enterData(password_field,password);
    }

        public void gotoLp()
        {
            browserFunctions.goTo(Base.propertiesRead("applicationUrl"));
        }

    public void clickLoginButton()
    {
        browserFunctions.click(login_button);


    }








}
