package id.mni.job.repository;

import id.mni.job.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface InterfaceUserRepository extends JpaRepository<User, BigInteger> {
    Boolean existsByUsername(String username);

    @Query("select u from User u join fetch u.roles where u.username = ?1")
    Optional<User> findByUsername(String username);
}
