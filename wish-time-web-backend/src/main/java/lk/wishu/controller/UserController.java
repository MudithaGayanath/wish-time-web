package lk.wishu.controller;

import lk.wishu.service.JWTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final JWTService jwtService;

    public UserController(JWTService jwtService) {
        System.out.println("getUserController constructor called");
        this.jwtService = jwtService;
    }

    @GetMapping("/getUser")
    public String getUser(){
        return "getUser called";
    }

//    @GetMapping("/signin")
//    public String signin(){
//        return jwtService.getJWTToken();
//    }

    @PostMapping("/getUsername")
    public String getUsername(){
        System.out.println("getUsername called in UserController");
        return "getUsername called";
    }
}
