package com.filterdemo.filter;

import com.filterdemo.entity.User;
import com.filterdemo.exception.BadRequestException;
import com.filterdemo.repo.UserRepository;
import java.io.IOException;
import java.util.Optional;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/*
In our application we will use JWT token to get user roles
 */
@Component
public class RoleBasedFilter implements Filter {
  @Autowired
  UserRepository userRepository;
  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    HttpServletRequest request = (HttpServletRequest) servletRequest;
    System.out.println(request.getHeader("token"));
    System.out.println();
    String token = request.getHeader("token");
    Optional<User> user = userRepository.findByEmail(token);
    if(user.isPresent()){
      User u = user.get();
      //get user roles
      String role = u.getRole();

      /*get end point of url
      ex- http://localhost:8080/edit-newsletter
      user role is - edit-newsletter
       */
      StringBuffer url = request.getRequestURL();
      String urls = url.toString();
      String[] arrOfStr = urls.split("http://localhost:8080/");

      //check user roles against roles of user if they are present
      if(arrOfStr[1].equalsIgnoreCase(role)){
        filterChain.doFilter(servletRequest,servletResponse);
      }
      else{
        throw new BadRequestException("Your are not permitted");

        }
      }
    }

  }

