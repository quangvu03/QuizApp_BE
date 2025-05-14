package com.example.helper;

import java.util.List;

public class TotalTime {

    public static String sumTimes(List<String> timeStrings) {
        int totalSeconds = 0;

        for (String time : timeStrings) {
            String[] parts = time.trim().split(":");
            int hours = 0, minutes = 0, seconds = 0;

            if (parts.length == 3) {
                hours = Integer.parseInt(parts[0].trim()); // Trim để bỏ khoảng trắng thừa
                minutes = Integer.parseInt(parts[1].trim()); // Trim để bỏ khoảng trắng thừa
                seconds = Integer.parseInt(parts[2].trim()); // Trim để bỏ khoảng trắng thừa
            } else if (parts.length == 2) {
                minutes = Integer.parseInt(parts[0].trim()); // Trim để bỏ khoảng trắng thừa
                seconds = Integer.parseInt(parts[1].trim()); // Trim để bỏ khoảng trắng thừa
            } else if (parts.length == 1) {
                seconds = Integer.parseInt(parts[0].trim()); // Trim để bỏ khoảng trắng thừa
            } else {
                throw new IllegalArgumentException("Thời gian không đúng định dạng: " + time);
            }

            totalSeconds += hours * 3600 + minutes * 60 + seconds;
        }

        int resultHours = totalSeconds / 3600;
        int remainingSeconds = totalSeconds % 3600;
        int resultMinutes = remainingSeconds / 60;
        int resultSeconds = remainingSeconds % 60;

        return String.format("%02d:%02d:%02d", resultHours, resultMinutes, resultSeconds);
    }



}
