package hu.elte.fswp.theater_booking.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "schedule")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Schedule {
    public static boolean isScheduleValid(Schedule schedule){
        return schedule.start != null && schedule.room != null && schedule.play != null;
    }
    @Id
    private int id;
    private LocalDateTime start;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "room_id", referencedColumnName = "id")
    private Room room;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "play_id", referencedColumnName = "id")
    private Play play;

    public int getId() {
        return id;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Play getPlay() {
        return play;
    }

    public void setPlay(Play play) {
        this.play = play;
    }
}
