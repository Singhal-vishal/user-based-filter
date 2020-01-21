package com.filterdemo.controller;

import com.filterdemo.entity.User;
import com.filterdemo.repo.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class sample {
  @Autowired
  UserRepository userRepository;

  @GetMapping(path = "/sample")
  public ResponseEntity<String> newMethod(){
    return new ResponseEntity<>("Hello There!!", HttpStatus.OK);
  }

  @GetMapping(path = "/edit-newsletter")
  public ResponseEntity<List<User>> getUsers(){
    System.out.println("Now I am here");
    return new ResponseEntity<>(this.userRepository.findAll(), HttpStatus.OK);
  }

//  @GetMapping(path = "/add-newsletter")
//  public ResponseEntity<> getUsers(){
//    System.out.println("Now I am here");
//    return new ResponseEntity<>(this.userRepository.findAll(), HttpStatus.OK);
//  }
}
