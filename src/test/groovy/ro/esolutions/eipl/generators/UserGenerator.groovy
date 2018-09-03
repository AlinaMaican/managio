package ro.esolutions.eipl.generators

import ro.esolutions.eipl.entities.User
import ro.esolutions.eipl.models.UserRole

class UserGenerator {

    static aUser(Map overrides = [:]) {
        Map values = [id : 0L,
                      username : "username",
                      firstName: "firstName",
                      lastName : "lastName",
                      password : "password",
                      userRole : UserRole.USER,
                      isActive : true,
                      email : "email"]
        values << overrides
        return User.newInstance(values)
    }
}