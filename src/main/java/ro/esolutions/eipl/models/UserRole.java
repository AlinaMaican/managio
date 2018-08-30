package ro.esolutions.eipl.models;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    USER, ADMIN, MANAGER, CONSULTANT;

    @Override
    public String getAuthority() {
        return this.toString();
    }
}