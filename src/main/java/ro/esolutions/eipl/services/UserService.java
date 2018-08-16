package ro.esolutions.eipl.services;

import org.springframework.stereotype.Service;
import ro.esolutions.eipl.entities.User;
import ro.esolutions.eipl.exceptions.UserAlreadyExistsException;
import ro.esolutions.eipl.exceptions.UserWithGivenIdDoesNotExistException;
import ro.esolutions.eipl.mappers.UserMapper;
import ro.esolutions.eipl.models.UserModel;
import ro.esolutions.eipl.repositories.UserRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository, "UserRepository must not be null");
    }

    public UserModel addNewUser(UserModel userModel) {
        Optional<User> userOptional = userRepository.findByUsername(userModel.getUsername());
        if (!userOptional.isPresent()) {
            userRepository.save(UserMapper.fromModelToEntity(userModel));
        } else {
            throw new UserAlreadyExistsException();
        }
        return userModel;
    }

    public UserModel getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return UserMapper.fromEntityToModel(userOptional.get());
        } else {
            throw new UserWithGivenIdDoesNotExistException();
        }
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }

    public UserModel deleteUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userRepository.deleteById(userId);
            return UserMapper.fromEntityToModel(userOptional.get());
        } else {
            throw new UserWithGivenIdDoesNotExistException();
        }
    }

    public UserModel editUserById(Long userId, @Valid UserModel userModel) {

        User user = userRepository.save(UserMapper.fromModelToEntity(userModel));
        return UserMapper.fromEntityToModel(user);
    }
}
