package com.yamacbayin.cinemaroomrestservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket {

    public static boolean ALIVE = true;
    public static boolean EXPIRED = false;

    private String token;
    private boolean isAlive;
    @JsonProperty("ticket")
    private Seat seat;

    public Ticket() {

    }

    public Ticket(String token, boolean isAlive, Seat seat) {
        this.token = token;
        this.isAlive = isAlive;
        this.seat = seat;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @JsonIgnore
    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return "Token{" +
                "token='" + token + '\'' +
                ", isAlive=" + isAlive +
                ", seat=" + seat +
                '}';
    }
}
