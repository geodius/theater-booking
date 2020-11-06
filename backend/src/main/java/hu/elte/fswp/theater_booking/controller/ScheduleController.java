package hu.elte.fswp.theater_booking.controller;

import hu.elte.fswp.theater_booking.entity.Play;
import hu.elte.fswp.theater_booking.entity.Room;
import hu.elte.fswp.theater_booking.entity.Schedule;
import hu.elte.fswp.theater_booking.model.ScheduleModel;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@RestController
public class ScheduleController {
    @PutMapping("/schedule/create")
    public ResponseEntity<Schedule> create(@RequestBody Schedule schedule){
        Optional<Schedule> result = ScheduleModel.getInstance().create(schedule);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }

    @GetMapping("/schedule/getAll")
    public ResponseEntity<List<Schedule>> getAll(){
        List<Schedule> results = ScheduleModel.getInstance().getAll();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/schedule/getByPlay")
    public ResponseEntity<List<Schedule>> getByPlay(@RequestBody Play play){
        List<Schedule> results = ScheduleModel.getInstance().getByPlay(play);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/schedule/getByRoom")
    public ResponseEntity<List<Schedule>> getByRoom(@RequestBody Room room){
        List<Schedule> results = ScheduleModel.getInstance().getByRoom(room);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/schedule/getBefore")
    public ResponseEntity<List<Schedule>> getBefore(@RequestBody Time limit){
        List<Schedule> results = ScheduleModel.getInstance().getBefore(limit);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/schedule/getAfter")
    public ResponseEntity<List<Schedule>> getAfter(@RequestBody Time limit){
        List<Schedule> results = ScheduleModel.getInstance().getAfter(limit);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/schedule/getBetween")
    public ResponseEntity<List<Schedule>> getBetween(@RequestBody Pair<Time, Time> timePair) {
        List<Schedule> results = ScheduleModel.getInstance().getBetween(timePair.getFirst(), timePair.getSecond());
        return ResponseEntity.ok(results);
    }

    @PatchMapping("/schedule/modify")
    public ResponseEntity<Schedule> modify(@RequestBody Schedule schedule) {
        Optional<Schedule> result = ScheduleModel.getInstance().modify(schedule);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_MODIFIED).build());
    }

    @DeleteMapping("/schedule/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Schedule schedule) {
        boolean result = ScheduleModel.getInstance().delete(schedule);
        return result ? ResponseEntity.ok(true) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}