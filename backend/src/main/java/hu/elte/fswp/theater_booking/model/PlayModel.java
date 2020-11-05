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
    public static PlayModel instance;

    public static PlayModel getInstance() {
        assert(instance != null);
        return instance;
    }

    private final PlayRepo PlayRepo;

    @Autowired
    public PlayModel(PlayRepo playRepo) {
        assert(instance == null);
        instance = this;
        this.PlayRepo = playRepo;
    }

    public Optional<Play> create(Play play) {
        if (!play.isPlayValid(play)) return Optional.empty();
        if (PlayRepo.findById(play.getId()).isPresent()) return Optional.empty();
        PlayRepo.save(play);
        return PlayRepo.findFirstByNameOrderByIdDesc(play.getName());
    }

    public List<Play> getByName(String name) {
        return PlayRepo.findAllByName(name);
    }

    public List<Play> getAll() {
        return Utility.ConvertIterableToList(PlayRepo.findAll());
    }

    public Optional<Play> modify(Play play) {
        if (!Play.isPlayValid(play)) return Optional.empty();
        if (PlayRepo.findById(play.getId()).isEmpty()) return Optional.empty();
        PlayRepo.save(play);
        return PlayRepo.findById(play.getId());
    }

    public boolean delete(Play play) {
        if (!PlayRepo.existsById(play.getId())) return false;
        PlayRepo.delete(play);
        return true;
    }
}