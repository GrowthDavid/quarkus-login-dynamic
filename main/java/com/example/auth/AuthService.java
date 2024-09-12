
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import io.quarkus.security.identity.SecurityIdentity;
import io.smallrye.jwt.build.Jwt;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import org.mindrot.jbcrypt.BCrypt;

@ApplicationScoped
public class AuthService {

    @Inject
    UserRepository userRepository;

    public String login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || user.failedLoginAttempts >= 5) {
            throw new SecurityException("Conta bloqueada ou inexistente.");
        }
        if (verifyPassword(password, user.passwordHash)) {
            user.failedLoginAttempts = 0;
            userRepository.persist(user);
            return generateToken(user);
        } else {
            user.failedLoginAttempts++;
            userRepository.persist(user);
            throw new SecurityException("Senha inválida.");
        }
    }

    public String generateToken(User user) {
        Set<String> roles = new HashSet<>();
        roles.add(user.role);
        return Jwt.issuer("https://your-issuer.com")
                .upn(user.email)
                .groups(roles)
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600))
                .sign();
    }

    private boolean verifyPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    private String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }
}
