package gabriel.dev.spring_boot_crud_java.repository;

import gabriel.dev.spring_boot_crud_java.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}
