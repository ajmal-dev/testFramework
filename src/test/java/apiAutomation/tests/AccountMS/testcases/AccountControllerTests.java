package test.java.apiAutomation.tests.AccountMS.testcases;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import test.java.apiAutomation.corefunctions.BaseFunctions;
import test.java.apiAutomation.corefunctions.BaseUris;
import test.java.apiAutomation.tests.AccountMS.accountpojo.createaccounts.AddressReg;
import test.java.apiAutomation.tests.AccountMS.accountpojo.createaccounts.CreateAccountPayload;
import test.java.apiAutomation.tests.AccountMS.accountpojo.createaccounts.ValidityofAccount;
import java.util.List;

public class AccountControllerTests
{

    public  static String account_Number;

    // Request Type = POST
    // Api = /accounts
    //ENV  = UAT
    @Test
    public void createAccountTest()
    {
        AddressReg regaddressobject=new AddressReg();
        CreateAccountPayload payload=new CreateAccountPayload(regaddressobject);
        RestAssured.defaultParser = Parser.JSON;
        String path="/accounts";
        String url=BaseUris.baseuri+path;

         Response response=       given().urlEncodingEnabled(true)
                        .body(payload)
                        .header("content-type","application/json")
                        .when()
                        .post(url)
                        .then()
                        // Response Validations
//                .assertThat().body("business_code",equalTo("BBS"))
                        .contentType(ContentType.JSON)
                        .extract()
                        .response();
        ExtentTest createAccountTest=BaseFunctions.extentapi.createTest("Create Account Test Path: /accounts");
        int statusCode=response.statusCode();


        //validations for response
        if(statusCode==200)
        {
            createAccountTest.log(Status.PASS,"The creation of account was successfull!");
            BaseFunctions.log.info("The create account test passed");
            account_Number=response.getBody().jsonPath().getString("account_number");
        }
        else
        {
            createAccountTest.log(Status.FAIL,"Error Message "  + response.jsonPath().getString("message"));
            createAccountTest.log(Status.FAIL,"Error Reason "  + response.jsonPath().getString("reason"));
            createAccountTest.log(Status.FAIL,"The create account test failed "  + statusCode);
            BaseFunctions.log.error("The Create account test post request failed "+ statusCode +response.getBody().prettyPrint());
            RestAssured.reset();
            Assert.fail();
        }
    }


    // Request Type = GET
    // Api = /accountProfiles
    //ENV  = UAT
    @Test
    public void getAccountProfileTest()
    {
        String path="/accountProfiles";
        String url= BaseUris.baseuri+path;
        RestAssured.defaultParser = Parser.JSON;

         Response response= given()
                        .header("content-type","application/json")
                        .param("id","00001195")
                        .param("id_type","ACCOUNT")
                        .param("user_type","RESIDENTIAL")
                        .when()
                        .get(url)
                        .then()
                        .statusCode(200)
                        .contentType(ContentType.JSON).extract().response();
        //Validations for response
        BaseFunctions.log.info("The getAccountProfile  Test  Started");
        ExtentTest Get_Account_Profile_Test= BaseFunctions.extentapi.createTest("Get Request of Account profile path : /accountProfile");
        Get_Account_Profile_Test.assignAuthor("Mohammed ajmal");
        String responseBody=response.getBody().asString();
        int httpstatuscode=response.getStatusCode();
        Get_Account_Profile_Test.log(Status.INFO,"The response received Successfully");
        BaseFunctions.log.info("The Response Received successfully!");
        Get_Account_Profile_Test.log(Status.INFO,"Validating the Response");
        List ids=response.getBody().jsonPath().getList("accounts.id");
        if(httpstatuscode==200&& ids.contains("00001195"))
        {
            Get_Account_Profile_Test.log(Status.PASS,"The profile details of account responded successfully");
            BaseFunctions.log.info("Validation successfully!" +httpstatuscode);
            assertThat(responseBody,matchesJsonSchemaInClasspath("AccountController/GetAccountProfile.json"));
        }
        else
        {
            Get_Account_Profile_Test.log(Status.FAIL,"The test failed"+httpstatuscode);
            BaseFunctions.log.info("The get Request for account profile  test failed" + httpstatuscode+" Reason: "+response.getBody().jsonPath().getString("reason"));
            RestAssured.reset();
            Assert.fail();
        }

        // json Schema validation

    }


    // Request Type = GET
    // Api = /accounts/{account_number}
    //ENV  = UAT
    @Test
    public void getAccountDetails()
    {
        String accountNumber=account_Number;
        String path="/accounts"+"/"+accountNumber;
        String url=BaseUris.baseuri+path;
        RestAssured.defaultParser=Parser.JSON;

         Response response=       given()
                                    .header("content-type","application/json")
                                    .when()
                                    .get(url)
                                    .then()
                                    .contentType(ContentType.JSON)
                                    .extract()
                                    .response();
        ExtentTest getAccountDetails=BaseFunctions.extentapi.createTest("Get Account Details API","Get details of the acount using account number");
        int httpStatusCode=response.statusCode();
        if(httpStatusCode==200 && response.getBody().jsonPath().getString("account_number").equalsIgnoreCase(account_Number))
        {
            getAccountDetails.log(Status.PASS,"The account details received successfully! Status Code "+httpStatusCode);
            BaseFunctions.log.info("The Test is successfull");
        }
        else
        {
            getAccountDetails.log(Status.FAIL,"The request failed reason: "+ response.getBody().jsonPath().getString("messsage")+"Reason: "+ httpStatusCode);
            Assert.fail();
        }
    }


    // Request Type = GET
    // Api = /accounts/{account_number}/isValidAccount
    // ENV  = UAT
    @Test
    public  void isAccountValid()
    {
        String accountNumber=account_Number;
        String path="/accounts"+"/"+accountNumber+"/isAccountValid";
        String url=BaseUris.baseuri+path;
        Faker data=new Faker();
        RestAssured.defaultParser=Parser.JSON;

         Response response=       given()
                                    .header("content-type","application/json")
                                    .param("email",data.name())
                                    .param("phone",data.phoneNumber())
                                    .when()
                                    .get(url)
                                    .then()
                                    .contentType(ContentType.JSON).extract().response();
         //Validation of responses

        ExtentTest isAccountValid=BaseFunctions.extentapi.createTest("Check account validity","check the account is valid using the account number");
        ValidityofAccount validity=response.getBody().as(ValidityofAccount.class);
        int httpStatusCode=response.statusCode();
        if(httpStatusCode==200&& validity.getValid().equals(true))
        {
            isAccountValid.log(Status.PASS,"The account is valid");
        }
        else if (httpStatusCode==200&& validity.getValid().equals(false))
        {
            isAccountValid.log(Status.WARNING,"The Account is Invalid");
        }
        else if(httpStatusCode!=200)
        {
            isAccountValid.log(Status.FAIL,"The test failed  status code :"+httpStatusCode+"Reason: "+response.getBody().jsonPath().getString("message"));
            Assert.fail();
        }
    }

}