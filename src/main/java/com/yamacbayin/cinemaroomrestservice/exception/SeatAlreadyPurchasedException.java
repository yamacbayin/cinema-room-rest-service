package com.yamacbayin.cinemaroomrestservice.exception;

import com.yamacbayin.cinemaroomrestservice.entity.Seat;

public class SeatAlreadyPurchasedException extends SeatException {

    public SeatAlreadyPurchasedException(String message, Seat seat) {
        super(message, seat);
        this.seat = seat;
    }

}
