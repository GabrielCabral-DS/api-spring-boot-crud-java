package gabriel.dev.spring_boot_crud_java.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String productName;
    private double price;
    private Integer quantity;
    private String category;


    public Product(){

    }

    public Product(Integer id, String productName, double price, Integer quantity, String category){
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.category = category;
    }


}
