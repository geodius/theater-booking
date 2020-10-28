package hu.elte.fswp.theater_booking.database;

import hu.elte.fswp.theater_booking.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface PersonRepo extends CrudRepository<Person, Integer> {
    Optional<Person> findByName(String name);
    Optional<Person> findByEmail(String email);
}
