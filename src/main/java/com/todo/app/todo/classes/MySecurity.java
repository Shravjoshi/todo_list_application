package com.todo.app.todo.classes;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.todo.app.todo.repo.UserRepo;



@Component
public class MySecurity extends OncePerRequestFilter
{

    @Autowired
    UserRepo userRepo;

    @Autowired
    JwtUtil jwtUtil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException 
            {
                String path=request.getServletPath();
                //System.out.println(jwtUtil);
                if(path.startsWith("/login")||path.startsWith("/welcome"))
                {
                    filterChain.doFilter(request, response);
                }
                else
                {
                    String sa[]=path.split("and");
                    String token=sa[sa.length-1];
                    String username=jwtUtil.extractUsername(token);
                    User user=userRepo.findByUsername(username);
                    UserTwo userTwo=new UserTwo(user);
                    if(jwtUtil.validateToken(token, userTwo))
                    {
                        request.setAttribute("username", user.getUsername());
                        System.out.println(request.getServletPath());
                        filterChain.doFilter(request, response);
                    }
                }   
            }
    
}
