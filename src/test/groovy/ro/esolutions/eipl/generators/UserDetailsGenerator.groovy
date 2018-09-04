package ro.esolutions.eipl.generators

import ro.esolutions.eipl.configurations.UserDetailsImpl

class UserDetailsGenerator {

    static UserDetailsImpl aUserDetails(Map overrides = [:]) {
        Map values = [
                password             : 'asd',
                username             : 'uName',
                accountNonExpired    : true,
                accountNonLocked     : true,
                authorities          : [],
                credentialsNonExpired: true,
                enabled              : true
        ]
        values << overrides
        return UserDetailsImpl.newInstance(values)
    }
}
