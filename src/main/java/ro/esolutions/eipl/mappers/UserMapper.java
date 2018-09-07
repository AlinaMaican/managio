package ro.esolutions.eipl.mappers;

import ro.esolutions.eipl.entities.User;
import ro.esolutions.eipl.models.UserModel;

public final class UserMapper {

    private UserMapper() {}

    public static UserModel fromEntityToModel(final User user) {
        return UserModel.builder()
                .id(user.getId())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .userRole(user.getUserRole())
                .isActive(user.getIsActive())
                .email(user.getEmail())
                .build();
    }

    public static User fromModelToEntity(final UserModel userModel) {
        return User.builder()
                .id(userModel.getId())
                .username(userModel.getUsername())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .userRole(userModel.getUserRole())
                .isActive(userModel.getIsActive())
                .email(userModel.getEmail())
                .build();
    }
}
