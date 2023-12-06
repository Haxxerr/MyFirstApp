package com.example.NewProject.payload.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewPasswordPutRequest {
  private final String newPasswordHash;
  private final String newPasswordHashRepeat;

  @JsonCreator
  public NewPasswordPutRequest(@JsonProperty("newPasswordHash") String newPasswordHash,
                               @JsonProperty("newPasswordHashRepeat") String newPasswordHashRepeat) {
    this.newPasswordHash = newPasswordHash;
    this.newPasswordHashRepeat = newPasswordHashRepeat;
  }

  public String getNewPasswordHash() {
    return newPasswordHash;
  }

  public String getNewPasswordHashRepeat() {
    return newPasswordHashRepeat;
  }
}
