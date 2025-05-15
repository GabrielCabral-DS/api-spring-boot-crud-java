package gabriel.dev.spring_boot_crud_java.repository;

import gabriel.dev.spring_boot_crud_java.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {

   Users findByEmail(String email);
}
