package test.java.apiAutomation.corefunctions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.github.javafaker.Faker;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import test.java.uiAutomation.core.Base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

public class BaseFunctions

{
    private static ExtentSparkReporter htmlReporter=new ExtentSparkReporter(readProperties("extentReportPathRestApi"));
    public static ExtentReports extentapi = new ExtentReports();
    public static Logger log=Logger.getLogger("apitestlogger");
    public static Faker generateData=new Faker();


    public static void setLog4jForRestAssured() throws IOException
    {
        try {
            System.out.println("Setting Logger........");
            InputStream in = Base.class.getResourceAsStream("/log4japis.properties");
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

        log.info("Setting the  Extennt html reporters");
        extentapi.attachReporter(htmlReporter);
        extentapi.setSystemInfo("Application", "APITESTS");
        extentapi.setSystemInfo("Platform", System.getProperty("os.name"));
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("API Test Automation Report");
        log.info("Extent report configurations set successfully !!!!");

    }

    //**************** Method to read the properties file **********
    public static String readProperties(String key) {
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

    //read Data from resource xlsx file
    public HashMap<String, ArrayList<String>> loadDataFromResource(String filepath, String sheetname, String exceltype) {
        HashMap<String, ArrayList<String>> data = new HashMap<String, ArrayList<String>>(); // Hashmap to store the excel data
        try {
            Base.log.info("Reading Data from Excel file............................");
            Workbook workbook = null;
            ArrayList<String> maincolumn = new ArrayList<String>();                                     // Store the keys of the data eg : cities,names
            InputStream inputstream = Base.class.getResourceAsStream(filepath);
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
