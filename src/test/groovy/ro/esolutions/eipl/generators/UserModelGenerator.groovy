package ro.esolutions.eipl.generators

import ro.esolutions.eipl.models.UserModel
import ro.esolutions.eipl.models.UserRole

class UserModelGenerator {

    static aUserModel(Map overrides = [:]) {
        Map values = [id : 0L,
                      username : "username",
                      firstName: "firstName",
                      lastName : "lastName",
                      password : "password",
                      userRole : UserRole.USER,
                      isActive : true,
                      email : "email"]
        values << overrides
        return UserModel.newInstance(values)
    }
}