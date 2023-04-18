package com.yamacbayin.cinemaroomrestservice.exception;

import com.yamacbayin.cinemaroomrestservice.entity.Seat;

public class SeatOutOfBoundsException extends SeatException {

    public SeatOutOfBoundsException(String message, Seat seat) {
        super(message, seat);
    }

}
