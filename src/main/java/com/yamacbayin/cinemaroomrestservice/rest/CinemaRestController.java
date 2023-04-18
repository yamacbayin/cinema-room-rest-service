package com.yamacbayin.cinemaroomrestservice.rest;

import com.yamacbayin.cinemaroomrestservice.entity.CinemaRoom;
import com.yamacbayin.cinemaroomrestservice.entity.Seat;
import com.yamacbayin.cinemaroomrestservice.entity.Ticket;
import com.yamacbayin.cinemaroomrestservice.service.CinemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CinemaRestController {

    private CinemaService cinemaService;

    @Autowired
    public CinemaRestController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @GetMapping("/seats")
    public CinemaRoom getCinemaRoom() {
        return cinemaService.getCinemaRoom();
    }


    @PostMapping("/purchase")
    public ResponseEntity<?> purchaseTicket(@RequestBody Seat seat) {
        return cinemaService.purchaseTicket(seat);
    }

    @PostMapping("/return")
    public ResponseEntity<?> refundTicket(@RequestBody Ticket ticket) {
        return cinemaService.refundTicket(ticket);
    }

    @PostMapping("/stats")
    public ResponseEntity<?> showStats(@RequestParam(defaultValue = "") String password) {
        return cinemaService.showStatistics(password);
    }
}
