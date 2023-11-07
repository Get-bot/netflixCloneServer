package com.netflixclone.app.controller;

import com.netflixclone.app.entity.role.Role;
import com.netflixclone.app.entity.user.User;
import com.netflixclone.app.exception.CustomException;
import com.netflixclone.app.payload.request.LoginRequest;
import com.netflixclone.app.payload.request.SignupRequest;
import com.netflixclone.app.payload.response.ApiResponse;
import com.netflixclone.app.payload.response.JwtResponse;
import com.netflixclone.app.repository.role.RoleRepository;
import com.netflixclone.app.repository.user.UserRepository;
import com.netflixclone.app.security.jwt.JwtUtils;
import com.netflixclone.app.security.services.UserDetailsImpl;
import com.netflixclone.app.service.role.RoleService;
import com.netflixclone.app.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
  private final AuthenticationManager authenticationManager;

  private final UserRepository userRepository;

  private final UserService userService;

  private final JwtUtils jwtUtils;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
    return ResponseEntity.ok().body(authenticateAndGenerateJWT(loginRequest.getEmail(), loginRequest.getPassword()));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerAndAuthenticateUser(@RequestBody SignupRequest signupRequest) throws CustomException {
    // 유저 등록
    userService.registerUser(signupRequest);

    JwtResponse jwtResponse = authenticateAndGenerateJWT(signupRequest.getEmail(), signupRequest.getPassword());
    ApiResponse<JwtResponse> response = ApiResponse.setApiResponse(true, "회원 가입이 완료 되었습니다!", jwtResponse);

    return ResponseEntity.ok().body(response);
  }

  // 인증 및 JWT 토큰 생성
  private JwtResponse authenticateAndGenerateJWT(String email, String password) {
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(email, password));
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = jwtUtils.generateJwtToken(authentication);
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
    List<String> roleNames = userDetails.getAuthorities().stream()
        .map(GrantedAuthority::getAuthority)
        .toList();

    return JwtResponse.setJwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roleNames);
  }

}
