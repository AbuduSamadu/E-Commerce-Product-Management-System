package abudu.product.controllers;

import abudu.product.dto.UserDTO;
import abudu.product.services.UserLoginService;
import abudu.product.services.UserRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class UserController {

    private final UserRegistrationService userRegistrationService;
    private final UserLoginService userLoginService;

    public UserController(UserRegistrationService userRegistrationService, UserLoginService userLoginService) {
        this.userLoginService = userLoginService;
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@Validated @RequestBody UserDTO userDTO) {
        UserDTO registeredUser = userRegistrationService.registerUser(userDTO);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@Validated @RequestBody UserDTO userDTO) {
        UserDTO loggedInUser = userLoginService.loginUser(userDTO.getEmail(), userDTO.getPassword());
        return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
    }
}

