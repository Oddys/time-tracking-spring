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
@Table(name = "user_activities")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserActivity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long userActivityId;

  @NotNull
  private Boolean assigned;

  @NotNull
  private Boolean statusChangeRequested;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToOne
  @JoinColumn(name = "activity_id")
  @NotNull
  private Activity activity;
}
