package gabriel.dev.spring_boot_crud_java.controller;

import gabriel.dev.spring_boot_crud_java.domain.dto.UserDTO;
import gabriel.dev.spring_boot_crud_java.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody @Valid UserDTO userDTO){
        userService.saveUsers(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.findAllUsers());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<UserDTO> getUsersById(@PathVariable UUID id){
        UserDTO userDTO = userService.findUsersById(id);
        return ResponseEntity.ok(userDTO);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> updateUserById(@PathVariable @Valid UUID id, @RequestBody UserDTO userDTO){
        userService.updateUsers(id,userDTO);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteUsersById(@PathVariable UUID id){
        userService.deleteUsersById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }




}
