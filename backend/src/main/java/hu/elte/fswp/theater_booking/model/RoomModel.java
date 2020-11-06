package hu.elte.fswp.theater_booking.model;

import hu.elte.fswp.theater_booking.database.RoomRepo;
import hu.elte.fswp.theater_booking.entity.Room;
import hu.elte.fswp.theater_booking.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomModel {
    public static RoomModel instance;

    public static RoomModel getInstance() {
        assert(instance != null);
        return instance;
    }

    private final RoomRepo roomRepo;

    @Autowired
    public RoomModel(RoomRepo roomRepo) {
        assert(instance == null);
        instance = this;
        this.roomRepo = roomRepo;
    }

    public Optional<Room> create(Room room) {
        if (!Room.isRoomValid(room)) return Optional.empty();
        if (roomRepo.findById(room.getId()).isPresent()) return Optional.empty();
        roomRepo.save(room);
        return roomRepo.findByName(room.getName());
    }

    public Optional<Room> getByName(String name) {
        return roomRepo.findByName(name);
    }

    public List<Room> getAll() {
        return Utility.ConvertIterableToList(roomRepo.findAll());
    }

    public List<Room> getByCapacityLessThan(int limit) {
        return roomRepo.findAllByCapacityLessThan(limit);
    }

    public List<Room> getByCapacityGreaterThan(int limit) {
        return roomRepo.findAllByCapacityGreaterThan(limit);
    }

    public List<Room> getByCapacityBetween(int lhs, int rhs) {
        return roomRepo.findAllByCapacityBetween(lhs, rhs);
    }

    public Optional<Room> modify(Room room) {
        if (!Room.isRoomValid(room)) return Optional.empty();
        if (roomRepo.findById(room.getId()).isEmpty()) return Optional.empty();
        roomRepo.save(room);
        return roomRepo.findById(room.getId());
    }

    public boolean delete(Room room) {
        if (!roomRepo.existsById(room.getId())) return false;
        roomRepo.delete(room);
        return true;
    }
}
