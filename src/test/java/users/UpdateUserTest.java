package users;

import global.BaseURL;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.core.Is;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UpdateUserTest extends BaseURL {
    @Test
    public void updateuser() {

        updateUserTest("morpheus", "zion resident");
    }

    public void updateUserTest (String name, String job) {
        RequestSpecification request = given();
        JSONObject params = new JSONObject();
        params.put("name", name);
        params.put("job", job);

        request.body(params.toString());

        request.header("Content-Type", "application/json");
        Response response = request.put("/api/users/2");
        response.then().assertThat()
                .statusCode(200)
                .body("name", Is.is("morpheus"))
                .body("job", Is.is("zion resident"))
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/resources/schema/user/Update.json")));
        System.out.println(response.asString());
    }
}
