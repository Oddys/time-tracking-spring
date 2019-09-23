package org.oddys.timetrackingspring.persist.entity;

public class ActivityRecords {

  private long activityRecordId;
  private java.sql.Date activityDate;
  private long duration;
  private long userActivityId;


  public long getActivityRecordId() {
    return activityRecordId;
  }

  public void setActivityRecordId(long activityRecordId) {
    this.activityRecordId = activityRecordId;
  }


  public java.sql.Date getActivityDate() {
    return activityDate;
  }

  public void setActivityDate(java.sql.Date activityDate) {
    this.activityDate = activityDate;
  }


  public long getDuration() {
    return duration;
  }

  public void setDuration(long duration) {
    this.duration = duration;
  }


  public long getUserActivityId() {
    return userActivityId;
  }

  public void setUserActivityId(long userActivityId) {
    this.userActivityId = userActivityId;
  }

}
