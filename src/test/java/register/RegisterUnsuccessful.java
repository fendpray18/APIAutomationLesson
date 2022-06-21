package register;

import global.BaseURL;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class RegisterUnsuccessful extends BaseURL {
    @Test
    public void registerUnsuccessful(){
        RequestSpecification request = given();
        JSONObject params = new JSONObject();
        params.put("email", "fendy@majoo.id");

        request.body(params.toString());

        request.header("Content-Type", "application/json");
        Response response = request.post("/api/register");
        response.then().assertThat()
                .statusCode(400)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/resources/schema/register/RegisterUnsuccessful.json")));
        System.out.println(response.asString());
    }

}
