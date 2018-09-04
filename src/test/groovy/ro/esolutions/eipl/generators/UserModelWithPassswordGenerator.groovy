package ro.esolutions.eipl.generators

import ro.esolutions.eipl.models.UserModelWithPassword
import ro.esolutions.eipl.models.UserRole

class UserModelWithPassswordGenerator {
    static aUserModelWithPassword(Map overrides = [:]) {
        Map values = [
                id       : 0L,
                username : "username",
                firstName: "firstName",
                lastName : "lastName",
                password : "password",
                userRole : UserRole.USER,
                isActive : true,
                email    : "email"]
        values << overrides
        return UserModelWithPassword.newInstance(values)
    }
}
