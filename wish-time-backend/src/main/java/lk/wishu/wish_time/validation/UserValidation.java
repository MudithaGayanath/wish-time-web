package lk.wishu.wish_time.validation;

import lk.wishu.wish_time.dto.request.SignUpRequest;
import lk.wishu.wish_time.dto.request.UserUpdateRequest;
import lk.wishu.wish_time.entity.User;
import lk.wishu.wish_time.entity.UserStatus;
import lk.wishu.wish_time.service.UserService;
import lk.wishu.wish_time.service.UserStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserValidation {
    @Autowired
    private UserStatusService  userStatusService;
    @Autowired
    private UserService userService;

    /**
     * To validate first name filed
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
     * To validate last name filed
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
     * To validate email filed
     * @param email
     * @return String message or null
     */
    public  String validateEmail(String email){
        if(email == null || email.isBlank() ){
            return "Email is required";
        }
        if(email.length() > 100){
            return "Email can't be longer than 100 characters.";
        }
        if(!email.matches(ValidationUtil.EMAIL_REGEX)){
            return "Inappropriate email address";
        }

        return null;
    }
    /**
     * To validate password filed
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
     * To validate username filed
     * @param userName
     * @return String message or null
     */
    public String validateUsername(String userName){
        if( userName == null || userName.isBlank() ){
            return "Username is required";
        }
        if(userName.length() > 45){
            return "Username can't be longer than 45 characters.";
        }
        return null;
    }

    public String validateStatus(String statusId){
        if(statusId == null || statusId.isBlank() ){
            return "Status is required";
        }
       try {
           if(userStatusService.getUserStatusById(Integer.parseInt(statusId)) == null){
               return "Status does not exist";
           }
       }catch (NumberFormatException e){
           return "Status must be integer";
       }

        return null;

    }

    public String isUsernameUsed(String userName){
         if(userService.getUserByUsername(userName) != null){
             return "Username is already used";
         }
         return null;
    }
    public String isEmailUsed(String email){
        if(userService.getUserByEmail(email) != null){
            return "Email is already used";
        }
        return null;
    }




}
