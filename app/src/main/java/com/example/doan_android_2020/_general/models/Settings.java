package com.example.doan_android_2020._general.models;

public class Settings {
    boolean DARK_MODE;

    public Settings(boolean DARK_MODE) {
        this.DARK_MODE = DARK_MODE;
    }

    public boolean isDARK_MODE() {
        return DARK_MODE;
    }

    public void setDARK_MODE(boolean DARK_MODE) {
        this.DARK_MODE = DARK_MODE;
    }
}
