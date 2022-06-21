package users;

import global.BaseURL;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class ListUsersTest extends BaseURL {
    @Test
    public void succesListUsersTest(ITestContext context){
        RequestSpecification request = given();
        request.param("page", 2);

        Response response = request.get("/api/users");
        response.then().assertThat()
                .statusCode(200)
                .body(JsonSchemaValidator.matchesJsonSchema(new File("src/resources/schema/user/ListUsers.json")))
        ;

        System.out.println(response.jsonPath().getInt("data[0].id"));
        System.out.println(response.asString());
        //System.out.println(response.jsonPath().getString("data[0].email"));
        context.setAttribute("id_user", response.jsonPath().getInt("data[0].id"));
    }
}
