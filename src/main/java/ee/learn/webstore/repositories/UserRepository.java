package ee.learn.webstore.repositories;

import ee.learn.webstore.entities.UserEE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEE, Long> {

    public UserEE findByUsername(String username);

}
