package hu.elte.fswp.theater_booking.model;

import hu.elte.fswp.theater_booking.database.ScheduleRepo;
import hu.elte.fswp.theater_booking.entity.Play;
import hu.elte.fswp.theater_booking.entity.Room;
import hu.elte.fswp.theater_booking.entity.Schedule;
import hu.elte.fswp.theater_booking.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleModel {
    public static ScheduleModel instance;

    public static ScheduleModel getInstance() {
        assert(instance != null);
        return instance;
    }

    private final ScheduleRepo scheduleRepo;

    @Autowired
    public ScheduleModel(ScheduleRepo scheduleRepo) {
        assert(instance == null);
        instance = this;
        this.scheduleRepo = scheduleRepo;
    }

    public Optional<Schedule> create(Schedule schedule) {
        if (!Schedule.isScheduleValid(schedule)) return Optional.empty();
        if (scheduleRepo.findById(schedule.getId()).isPresent()) return Optional.empty();
        scheduleRepo.save(schedule);
        return scheduleRepo.findByStartAndPlayAndRoomOrderByIdDesc(schedule.getStart(), schedule.getPlay(), schedule.getRoom());
    }

    public List<Schedule> getAll() {
        return Utility.ConvertIterableToList(scheduleRepo.findAll());
    }

    public List<Schedule> getByPlay(Play play) {
        return scheduleRepo.findAllByPlay(play);
    }

    public List<Schedule> getByRoom(Room room) {
        return scheduleRepo.findAllByRoom(room);
    }

    public List<Schedule> getBefore(Time limit) {
        return scheduleRepo.findAllByStartBefore(limit);
    }

    public List<Schedule> getAfter(Time limit) {
        return scheduleRepo.findAllByStartAfter(limit);
    }

    public List<Schedule> getBetween(Time lhs, Time rhs) {
        return scheduleRepo.findAllByStartBetween(lhs, rhs);
    }

    public Optional<Schedule> modify(Schedule schedule) {
        if (!Schedule.isScheduleValid(schedule)) return Optional.empty();
        if (scheduleRepo.findById(schedule.getId()).isEmpty()) return Optional.empty();
        scheduleRepo.save(schedule);
        return scheduleRepo.findById(schedule.getId());
    }

    public boolean delete(Schedule schedule) {
        if (!scheduleRepo.existsById(schedule.getId())) return false;
        scheduleRepo.delete(schedule);
        return true;
    }
}
