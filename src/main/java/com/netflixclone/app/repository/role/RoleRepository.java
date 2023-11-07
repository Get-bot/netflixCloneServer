package com.netflixclone.app.repository.role;

import com.netflixclone.app.entity.role.Role;
import com.netflixclone.app.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
