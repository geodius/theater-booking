package hu.elte.fswp.theater_booking.model;


import hu.elte.fswp.theater_booking.database.ReservationRepo;
import hu.elte.fswp.theater_booking.entity.*;
import hu.elte.fswp.theater_booking.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationModel {
    private static ReservationModel instance;

    public static ReservationModel getInstance() {
        assert(instance != null);
        return instance;
    }

    private final ReservationRepo reservationRepo;

    @Autowired
    public ReservationModel(ReservationRepo reservationRepo) {
        assert(instance == null);
        instance = this;
        this.reservationRepo = reservationRepo;
    }

    public Optional<Reservation> create(Reservation reservation) {
        if (reservationRepo.findById(reservation.getId()).isPresent()) return Optional.empty();
        Optional<Schedule> temp = ScheduleModel.getInstance().getById(reservation.getSchedule().getId());
        if (temp.isEmpty()) return Optional.empty();
        Schedule schedule = temp.get();
        if (!ScheduleModel.getInstance().isSeatAvailable(schedule, reservation.getSeat())){
            return Optional.empty();
        }
        reservationRepo.save(reservation);
        return reservationRepo.findByScheduleAndSeat(reservation.getSchedule(), reservation.getSeat());
    }

    public List<Reservation> getAll() {
        return Utility.ConvertIterableToList(reservationRepo.findAll());
    }

    public List<Reservation> getByPlay(Play play) {
        return reservationRepo.findAllBySchedule_Play(play);
    }

    public List<Reservation> getByRoom(Room room) {
        return reservationRepo.findAllBySchedule_Room(room);
    }

    public List<Reservation> getBySchedule(Schedule schedule) {
        return reservationRepo.findAllBySchedule(schedule);
    }

    public List<Reservation> getByPerson(Person person) {
        return reservationRepo.findAllByPerson(person);
    }

    public Optional<Reservation> modify(Reservation reservation) {
        Optional<Reservation> temp = reservationRepo.findById(reservation.getId());
        if ( temp.isEmpty() ){
            return Optional.empty();
        }
        Reservation dbReservation = temp.get();
        dbReservation.setSeat(reservation.getSeat());
        if (!dbReservation.equals(reservation)){
            return Optional.empty();
        }
        reservationRepo.save(dbReservation);
        return Optional.of(dbReservation);
    }

    public boolean delete(Reservation reservation) {
        if (!reservationRepo.existsById(reservation.getId())) return false;
        reservationRepo.delete(reservation);
        return true;
    }
}

