package lk.wishu.wish_time.controller;

import lk.wishu.wish_time.entity.User;
import lk.wishu.wish_time.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping(value = "/getUser")
    public ResponseEntity<User> getUser(@RequestParam int userId){
        User user = userService.getUserById(userId);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ResponseEntity<User> response = new ResponseEntity<>(user, HttpStatus.OK);
        return response;
    }

}
