package test.java.uiAutomation.core;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class BrowserFunctions
{
    private  Actions action ;
    //********* javascript executer scroll to an element visibility ***
    public  void scrollToElement(By webelement) {
        WebElement element= Base.driver.findElement(webelement);
        Base.log.info("Scrolling to the element");
        ((JavascriptExecutor) Base.driver).executeScript("arguments[0].scrollIntoView();",element);
    }
    //***************** Scroll to bottom of screen *********************
    public  void scrollBottom() {
        Base.log.info("Scrolling to Bottom of page");
        ((JavascriptExecutor) Base.driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    //***************** wait till the page completely loads.************
    public void waitToLoad() {
        WebDriverWait wait =new WebDriverWait(Base.driver,20);

            Base.log.info("waiting for the page to load [JS executor]");
            wait.until((ExpectedCondition<Boolean>) wd ->
                    ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

        // Initially bellow given if condition will check ready state of page.

    }

    public void switch_to_window(int tab_number)
    {
        List<String> handles=new ArrayList<>(Base.driver.getWindowHandles());
        Base.driver.switchTo().window(handles.get(tab_number));
    }
    //***************** hover element *****************************
    public void hoverClick(By Hover_element,By click_element) {
        try
        {
            action = new Actions(Base.driver);
            WebElement element = Base.driver.findElement(Hover_element);
            action.moveToElement(element).moveToElement(Base.driver.findElement(click_element)).click().build().perform();
        }
        catch(ElementNotVisibleException e)
        {
            Base.log.error("The element"+Hover_element+ "or"+click_element+" is not visible for the webdriver, Hidden in the DOM, Check the element");
            e.printStackTrace();
        }
        catch (NoSuchElementException e)
        {
            Base.log.error("The element"+Hover_element+ "or"+click_element+" to be clicked,is not present, please check the  element locator");
            e.printStackTrace();
        }
        catch (ElementClickInterceptedException e)
        {
            Base.log.error("Driver not able to click the element"+Hover_element+ "or"+click_element+", Due to interception");
            e.printStackTrace();
        }
        }

    //**************** method to click a we element*******************
    public void click(By webelement) {

        {
            Base.driver.findElement(webelement).click();
        }

    }
    //**********************goto a frame *******
    public void selectaframe(By element)
    {
        Base.driver.switchTo().frame(Base.driver.findElement(element));
    }
    //*****************move to default frame****\
    public void defaultframe()
    {
        Base.driver.switchTo().defaultContent();
    }
    //**************** method to double click an element. ************
    public void doubleClick(By webelement) {
        action=new Actions(Base.driver);

            WebElement ele = Base.driver.findElement(webelement);
            action.doubleClick(ele).perform();


    }
    //*************** Enter an element to a field*******************
    public void enterData(By webelement,String data) {

            Base.log.info("Entering the input to field ..............");
            Base.driver.findElement(webelement).sendKeys(data);


    }
    //*************** Enter an element to a field*******************
    public void enterData1(By webelement,String data) {
        try
        {
            Base.log.info("Entering the input to field ..............");
            Base.driver.findElement(webelement).sendKeys(data,Keys.ENTER);
        }
        catch (NoSuchElementException e)
        {
            Base.log.error("The element " + webelement + " was not located, Check the locator. This happened when entering data to the field");
            e.printStackTrace();
        }
        catch (ElementNotInteractableException e)
        {
            Base.log.error("The element " + webelement + " was not located, Check the locator, This happened when entering data to the field");
            e.printStackTrace();
        }
    }
    //************** driver function for navigating to webpage*****
    public void goTo(String url) {

            Base.log.info("Navigating to the url "+ url);
            Base.driver.get(url);

    }
    //********** driver function for moving mouse to a element*****
    public void moveToElement(By webelement) {
        try
        {
            WebElement target=Base.driver.findElement(webelement);
            action.moveToElement(target);
        }
        catch (NoSuchElementException e)
        {
            Base.log.error("The element " + webelement + " was not located, Check the locator ,this Happened when moving cursor to a element");
            e.printStackTrace();
        }
        catch (ElementNotInteractableException e)
        {
            Base.log.error("The element " + webelement + " was not located, Check the locator, this Happened when moving cursor to a element");
            e.printStackTrace();
        }
    }
    //***************** wait by a condition *********************
    public void waitUntill(By webelement)
    {
        WebDriverWait wait =new WebDriverWait(Base.driver,20);

            Base.log.info("waiting for the element to be visible.");
            wait.until(ExpectedConditions.visibilityOfElementLocated(webelement));
    }
    //**************** implicit wait ***********
    public void pauseImplicit(int timeout)
    {
        Base.driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
    }
    //*****************Thread sleep *****************
    public void pause(long millis) throws InterruptedException {
        Thread.sleep(millis);
    }
    //***************** Check for a element on the screen**********
    public Boolean searchElement(By webelement) {
        Boolean verify_flag=false;
        if (Base.driver.findElement(webelement).isDisplayed())
        {
            Base.log.info("The element is displayed on the page");
            verify_flag=true;
            return verify_flag;
        }
        else
        {
            Base.log.error("searching element failed : The element is not displayed");
            return verify_flag;
        }
    }
    //***************** Check a list of elements are on the screen *********
    public Boolean searchElements(List<By> webelements)
    {
        Boolean element_flag=true;
        int size=webelements.size();
        for(int i=0;i<size;i++)
        {
            if(Base.driver.findElement(webelements.get(i)).isDisplayed())
            {}
            else
            { element_flag=false; }
        }
        if(element_flag.equals(true))
        {
            System.out.println("All elements are present");
            Base.log.info("searching a list of elements : All elements are present");
        }
        else
        {
            Base.log.error("searching elements failed: some webelements elements are missing");
            System.out.println("some webelements elements are missing");
        }
        return element_flag;
    }
    // get missing elements on the screen from bunch of elements ******
    public List<By> missingElements(List<By> webelements)
    {
        Base.log.info("Searching missing elements on the page...........");
        List<By> missing_Elements = null;
            int number_of_elements = webelements.size();
            for (int i = 0; i < number_of_elements; i++) {
                if (Base.driver.findElement(webelements.get(i)).isDisplayed()) {
                    Base.log.info("The element " + webelements.get(i) + " is displayed");
                } else {
                    Base.log.info("Found a missing element on the page" + webelements.get(i));
                    missing_Elements.add(webelements.get(i));
                }
            }
        return missing_Elements;
    }
    //***************** verify by title******************************
    public Boolean verifyByTitle(String expected_title) {
        Boolean verify_flag=false;
        if(expected_title.equalsIgnoreCase(Base.driver.getTitle()))
        {
            verify_flag=true;
            Base.log.info("Title verified : The title verification is successfull");
        }
        else
        {
            Base.log.warn("Title verification: The title didnt match!");
        }
        return verify_flag;
    }
    //**************** verify by element ************************
    public Boolean verifyByElement(By webelement)
    {
        Boolean verify_flag=false;
            if (Base.driver.findElement(webelement).isDisplayed()) {
                verify_flag = true;
                Base.log.info("verify By element: The element is displayed, verification successfull.");
            } else {
                Base.log.warn("verify with element : The element is not displayed / not found");
            }
            return verify_flag;
    }
    //**************** navigate  previous  page ***********************
    public void goBack()
    {

        Base.log.info("Navigating to previous page.........");
        Base.driver.navigate().back();
        waitToLoad();
    }
    //**************** refresh page*************************
    public void refresh()
    {
        Base.log.info("Page  is Refreshing.......");
        Base.driver.navigate().refresh();
        waitToLoad();
    }
    //****************verify a page using page  source*****
    public Boolean verifyPage(String string_in_page)
    {
        Boolean verify_flag=false;
        Base.log.info("Verifinng the page.............");
        if(Base.driver.getPageSource().contains(string_in_page))
        {
            verify_flag=true;
            Base.log.info(string_in_page+" is present, Verification successfull");
        }
        else
        {
            Base.log.info("is  not present, verification failed.");
        }
        return verify_flag;
    }

    //*************** Alert is present********
    public Boolean checkAlert()
    {
        try
        {
            Alert alert=Base.driver.switchTo().alert();
            Base.log.error(alert.getText());
            alert.accept();
            Base.log.info(" The Alert is present");
            return true;
        }
        catch(NoAlertPresentException e)
        {
            Base.log.info("The alert is not present!!");
            return false;
        }
    }
    //************** Page load timeout****
    public  void loadtimeout(int timeoutsec)
    {
        Base.driver.manage().timeouts().pageLoadTimeout(timeoutsec, TimeUnit.SECONDS);
    }

    //********** click  OK for Alerts *****
    public void alertOk()
    {
        Base.driver.switchTo().alert().accept();
    }


    //  ****** Alert Get text******
    public String getAlertText()
    {
        try {
            String text = Base.driver.switchTo().alert().getText();
            return  text;
        }
        catch(NoAlertPresentException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
