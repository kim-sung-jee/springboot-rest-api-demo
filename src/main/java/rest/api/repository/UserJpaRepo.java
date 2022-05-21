package rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.api.entity.User;

import java.util.Optional;

public interface UserJpaRepo extends JpaRepository<User,Long> {
    Optional<User> findByUid(String email);
}
