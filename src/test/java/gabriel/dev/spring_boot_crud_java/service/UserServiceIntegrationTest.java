package gabriel.dev.spring_boot_crud_java.service;

import gabriel.dev.spring_boot_crud_java.domain.Users;
import gabriel.dev.spring_boot_crud_java.domain.dto.UserDTO;
import gabriel.dev.spring_boot_crud_java.mapper.UserMapper;
import gabriel.dev.spring_boot_crud_java.repository.UserRepository;
import gabriel.dev.spring_boot_crud_java.validator.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserValidator userValidator;

    private Users user;
    private UserDTO userDTO;
    private UUID userId;

    @BeforeEach
    void setUp() {
        user = new Users();
        user.setName("Gabriel");
        user.setEmail("gabriel@email.com");
        user.setPassword(passwordEncoder.encode("senha123"));
        userRepository.save(user);
        userId = user.getId();

        userDTO = new UserDTO();
        userDTO.setId(userId);
        userDTO.setName("Gabriel");
        userDTO.setEmail("gabriel@email.com");
        userDTO.setPassword("senha123");
    }

    @Test
    void testSaveUsers() {
        UserDTO newUserDTO = new UserDTO();
        newUserDTO.setName("Novo User");
        newUserDTO.setEmail("novo@email.com");
        newUserDTO.setPassword("123456");

        UserDTO savedUser = userService.saveUsers(newUserDTO);

        assertNotNull(savedUser.getId());
        assertEquals("Novo User", savedUser.getName());
        assertEquals("novo@email.com", savedUser.getEmail());

        Users savedEntity = userRepository.findById(savedUser.getId()).orElse(null);
        assertNotNull(savedEntity);
        assertEquals("Novo User", savedEntity.getName());
    }

    @Test
    void testFindAllUsers() {
        List<UserDTO> users = userService.findAllUsers();

        assertFalse(users.isEmpty());
        assertTrue(users.stream().anyMatch(u -> u.getEmail().equals("gabriel@email.com")));
    }

    @Test
    void testGetByEmail_UserExists() {
        UserDTO found = userService.getByEmail("gabriel@email.com");

        assertNotNull(found);
        assertEquals("Gabriel", found.getName());
    }

    @Test
    void testGetByEmail_UserDoesNotExist() {
        UserDTO found = userService.getByEmail("naoexiste@email.com");

        assertNull(found);
    }

    @Test
    void testFindUsersById_UserExists() {
        UserDTO found = userService.findUsersById(userId);

        assertNotNull(found);
        assertEquals("Gabriel", found.getName());
    }

    @Test
    void testFindUsersById_UserNotFound() {
        UUID fakeId = UUID.randomUUID();

        assertThrows(RuntimeException.class, () -> userService.findUsersById(fakeId));
    }

    @Test
    void testUpdateUsers_UserExists() {
        UserDTO updateDTO = new UserDTO();
        updateDTO.setName("Gabriel Atualizado");
        updateDTO.setEmail("gabriel.atualizado@email.com");
        updateDTO.setPassword("novaSenha");

        UserDTO updated = userService.updateUsers(userId, updateDTO);

        assertEquals("Gabriel Atualizado", updated.getName());
        assertEquals("gabriel.atualizado@email.com", updated.getEmail());

        Users updatedEntity = userRepository.findById(userId).orElseThrow();
        assertEquals("Gabriel Atualizado", updatedEntity.getName());
    }

    @Test
    void testUpdateUsers_UserNotFound() {
        UUID fakeId = UUID.randomUUID();

        UserDTO updateDTO = new UserDTO();
        updateDTO.setName("Nome qualquer");

        assertThrows(RuntimeException.class, () -> userService.updateUsers(fakeId, updateDTO));
    }

    @Test
    void testDeleteUsersById_UserExists() {
        userService.deleteUsersById(userId);

        assertFalse(userRepository.findById(userId).isPresent());
    }

    @Test
    void testDeleteUsersById_UserNotFound() {
        UUID fakeId = UUID.randomUUID();

        assertThrows(RuntimeException.class, () -> userService.deleteUsersById(fakeId));
    }
}
