package ru.gb.user.service;

import org.springframework.http.ResponseEntity;
import ru.gb.user.model.User;

public interface UserService {
    ResponseEntity<?> saveUser(User user);

    ResponseEntity<?> confirmEmail(String confirmationToken);
}
