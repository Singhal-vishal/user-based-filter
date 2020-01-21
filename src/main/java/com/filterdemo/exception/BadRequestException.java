package com.filterdemo.exception;

public class BadRequestException extends RuntimeException {

  public BadRequestException(String s) {
    System.out.println(s);
  }

}
