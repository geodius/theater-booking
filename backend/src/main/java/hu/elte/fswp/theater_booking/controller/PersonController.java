package hu.elte.fswp.theater_booking.controller;

import hu.elte.fswp.theater_booking.entity.Person;
import hu.elte.fswp.theater_booking.entity.Role;
import hu.elte.fswp.theater_booking.model.PersonModel;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {
    @PutMapping("/person/create")
    public ResponseEntity<Person> create(@RequestBody Person person){
        Optional<Person> result = PersonModel.getInstance().create(person);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build());
    }

    @GetMapping("/person/getAll")
    public ResponseEntity<List<Person>> getAll(){
        List<Person> results = PersonModel.getInstance().getAll();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/person/getByEmail")
    public ResponseEntity<Person> getByEmail(@RequestBody String email){
        Optional<Person> result = PersonModel.getInstance().getByEmail(email);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    @GetMapping("/person/getByName")
    public ResponseEntity<List<Person>> getByName(@RequestBody String name){
        List<Person> results = PersonModel.getInstance().getByName(name);
        return ResponseEntity.ok(results);
    }

    @PatchMapping("/person/assignRole")
    public ResponseEntity<Person> assignRole(@RequestBody Pair<Person, Role> pair){
        Optional<Person> result = PersonModel.getInstance().modifyRole(pair.getFirst(), pair.getSecond(), true);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PatchMapping("/person/removeRole")
    public ResponseEntity<Person> removeRole(@RequestBody Pair<Person, Role> pair){
        Optional<Person> result = PersonModel.getInstance().modifyRole(pair.getFirst(), pair.getSecond(), false);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PatchMapping("/person/modify")
    public ResponseEntity<Person> modify(@RequestBody Person person) {
        Optional<Person> result = PersonModel.getInstance().modify(person);
        return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_MODIFIED).build());
    }

    @DeleteMapping("/person/delete")
    public ResponseEntity<Boolean> delete(@RequestBody Person person) {
        boolean result = PersonModel.getInstance().delete(person);
        return result ? ResponseEntity.ok(true) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
