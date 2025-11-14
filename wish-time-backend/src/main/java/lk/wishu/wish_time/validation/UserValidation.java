package lk.wishu.wish_time.validation;

import lk.wishu.wish_time.dto.request.SignUpRequest;
import lk.wishu.wish_time.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserValidation {

    @Autowired
    private UserService userService;

    /**
     * To validate first name filed in SignUp
     * @param firstName
     * @return String message or null
     */
    public String validateFirstName(String firstName){
        if(firstName == null || firstName.isBlank() ){
            return "First name is required";
        }
        if(firstName.length() > 45){
            return "First name can't be longer than 45 characters.";
        }
        if(!firstName.matches(ValidationUtil.NAME_REGEX)){
            return "First name must contain alphanumeric characters";
        }
        return null;
    }

    /**
     * To validate last name filed in SignUp
     * @param lastName
     * @return String message or null
     */
    public String validateLastName(String lastName){
        if( lastName == null || lastName.isBlank() ){
            return "Last name is required";
        }
        if(lastName.length() > 45){
            return "Last name can't be longer than 45 characters.";
        }
        if(!lastName.matches(ValidationUtil.NAME_REGEX)){
            return "Last name must contain alphanumeric characters";
        }
        return null;
    }
    /**
     * To validate email filed in SignUp
     * @param email
     * @return String message or null
     */
    public  String validateEmail(String email,boolean checkWithDB){
        if(email == null || email.isBlank() ){
            return "Email is required";
        }
        if(email.length() > 100){
            return "Email can't be longer than 100 characters.";
        }
        if(!email.matches(ValidationUtil.EMAIL_REGEX)){
            return "Inappropriate email address";
        }
        if(checkWithDB){
            if(userService.getUserByEmail(email) != null){
                return "Email already exists";
            }
        }

        return null;
    }
    /**
     * To validate password filed in SignUp
     * @param password
     * @return String message or null
     */
    public String validatePassword(String password){
        if(password == null || password.isBlank() ){
            return "Password is required";
        }
        if(!password.matches(ValidationUtil.PASSWORD_REGEX)){
            return "Password at least contain one uppercase, one lowercase, one number, and min 4 max 8 characters";
        }
        return null;
    }
    /**
     * To validate username filed in SignUp
     * @param userName
     * @return String message or null
     */
    public String validateUsername(String userName,boolean checkWithDB){
        if( userName == null || userName.isBlank() ){
            return "Username is required";
        }
        if(userName.length() > 45){
            return "Username can't be longer than 45 characters.";
        }
        try {
           if(checkWithDB){
               if(userService.getUserByUsername(userName) != null){
                   return "Username already exists";
               }
           }
        }catch (UsernameNotFoundException e){

        }
        return null;
    }
    /**
     * To validate all filedes in SignUp
     * @param data
     * @return String message or null
     */
    public HashMap<String,String> validate(SignUpRequest data){
        HashMap<String,String> map = new HashMap<>();
        String firstNameError = this.validateFirstName(data.getFirstName());
        String lastNameError = this.validateLastName(data.getLastName());
        String emailError = this.validateEmail(data.getEmail(),true);
        String passwordError = this.validatePassword(data.getPassword());
        String usernameError = this.validateUsername(data.getUserName(),true);

        if(firstNameError != null){
            map.put("firstName", firstNameError);
        }
        if(lastNameError != null){
            map.put("lastName", lastNameError);
        }
        if(emailError != null){
            map.put("email", emailError);
        }
        if(passwordError != null){
            map.put("password", passwordError);
        }
        if(usernameError != null){
            map.put("username", usernameError);
        }
        return map;
    }
}
