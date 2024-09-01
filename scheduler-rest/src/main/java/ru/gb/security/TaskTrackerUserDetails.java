package ru.gb.security;


import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.gb.user.model.User;

import java.util.Collection;
import java.util.List;

@Getter
public class TaskTrackerUserDetails implements UserDetails {

    private final User user;

    public TaskTrackerUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return this.user.getUserPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUserEmail();
    }

}
