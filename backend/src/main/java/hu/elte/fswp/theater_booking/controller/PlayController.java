package hu.elte.fswp.theater_booking.controller;


import hu.elte.fswp.theater_booking.entity.Play;
import hu.elte.fswp.theater_booking.model.PlayModel;
import org.hibernate.collection.internal.PersistentBag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PlayController {
    @PutMapping("/play/create")
    public ResponseEntity<Play> create(@RequestBody Play play){
        Optional<Play> result = PlayModel.getInstance().create(play);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }

    @GetMapping("/play/getAll")
    public ResponseEntity<List<Play>> getAll(){
        List<Play> results = PlayModel.getInstance().getAll();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/play/getByName")
    public ResponseEntity<List<Play>> getByName(@RequestBody String name){
        List<Play> results = PlayModel.getInstance().getByName(name);
        return ResponseEntity.ok(results);
    }

    @PatchMapping("/play/modify")
    public ResponseEntity<Play> modify(@RequestBody Play play) {
        Optional<Play> result = PlayModel.getInstance().modify(play);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_MODIFIED).build());
    }

    @DeleteMapping("/play/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Play play) {
        boolean result = PlayModel.getInstance().delete(play);
        return result ? ResponseEntity.ok(true) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}