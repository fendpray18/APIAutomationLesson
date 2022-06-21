package global;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseURL {

    @BeforeClass
    public void setup()
    {

        RestAssured.baseURI = "https://reqres.in";
    }
}
