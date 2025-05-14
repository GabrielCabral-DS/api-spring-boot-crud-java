package gabriel.dev.spring_boot_crud_java.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDTO {

    private UUID id;
    @NotBlank(message = "Campo Obrigatório!")
    private String name;
    @NotBlank(message = "Campo Obrigatório!")
    private String email;
    @NotBlank(message = "Campo Obrigatório!")
    private String password;
    @NotBlank(message = "Campo Obrigatório!")
    private String phone;
    @NotBlank(message = "Campo Obrigatório!")
    private String address;
    @NotBlank(message = "Campo Obrigatório!")
    private String city;
    @NotBlank(message = "Campo Obrigatório!")
    private String state;

    public UserDTO(){

    }

    public UserDTO(User user){
        id = user.getId();
        name = user.getName();
        email = user.getEmail();
        password = user.getPassword();
        phone = user.getPhone();
        address = user.getAddress();
        city = user.getCity();
        state = user.getState();
    }
}
