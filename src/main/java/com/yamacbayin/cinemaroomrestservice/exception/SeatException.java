package com.yamacbayin.cinemaroomrestservice.exception;

import com.yamacbayin.cinemaroomrestservice.entity.Seat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class SeatException extends RuntimeException {

    protected Seat seat;

    public SeatException(String message, Seat seat) {
        super(message);
        this.seat = seat;
    }

    public Seat getSeat() {
        return seat;
    }

}