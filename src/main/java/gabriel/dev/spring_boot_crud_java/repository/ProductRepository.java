package gabriel.dev.spring_boot_crud_java.repository;

import gabriel.dev.spring_boot_crud_java.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
