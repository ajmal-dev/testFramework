/*  @Author = Mohammed Ajmal */
package test.java.core;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Base
{
    //****************Add All the constants here*******************
    public static RemoteWebDriver driver;    //Global Remote driver
    public final  String applicationUrl = Base.propertiesRead("applicationUrl");
    private static List<String> screenshotspathlist=new ArrayList<>();
    private static ExtentSparkReporter htmlReporter=new ExtentSparkReporter(Base.propertiesRead("extentReportPath"));
    public static Faker randomData=new Faker();
    public static ExtentReports extent = new ExtentReports();
    public static Logger log=Logger.getLogger("faslogger");
//    public static HashMap<String, ArrayList<String>> testcase_library_data=loadData(Base.propertiesRead("testCaseReportPath"),"Status","vertical");




    public static void setLog4j() throws IOException
    {
        try {
            System.out.println("Setting Logger........");
            InputStream in = Base.class.getResourceAsStream("/log4j.properties");
            Properties prop = new Properties();
            prop.load(in);
            PropertyConfigurator.configure(prop);
            System.out.println("The logger set successfuly");
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("The File  not found !!!!!, Log4j.properties");
        }
        catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("IO exception in setting log4j");
        }
    }

    // **************Setting the configuration*********
    public static void extentConfiguration() {

            log.info("Setting the  Extent html reporters");
            extent.attachReporter(htmlReporter);
            extent.setSystemInfo("Application", "FAS");
            extent.setSystemInfo("Platform", System.getProperty("os.name"));
            htmlReporter.config().setTheme(Theme.STANDARD);
            htmlReporter.config().setDocumentTitle("Test Automation Report");
            log.info("Extent report configurations set successfully !!!!");
            
    }
    //*************** Setting the environment *********
    public static void setEnvironment() throws IOException, InterruptedException {
        Base.log.info("Configuring the Environment started ..............");
        try
        {
            if (Base.propertiesRead("environment").equalsIgnoreCase("local")) {
                Base.driver = WebDriver.launchwebdriver(Base.propertiesRead("webbrowser"));
            }
            else if (Base.propertiesRead("environment").equalsIgnoreCase("docker") ) {
                Base.driver = RemoteDriver.launching(Base.propertiesRead("webbrowser")); //Launch the browser
            }
        }
        catch(IOException e)
        {
            log.error("ERROR: The environment configuration FAILED !!!");
        }
    }

    //**************** Method to read the properties file **********
    public static String propertiesRead(String key) {
        Properties prop = new Properties();
        try
        {
            InputStream input=Base.class.getResourceAsStream("/properties.properties");
            prop.load(input);
        }
        catch (IOException e)
        {
            Base.log.error("Cant Read from properties.properties file, Check the keywords or path of the properties file");
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }

    //****************Method to send mail***************************
    public static void sendMail()
    {
        try {
            MultiPartEmail email = new MultiPartEmail();

            //Attachment of Extent report
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(Base.propertiesRead("extentReportPath"));                    //reports/testcase_Report.xlsx The extent report in html format
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription("Fas Web App Test Automation Report");
            attachment.setName("FasWeb_App_Test_Automation_Report.html");
            email.attach(attachment);

            //Attachment of testcase status
            attachment = new EmailAttachment();
            attachment.setPath(propertiesRead("testCaseReportPath"));                 //reports/testcase_Report.xlsx. The status of testcases report
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription("Fas Web App Test Automation Execution Results");
            attachment.setName("TestCaseExecution_Results.xlsx");
            email.attach(attachment);

            // Attachment of screenshots
//            attachment = new EmailAttachment();
//            attachment.setPath(propertiesRead("screenshotsZipPath"));                 //screenshots.zip  The zip file for all screenshots
//            attachment.setDisposition(EmailAttachment.ATTACHMENT);
//            attachment.setDescription("Screenshots of the testcases");
//            attachment.setName("screensnapshots.zip");
//            email.attach(attachment);

            // Attachment of log files
            attachment = new EmailAttachment();
            attachment.setPath(propertiesRead("testLogPath"));                 //screenshots.zip  The zip file for all screenshots
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription("logs of testcases");
            attachment.setName("Test_Logs.txt");
            email.attach(attachment);

            DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
            Date dateobj = new Date();
            System.out.println(df.format(dateobj));
            email.setHostName("smtp.gmail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator("testdoxa@gmail.com", "Ajshanej@2020"));
            email.setSSLOnConnect(true);
            email.setFrom("testdoxa@gmail.com");
            email.setSubject("Test Automation report  " + df.format(dateobj));
            String ENDL = System.getProperty("line.separator");
            email.setMsg("This is the Test Execution Report of the Automation Tests run on the FAS Web application." + ENDL + "Please Review the Results of the execution:" + ENDL + ENDL + "   1. Testcase Execution Report [TestCaseExecution_Results.xlsx]" + ENDL + "   2. Test Automation Graphical Report [FasWeb_App_Test_Automation_Report.html]" + ENDL + "   3. Screenshots of the Testcases" + ENDL + ENDL + "Note : Please Download the HTML report for Viewing and Unzip the screensnap to view the screenshots" + ENDL + "Environment :" + Base.propertiesRead("environment") + ENDL + "Browser :" + propertiesRead("webbrowser"));
            email.addTo(propertiesRead("ReportsRecipients"));
            email.send();
        }
        catch (EmailException e)
        {
            Base.log.error("The email was not send, Check !!");
            e.printStackTrace();
        }
    }
    public static String getDate()
    {
        DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date dateobj = new Date();
        return df.format(dateobj);
    }

    //*************** take screenshots as base64 string*****
    public static String CaptureScreen()
    {
        TakesScreenshot newScreen = (TakesScreenshot) driver;
        String scnShot = newScreen.getScreenshotAs(OutputType.BASE64);
        return "data:image/jpg;base64, " + scnShot ;
    }
    //*************** Method to take screenshots*******************
    public static void takeSnapShot(String imagename)  {
        try {
            TakesScreenshot scrShot = ((TakesScreenshot) driver);
            File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
            String pathfilename = propertiesRead("screenshotsPath") + "/" + imagename + ".png";
            File DestFile = new File(pathfilename);
            FileUtils.copyFile(SrcFile, DestFile);
            screenshotspathlist.add(pathfilename);
        }
        catch(IOException e)
        {
            Base.log.error("Cant take screenshots, Maybe occured while copying screenshots file.");
            e.printStackTrace();
        }
    }
    // *********************Zipping the screenshots taken in the tests ********************
//    public static void zipFiles() {
//
//        FileOutputStream fos = null;
//        ZipOutputStream zipOut = null;
//        FileInputStream fis = null;
//        try {
//            fos = new FileOutputStream(propertiesRead("screenshotsZipPath"));
//            zipOut = new ZipOutputStream(new BufferedOutputStream(fos));
//            for(String filePath:screenshotspathlist){
//                File input = new File(filePath);
//                fis = new FileInputStream(input);
//                ZipEntry ze = new ZipEntry(input.getName());
//                System.out.println("Zipping the file: "+input.getName());
//                zipOut.putNextEntry(ze);
//                byte[] tmp = new byte[4*1024];
//                int size = 0;
//                while((size = fis.read(tmp)) != -1){
//                    zipOut.write(tmp, 0, size);
//                }
//                zipOut.flush();
//                fis.close();
//            }
//            zipOut.close();
//            System.out.println("Done... Zipped the files...");
//        } catch (FileNotFoundException e)
//        {
//            e.printStackTrace();
//            Base.log.error("Cant ind the zip file!!!");
//        } catch (IOException e)
//        {
//            Base.log.error("Error in the INput/output, while zipping the screenshots");
//            e.printStackTrace();
//        } finally
//        {
//            try
//            {
//                if(fos != null) fos.close();
//            }
//            catch(Exception e)
//            {
//                e.printStackTrace();
//                Base.log.error("Thhe output stream file is null, Occured while zipping the screenshots.!!");
//            }
//        }
//    }
    public static HashMap<String, ArrayList<String>> loadData(String filepath,String sheetname, String exceltype) {
        HashMap<String, ArrayList<String>> data = new HashMap<String, ArrayList<String>>(); // Hashmap to store the excel data
        try {
            Base.log.info("Reading Data from Excel file............................");
            Workbook workbook = null;
            ArrayList<String> maincolumn = new ArrayList<String>();                                     // Store the keys of the data eg : cities,names
//        String filename = propertiesRead("datasourcepath");                                //  Read the filename from properties file
//        File datafile = new File(filename);
//        FileInputStream inputstream = new FileInputStream(datafile);
            FileInputStream inputstream = new FileInputStream(filepath);
            String extension = filepath.substring(filepath.indexOf("."));
            if (extension.equals(".xlsx")) {
                workbook = new XSSFWorkbook(inputstream);
            } else if (extension.equals(".xls")) {
                workbook = new HSSFWorkbook(inputstream);
            } else {
                System.out.println("Invalid Datasource file");
            }
            Sheet sheet = workbook.getSheet(sheetname);
            int number_of_rows = sheet.getPhysicalNumberOfRows();
            int k = 0;
            if (exceltype == "horizontal") {
                for (int i = 0; i < number_of_rows; i++)                                                        //Looping the rows
                {
                    Row row = sheet.getRow(i);
                    int number_of_cells = row.getPhysicalNumberOfCells();
                    if (i != 0) {
                        for (int j = 0; j < number_of_cells; j++)                                                   //looping cells in the row.
                        {
                            Cell cell = row.getCell(j);
                            if (j == 0)                                                                             //condition to fetch the key values
                            {
                                CellType type = cell.getCellType();
                                if (type == CellType.STRING) {
                                    data.put(cell.getStringCellValue(), new ArrayList<String>());           //Adding key values to map
                                    maincolumn.add(cell.getStringCellValue());                                      //Adding the key values in the main column arraylist
                                    k++;                                                                            //increment k to track the position of row looping
                                } else {
                                    String convertedCell = Double.toString(cell.getNumericCellValue());             //converting the numeric value to String cell value
                                    data.put(convertedCell, new ArrayList<String>());
                                    maincolumn.add(cell.getStringCellValue());
                                    k++;
                                }
                            } else if (j != 0 && i == k)                                                              // Condition to fetch the value for the respective keys in the row
                            {
                                int l = k - 1;
                                CellType type1 = cell.getCellType();
                                if (type1 == CellType.STRING) {
                                    data.get(maincolumn.get(l)).add(cell.getStringCellValue());             // Put the values in the Hashmap.
                                } else {
                                    String convertedCell1 = Double.toString(cell.getNumericCellValue());
                                    data.get(maincolumn.get(l)).add(convertedCell1);
                                }
                            }
                        }
                    }
                }
            } else {
                for (int i = 0; i < number_of_rows; i++)                                                        //Looping the rows
                {
                    Row row = sheet.getRow(i);
                    for (int j = 0; j < row.getPhysicalNumberOfCells(); j++) {
                        if (i == 0) {
                            CellType type = row.getCell(j).getCellType();
                            if (type == CellType.STRING) {
                                data.put(row.getCell(j).getStringCellValue(), new ArrayList<String>());
                                maincolumn.add(row.getCell(j).getStringCellValue());
                            } else {
                                String convertedCell1 = Double.toString(row.getCell(j).getNumericCellValue());
                                maincolumn.add(convertedCell1);
                            }
                        } else {
                            for (int m = 0; m < maincolumn.size(); m++) {
                                if (j == m) {
                                    CellType type = row.getCell(j).getCellType();
                                    if (type == CellType.STRING) {
                                        String columnName = maincolumn.get(m);
                                        data.get(columnName).add(row.getCell(j).getStringCellValue());
                                    } else {
                                        String columnName = maincolumn.get(m);
                                        String convertedCell1 = Double.toString(row.getCell(j).getNumericCellValue());
                                        data.get(columnName).add(convertedCell1);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        catch(IOException e)
        {
            Base.log.error("Can't Read from the Excel sheet, Maybe the excel file not found, Check");
            e.printStackTrace();
        }
        return data;                                                                                        //    Return the Hashmap to the method.
    }
}




