package test.java.core;///*  @Author = Mohammed Ajmal */
//package main.java.testdriven.core;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//public class TestCaseLibrary
//{
//    private int number_of_testcases;
//    private int number_of_passed_testcases;
//    private int number_of_failed_testcases;
//    private String testcase_id;
//    private String testcase_status;
//    ExcelUtilis excelUtilis=new ExcelUtilis();
//    Base baseObjects=new Base();
//
//
//    // Method to get the number of testcases to be executed.
//    public int getNumber_of_testcases()
//    {
//        number_of_testcases=0;
//        try
//        {
//            number_of_testcases=Base.testcase_library_data.get("testcase_id").size();
//        }
//        catch(NullPointerException e)
//        {
//            e.printStackTrace();
//            Base.log.error("Error while getting the number of test cases");
//        }
//        return number_of_testcases;
//    }
//
//    // Methhod to get the number of passed testcases.
//    public int getNumber_of_passed_testcases()
//    {
//        number_of_passed_testcases=0;
//        int size_of_status=0;
//            ArrayList<String> status=Base.testcase_library_data.get("status");
//            size_of_status=status.size();
//            for(int i=0;i<size_of_status;i++)
//            {
//                if(status.get(i).equalsIgnoreCase("pass"))
//                {
//                    number_of_passed_testcases++;
//                }
//            }
//        return number_of_passed_testcases;
//    }
//
//    // Method to get number of failed testcases.
//    public int getNumber_of_failed_testcases()
//    {
//        number_of_failed_testcases=0;
//        int size_of_status=0;
//        ArrayList<String> status=Base.testcase_library_data.get("status");
//        size_of_status=status.size();
//        for(int i=0;i<size_of_status;i++)
//        {
//            if(status.get(i).equalsIgnoreCase("fail"))
//            {
//                number_of_failed_testcases++;
//            }
//        }
//        return number_of_failed_testcases;
//    }
//
//    // Method to fetch the test case id of the failed test cases
//    public ArrayList<String> getTestCaseIds(String status)
//    {
//        ArrayList<String> fetchedids = new ArrayList<String>();
//        List<String> statuslist=Base.testcase_library_data.get("status");
//        List<String> testcaseIds=Base.testcase_library_data.get("testcase_id");
//        int numOfStatus=statuslist.size();
//        for(int i=0;i<numOfStatus;i++)
//        {
//            if(statuslist.get(i).equalsIgnoreCase(status))
//            {
//                String testid=testcaseIds.get(i);
//                fetchedids.add(testid);
//            }
//        }
//        return fetchedids;
//    }
//    // Method to set the status  of testcases.
////    public void setStatus(String testcase_id,String status,String reasons )
////    {
////        try
////        {
////       excelUtilis.testStatus(testcase_id,status,reasons);
////        }
////        catch(IOException e)
////        {
////            e.printStackTrace();
////            Base.log.error("Cannot set status for test case");
////        }
////    }
//
//    public void getStatusResults()
//    {
//
//    }
//}
