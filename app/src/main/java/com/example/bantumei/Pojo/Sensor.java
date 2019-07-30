package com.example.bantumei.Pojo;

import androidx.annotation.NonNull;

public class Sensor {
    private String SoilMoisture;
    private String Temperature;
    private String Humidity;

    public Sensor( String soilMoisture, String temperature, String humidity) {
        SoilMoisture = soilMoisture;
        Temperature = temperature;
        Humidity = humidity;
    }

    @NonNull
    @Override
    public String toString() {
        return "Temperatur"+Temperature+" C";
    }
}
