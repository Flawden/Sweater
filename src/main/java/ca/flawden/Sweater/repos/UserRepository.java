package ca.flawden.Sweater.repos;

import ca.flawden.Sweater.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
//    List<User> findAllByByUsername(String username);

}
