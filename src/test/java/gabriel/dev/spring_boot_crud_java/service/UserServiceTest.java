package gabriel.dev.spring_boot_crud_java.service;

import gabriel.dev.spring_boot_crud_java.domain.Users;
import gabriel.dev.spring_boot_crud_java.domain.dto.UserDTO;
import gabriel.dev.spring_boot_crud_java.mapper.UserMapper;
import gabriel.dev.spring_boot_crud_java.repository.UserRepository;
import gabriel.dev.spring_boot_crud_java.validator.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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

    private Users user;
    private UserDTO userDTO;
    private UUID userId;

    @BeforeEach
    void setUp() {
        userId = UUID.randomUUID();
        user = new Users();
        user.setId(userId);
        user.setName("Gabriel");
        user.setEmail("gabriel@email.com");
        user.setPassword("senha123");

        userDTO = new UserDTO();
        userDTO.setId(userId);
        userDTO.setName("Gabriel");
        userDTO.setEmail("gabriel@email.com");
        userDTO.setPassword("senha123");
    }

    @Test
    void testSaveUsers() {
        when(userMapper.toEntity(userDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDTO(user)).thenReturn(userDTO);

        UserDTO result = userService.saveUsers(userDTO);

        verify(userValidator).validate(user);
        verify(userRepository).save(user);
        assertEquals(userDTO, result);
    }

    @Test
    void testFindAllUsers() {
        List<Users> usersList = List.of(user);
        List<UserDTO> dtoList = List.of(userDTO);

        when(userRepository.findAll()).thenReturn(usersList);
        when(userMapper.toDtoList(usersList)).thenReturn(dtoList);

        List<UserDTO> result = userService.findAllUsers();

        assertEquals(1, result.size());
        assertEquals(userDTO, result.get(0));
    }

    @Test
    void testGetByEmail_UserExists() {
        when(userRepository.findByEmail("gabriel@email.com")).thenReturn(Optional.of(user));
        when(userMapper.toDTO(user)).thenReturn(userDTO);

        UserDTO result = userService.getByEmail("gabriel@email.com");

        assertNotNull(result);
        assertEquals("Gabriel", result.getName());
    }

    @Test
    void testGetByEmail_UserDoesNotExist() {
        when(userRepository.findByEmail("naoexiste@email.com")).thenReturn(Optional.empty());

        UserDTO result = userService.getByEmail("naoexiste@email.com");

        assertNull(result);
    }

    @Test
    void testFindUsersById_UserExists() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(userMapper.toDTO(user)).thenReturn(userDTO);

        UserDTO result = userService.findUsersById(userId);

        assertNotNull(result);
        assertEquals(userDTO, result);
    }

    @Test
    void testFindUsersById_UserNotFound() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.findUsersById(userId));
    }

    @Test
    void testUpdateUsers_UserExists() {
        UserDTO updatedDTO = new UserDTO();
        updatedDTO.setName("Novo Nome");
        updatedDTO.setEmail("novo@email.com");
        updatedDTO.setPassword("novaSenha");
        updatedDTO.setPhone("123456789");
        updatedDTO.setAddress("Rua 123");
        updatedDTO.setCity("Cidade");
        updatedDTO.setState("Estado");

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(passwordEncoder.encode("novaSenha")).thenReturn("senhaCodificada");
        when(userRepository.save(any(Users.class))).thenReturn(user);
        when(userMapper.toDTO(user)).thenReturn(updatedDTO);

        UserDTO result = userService.updateUsers(userId, updatedDTO);

        verify(userValidator).validate(user);
        verify(userRepository).save(user);
        assertEquals("Novo Nome", result.getName());
        assertEquals("novo@email.com", result.getEmail());
    }

    @Test
    void testUpdateUsers_UserNotFound() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.updateUsers(userId, userDTO));
    }

    @Test
    void testDeleteUsersById_UserExists() {
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        userService.deleteUsersById(userId);

        verify(userRepository).delete(user);
    }

    @Test
    void testDeleteUsersById_UserNotFound() {
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userService.deleteUsersById(userId));
    }
}
