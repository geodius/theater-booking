package hu.elte.fswp.theater_booking.controller;

import hu.elte.fswp.theater_booking.entity.Room;
import hu.elte.fswp.theater_booking.model.RoomModel;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
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

    @GetMapping("/room/getByName/{encodedName}")
    public ResponseEntity<Optional<Room>> getByName(@PathVariable String encodedName){
        String name;
        try { name = new String(Base64.getDecoder().decode(encodedName), Charset.forName("ISO-8859-2")); }
        catch (Exception e) { return ResponseEntity.badRequest().build(); }

        Optional<Room> results = RoomModel.getInstance().getByName(name);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/room/getByCapacityLessThan/{limit}")
    public ResponseEntity<List<Room>> getByCapacityLessThan(@PathVariable int limit){
        List<Room> results = RoomModel.getInstance().getByCapacityLessThan(limit);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/room/getByCapacityGreaterThan/{limit}")
    public ResponseEntity<List<Room>> getByCapacityGreaterThan(@PathVariable int limit){
        List<Room> results = RoomModel.getInstance().getByCapacityGreaterThan(limit);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/room/getByCapacityBetween/{start}/{end}")
    public ResponseEntity<List<Room>> getByCapacityBetween(@PathVariable int start, @PathVariable int end){
        List<Room> results = RoomModel.getInstance().getByCapacityBetween(start, end);
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
