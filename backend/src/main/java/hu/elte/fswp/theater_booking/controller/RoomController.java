package hu.elte.fswp.theater_booking.controller;

import hu.elte.fswp.theater_booking.entity.Room;
import hu.elte.fswp.theater_booking.model.RoomModel;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class RoomController {
    @PutMapping("/room/create")
    public ResponseEntity<Room> create(@RequestBody Room room){
        Optional<Room> result = RoomModel.getInstance().create(room);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }

    @GetMapping("/room/getAll")
    public ResponseEntity<List<Room>> getAll(){
        List<Room> results = RoomModel.getInstance().getAll();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/room/getByName")
    public ResponseEntity<Optional<Room>> getByName(@RequestBody String name){
        Optional<Room> results = RoomModel.getInstance().getByName(name);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/room/getByCapacityLessThan")
    public ResponseEntity<List<Room>> getByCapacityLessThan(@RequestBody int limit){
        List<Room> results = RoomModel.getInstance().getByCapacityLessThan(limit);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/room/getByCapacityGreaterThan")
    public ResponseEntity<List<Room>> getByCapacityGreaterThan(@RequestBody int limit){
        List<Room> results = RoomModel.getInstance().getByCapacityGreaterThan(limit);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/room/getByCapacityBetween")
    public ResponseEntity<List<Room>> getByCapacityBetween(@RequestBody Pair<Integer, Integer> intPair){
        List<Room> results = RoomModel.getInstance().getByCapacityBetween(intPair.getFirst(), intPair.getSecond());
        return ResponseEntity.ok(results);
    }

    @PatchMapping("/room/modify")
    public ResponseEntity<Room> modify(@RequestBody Room room) {
        Optional<Room> result = RoomModel.getInstance().modify(room);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_MODIFIED).build());
    }

    @DeleteMapping("/room/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Room room) {
        boolean result = RoomModel.getInstance().delete(room);
        return result ? ResponseEntity.ok(true) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
