package ru.gb.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.mail.notification.service.RegistrationService;
import ru.gb.user.model.User;

import ru.gb.mail.confirmation.service.UserServiceImpl;

//@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceIml;
    private RegistrationService registrationService;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return userServiceIml.saveUserEmail(user);
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        return userServiceIml.confirmEmail(confirmationToken);
    }


}
