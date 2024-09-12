
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import javax.inject.Inject;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
public class AuthServiceTest {

    @Inject
    AuthService authService;

    @Test
    public void testLoginSuccess() {
        String token = authService.login("test@example.com", "correct_password");
        assertNotNull(token);
    }

    @Test
    public void testLoginFailure() {
        assertThrows(SecurityException.class, () -> {
            authService.login("test@example.com", "wrong_password");
        });
    }
}
