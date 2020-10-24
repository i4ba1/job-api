package id.mni.job.repository;

import com.sun.xml.bind.v2.model.core.ID;
import id.mni.job.models.ERole;
import id.mni.job.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InterfaceRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
