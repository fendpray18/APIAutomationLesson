import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class jsonSchemaTest {
    @Test
    public void first(){
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification request = given();

        JSONObject params = new JSONObject();
        params.put("name", "John Tere");
        params.put("job", "leader");

        request.header("Content-Type", "application/json");
        request.body(params.toString());
        String schemaPath = "src/resources/CreateUserSchema.json";


        Response response = request.post("/api/users");
        response.then().assertThat().statusCode(201).
            body(JsonSchemaValidator.matchesJsonSchema(new File(schemaPath)));

        System.out.println(response.asString());
    }
}
