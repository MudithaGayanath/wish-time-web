package lk.wishu.wish_time.controller;

import lk.wishu.wish_time.dto.request.SignInRequest;
import lk.wishu.wish_time.dto.request.SignUpRequest;
import lk.wishu.wish_time.dto.response.*;
import lk.wishu.wish_time.entity.User;
import lk.wishu.wish_time.service.JWTService;
import lk.wishu.wish_time.service.UserService;
import lk.wishu.wish_time.validation.UserValidation;
import org.hibernate.collection.spi.BagSemantics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/signIn")
    public ResponseEntity<BaseResponse> signIn(@RequestBody SignInRequest data) {
         return  userService.signIn(data);
    }

    @PostMapping(value = "/signUp")
    public ResponseEntity<BaseResponse> signUp(@RequestBody SignUpRequest data) { return userService.insert(data);}


}
