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
@NoArgsConstructor
@Data
public class Activity {
  @Id
  @GeneratedValue(strategy= GenerationType.AUTO)
  private long activityId;

  @NotNull
  private String activityName;
}
