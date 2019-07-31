package com.example.bantumei.Pojo;

import androidx.annotation.NonNull;

import java.util.Date;

public class Sensor {
    public String SoilMoisture;
    public String Temperature;
    public String Humidity;
    public Date timeStamp;

    public Sensor( String soilMoisture, String temperature, String humidity,Date timeStamp) {
        SoilMoisture = soilMoisture;
        Temperature = temperature;
        Humidity = humidity;
        this.timeStamp = timeStamp;
    }

    @NonNull
    @Override
    public String toString() {
        return "Temperatur"+Temperature+" C";
    }
}
