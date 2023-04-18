package com.yamacbayin.cinemaroomrestservice.service;

import com.yamacbayin.cinemaroomrestservice.entity.CinemaRoom;
import com.yamacbayin.cinemaroomrestservice.entity.Seat;
import com.yamacbayin.cinemaroomrestservice.entity.Ticket;
import com.yamacbayin.cinemaroomrestservice.exception.SeatAlreadyPurchasedException;
import com.yamacbayin.cinemaroomrestservice.exception.SeatOutOfBoundsException;
import com.yamacbayin.cinemaroomrestservice.exception.WrongPasswordException;
import com.yamacbayin.cinemaroomrestservice.exception.WrongTokenException;
import com.yamacbayin.cinemaroomrestservice.statistics.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class CinemaService {
    private CinemaRoom cinemaRoom;
    private Statistics statistics;

    @Autowired
    public CinemaService(CinemaRoom cinemaRoom, Statistics statistics) {
        this.cinemaRoom = cinemaRoom;
        this.statistics = statistics;
    }

    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }

    public ResponseEntity<?> purchaseTicket(Seat seat) {
        //check if the row and column numbers are correct
        if (seat.getRow() < 1
                || seat.getRow() > cinemaRoom.getTotalRows()
                || seat.getColumn() < 1
                || seat.getColumn() > cinemaRoom.getTotalColumns()) {
            throw new SeatOutOfBoundsException("The number of a row or a column is out of bounds!", seat);
        }

        //check if the seat is already purchased
        Optional<Seat> purchasedSeat = cinemaRoom.purchaseSeat(seat);
        if (purchasedSeat.isPresent()) {
            Ticket ticket = cinemaRoom.generateTicket(purchasedSeat.get());
            statistics.ticketPurchased(ticket.getSeat().getPrice());
            return new ResponseEntity<>(ticket, HttpStatus.OK);
        } else {
            throw new SeatAlreadyPurchasedException("The ticket has been already purchased!", seat);
        }
    }

    public ResponseEntity<?> refundTicket(Ticket ticket) {
        Optional<Seat> refundedSeat = cinemaRoom.refundSeat(ticket.getToken());
        if (refundedSeat.isPresent()) {
            statistics.ticketRefunded(refundedSeat.get().getPrice());
            return new ResponseEntity<>(Map.of("returned_ticket", refundedSeat.get()), HttpStatus.OK);
        } else {
            throw new WrongTokenException("Wrong token!", ticket.getToken());
        }
    }

    public ResponseEntity<?> showStatistics(String password) {
        if (password.equalsIgnoreCase("super_secret")) {
            return new ResponseEntity<>(statistics, HttpStatus.OK);
        } else {
            throw new WrongPasswordException("The password is wrong!");
        }
    }
}
