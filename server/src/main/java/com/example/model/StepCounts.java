package com.example.model;

public class StepCounts {
    private int userId;
    private int dayId;
    private int timeInterval;
    private int stepCount;

    public StepCounts(int userId, int dayId, int timeInterval, int stepCount) {
        this.userId = userId;
        this.dayId = dayId;
        this.timeInterval = timeInterval;
        this.stepCount = stepCount;
    }

    public int getUserId() {
        return userId;
    }

    public int getDayId() {
        return dayId;
    }

    public int getTimeInterval() {
        return timeInterval;
    }

    public int getStepCount() {
        return stepCount;
    }

}
