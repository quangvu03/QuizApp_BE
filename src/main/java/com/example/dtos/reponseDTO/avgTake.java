package com.example.dtos.reponseDTO;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class avgTake {
    private double avgScore;
    private String avgtime;

    private int totalTake;



    public double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    public String getAvgtime() {
        return avgtime;
    }

    public void setAvgtime(String avgtime) {
        this.avgtime = avgtime;
    }

    public int getTotalTake() {
        return totalTake;
    }

    public void setTotalTake(int totalTake) {
        this.totalTake = totalTake;
    }
}
