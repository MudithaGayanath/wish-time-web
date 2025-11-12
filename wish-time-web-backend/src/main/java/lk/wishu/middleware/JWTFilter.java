package lk.wishu.middleware;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.wishu.entity.User;
import lk.wishu.service.JWTService;
import lk.wishu.service.MyUserDetilsService;
import lk.wishu.service.UserService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@Order(1)
@AllArgsConstructor
public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final MyUserDetilsService myUserDetilsService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,@NonNull HttpServletResponse response,@NonNull FilterChain filterChain) throws ServletException, IOException {
        System.out.println("JWTFilter called");
        try {

//            if(SecurityContextHolder.getContext().getAuthentication() == null) {
                System.out.println("Header "+request.getHeader("Authorization"));
            UserDetails user = myUserDetilsService.loadUserByUsername(
                    jwtService.getUsername(
                            request.getHeader("Authorization")
                                    .split(" ")[1]));
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(token);
//            }
        } catch (NullPointerException | ArrayIndexOutOfBoundsException | UsernameNotFoundException e) {
            System.out.println("JWTFilter exception " + e.getMessage());
            e.printStackTrace();
        }
        filterChain.doFilter(request, response);
    }

}

