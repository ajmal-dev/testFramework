package test.java.bdd.cucumberTestng.pageobjects.google;

import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import test.java.bdd.cucumberTestng.coreSelenium.Base;
import test.java.bdd.cucumberTestng.coreSelenium.BrowserFunctions;

public class GoogleHomePage

{
    RemoteWebDriver driver;
    BrowserFunctions browserActions=new BrowserFunctions();
    public GoogleHomePage(RemoteWebDriver driver)
    {
        this.driver=driver;
        PageFactory.initElements(driver, this);

    }
    @FindBy(xpath = "//im[@alt='Google']")
    WebElement google_page_header;
    @FindBy(xpath = "//input[@title='Search']")
    WebElement search_box;
    @FindBy(xpath = "(//input[@value='Google Search'])[2]")
    WebElement search_button;

    public void gotoGooglehomePage(String url)
    {
        Base.driver.get(url);
    }

    public boolean checkGoogleHomepageIsLoaded()
    {
        try {
            google_page_header.isDisplayed();
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
        browserActions.enterData(search_box,keywords);
    }
    public void clickSubmitButton()
    {
        browserActions.click(search_button);
    }


}
