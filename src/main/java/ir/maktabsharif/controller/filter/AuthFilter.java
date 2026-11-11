package ir.maktabsharif.controller.filter;

import ir.maktabsharif.dto.user.UserResponseDto;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebFilter({"/subject/*" , "/session/*","/user/profiler"})
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        HttpSession session = request.getSession(false);

        if (session== null){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        UserResponseDto userResponseDto = (UserResponseDto) session.getAttribute("user");

        if (userResponseDto == null){
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"your user is not login");
            return;
        }

        filterChain.doFilter(request,response);
    }
}
