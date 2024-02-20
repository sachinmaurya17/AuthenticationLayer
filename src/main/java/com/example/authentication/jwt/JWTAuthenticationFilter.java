package com.example.authentication.jwt;

import com.example.authentication.security.UserDetailServiceImp;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserDetailServiceImp userDetailServiceImp;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestHeader = request.getHeader("Authorization");
        String userName = null;
        String token = null;
        if(StringUtils.hasLength(requestHeader) && requestHeader.startsWith("Bearer")){
            token = requestHeader.substring(7);
            try{
                if(StringUtils.hasLength(token) && SecurityContextHolder.getContext().getAuthentication() == null){
                    userName = jwtHelper.getUsernameFromToken(token);
                    UserDetails userDetails = userDetailServiceImp.loadUserByUsername(userName);
                    Boolean validateToken = jwtHelper.validateToken(token, userDetails);
                    if (validateToken) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, null);
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        logger.info("Validation fails !!");
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            System.out.println("no bearer token contains");
        }
        filterChain.doFilter(request,response);
    }
}
