package lk.wishu.wish_time.controller;

import lk.wishu.wish_time.dto.request.UserUpdateRequest;
import lk.wishu.wish_time.dto.response.ErrorResponse;
import lk.wishu.wish_time.dto.response.UserUpdateResponse;
import lk.wishu.wish_time.entity.User;
import lk.wishu.wish_time.service.UserService;
import lk.wishu.wish_time.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserValidation validation;

    @GetMapping(value = "/getUser")
    public ResponseEntity<User> getUser(@RequestParam int userId) {
        User user = userService.getUserById(userId);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ResponseEntity<User> response = new ResponseEntity<>(user, HttpStatus.OK);
        return response;
    }

    @PutMapping(value = "/updateUser")
    public ResponseEntity<Object> update(@RequestBody UserUpdateRequest data) {
        if(validateUpdate(data)){
            return new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(userService.update(data), HttpStatus.OK);
        }

    }

//    @DeleteMapping(value = "/userDelete/${userId}")
//    public ResponseEntity<Object> deleteUser(@RequestParam int userId) {
//
//    }

    private boolean validateUpdate(UserUpdateRequest data) {
        boolean status = true;
        String firstNameError = validation.validateFirstName(data.getFirstName());
        String lastNameError = validation.validateLastName(data.getLastName());
        String statusError = validation.validateStatus(data.getStatusId());
        String passwordError = validation.validatePassword(data.getPassword());
        String emailError = validation.validateEmail(data.getEmail());

        if (firstNameError != null) {
            status = false;
            data.setFirstName(firstNameError);
        }
        if (lastNameError != null) {
            status = false;
            data.setLastName(lastNameError);
        }
        if (emailError != null  ) {
            status = false;
            data.setEmail(emailError);
        }
        if (passwordError != null) {
            status = false;
            data.setPassword(passwordError);
        }
        if (statusError != null) {
            status = false;
            data.setStatusId(statusError);
        }

        return status;
    }

}
