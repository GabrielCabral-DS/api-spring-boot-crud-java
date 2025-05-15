package gabriel.dev.spring_boot_crud_java.validator;

import gabriel.dev.spring_boot_crud_java.domain.Users;
import gabriel.dev.spring_boot_crud_java.exception.RegistroDuplicadoException;
import gabriel.dev.spring_boot_crud_java.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserValidator {

    private final UserRepository userRepository;

    public UserValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void validate(Users users){
        if (thereIsARegistered (users)){
            throw  new RegistroDuplicadoException("User already registered");
        }
    }

    private boolean thereIsARegistered(Users users){
        Optional<Users> usersOptional = userRepository.findByName(users.getName());
        Optional<Users> optionalUsers = userRepository.findByEmail(users.getEmail());

        if (users.getId() == null){
            return usersOptional.isPresent() || optionalUsers.isPresent();
        }

        return !users.getId().equals(usersOptional.get().getId()) && usersOptional.isPresent() ||
                !users.getId().equals(optionalUsers.get().getId()) && optionalUsers.isPresent();
    }

}
