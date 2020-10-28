package hu.elte.fswp.theater_booking.database;

import hu.elte.fswp.theater_booking.entity.Person;
import hu.elte.fswp.theater_booking.entity.Reservation;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ReservationRepo extends CrudRepository<Reservation, Integer> {
    Optional<Reservation> findByPerson(Person person);
}
