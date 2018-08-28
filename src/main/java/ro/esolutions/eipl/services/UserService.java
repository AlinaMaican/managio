package ro.esolutions.eipl.services;

import org.springframework.stereotype.Service;
import ro.esolutions.eipl.entities.User;
import ro.esolutions.eipl.exceptions.UserAlreadyExistsException;
import ro.esolutions.eipl.exceptions.UserEmailAlreadyExists;
import ro.esolutions.eipl.exceptions.UserNotFoundException;
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

    public UserService(final UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository, "UserRepository must not be null");
    }

    public UserModel addNewUser(final UserModel userModel) {
        checkUsername(userModel);
        checkEmail(userModel);
        return UserMapper.fromEntityToModel(userRepository.save(UserMapper.fromModelToEntity(userModel)));
    }

    public UserModel getUserById(final Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return UserMapper.fromEntityToModel(userOptional.get());
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAllByOrderByIdAsc()
                .stream()
                .map(UserMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }

    public UserModel deleteUserById(final Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userRepository.deleteById(userId);
            return UserMapper.fromEntityToModel(userOptional.get());
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    public UserModel editUserById(final Long userId, final @Valid UserModel userModel) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userModel.setId(userId);
            User user = userRepository.save(UserMapper.fromModelToEntity(userModel));
            return UserMapper.fromEntityToModel(user);
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    private boolean checkEmail(final UserModel userModel) {
        final String email = userModel.getEmail();
        Optional<User> persistedUser = userRepository.findFirstByEmail(email);
        if (persistedUser.isPresent()) {
            throw new UserEmailAlreadyExists(email);
        }
        return true;
    }

    private boolean checkUsername(final UserModel userModel) {
        Optional<User> userOptional = userRepository.findByUsername(userModel.getUsername());
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException(userModel.getUsername());
        }
        return true;
    }
}
