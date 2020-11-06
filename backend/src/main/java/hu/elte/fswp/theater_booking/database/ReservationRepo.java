package hu.elte.fswp.theater_booking.database;

import hu.elte.fswp.theater_booking.entity.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ReservationRepo extends CrudRepository<Reservation, Integer> {
    Optional<Reservation> findByScheduleAndSeat(Schedule schedule, int seat);

    List<Reservation> findAllByPerson(Person person);
    List<Reservation> findAllBySchedule_Play(Play play);
    List<Reservation> findAllBySchedule_Room(Room room);
    List<Reservation> findAllBySchedule(Schedule schedule);
}
