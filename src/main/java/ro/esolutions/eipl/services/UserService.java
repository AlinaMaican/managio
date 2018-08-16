package ro.esolutions.eipl.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.esolutions.eipl.mappers.UserMapper;
import ro.esolutions.eipl.models.UserModel;
import ro.esolutions.eipl.repositories.UserRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository, "UserRepository must not be null");
    }

    public UserModel addNewUser(UserModel userModel) {
        return null;
    }

    public UserModel getUserById(Integer userId) {
        return null;
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }

    public UserModel deleteUserById() {
        return null;
    }

    public UserModel editUserById(Integer userId, @Valid UserModel userModel) {
        return null;
    }
}
