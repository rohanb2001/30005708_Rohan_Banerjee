package console.schemas;

public class ClassSchedule {
	private int scheduleId;
    private String className;
    private int trainerId;
    private String dayOfWeek;
    private String startTime;
    private String endTime;

    // Getters
    public int getScheduleId() {
        return scheduleId;
    }

    public String getClassName() {
        return className;
    }

    public int getTrainerId() {
        return trainerId;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    // Setters
    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setTrainerId(int trainerId) {
        this.trainerId = trainerId;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
