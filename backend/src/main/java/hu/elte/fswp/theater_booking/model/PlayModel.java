package hu.elte.fswp.theater_booking.model;

import hu.elte.fswp.theater_booking.entity.Play;
import hu.elte.fswp.theater_booking.database.PlayRepo;
import hu.elte.fswp.theater_booking.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayModel {
    private static PlayModel instance;

    public static PlayModel getInstance() {
        assert(instance != null);
        return instance;
    }

    private final PlayRepo playRepo;

    @Autowired
    public PlayModel(PlayRepo playRepo) {
        assert(instance == null);
        instance = this;
        this.playRepo = playRepo;
    }

    public Optional<Play> create(Play play) {
        if (!play.isPlayValid(play)) return Optional.empty();
        if (playRepo.findById(play.getId()).isPresent()) return Optional.empty();
        playRepo.save(play);
        return playRepo.findFirstByNameOrderByIdDesc(play.getName());
    }

    public List<Play> getByName(String name) {
        return playRepo.findAllByName(name);
    }

    public List<Play> getAll() {
        return Utility.ConvertIterableToList(playRepo.findAll());
    }

    public Optional<Play> modify(Play play) {
        if (!Play.isPlayValid(play)) return Optional.empty();
        if (playRepo.findById(play.getId()).isEmpty()) return Optional.empty();
        playRepo.save(play);
        return playRepo.findById(play.getId());
    }

    public boolean delete(Play play) {
        if (!playRepo.existsById(play.getId())) return false;
        playRepo.delete(play);
        return true;
    }
}