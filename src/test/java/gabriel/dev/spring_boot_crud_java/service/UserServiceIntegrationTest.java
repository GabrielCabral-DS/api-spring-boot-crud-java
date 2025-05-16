package gabriel.dev.spring_boot_crud_java.service;

import gabriel.dev.spring_boot_crud_java.domain.Users;
import gabriel.dev.spring_boot_crud_java.domain.dto.UserDTO;
import gabriel.dev.spring_boot_crud_java.mapper.UserMapper;
import gabriel.dev.spring_boot_crud_java.repository.UserRepository;
import gabriel.dev.spring_boot_crud_java.validator.UserValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
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
        user.setName("Gabriel Cabral");
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
    void testFindUsersById_UserExists() {
        UserDTO found = userService.findUsersById(userId);

        assertNotNull(found);
        assertEquals("Gabriel Cabral", found.getName());
    }

    @Test
    void testFindUsersById_UserNotFound() {
        UUID fakeId = UUID.randomUUID();

        assertThrows(RuntimeException.class, () -> userService.findUsersById(fakeId));
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
