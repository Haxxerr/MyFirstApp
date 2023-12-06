package com.example.NewProject.exception;

public class EmailAlreadyExistsException extends RuntimeException{

  public EmailAlreadyExistsException(String message) {
    super(message);
  }
}
