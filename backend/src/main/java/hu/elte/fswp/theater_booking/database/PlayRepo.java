package hu.elte.fswp.theater_booking.database;

import hu.elte.fswp.theater_booking.entity.Play;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PlayRepo extends CrudRepository<Play,Integer> {
    List<Play> findAllByName(String name);
    Optional<Play> findFirstByNameOrderByIdDesc(String name);
}

