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

@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthController {

    @Autowired
    private UserService userService;
//    @Autowired
//    private UserValidation validation;

    @PostMapping(value = "/signIn")
    public ResponseEntity<BaseResponse> signIn(@RequestBody SignInRequest data) {
         return  userService.signIn(data);
    }

//    @PostMapping(value = "/signUp")
//    public ResponseEntity<BaseResponseDTO> signUp(@RequestBody SignUpRequest data) {
//        HashMap<String,String> erros =  new HashMap<>();
//        //validation
//        String firstNameError = validation.validateFirstName(data.getFirstName());
//        String lastNameError = validation.validateLastName(data.getLastName());
//        String emailError = validation.validateEmail(data.getEmail());
//        String passwordError = validation.validatePassword(data.getPassword());
//        String usernameError = validation.validateUsername(data.getUserName());
//
//        if(firstNameError != null){
//            erros.put("firstName", firstNameError);
//        }
//        if(lastNameError != null){
//            erros.put("lastName", lastNameError);
//        }
//        if(emailError != null){
//            erros.put("email", emailError);
//        }
//        if(passwordError != null){
//            erros.put("password", passwordError);
//        }
//        if(usernameError != null){
//            erros.put("username", usernameError);
//        }
//
//        SignUpResopnse res = new SignUpResopnse();
//        if(!erros.isEmpty()){
//            res.setErrors(erros);
//            return new ResponseEntity<>(res,HttpStatus.BAD_REQUEST);
//        }
//        userService.insert(data);
//        return new ResponseEntity<>(res,HttpStatus.OK);
//
//    }


}
