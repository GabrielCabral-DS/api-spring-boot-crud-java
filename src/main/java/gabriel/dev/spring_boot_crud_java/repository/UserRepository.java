package gabriel.dev.spring_boot_crud_java.repository;

import gabriel.dev.spring_boot_crud_java.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {

   Optional<Users> findByEmail(String email);
   Optional<Users> findByName(String name);
}
