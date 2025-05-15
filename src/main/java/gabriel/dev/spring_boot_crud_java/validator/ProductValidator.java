package gabriel.dev.spring_boot_crud_java.validator;

import gabriel.dev.spring_boot_crud_java.domain.Product;
import gabriel.dev.spring_boot_crud_java.exception.RegistroDuplicadoException;
import gabriel.dev.spring_boot_crud_java.repository.ProductRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductValidator {

    private final ProductRepository productRepository;

    public ProductValidator(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void validate(Product product){
        if (thereIsARegistered (product)){
            throw  new RegistroDuplicadoException("Product already registered");
        }
    }

    private boolean thereIsARegistered(Product product){
        Optional<Product> existingProduct = productRepository.findByProductName(
                product.getProductName()
        );

        if (product.getId() == null){
            return existingProduct.isPresent();
        }

        return !product.getId().equals(existingProduct.get().getId()) && existingProduct.isPresent();
    }

}
