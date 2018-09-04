package ro.esolutions.eipl.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.esolutions.eipl.entities.User;
import ro.esolutions.eipl.exceptions.ResourceNotFound;
import ro.esolutions.eipl.mappers.UserMapper;
import ro.esolutions.eipl.mappers.UserWithPasswordMapper;
import ro.esolutions.eipl.models.UserModel;
import ro.esolutions.eipl.models.UserModelWithPassword;
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
@RequiredArgsConstructor
@DependsOn({"passwordEncoder"})
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(final UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository, "UserRepository must not be null");
    }

    public UserModel addUser(final UserModelWithPassword userModel) {
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        return fromEntityToModel(userRepository.save(UserWithPasswordMapper.fromModelToEntity(userModel)));

    public UserModel addNewUser(final UserModel userModel) {
        checkUsername(userModel);
        checkEmail(userModel);
        return UserMapper.fromEntityToModel(userRepository.save(UserMapper.fromModelToEntity(userModel)));
    }

    public UserModel getUserById(final Long userId) {
        return fromEntityToModel(this.findByIdOrThrow(userId));
    }

    public List<UserModel> getAllUsers() {
        return userRepository.findAllByOrderByIdAsc()
                .stream()
                .map(UserMapper::fromEntityToModel)
                .collect(Collectors.toList());
    }

    public Page<UserModel> getAllUsers(Pageable pageable) {
        return userRepository.findAllByOrderByIdAsc(pageable).map(UserMapper::fromEntityToModel);
    }

    public UserModel deleteUserById(final Long userId) {
        final User user = findByIdOrThrow(userId);
        userRepository.deleteById(userId);
        return fromEntityToModel(user);
    }

    public UserModel editUserById(final Long userId, final UserModel userModel) {
        final User user = fromModelToEntity(userModel);
        user.setPassword(findByIdOrThrow(userId).getPassword());
        user.setId(userId);
        return UserMapper.fromEntityToModel(userRepository.save(user));
    }

    public UserModel changePasswordById(final Long userId, final String newPassword) {
        final User user = findByIdOrThrow(userId);
        setNewPassword(user, newPassword);
        return fromEntityToModel(userRepository.save(user));
    }

    /**
     * @throws ResourceNotFound RuntimeException when there is no user with given id
     */
    private User findByIdOrThrow(final Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFound(id, User.class.getName()));
    }

    private void setNewPassword(final User user, final String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.findById(userId).map(user -> { user.setPassword(newPassword);
                               return UserMapper.fromEntityToModel(userRepository.save(user));})
	            .orElseThrow( () -> new UserNotFoundException(userId));
    }
}