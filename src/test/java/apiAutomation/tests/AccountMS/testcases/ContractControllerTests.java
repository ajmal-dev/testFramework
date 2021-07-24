package test.java.apiAutomation.tests.AccountMS.testcases;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import test.java.apiAutomation.corefunctions.BaseFunctions;
import test.java.apiAutomation.corefunctions.BaseUris;
import test.java.apiAutomation.tests.AccountMS.contractPojo.CreateContractPayload;
import test.java.apiAutomation.tests.AccountMS.contractPojo.regAddress;

import static io.restassured.RestAssured.given;

public class ContractControllerTests

{
    public static String contractNumber;


    // Request Type = POST
    // Api = /contracts
    //ENV  = UAT
    @Test
    public void createContractPost() {
        String accountNumber = AccountControllerTests.account_Number;
        RestAssured.defaultParser = Parser.JSON;
        regAddress regisAddress = new regAddress();
        String path="/contracts";
        String url= BaseUris.baseuri+path;
        CreateContractPayload payload = new CreateContractPayload(regisAddress, accountNumber);
        Response response=
                given()
                        .header("content-type", "application/json")
                        .body(payload)
                        .when()
                        .post(url)
                        .then()
                        .contentType(ContentType.JSON)
                        .extract()
                        .response();
        ExtentTest createContractTest = BaseFunctions.extentapi.createTest("Create contract post request test");
        if (response.statusCode() == 200 && response.getBody().jsonPath().getBoolean("contract_number") == true) {
            contractNumber = response.getBody().jsonPath().getString("contract_number");
            createContractTest.log(Status.PASS, "The Test passed");
            BaseFunctions.log.info("The Create Contract test passed");

        } else {
            createContractTest.log(Status.FAIL, "The test failed message: " + response.getBody().jsonPath().getString("message"));
            Assert.fail();
        }
    }


    // Request :      POST
    // Path:         /accounts/{account_number}/contracts/{contract_number}
    // Environment :  UAT
    // Functionality: Create get contract details.
        @Test
        public void getContractDetails()
        {
            String account_number="99023111";
            String contract_number="001";
            String path="/accounts"+"/"+account_number+"/contracts"+"/"+contract_number;
            String url=BaseUris.baseuri+path;
            RestAssured.defaultParser = Parser.JSON;

             Response response=       given()
                            .header("content-type", "application/json")
                            .when()
                            .get(url)
                            .then()
                            .contentType(ContentType.JSON)
                            .extract()
                            .response();

            ExtentTest getContractDetails=BaseFunctions.extentapi.createTest("get contract Details","Getting the contract details using contracct number and account nnumber");
            if(response.statusCode()==200 &&response.getBody().jsonPath().getString("contract_number").equalsIgnoreCase("001"))
            {
                getContractDetails.log(Status.PASS,"The get Contract Test is passed");
                BaseFunctions.log.info("The get contract Test passed");
            }
            else
            {
                getContractDetails.log(Status.FAIL,"The get contract details test failed Message : "+response.getBody().jsonPath().getString("message")+ "status code :"+response.statusCode());
                Assert.fail();
            }
        }






}
