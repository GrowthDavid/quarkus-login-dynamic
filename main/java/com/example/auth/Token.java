
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Entity;
import javax.persistence.Column;

@Entity
public class Token extends PanacheEntity {

    @Column(nullable = false, unique = true)
    public String token;

    @Column(nullable = false)
    public Long userId;

    @Column(nullable = false)
    public String type; 

    @Column(nullable = false)
    public long expiration;
}
