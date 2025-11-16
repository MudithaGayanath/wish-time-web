package lk.wishu.wish_time.service;


//import lk.wishu.dto.request.SignUpRequestDTO;

import lk.wishu.wish_time.dto.request.SignInRequest;
import lk.wishu.wish_time.dto.request.SignUpRequest;
import lk.wishu.wish_time.dto.request.UserUpdateRequest;
import lk.wishu.wish_time.dto.response.BaseResponse;
import lk.wishu.wish_time.dto.response.ErrorResponse;
import lk.wishu.wish_time.dto.response.SignInResponse;
import lk.wishu.wish_time.dto.response.UserUpdateResponse;
import lk.wishu.wish_time.entity.User;
import lk.wishu.wish_time.repository.UserRepo;
import lk.wishu.wish_time.validation.UserValidation;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    @Lazy
    private JWTService jwtService;
    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserStatusService userStatusService;

    @Autowired
    @Lazy
    private UserValidation validation;

    public List<User> getUsers() {
        return userRepo.findAll();
    }


    /**
     * For JWT Filter
     *
     * @param userName
     * @return User object
     */
    public User getUserByUsername(String userName) {
        return userRepo.findByUserName(userName).orElse(null);
    }


    public ResponseEntity<BaseResponse> signIn(SignInRequest data) {
        HashMap<String, String> errors = new HashMap<>();
        if (data.getUsername() == null || data.getUsername().isBlank()) {
            errors.put("username", "Username required");
        }
        if (data.getPassword() == null || data.getPassword().isBlank()) {
            errors.put("password", "Password required");
        }

        if (!errors.isEmpty()) {
            return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
        }

        try {
            User user = this.getUserByUsername(data.getUsername());
            if (passwordEncoder.matches(data.getPassword(), user.getPassword())) {
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUserName(), data.getPassword());
                Authentication authentication = authenticationManager.authenticate(token);
                if (authentication.isAuthenticated()) {
                    SignInResponse res = new SignInResponse();
                    res.setToken(jwtService.getJWTToken(user.getUserName()));
                    return new ResponseEntity<>(res,HttpStatus.OK);
                } else {
                    errors.put("auth", "Authentication Failed");
                    return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.UNAUTHORIZED);
                }
            } else {
                throw new UsernameNotFoundException("Invalid username or password");
            }
        } catch (UsernameNotFoundException e) {
            errors.put("credentials", "Username or password is incorrect");
            return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<BaseResponse> insert(SignUpRequest data) {

        HashMap<String,String> erros =  new HashMap<>();
//        //validation
        String firstNameError = validation.validateFirstName(data.getFirstName());
        String lastNameError = validation.validateLastName(data.getLastName());
        String emailError = validation.validateEmail(data.getEmail());
        String passwordError = validation.validatePassword(data.getPassword());
        String usernameError = validation.validateUsername(data.getUserName());

        if(firstNameError != null){
            erros.put("firstName", firstNameError);
        }
        if(lastNameError != null){
            erros.put("lastName", lastNameError);
        }
        if(emailError != null){
            erros.put("email", emailError);
        }
        if(passwordError != null){
            erros.put("password", passwordError);
        }
        if(usernameError != null){
            erros.put("username", usernameError);
        }

        if(!erros.isEmpty()){
            return new ResponseEntity<>(new ErrorResponse(erros),HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setUserName(data.getUserName());
        System.out.println(data.getPassword() + " User password");
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setEmail(data.getEmail());
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setCreatedAt(LocalDateTime.now());
        user.setUserStatus(userStatusService.getUserStatusByStatusName(UserStatusService.ACTIVE));
        userRepo.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public UserUpdateResponse update(UserUpdateRequest data) throws HibernateException {
        User user = this.getUserByUsername(data.getUserName());
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setEmail(data.getEmail());
        user.setPassword(passwordEncoder.encode(data.getPassword()));
        user.setUpdatedAt(LocalDateTime.now());

        user.setUserStatus(userStatusService.getUserStatusById(Integer.parseInt(data.getStatusId())));

        user = userRepo.save(user);
        UserUpdateResponse response = new UserUpdateResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setUserName(user.getUserName());
        response.setCreatedAt(String.valueOf(user.getCreatedAt()));
        response.setUpdatedAt(String.valueOf(user.getUpdatedAt()));


        return response;
    }

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }


    public User getUserById(int userId) {
        return userRepo.findById(userId).orElse(null);
    }
}
