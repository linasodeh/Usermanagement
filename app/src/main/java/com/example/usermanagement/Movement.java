package com.example.usermanagement;

public class Movement {

    private String type;
    private int time;

    public Movement() {
    }

    public Movement(String type, int time) {
        this.type = type;
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Movement{" +
                "type='" + type + '\'' +
                ", time=" + time +
                '}';
    }
}
