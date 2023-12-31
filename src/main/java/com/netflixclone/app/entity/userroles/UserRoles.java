package com.netflixclone.app.entity.userroles;

import com.netflixclone.app.entity.role.Role;
import com.netflixclone.app.entity.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "user_roles")
public class UserRoles {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "role_id")
  private Role role;


  public static UserRoles createUserRoles(User user, Role role) {
    UserRoles userRoles = new UserRoles();
    userRoles.user = user;
    userRoles.role = role;
    return userRoles;
  }

}
