package hu.elte.fswp.theater_booking.database;

import hu.elte.fswp.theater_booking.entity.Room;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface RoomRepo extends CrudRepository<Room, Integer> {
    Optional<Room> findByName(String name);

    List<Room> findAllByCapacityLessThan(int limit);
    List<Room> findAllByCapacityGreaterThan(int limit);
    List<Room> findAllByCapacityBetween(int lhs, int rhs);
}
