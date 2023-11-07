package com.netflixclone.app.service.role;

import com.netflixclone.app.entity.role.Role;
import com.netflixclone.app.enums.ERole;
import com.netflixclone.app.repository.role.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

  private final RoleRepository roleRepository;

  @Override
  public Set<Role> getDefaultRole() {
    Role userRole = roleRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Error: 해당하는 권한이 없습니다."));
    Set<Role> roles = new HashSet<>();
    roles.add(userRole);
    return roles;
  }
}
