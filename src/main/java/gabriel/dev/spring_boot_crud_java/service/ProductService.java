package gabriel.dev.spring_boot_crud_java.service;

import gabriel.dev.spring_boot_crud_java.domain.Product;
import gabriel.dev.spring_boot_crud_java.domain.dto.ProductDTO;
import gabriel.dev.spring_boot_crud_java.mapper.ProductMapper;
import gabriel.dev.spring_boot_crud_java.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductService(ProductMapper productMapper, ProductRepository productRepository) {
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }


    public ProductDTO registerProduct(ProductDTO productDTO){
        Product product = productMapper.toEntity(productDTO);
        return productMapper.toDTO(productRepository.save(product));
    }

    public List<ProductDTO> findAllProducts(){
        List<Product> products = productRepository.findAll();
        return productMapper.todoDtoList(products);
    }

    public ProductDTO getProductById(Integer id){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        return productMapper.toDTO(product);
    }

    public ProductDTO updateProduct(Integer id, ProductDTO productDTO){
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setProductName(productDTO.getProductName());
        product.setPrice(productDTO.getPrice());
        product.setCategory(productDTO.getCategory());
        product.setQuantity(productDTO.getQuantity());
        return productMapper.toDTO(productRepository.save(product));
    }

    public void deleteProductById(Integer id){
        productRepository.deleteById(id);
    }



}
