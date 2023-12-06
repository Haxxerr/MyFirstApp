package com.example.NewProject.payload.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PasswordResetRequest {

  private String email;

  @JsonCreator
  public PasswordResetRequest(@JsonProperty("email") String email) {
    this.email = email;

  }

  public String getEmail() {
    return email;
  }

  public PasswordResetRequest setEmail(String email) {
    this.email = email;
    return this;
  }
}
