package ir.maktabsharif.controller.filter;

import ir.maktabsharif.dto.user.UserResponseDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.logging.LogRecord;

@WebFilter({"/user/register","/user/login","/user/singUp"})
public class AuthCheck implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);

        UserResponseDto userResponseDto = (UserResponseDto) session.getAttribute("user");

        if (userResponseDto!= null){
            request.getRequestDispatcher("/WEB-INF/erroe/yourLogin.jsp").forward(request,response);
            return;
        }

        filterChain.doFilter(request,response);

    }
}
