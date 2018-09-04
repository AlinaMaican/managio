package ro.esolutions.eipl.mappers;

import ro.esolutions.eipl.entities.User;
import ro.esolutions.eipl.models.UserModel;
import ro.esolutions.eipl.models.UserModelWithPassword;

public final class UserWithPasswordMapper {

    private UserWithPasswordMapper() {
    }

    public static UserModelWithPassword fromEntityToModel(final User user) {
        return UserModelWithPassword.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .userRole(user.getUserRole())
                .isActive(user.getIsActive())
                .email(user.getEmail())
                .build();
    }

    public static User fromModelToEntity(final UserModelWithPassword userModel) {
        return User.builder()
                .id(userModel.getId())
                .username(userModel.getUsername())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .password(userModel.getPassword())
                .userRole(userModel.getUserRole())
                .isActive(userModel.getIsActive())
                .email(userModel.getEmail())
                .build();
    }
}
