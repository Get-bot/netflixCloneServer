package com.netflixclone.app.service.role;

import com.netflixclone.app.entity.role.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface RoleService {
  Set<Role> getDefaultRole();
}
