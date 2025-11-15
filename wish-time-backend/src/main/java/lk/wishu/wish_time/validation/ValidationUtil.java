package lk.wishu.wish_time.validation;

import lk.wishu.wish_time.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ValidationUtil {
    public static final String  NAME_REGEX = "^[A-Za-z]+$";
    public static final String EMAIL_REGEX = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
//    at least one uppercase, one lowercase, one number, and min 4 max 8 characters
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{4,8}$";



}
