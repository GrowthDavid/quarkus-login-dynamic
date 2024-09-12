
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class AuthResourceTest {

    @Test
    public void testLoginEndpoint() {
        given()
          .contentType("application/json")
          .body("{"email":"test@example.com", "password":"correct_password"}")
          .when().post("/api/auth/login")
          .then()
             .statusCode(200)
             .body("token", is(notNullValue()));
    }
}
