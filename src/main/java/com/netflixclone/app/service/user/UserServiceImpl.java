package com.netflixclone.app.service.user;

import com.netflixclone.app.entity.role.Role;
import com.netflixclone.app.entity.user.User;
import com.netflixclone.app.entity.userroles.UserRoles;
import com.netflixclone.app.exception.CustomException;
import com.netflixclone.app.payload.request.SignupRequest;
import com.netflixclone.app.repository.role.RoleRepository;
import com.netflixclone.app.repository.user.UserRepository;
import com.netflixclone.app.repository.userroles.UserRolesRepository;
import com.netflixclone.app.service.role.RoleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  private final UserRolesRepository userRolesRepository;

  private final RoleService roleService;

  private final PasswordEncoder encoder;

  @Override
  @Transactional
  public void registerUser(SignupRequest signupRequest) throws CustomException {
    if (userRepository.existsByEmail(signupRequest.getEmail())) {
      throw new CustomException("이미 사용중인 이메일입니다.");
    }
    User user = User.registerUser(signupRequest.getEmail(), signupRequest.getUsername(), encoder.encode(signupRequest.getPassword()), roleService.getDefaultRole());
    userRepository.save(user);
  }

}
