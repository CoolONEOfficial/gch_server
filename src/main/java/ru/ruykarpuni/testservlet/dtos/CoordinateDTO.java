package ru.ruykarpuni.testservlet.dtos;

public class CoordinateDTO {
    String latitude;
    String longtitude;

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(String longtitude) {
        this.longtitude = longtitude;
    }

    public CoordinateDTO(String latitude, String longtitude) {
        this.latitude = latitude;
        this.longtitude = longtitude;
    }
}
