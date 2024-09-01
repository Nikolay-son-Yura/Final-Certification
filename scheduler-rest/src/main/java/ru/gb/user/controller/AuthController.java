package ru.gb.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.mail.confirmation.service.UserServiceImpl;
import ru.gb.user.model.User;
import ru.gb.user.service.RegistrationService;


@Tag(name = "Регистрация", description = "Точка входа для регистрации пользователя")
@RestController
@RequiredArgsConstructor
public class AuthController {
    private final RegistrationService registrationService;
    private final UserServiceImpl userService;

    @PostMapping(path = "/user")
    private ResponseEntity<HttpStatus> registration(User user){

        registrationService.register(user);
        userService.saveUserEmail(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        return userService.confirmEmail(confirmationToken);
    }
}
