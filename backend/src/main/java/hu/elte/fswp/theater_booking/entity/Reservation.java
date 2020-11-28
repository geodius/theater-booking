package hu.elte.fswp.theater_booking.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "reservation")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Reservation {
    @Id
    private int id;
    private int seat;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "people_id", referencedColumnName = "id")
    private Person person;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "schedule_id", referencedColumnName = "id")
    private Schedule schedule;

    public int getId() {
        return id;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}
