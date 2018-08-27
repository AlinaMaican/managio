package ro.esolutions.eipl.services;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import ro.esolutions.eipl.entities.User;
import ro.esolutions.eipl.exceptions.UserAlreadyExistsException;
import ro.esolutions.eipl.exceptions.UserNotFoundException;
import ro.esolutions.eipl.mappers.UserMapper;
import ro.esolutions.eipl.models.UserModel;
import ro.esolutions.eipl.repositories.UserRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
            throw new UserAlreadyExistsException(userModel.getUsername());
        }
        return userModel;
    }

    public UserModel getUserById(Long userId) {
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

    public UserModel deleteUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            userRepository.deleteById(userId);
            return UserMapper.fromEntityToModel(userOptional.get());
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    public UserModel editUserById(Long userId, @Valid UserModel userModel) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userRepository.save(UserMapper.fromModelToEntity(userModel));
            return UserMapper.fromEntityToModel(user);
        } else {
            throw new UserNotFoundException(userId);
        }
    }

    public UserModel getFirstUser() {
        return UserMapper.fromEntityToModel(userRepository.findOneByOrderById());
    }

    public User changePasswordById(Long userId, String newPassword, BindingResult bindingResult) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            Pattern passwordPattern = Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
            Matcher passwordMatcher = passwordPattern.matcher(newPassword);
            if (!passwordMatcher.matches()) {
                bindingResult.addError(new ObjectError("PasswordError", "Password must contain at " +
                        "least 8 characters, including  one letter and one number!"));
                return null;
            }
            userOptional.get().setPassword(newPassword);
            return userRepository.save(userOptional.get());
        } else {
            throw new UserNotFoundException(userId);
        }
    }
}
