package ir.maktabsharif.controller;

import com.auth0.jwt.JWT;
import ir.maktabsharif.dto.user.UserRequestDto;
import ir.maktabsharif.dto.user.UserResponseDto;
import ir.maktabsharif.repository.user.UserRepositoryImpl;
import ir.maktabsharif.service.jwt.Jwt;
import ir.maktabsharif.service.user.UserService;
import ir.maktabsharif.service.user.UserServiceImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.ArrayList;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final Jwt jwt;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, Jwt jwt, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.jwt = jwt;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/all")
    public String allUsers(Model model){
        model.addAttribute("users",userService.findAll());
        return "allUsers";
    }


    @GetMapping("/register")
    public String ReigesterForm() {
        return "registerForm";
    }


    @PostMapping("/register")
    public String ReigesterAdd(HttpServletResponse response,
                               HttpServletRequest request,
                               @RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("confirm") String passwordConfrime,
                               @RequestParam("fullname") String fullName
    ) {

        if (password.equals(passwordConfrime)) {
            UserRequestDto userRequestDto = UserRequestDto.builder()
                    .subjectsId(new ArrayList<>())
                    .fullname(fullName)
                    .password(password)
                    .username(username)
                    .studySessionsID(new ArrayList<>())
                    .build();

            UserResponseDto userResponseDto = (UserResponseDto) userService.create(userRequestDto);
            String token = jwt.generate(userResponseDto.getUsername());

            Cookie cookie = new Cookie("token",token);
            response.addCookie(cookie);
            HttpSession session = request.getSession();
            session.setAttribute("user",userResponseDto);
            return "loginSucsesfully";

        }else {
            return "registerForm";
        }

    }


    @GetMapping("/login")
    public String loginPage(HttpServletRequest request,HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();

        Cookie[] cookies = request.getCookies();

        String token = null;
        for (Cookie c: cookies){
            if (c.getName().equals("token")){
                token = c.getValue();
                break;
            }
        }

        if (token != null){
            return "loginPage";
        }else {
            return "registerForm";
        }
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request,HttpServletResponse response,

                        @RequestParam("password") String password,
                        @RequestParam("confrime") String confrimePassword

    ) throws IOException {

        if (password.equals(confrimePassword)){
            HttpSession session = request.getSession();

            Cookie[] cookies = request.getCookies();

            String token = null;
            for (Cookie c: cookies){
                if (c.getName().equals("token")){
                    token = c.getValue();
                    break;
                }
            }

            if (token != null){
                String username = jwt.parse(token);
                UserResponseDto userResponseDto = (UserResponseDto) userService.findByUserrName(username);

                if (passwordEncoder.matches(password,userResponseDto.getPasswordHash())){
                    session.setAttribute("user",userResponseDto);
                    return "loginSucsesfully";
                }else {
                    return "redirect:login";
                }

            }else {
                response.sendError(404);
                return "registerForm";
            }
        }else {
            return "redirect:login";
        }


    }




}


