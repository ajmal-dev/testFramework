package test.java.apiAutomation.tests.Graphql.Graphqlcc.tests;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import test.java.apiAutomation.corefunctions.BaseFunctions;
import test.java.apiAutomation.tests.Graphql.Graphqlcc.pojo.CcOrderInput;
import test.java.apiAutomation.tests.Graphql.Graphqlcc.pojo.CreateCcOrderPayload;
import test.java.apiAutomation.tests.Graphql.Graphqlcc.pojo.VariablesCreateCc;

import static io.restassured.RestAssured.given;

public class CreateCcorder
{
   @Test
    public void createCcOrder()
   {
       String url="http://a1c44ef5922e111eaa048065b50cf455-0a7eb6499af6ad81.elb.eu-west-1.amazonaws.com/graphql-cc/graphql";
       RestAssured.defaultParser= Parser.JSON;
       ExtentTest GraphqlCreatecc = BaseFunctions.extentapi.createTest("Graphql endpoint test for createCC");
       CcOrderInput input=new CcOrderInput("we24f2fg3g4g","API","SD2234424","ACHKNOWLEDGED");
       VariablesCreateCc variables=new VariablesCreateCc(input);
       CreateCcOrderPayload payload=new CreateCcOrderPayload("mutation createCcOrder($ccOrderInput: CcOrderCreateInput!) {\n createCcOrder(ccOrderInput: $ccOrderInput) {\n orderState\n createDate\n }\n}\n",variables,"createCcOrder");

       Response response= given()
               .header("Content-Type" ,"application/json")
               .body(payload)
               .when()
               .post(url)
               .then()
               .contentType(ContentType.JSON)
               .extract()
               .response();

       if (response.statusCode()==200 && response.getBody().jsonPath().getString("data.createCcOrder.orderState").equalsIgnoreCase("ACHKNOWLEDGED"))
       {
           GraphqlCreatecc.log(Status.PASS,"The graphql server test passed");
       }

   }
}
