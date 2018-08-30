package ro.esolutions.eipl.services;

import org.springframework.stereotype.Service;
import ro.esolutions.eipl.entities.User;
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

import static ro.esolutions.eipl.mappers.UserMapper.fromEntityToModel;
import static ro.esolutions.eipl.mappers.UserMapper.fromModelToEntity;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserService(final UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository, "UserRepository must not be null");
    }

    public UserModel addUser(final UserModel userModel) {
        return fromEntityToModel(userRepository.save(fromModelToEntity(userModel)));
    }

    public UserModel getUserById(final Long userId) {
        return fromEntityToModel(userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId)));
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
            return fromEntityToModel(userOptional.get());
        } else {
            throw new UserNotFoundException(userId);
        }
    }

//  TODO popescustefanradu 2018-08-30T11:06 refactor into editUser(UserModel)
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

    public UserModel getFirstUser() {
        Optional<User> userOptional = userRepository.findById(1L);
        if (userOptional.isPresent()) {
            return UserMapper.fromEntityToModel(userOptional.get());
        } else {
            throw new UserNotFoundException(1L);
        }
    }

    public UserModel changePasswordById(final Long userId, final String newPassword) {
        return userRepository.findById(userId).map(user -> {
            user.setPassword(newPassword);
            return UserMapper.fromEntityToModel(userRepository.save(user));
        })
                .orElseThrow(() -> new UserNotFoundException(userId));

    }
}