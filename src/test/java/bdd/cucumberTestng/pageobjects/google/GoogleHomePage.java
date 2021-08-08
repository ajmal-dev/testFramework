package test.java.bdd.cucumberTestng.pageobjects.google;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import test.java.core.Base;
import test.java.core.BrowserFunctions;

public class GoogleHomePage

{
    RemoteWebDriver driver;
    BrowserFunctions browserActions=new BrowserFunctions();
    public GoogleHomePage(RemoteWebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);

    }


    private By google_page_header=By.xpath("//im[@alt='Google']");
    private By search_box=By.xpath("//input[@title='Search']");
    private By search_button=By.xpath("(//input[@value='Google Search'])[2]")      ;

    public void gotoGooglehomePage(String url)
    {
        Base.driver.get(url);
    }

    public boolean checkGoogleHomepageIsLoaded()
    {
        try {
            Base.driver.findElement(google_page_header).isDisplayed();
            return true;
        }
        catch(NoSuchElementException e)
        {
            return false;
        }
        catch(ElementNotVisibleException e)
        {
            return false;
        }
    }

    public void enterKeywordForSearch(String keywords)
    {
        browserActions.enterData( search_box,keywords);
    }
    public void clickSubmitButton()
    {
        browserActions.click( search_button);
    }


}
