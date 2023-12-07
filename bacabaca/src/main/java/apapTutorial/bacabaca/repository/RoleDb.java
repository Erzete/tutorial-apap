package apapTutorial.bacabaca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import apapTutorial.bacabaca.model.Role;

@Repository
public interface RoleDb extends JpaRepository<Role, Long> {
    List<Role> findAll();
    Optional<Role> findByRole(String role);
}
