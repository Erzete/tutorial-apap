package apapTutorial.bacabaca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import apapTutorial.bacabaca.model.UserModel;

@Repository
public interface UserDb extends JpaRepository<UserModel, Long> {
    UserModel findByUsername(String username);
    UserModel findByUsernameAndPassword(String usename, String password);
}
