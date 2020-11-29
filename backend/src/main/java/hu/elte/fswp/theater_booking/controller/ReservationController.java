package hu.elte.fswp.theater_booking.controller;

import hu.elte.fswp.theater_booking.entity.*;
import hu.elte.fswp.theater_booking.model.*;
import hu.elte.fswp.theater_booking.security.TheaterBookingUserDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ReservationController {
    @PutMapping("/reservation/create")
    public ResponseEntity<Reservation> create(@RequestBody Reservation reservation){
        Person currentPerson = TheaterBookingUserDetailsService.getCurrentUser();
        if (!currentPerson.hasRole(RoleType.ADMIN)) reservation.setPerson(currentPerson);
        Optional<Reservation> result = ReservationModel.getInstance().create(reservation);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }

    @GetMapping("/reservation/getAll")
    public ResponseEntity<List<Reservation>> getAll(){
        List<Reservation> results = ReservationModel.getInstance().getAll();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/reservation/getByPlay/{playId}")
    public ResponseEntity<List<Reservation>> getByPlay(@PathVariable int playId){
        Optional<Play> play = PlayModel.getInstance().getById(playId);
        if (play.isEmpty()) return ResponseEntity.notFound().build();

        List<Reservation> results = ReservationModel.getInstance().getByPlay(play.get());
        return ResponseEntity.ok(results);
    }

    @GetMapping("/reservation/getByRoom/{roomId}")
    public ResponseEntity<List<Reservation>> getByRoom(@PathVariable int roomId){
        Optional<Room> room = RoomModel.getInstance().getById(roomId);
        if (room.isEmpty()) return ResponseEntity.notFound().build();

        List<Reservation> results = ReservationModel.getInstance().getByRoom(room.get());
        return ResponseEntity.ok(results);
    }

    @GetMapping("/reservation/getBySchedule/{scheduleId}")
    public ResponseEntity<List<Reservation>> getBySchedule(@PathVariable int scheduleId){
        Optional<Schedule> schedule = ScheduleModel.getInstance().getById(scheduleId);
        if (schedule.isEmpty()) return ResponseEntity.notFound().build();

        List<Reservation> results = ReservationModel.getInstance().getBySchedule(schedule.get());
        return ResponseEntity.ok(results);
    }

    @GetMapping("/reservation/getByPerson/{personId}")
    public ResponseEntity<List<Reservation>> getByPerson(@PathVariable int personId){
        Optional<Person> person = PersonModel.getInstance().getById(personId);
        if (person.isEmpty()) return ResponseEntity.notFound().build();

        List<Reservation> results = ReservationModel.getInstance().getByPerson(person.get());
        return ResponseEntity.ok(results);
    }

    //only seat can be modified
    @PatchMapping("/reservation/modify")
    public ResponseEntity<Reservation> modify(@RequestBody Reservation reservation) {
        Person currentPerson = TheaterBookingUserDetailsService.getCurrentUser();
        if (!currentPerson.hasRole(RoleType.ADMIN) && !reservation.getPerson().equals(currentPerson)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Optional<Reservation> result = ReservationModel.getInstance().modify(reservation);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_MODIFIED).build());
    }

    @DeleteMapping("/reservation/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Reservation reservation) {
        Person currentPerson = TheaterBookingUserDetailsService.getCurrentUser();
        if (!currentPerson.hasRole(RoleType.ADMIN) && !reservation.getPerson().equals(currentPerson)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        boolean result = ReservationModel.getInstance().delete(reservation);
        return result ? ResponseEntity.ok(true) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}