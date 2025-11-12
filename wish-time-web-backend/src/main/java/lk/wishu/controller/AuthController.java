package lk.wishu.controller;

import lk.wishu.dto.request.SingUpRequestDTO;
import lk.wishu.dto.request.SignInRequestDTO;
import lk.wishu.dto.response.SignInResponseDTO;
import lk.wishu.dto.response.SignUpResponseDTO;
import lk.wishu.entity.User;
import lk.wishu.service.JWTService;
import lk.wishu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JWTService  jwtService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public SignInResponseDTO signin(@RequestBody SignInRequestDTO requestDTO) {
        User user = userService.getUserByUsername(requestDTO.getUsername());
       String tk =  jwtService.getJWTToken(user.getUserName());
        System.out.println(tk);
        SignInResponseDTO output = new SignInResponseDTO();
        output.setToken(tk);
        return output;
    }

    @PostMapping("/signup")
    public String signup( ){
        return "signup";
    }

    @GetMapping("/authTest")
    public String authTest(){

        return "authTest";
    }
}
