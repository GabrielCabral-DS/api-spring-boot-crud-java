package gabriel.dev.spring_boot_crud_java.mapper;

import gabriel.dev.spring_boot_crud_java.domain.Product;
import gabriel.dev.spring_boot_crud_java.domain.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDTO toDTO(Product product);
    Product toEntity(ProductDTO productDTO);

}
