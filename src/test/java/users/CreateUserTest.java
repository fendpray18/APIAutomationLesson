package users;

import global.BaseURL;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class CreateUserTest extends BaseURL {
    @Test

    public void CreateUserTest()
    {
        RequestSpecification request = given();
        JSONObject params = new JSONObject();
        params.put("name", "morpheus");
        params.put("job", "leader");

        request.body(params.toString());

        request.header("Content-Type", "application/json");
        Response response = request.post("/api/users");
        response.then().assertThat()
                .statusCode(201)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/resources/schema/user/Create.json")));
        System.out.println(response.asString());
    }



}
