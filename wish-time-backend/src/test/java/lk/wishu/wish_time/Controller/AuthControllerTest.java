package lk.wishu.wish_time.Controller;

import lk.wishu.wish_time.controller.AuthController;
import lk.wishu.wish_time.dto.request.SignUpRequest;
import lk.wishu.wish_time.dto.response.SignUpResopnse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

@Service
public class AuthControllerTest {

    private AuthController authController =  new AuthController() ;
    private SignUpRequest  signUpRequest  =  new SignUpRequest();

    @BeforeTestMethod
    public void setUp() throws Exception {
        signUpRequest.setEmail("sdfsd");
        signUpRequest.setPassword("sdfsd");
        signUpRequest.setFirstName("sdfsd");
        signUpRequest.setLastName("sdfsd");
        signUpRequest.setLastName("sdf");
    }


}
