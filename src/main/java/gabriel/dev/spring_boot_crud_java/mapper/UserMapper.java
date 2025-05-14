package gabriel.dev.spring_boot_crud_java.mapper;

import gabriel.dev.spring_boot_crud_java.domain.User;
import gabriel.dev.spring_boot_crud_java.domain.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {


    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);

}
