package gabriel.dev.spring_boot_crud_java.repository;

import gabriel.dev.spring_boot_crud_java.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<Product> findByProductName(String productName);
}
