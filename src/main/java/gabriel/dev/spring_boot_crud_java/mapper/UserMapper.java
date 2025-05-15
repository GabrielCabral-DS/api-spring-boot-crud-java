package gabriel.dev.spring_boot_crud_java.mapper;

import gabriel.dev.spring_boot_crud_java.domain.Users;
import gabriel.dev.spring_boot_crud_java.domain.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {


    UserDTO toDTO(Users user);
    Users toEntity(UserDTO userDTO);
    List<UserDTO> toDtoList(List<Users> users);

}
