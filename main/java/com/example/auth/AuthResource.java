
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.inject.Inject;

@Path("/api/auth")
public class AuthResource {

    @Inject
    AuthService authService;

    @POST
    @Path("/login")
    public Response login(LoginRequest request) {
        try {
            String token = authService.login(request.email, request.password);
            return Response.ok(new TokenResponse(token)).build();
        } catch (Exception e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity("Invalid credentials").build();
        }
    }

    @POST
    @Path("/register")
    public Response register(RegisterRequest request) {
        return Response.ok("User registered").build();
    }

    @POST
    @Path("/reset-password")
    public Response resetPassword(ResetPasswordRequest request) {
        return Response.ok("Password reset").build();
    }
}
