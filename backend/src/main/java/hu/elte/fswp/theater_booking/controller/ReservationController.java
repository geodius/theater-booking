package hu.elte.fswp.theater_booking.controller;

import hu.elte.fswp.theater_booking.entity.*;
import hu.elte.fswp.theater_booking.model.ReservationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ReservationController {
    @PutMapping("/reservation/create")
    public ResponseEntity<Reservation> create(@RequestBody Reservation reservation){
        Optional<Reservation> result = ReservationModel.getInstance().create(reservation);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }

    @GetMapping("/reservation/getAll")
    public ResponseEntity<List<Reservation>> getAll(){
        List<Reservation> results = ReservationModel.getInstance().getAll();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/reservation/getByPlay")
    public ResponseEntity<List<Reservation>> getByPlay(@RequestBody Play play){
        List<Reservation> results = ReservationModel.getInstance().getByPlay(play);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/reservation/getByRoom")
    public ResponseEntity<List<Reservation>> getByRoom(@RequestBody Room room){
        List<Reservation> results = ReservationModel.getInstance().getByRoom(room);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/reservation/getBySchedule")
    public ResponseEntity<List<Reservation>> getBySchedule(@RequestBody Schedule schedule){
        List<Reservation> results = ReservationModel.getInstance().getBySchedule(schedule);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/reservation/getByPerson")
    public ResponseEntity<List<Reservation>> getByPerson(@RequestBody Person person){
        List<Reservation> results = ReservationModel.getInstance().getByPerson(person);
        return ResponseEntity.ok(results);
    }

    //only seat can be modified
    @PatchMapping("/reservation/modify")
    public ResponseEntity<Reservation> modify(@RequestBody Reservation reservation) {
        Optional<Reservation> result = ReservationModel.getInstance().modify(reservation);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_MODIFIED).build());
    }

    @DeleteMapping("/reservation/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Reservation reservation) {
        boolean result = ReservationModel.getInstance().delete(reservation);
        return result ? ResponseEntity.ok(true) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}