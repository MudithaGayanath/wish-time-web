package lk.wishu.wish_time.validation;

import lk.wishu.wish_time.dto.request.SignUpRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;


public class UserValidationTest {
    private UserValidation validation = new UserValidation();

    @Test
    public void testFirstName(){
        Assertions.assertEquals("First name must contain alphanumeric characters",validation.validateFirstName("mudit kha"));
    }



}
