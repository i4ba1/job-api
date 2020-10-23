package id.mni.job.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tbl_user")
public class User {
    @Id
    private BigInteger id;

    @Column(name="username", columnDefinition = "varchar(100)", nullable = false)
    private String username;

    @Column(name="passwd", columnDefinition = "varchar(100)", nullable = false)
    private String password;

    @Column(name="created_at", columnDefinition = "timestamp", nullable = false)
    private Timestamp createdAt;

    @Column(name="updated_at", columnDefinition = "timestamp", nullable = false)
    private Timestamp updatedAt;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(	name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public User(String username, String password, Timestamp from, Timestamp from1) {
    }
}
