
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @Email
    @NotBlank
    public String email;
    
    @NotBlank
    public String password;
}
