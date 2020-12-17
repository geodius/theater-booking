package hu.elte.fswp.theater_booking.controller;

import hu.elte.fswp.theater_booking.entity.Play;
import hu.elte.fswp.theater_booking.entity.Room;
import hu.elte.fswp.theater_booking.entity.Schedule;
import hu.elte.fswp.theater_booking.model.PlayModel;
import hu.elte.fswp.theater_booking.model.RoomModel;
import hu.elte.fswp.theater_booking.model.ScheduleModel;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
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

    @GetMapping("/schedule/getById/{id}")
    public ResponseEntity<Schedule> getById(@PathVariable int id) {
        Optional<Schedule> schedule = ScheduleModel.getInstance().getById(id);
        return schedule.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/schedule/getByPlay/{playId}")
    public ResponseEntity<List<Schedule>> getByPlay(@PathVariable int playId){
        Optional<Play> play = PlayModel.getInstance().getById(playId);
        if (play.isEmpty()) return ResponseEntity.notFound().build();
        List<Schedule> results = ScheduleModel.getInstance().getByPlay(play.get());
        return ResponseEntity.ok(results);
    }

    @GetMapping("/schedule/getByRoom/{roomId}")
    public ResponseEntity<List<Schedule>> getByRoom(@PathVariable int roomId){
        Optional<Room> room = RoomModel.getInstance().getById(roomId);
        if (room.isEmpty()) return ResponseEntity.notFound().build();
        List<Schedule> results = ScheduleModel.getInstance().getByRoom(room.get());
        return ResponseEntity.ok(results);
    }

    @GetMapping("/schedule/getBefore/{epoch}")
    public ResponseEntity<List<Schedule>> getBefore(@PathVariable long epoch){
        LocalDateTime limit = LocalDateTime.ofEpochSecond(epoch, 0, ZoneOffset.MIN);
        List<Schedule> results = ScheduleModel.getInstance().getBefore(limit);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/schedule/getAfter/{epoch}")
    public ResponseEntity<List<Schedule>> getAfter(@PathVariable long epoch){
        LocalDateTime limit = LocalDateTime.ofEpochSecond(epoch, 0, ZoneOffset.MIN);
        List<Schedule> results = ScheduleModel.getInstance().getAfter(limit);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/schedule/getBetween/{startEpoch}/{endEpoch}")
    public ResponseEntity<List<Schedule>> getBetween(@PathVariable long startEpoch, @PathVariable long endEpoch) {
        LocalDateTime start = LocalDateTime.ofEpochSecond(startEpoch, 0, ZoneOffset.MIN);
        LocalDateTime end = LocalDateTime.ofEpochSecond(endEpoch, 0, ZoneOffset.MIN);
        List<Schedule> results = ScheduleModel.getInstance().getBetween(start, end);
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