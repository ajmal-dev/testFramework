package test.java.uiAutomation.core;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExcelUtilis {
    Base baseObject = new Base();
    private HashMap<String, ArrayList<String>> user_data = loadDataFromResource("/datainput.xlsx", "users", "vertical");
    //**************** Method to Load all data in excel sheet (pass horizotal/Vertical data pattern)
    public HashMap<String, ArrayList<String>> loadDataFromResource(String filepath,String sheetname, String exceltype) {
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

    public void writeInExcel(String filepath,String sheetname,int rownum,int columnnum,String data) throws IOException {
        File file = new File(filepath);       //reports/testcase_Report.xlsx
        FileOutputStream outputStream = new FileOutputStream(file);
        XSSFWorkbook workbook=new XSSFWorkbook();
        XSSFSheet sheet=workbook.getSheet(sheetname);
        Row row=sheet.getRow(rownum);
        Cell cell=row.getCell(columnnum);
        cell.setCellValue(data);
        workbook.write(outputStream);
    }

    // ***************Method to write testcase execution status.***
//    public static void testStatus(String testcaseNumber, String status, String reason) throws IOException {
//        Base.log.info("Updating the testcase status in the test_case Report.xlsx");
//        File file = new File(Base.propertiesRead("testCaseReportPath"));       //reports/testcase_Report.xlsx
//        FileInputStream inputstream = new FileInputStream(file);
//        XSSFWorkbook workbook = new XSSFWorkbook(inputstream);
//        XSSFSheet sheet = workbook.getSheet("status");
//        int rowcount = sheet.getPhysicalNumberOfRows();
//        for (int i = 1; i <= rowcount; i++) {
//            Row row = sheet.getRow(i);
//            Cell cell = row.getCell(0);
//            String value = cell.getStringCellValue();
//
//            if (value.equalsIgnoreCase(testcaseNumber)) {
//                Cell statuscell = row.getCell(2);
//                Cell reasoncell = row.getCell(3);
//                statuscell.setCellValue(status);            // Assign the status of the test case
//                reasoncell.setCellValue(reason);            // Write the reason for failure
//                break;
//            }
//        }
//        inputstream.close();
//        FileOutputStream outputStream = new FileOutputStream(file);
//        workbook.write(outputStream);
//        outputStream.close();
//
//    }



    // method to get index of the  type  of user from excel file sheet
    public int getUserTypeIndex(String type)
    {
        int type_index=0;
        List<String> type_list= user_data.get("Type");
        int number_of_types=type_list.size();
        for(int i=0;i<number_of_types;i++)
        {
            if(type_list.get(i).equalsIgnoreCase(type))
            {
                type_index=i;
                break;
            }
        }
        return type_index;
    }
    // Method to get user id from the type of  user
    public String getUserId(String type)
    {
        int type_index=getUserTypeIndex(type);
        String user_id=user_data.get("user id").get(type_index);
        return user_id;
    }
    // get the password of the user by userid.
    public String getPassword(String user_id)
    {
        String password=null;
        int user_id_index=0;
        List<String> users_list= user_data.get("user id");
        int number_of_users=users_list.size();
        for(int i=0;i<number_of_users;i++)
        {
            if(users_list.get(i).equalsIgnoreCase(user_id))
            {
                user_id_index=i;
                password=user_data.get("password").get(user_id_index);
                break;
            }
        }
        return password;
    }
}
