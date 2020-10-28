package hu.elte.fswp.theater_booking.database;

import hu.elte.fswp.theater_booking.entity.Play;
import hu.elte.fswp.theater_booking.entity.Room;
import hu.elte.fswp.theater_booking.entity.Schedule;
import org.springframework.data.repository.CrudRepository;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepo extends CrudRepository<Schedule, Integer> {
    Optional<Schedule> findByRoom(Room room);
    Optional<Schedule> findByPlay(Play play);

    List<Schedule> findAllByStartBefore(Time limit);
    List<Schedule> findAllByStartAfter(Time limit);
    List<Schedule> findAllByStartBetween(Time lhs, Time rhs);
}
