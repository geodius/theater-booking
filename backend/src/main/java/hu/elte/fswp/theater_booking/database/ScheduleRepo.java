package hu.elte.fswp.theater_booking.database;

import hu.elte.fswp.theater_booking.entity.Play;
import hu.elte.fswp.theater_booking.entity.Room;
import hu.elte.fswp.theater_booking.entity.Schedule;
import org.springframework.data.repository.CrudRepository;

import javax.swing.text.html.Option;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepo extends CrudRepository<Schedule, Integer> {
    List<Schedule> findAllByRoom(Room room);
    List<Schedule> findAllByPlay(Play play);
    Optional<Schedule> findByStartAndPlayAndRoomOrderByIdDesc(LocalDateTime start, Play play, Room room);

    List<Schedule> findAllByStartBefore(LocalDateTime limit);
    List<Schedule> findAllByStartAfter(LocalDateTime limit);
    List<Schedule> findAllByStartBetween(LocalDateTime lhs, LocalDateTime rhs);
}
