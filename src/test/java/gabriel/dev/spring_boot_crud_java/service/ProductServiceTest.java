package gabriel.dev.spring_boot_crud_java.service;

import gabriel.dev.spring_boot_crud_java.domain.Product;
import gabriel.dev.spring_boot_crud_java.domain.dto.ProductDTO;
import gabriel.dev.spring_boot_crud_java.mapper.ProductMapper;
import gabriel.dev.spring_boot_crud_java.repository.ProductRepository;
import gabriel.dev.spring_boot_crud_java.validator.ProductValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductMapper productMapper;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductValidator productValidator;

    @InjectMocks
    private ProductService productService;

    @Test
    @DisplayName("Test registerProduct method")
    void testRegisterProduct() {
        ProductDTO productDTO = new ProductDTO();
        Product product = new Product();
        Product savedProduct = new Product();
        ProductDTO resultDTO = new ProductDTO();

        when(productMapper.toEntity(productDTO)).thenReturn(product);
        when(productRepository.save(product)).thenReturn(savedProduct);
        when(productMapper.toDTO(savedProduct)).thenReturn(resultDTO);

        ProductDTO result = productService.registerProduct(productDTO);

        verify(productValidator).validate(product);
        assertEquals(resultDTO, result);
    }

    @Test
    @DisplayName("Test findAllProducts method")
    void testFindAllProducts() {
        List<Product> products = List.of(new Product(), new Product());
        List<ProductDTO> productDTOs = List.of(new ProductDTO(), new ProductDTO());

        when(productRepository.findAll()).thenReturn(products);
        when(productMapper.todoDtoList(products)).thenReturn(productDTOs);

        List<ProductDTO> result = productService.findAllProducts();

        assertEquals(productDTOs.size(), result.size());
    }

    @Test
    @DisplayName("Test getProductById method")
    void testGetProductById_Found() {
        Integer id = 1;
        Product product = new Product();
        ProductDTO dto = new ProductDTO();

        when(productRepository.findById(id)).thenReturn(Optional.of(product));
        when(productMapper.toDTO(product)).thenReturn(dto);

        ProductDTO result = productService.getProductById(id);

        assertEquals(dto, result);
    }

    @Test
    @DisplayName("Test getProductById method - Not Found")
    void testGetProductById_NotFound() {
        Integer id = 1;
        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.getProductById(id));
    }

    @Test
    @DisplayName("Test updateProduct method")
    void testUpdateProduct_Success() {
        Integer id = 1;
        ProductDTO productDTO = new ProductDTO();
        productDTO.setProductName("New Product");
        productDTO.setPrice(99.99);
        productDTO.setCategory("Eletrônicos");
        productDTO.setQuantity(10);

        Product existingProduct = new Product();
        Product savedProduct = new Product();
        ProductDTO resultDTO = new ProductDTO();

        when(productRepository.findById(id)).thenReturn(Optional.of(existingProduct));
        when(productRepository.save(existingProduct)).thenReturn(savedProduct);
        when(productMapper.toDTO(savedProduct)).thenReturn(resultDTO);

        ProductDTO result = productService.updateProduct(id, productDTO);

        verify(productValidator).validate(existingProduct);
        assertEquals(resultDTO, result);
    }

    @Test
    @DisplayName("Test updateProduct method - Not Found")
    void testUpdateProduct_NotFound() {
        Integer id = 1;
        ProductDTO productDTO = new ProductDTO();

        when(productRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> productService.updateProduct(id, productDTO));
    }

    @Test
    @DisplayName("Test deleteProductById method")
    void testDeleteProductById() {
        Integer id = 1;

        productService.deleteProductById(id);

        verify(productRepository).deleteById(id);
    }

}