package users;

import global.BaseURL;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class SingleUserTest extends BaseURL {
    @Test
    public void successSingleUserTest(ITestContext context){

        //System.out.println(context.getAttribute("id_user"));
        RequestSpecification request = given();

        Response response = request.get("/api/users"+context.getAttribute("id_user"));
        response.then().assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/resources/schema/user/SingleUser.json")))
        ;
        System.out.println(response.asString());
    }
}
