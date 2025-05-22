package gabriel.dev.spring_boot_crud_java.service;

import gabriel.dev.spring_boot_crud_java.domain.Users;
import gabriel.dev.spring_boot_crud_java.domain.dto.UserDTO;
import gabriel.dev.spring_boot_crud_java.mapper.UserMapper;
import gabriel.dev.spring_boot_crud_java.repository.UserRepository;
import gabriel.dev.spring_boot_crud_java.validator.UserValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserMapper userMapper;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserValidator userValidator;

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Test saveUsers method")
    void testSaveUsers() {
        UserDTO userDTO = new UserDTO();
        Users user = new Users();
        Users savedUser = new Users();
        UserDTO resultDTO = new UserDTO();

        when(userMapper.toEntity(userDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(savedUser);
        when(userMapper.toDTO(savedUser)).thenReturn(resultDTO);

        UserDTO result = userService.saveUsers(userDTO);

        verify(userValidator).validate(user);
        assertEquals(resultDTO, result);
    }

    @Test
    @DisplayName("Test findAllUsers method")
    void testFindAllUsers() {
        List<Users> users = List.of(new Users(), new Users());
        List<UserDTO> userDTOs = List.of(new UserDTO(), new UserDTO());

        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.toDtoList(users)).thenReturn(userDTOs);

        List<UserDTO> result = userService.findAllUsers();

        assertEquals(userDTOs.size(), result.size());
    }

    @Test
    @DisplayName("Test getByEmail method")
    void testGetByEmail_Found() {
        String email = "email@test.com";
        Users user = new Users();
        UserDTO dto = new UserDTO();

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(userMapper.toDTO(user)).thenReturn(dto);

        UserDTO result = userService.getByEmail(email);

        assertEquals(dto, result);
    }

    @Test
    @DisplayName("Test getByEmail method - Not Found")
    void testGetByEmail_NotFound() {
        String email = "notfound@test.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        UserDTO result = userService.getByEmail(email);

        assertNull(result);
    }

    @Test
    @DisplayName("Test findUsersById method")
    void testFindUsersById_Found() {
        UUID id = UUID.randomUUID();
        Users user = new Users();
        UserDTO dto = new UserDTO();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(userMapper.toDTO(user)).thenReturn(dto);

        UserDTO result = userService.findUsersById(id);

        assertEquals(dto, result);
    }

    @Test
    @DisplayName("Test findUsersById method - Not Found")
    void testFindUsersById_NotFound() {
        UUID id = UUID.randomUUID();
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.findUsersById(id));
    }

    @Test
    @DisplayName("Test updateUsers method")
    void testUpdateUsers_Success() {
        UUID id = UUID.randomUUID();
        UserDTO userDTO = new UserDTO();
        userDTO.setPassword("123");
        Users user = new Users();
        Users updatedUser = new Users();
        UserDTO resultDTO = new UserDTO();

        when(userRepository.findById(id)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("123")).thenReturn("encoded123");
        when(userRepository.save(user)).thenReturn(updatedUser);
        when(userMapper.toDTO(updatedUser)).thenReturn(resultDTO);

        UserDTO result = userService.updateUsers(id, userDTO);

        verify(userValidator).validate(user);
        assertEquals(resultDTO, result);
    }

    @Test
    @DisplayName("Test updateUsers method - Not Found")
    void testUpdateUsers_NotFound() {
        UUID id = UUID.randomUUID();
        UserDTO userDTO = new UserDTO();
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.updateUsers(id, userDTO));
    }

    @Test
    @DisplayName("Test deleteUsersById method")
    void testDeleteUsersById_Success() {
        UUID id = UUID.randomUUID();
        Users user = new Users();
        when(userRepository.findById(id)).thenReturn(Optional.of(user));

        userService.deleteUsersById(id);

        verify(userRepository).delete(user);
    }

    @Test
    @DisplayName("Test deleteUsersById method - Not Found")
    void testDeleteUsersById_NotFound() {
        UUID id = UUID.randomUUID();
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.deleteUsersById(id));
    }

}