package hu.elte.fswp.theater_booking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "plays")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Play {
    @Id
    private int id;
    private String name;
    private int length;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Schedule> schedules;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
