package org.oddys.timetrackingspring.persist.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="activities")
@Data
@NoArgsConstructor
public class Activity {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private Long activityId;

  @NotNull
  private String activityName;
}
