package com.example.NewProject.exception;

public class UserNameAlreadyExistsException extends RuntimeException{

  public UserNameAlreadyExistsException(String message) {
    super(message);
  }
}
