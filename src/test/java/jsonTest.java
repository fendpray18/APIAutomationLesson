import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class jsonTest {
    @Test
    public void first(){
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification request = given();

        JSONObject params = new JSONObject();
        params.put("name", "John Tere");
        params.put("job", "leader");

        request.body(params.toString());


        Response response = request.post("/api/users");
        response.then().assertThat().statusCode(201);

        System.out.println(response.asString());
    }
}
