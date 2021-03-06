package org.oddys.timetrackingspring.persist.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy= GenerationType.IDENTITY)
  private Long userId;

  @NotNull
  private String login;

  @NotNull
  private String password;

  @NotNull
  private String firstName;

  @NotNull
  private String lastName;

  @ManyToOne
  @JoinColumn(name = "role_id")
  @NotNull
  private Role role;
}
