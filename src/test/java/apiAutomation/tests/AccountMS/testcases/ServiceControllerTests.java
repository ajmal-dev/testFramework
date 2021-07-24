package test.java.apiAutomation.tests.AccountMS.testcases;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import test.java.apiAutomation.corefunctions.BaseFunctions;
import test.java.apiAutomation.corefunctions.BaseUris;

import static io.restassured.RestAssured.given;

public class ServiceControllerTests
{
    //Suspend Account
    // Request Type = POST
    // Api =/accounts/{account_number}/contracts/{contract_number}/action/{action_code}
    //ENV  = UAT
    @Test
    public void patchAccountContract()
    {
        ExtentTest patchaccountContract= BaseFunctions.extentapi.createTest("suspend the account");
        String accountNumber= AccountControllerTests.account_Number;
        String contractNumber=ContractControllerTests.contractNumber;
        String path="/accounts/"+accountNumber+"/contracts/"+contractNumber+"/action/suspend";
        String url= BaseUris.baseuri+path;
        RestAssured.defaultParser= Parser.JSON;

        Response response = given()
                .header("content-type", "application/json")
                .when()
                .patch(url)
                .then()
                .contentType(ContentType.JSON)
                .extract()
                .response();

        // validation of responses

        if(response.statusCode()==200)
        {
            patchaccountContract.log(Status.PASS,"The acccount was successfully suspended");
        }
        else
        {
            BaseFunctions.log.info("The test for account patch has been failed");
            patchaccountContract.log(Status.FAIL,"The test for account patch has been failed");
        }
    }
}
