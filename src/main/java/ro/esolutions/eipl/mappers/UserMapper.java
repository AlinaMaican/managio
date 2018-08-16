package ro.esolutions.eipl.mappers;

import ro.esolutions.eipl.entities.UserEntity;
import ro.esolutions.eipl.models.UserModel;

public class UserMapper {

    public UserModel fromEntityToModel(UserEntity userEntity) {
        return UserModel.builder()
                .username(userEntity.getUsername())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .password(userEntity.getPassword())
                .userRole(userEntity.getUserRole())
                .isActive(userEntity.getIsActive())
                .build();
    }

    public UserEntity fromModelToEntity(UserModel userModel) {
        return UserEntity.builder()
                .username(userModel.getUsername())
                .firstName(userModel.getFirstName())
                .lastName(userModel.getLastName())
                .password(userModel.getPassword())
                .userRole(userModel.getUserRole())
                .isActive(userModel.getIsActive())
                .build();
    }
}
