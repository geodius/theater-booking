package hu.elte.fswp.theater_booking.database;

import hu.elte.fswp.theater_booking.entity.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PersonRepo extends CrudRepository<Person, Integer> {
    List<Person> findAllByName(String name);
    Optional<Person> findByEmail(String email);
}
