package rest.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rest.api.entity.User;

public interface UserJpaRepo extends JpaRepository<User,Long> {

}
