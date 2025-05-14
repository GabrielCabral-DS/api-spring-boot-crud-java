package gabriel.dev.spring_boot_crud_java.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    private String name;
    private String email;
    private String password;
    private String phone;
    private String address;
    private String city;
    private String state;

    public User(){

    }

    public User(String name,String email,String password, String phone, String address, String city, String state){
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
    }
}
