package com.example.NewProject.payload.response;

import java.util.List;

public class UserInfoResponse {
  private Long id;
  private String username;
  private String email;
  private final List<String> roles;

  public UserInfoResponse(Long id, String username, String email, List<String> roles) {
    this.id = id;
    this.username = username;
    this.email = email;
    this.roles = roles;
  }

  private UserInfoResponse(Builder builder) {
    setId(builder.id);
    setUsername(builder.username);
    setEmail(builder.email);
    roles = builder.roles;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static Builder builder(UserInfoResponse copy) {
    Builder builder = new Builder();
    builder.id = copy.getId();
    builder.username = copy.getUsername();
    builder.email = copy.getEmail();
    builder.roles = copy.getRoles();
    return builder;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public List<String> getRoles() {
    return roles;
  }


  public static final class Builder {
    private Long id;
    private String username;
    private String email;
    private List<String> roles;

    private Builder() {
    }

    public Builder id(Long id) {
      this.id = id;
      return this;
    }

    public Builder username(String username) {
      this.username = username;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public Builder roles(List<String> roles) {
      this.roles = roles;
      return this;
    }

    public UserInfoResponse build() {
      return new UserInfoResponse(this);
    }
  }
}
