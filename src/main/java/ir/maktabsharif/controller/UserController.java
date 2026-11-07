package ir.maktabsharif.controller;

import com.auth0.jwt.JWT;
import ir.maktabsharif.repository.user.UserRepositoryImpl;
import ir.maktabsharif.service.user.UserServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;
    private final JWT jwt;

    public UserController(UserServiceImpl userService, JWT jwt) {
        this.userService = userService;
        this.jwt = jwt;
    }


    @GetMapping("/register")
    public String ReigesterForm(){
        return "registerForm";
    }




}


