package com.example.backend.security.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.example.backend.common.payload.response.OkResponse;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class AuthDto extends OkResponse {

  @JsonIgnore
  private String accessToken;

  @JsonIgnore
  private String refreshToken;
}