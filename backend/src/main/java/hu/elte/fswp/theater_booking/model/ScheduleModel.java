package hu.elte.fswp.theater_booking.model;

import hu.elte.fswp.theater_booking.database.ScheduleRepo;
import hu.elte.fswp.theater_booking.entity.Play;
import hu.elte.fswp.theater_booking.entity.Room;
import hu.elte.fswp.theater_booking.entity.Schedule;
import hu.elte.fswp.theater_booking.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleModel {
    private static ScheduleModel instance;

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
        Optional<Play> dbPlay = PlayModel.getInstance().getById(schedule.getPlay().getId());
        if (dbPlay.isEmpty()) return Optional.empty();
        Optional<Room> dbRoom = RoomModel.getInstance().getById(schedule.getRoom().getId());
        if (dbRoom.isEmpty()) return Optional.empty();
        schedule.setPlay(dbPlay.get());
        schedule.setRoom(dbRoom.get());
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

    public List<Schedule> getBefore(LocalDateTime limit) {
        return scheduleRepo.findAllByStartBefore(limit);
    }

    public List<Schedule> getAfter(LocalDateTime limit) {
        return scheduleRepo.findAllByStartAfter(limit);
    }

    public List<Schedule> getBetween(LocalDateTime lhs, LocalDateTime rhs) {
        return scheduleRepo.findAllByStartBetween(lhs, rhs);
    }

    public Optional<Schedule> modify(Schedule schedule) {
        if (!Schedule.isScheduleValid(schedule)) return Optional.empty();
        if (scheduleRepo.findById(schedule.getId()).isEmpty()) return Optional.empty();
        scheduleRepo.save(schedule);
        return scheduleRepo.findById(schedule.getId());
    }

    public boolean isSeatAvailable(Schedule schedule, int seat){
        return schedule.getRoom().isSeatValid(seat) && schedule.getReservations().stream().noneMatch(r -> r.getSeat() == seat);
    }

    public boolean delete(Schedule schedule) {
        if (!scheduleRepo.existsById(schedule.getId())) return false;
        scheduleRepo.delete(schedule);
        return true;
    }

    public Optional<Schedule> getById(int id) {
        return scheduleRepo.findById(id);
    }
}
