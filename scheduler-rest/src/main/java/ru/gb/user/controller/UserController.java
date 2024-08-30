package ru.gb.user.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.user.model.User;

import ru.gb.mail.confirmation.service.UserServiceImpl;
import ru.gb.user.service.UserService;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userServiceIml;
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return userServiceIml.saveUser(user);
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        return userServiceIml.confirmEmail(confirmationToken);
    }

//    @GetMapping("{id}")
//    public ResponseEntity<List<User>> get(){
//        return ResponseEntity.ok(userService.findAll());
//    }
}
