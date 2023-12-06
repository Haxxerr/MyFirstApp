package com.example.NewProject.exception;

public class EmailSendFailException extends RuntimeException {

  public EmailSendFailException(String message) {
    super(message);
  }

}
