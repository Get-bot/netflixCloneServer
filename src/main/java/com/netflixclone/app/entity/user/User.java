package com.netflixclone.app.entity.user;

import com.netflixclone.app.entity.role.Role;
import com.netflixclone.app.entity.userroles.UserRoles;
import com.netflixclone.app.enums.UserState;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  private String username;

  private String password;

  @CreatedDate
  private LocalDateTime createdAt;

  @OneToMany(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
  private Set<UserRoles> roles = new HashSet<>();

  private Integer status;

  public static User registerUser(String email, String username, String password, Set<Role> roles) {
    User user = new User();
    user.email = email;
    user.username = username;
    user.password = password;
    user.status = UserState.ACTIVE.getValue();

    for (Role role : roles) {
      UserRoles userRoles = UserRoles.createUserRoles(user, role);
      user.getRoles().add(userRoles);
    }

    return user;
  }

}
