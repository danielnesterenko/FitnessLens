package com.nesterenko.fitnesslens;

public class RV_ExerciseClass {

    private String exerciseNamePrefix;
    private String exerciseName;
    private String liftedPrefix;
    private String lifted;
    private String liftedSuffix;

    public RV_ExerciseClass(String exerciseName, String lifted) {
        this.exerciseName = exerciseName;
        this.lifted = lifted;
    }

    public String getExerciseNamePrefix() {
        return exerciseNamePrefix;
    }

    public void setExerciseNamePrefix(String exerciseNamePrefix) {
        this.exerciseNamePrefix = exerciseNamePrefix;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getLiftedPrefix() {
        return liftedPrefix;
    }

    public void setLiftedPrefix(String liftedPrefix) {
        this.liftedPrefix = liftedPrefix;
    }

    public String getLifted() {
        return lifted;
    }

    public void setLifted(String lifted) {
        this.lifted = lifted;
    }

    public String getLiftedSuffix() {
        return liftedSuffix;
    }

    public void setLiftedSuffix(String liftedSuffix) {
        this.liftedSuffix = liftedSuffix;
    }
}
