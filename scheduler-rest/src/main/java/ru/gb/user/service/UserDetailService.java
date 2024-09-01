package ru.gb.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.gb.security.TaskTrackerUserDetails;
import ru.gb.user.model.User;
import ru.gb.user.repository.UserRepository;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByUserEmail(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден!");
        }
        return new TaskTrackerUserDetails(user.get());
    }
}
