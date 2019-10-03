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
import java.time.LocalDate;

@Entity
@Table(name = "activity_records")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivityRecord {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long activityRecordId;

  @NotNull
  private LocalDate activityDate;

  @NotNull
  private Long duration;

  @ManyToOne
  @JoinColumn(name = "user_activity_id")
  @NotNull
  private UserActivity userActivity;
}
