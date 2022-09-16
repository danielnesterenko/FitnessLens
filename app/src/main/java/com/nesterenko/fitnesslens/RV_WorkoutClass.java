package com.nesterenko.fitnesslens;

public class RV_WorkoutClass {

    private String workoutName;
    private String lifted;
    private String staticKG;

    public RV_WorkoutClass(String workoutName, String lifted) {
        this.workoutName = workoutName;
        this.lifted = lifted;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public String getLifted() {
        return lifted;
    }

    public void setLifted(String lifted) {
        this.lifted = lifted;
    }
}
