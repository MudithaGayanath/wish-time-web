package lk.wishu.wish_time.validation;

import lk.wishu.wish_time.dto.request.SignUpRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.stereotype.Component;

import java.util.HashMap;


public class UserValidationTest {
    private UserValidation validation = new UserValidation();

    @Test
    public void testFirstName(){
        Assertions.assertEquals("First name must contain alphanumeric characters",validation.validateFirstName("mudit kha"));
    }

    @Test
    public void validation(){
        SignUpRequestDTO data = new SignUpRequestDTO();
        data.setFirstName("Mudit kha");
        data.setLastName("");

        HashMap<String, String> validate = validation.validate(data);
        System.out.println(validate);

    }

}
