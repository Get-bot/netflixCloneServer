package com.netflixclone.app.entity.role;

import com.netflixclone.app.entity.user.User;
import com.netflixclone.app.entity.userroles.UserRoles;
import com.netflixclone.app.enums.ERole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "roles")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Enumerated(EnumType.STRING)
  private ERole name;

  private String description;

}
