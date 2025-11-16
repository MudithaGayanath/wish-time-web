package lk.wishu.wish_time.middleware;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lk.wishu.wish_time.entity.User;
import lk.wishu.wish_time.service.JWTService;
import lk.wishu.wish_time.service.MyUserDetailsService;
import lk.wishu.wish_time.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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
public class JWTFilter extends OncePerRequestFilter {
    @Autowired
    @Lazy
    private JWTService jwtService;
    @Autowired
    @Lazy
    private MyUserDetailsService myUserDetailsService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = request.getHeader("Authorization").split(" ")[1];
            if(jwtService.validateToken(token)){
                if(SecurityContextHolder.getContext().getAuthentication() == null){
                    UserDetails userDetails = myUserDetailsService.loadUserByUsername(jwtService.getUsername(token));
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);

                }
            }else{
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
            }
            System.out.println("doFilterInternal end");

        } catch (NullPointerException e) {
        } catch (ArrayIndexOutOfBoundsException e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
        filterChain.doFilter(request,response);
    }
}
