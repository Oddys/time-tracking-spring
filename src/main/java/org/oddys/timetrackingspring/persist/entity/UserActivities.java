package org.oddys.timetrackingspring.persist.entity;

public class UserActivities {

  private long userActivityId;
  private long assigned;
  private long statusChangeRequested;
  private long userId;
  private long activityId;


  public long getUserActivityId() {
    return userActivityId;
  }

  public void setUserActivityId(long userActivityId) {
    this.userActivityId = userActivityId;
  }


  public long getAssigned() {
    return assigned;
  }

  public void setAssigned(long assigned) {
    this.assigned = assigned;
  }


  public long getStatusChangeRequested() {
    return statusChangeRequested;
  }

  public void setStatusChangeRequested(long statusChangeRequested) {
    this.statusChangeRequested = statusChangeRequested;
  }


  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }


  public long getActivityId() {
    return activityId;
  }

  public void setActivityId(long activityId) {
    this.activityId = activityId;
  }

}
