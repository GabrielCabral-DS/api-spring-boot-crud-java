package gabriel.dev.spring_boot_crud_java.service;

import gabriel.dev.spring_boot_crud_java.domain.Users;
import gabriel.dev.spring_boot_crud_java.domain.dto.UserDTO;
import gabriel.dev.spring_boot_crud_java.mapper.UserMapper;
import gabriel.dev.spring_boot_crud_java.repository.UserRepository;
import gabriel.dev.spring_boot_crud_java.validator.UserValidator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {


    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserValidator userValidator;

    public UserService(UserMapper userMapper, UserRepository userRepository, PasswordEncoder passwordEncoder, UserValidator userValidator) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userValidator = userValidator;
    }



    public UserDTO saveUsers(UserDTO userDTO){
        Users user = userMapper.toEntity(userDTO);
        userValidator.validate(user);
        return userMapper.toDTO(userRepository.save(user));
    }

    public List<UserDTO> findAllUsers(){
        List<Users> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    public UserDTO getByEmail(String email){
        Optional<Users> user = userRepository.findByEmail(email);
        return user.map(userMapper::toDTO).orElse(null);
    }

    public UserDTO findUsersById(UUID id){
        Users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.toDTO(user);
    }

    public UserDTO updateUsers(UUID id, UserDTO userDTO){
        Users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setCity(userDTO.getCity());
        user.setState(userDTO.getState());
        userValidator.validate(user);
        return userMapper.toDTO(userRepository.save(user));
    }

    public void deleteUsersById(UUID id){
        Users user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.delete(user);
    }







}
