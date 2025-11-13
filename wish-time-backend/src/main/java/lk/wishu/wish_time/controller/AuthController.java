package lk.wishu.wish_time.controller;

import jakarta.servlet.http.HttpServletRequest;
import lk.wishu.wish_time.dto.request.SignInRequestDTO;
import lk.wishu.wish_time.dto.request.SignUpRequestDTO;
import lk.wishu.wish_time.dto.response.SignInResponse;
import lk.wishu.wish_time.dto.response.SignUpResopnseDTO;
import lk.wishu.wish_time.entity.User;
import lk.wishu.wish_time.service.JWTService;
import lk.wishu.wish_time.service.UserService;
import lk.wishu.wish_time.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.webauthn.authentication.WebAuthnAuthenticationRequestToken;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {
    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserValidation userValidation;

    @PostMapping(value = "/signIn")
    public ResponseEntity<SignInResponse> signIn( @RequestBody SignInRequestDTO data) {
        SignInResponse res = new SignInResponse();
        ResponseEntity<SignInResponse> response;
        User user = userService.getUserByUsername(data.getUsername());
        if (passwordEncoder.matches(data.getPassword(), user.getPassword())) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(data.getUsername(), data.getPassword());
//            token.setDetails(request);
                Authentication authenticate = authenticationManager.authenticate(token);

                if (authenticate.isAuthenticated()) {
                    res.setToken(jwtService.getJWTToken(user.getUserName()));
                    response = new ResponseEntity<>(res, HttpStatus.OK);
                } else {
                    List<String> errors = new ArrayList<>();
                    errors.add("Authentication error");
                    response = new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
                }
        }else{
            List<String> errors = new ArrayList<>();
            errors.add("Username or password is incorrect");
            response = new ResponseEntity<>(res,HttpStatus.UNAUTHORIZED);
        }


        return response;
    }

    @PostMapping(value = "/signUp")
    public ResponseEntity<SignUpResopnseDTO> signUp( @RequestBody SignUpRequestDTO data) {
        HashMap<String,String> erros = userValidation.validate(data);
        SignUpResopnseDTO res = new SignUpResopnseDTO();
        if(!erros.isEmpty()){

            res.setErrors(erros);
            return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
        }
        userService.insert(data);
        return new ResponseEntity<>(res,HttpStatus.OK);

    }


}
