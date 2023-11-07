package com.netflixclone.app.payload.request;

import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.NotBlank;

import java.util.Set;

@Getter
@Setter
public class SignupRequest {
  @NotBlank
  private String username;

  @NotBlank
  private String email;

  private Set<String> role;

  @NotBlank
  private String password;
}
