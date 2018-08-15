package ro.esolutions.eipl.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.esolutions.eipl.models.UserModel;
import ro.esolutions.eipl.repositories.UserRepository;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public UserModel addNewUser(UserModel userModel) {

        return null;

    }

    public UserModel getUserById(Integer userId) {
        return null;
    }

    public List<UserModel> getAllUsers() {
        return null;
    }

    public UserModel deleteUserById() {
        return null;
    }

    public UserModel editUserById(Integer userId, @Valid UserModel userModel) {
        return null;
    }
}
