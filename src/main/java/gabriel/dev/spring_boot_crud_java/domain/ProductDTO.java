package gabriel.dev.spring_boot_crud_java.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO {

    private Integer id;
    @NotBlank(message = "Nome do produto é Obrigatório!")
    private String productName;
    @NotNull(message = "Campo Obrigatório!")
    private double price;
    @NotNull(message = "Campo Obrigatório!")
    private Integer quantity;
    @NotBlank(message = "Campo Obrigatório!")
    private String category;



    public  ProductDTO(){

    }


    public ProductDTO(Product product){
        id = product.getId();
        productName = product.getProductName();
        price = product.getPrice();
        quantity = product.getQuantity();
        category = product.getCategory();

    }

}
