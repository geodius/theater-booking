package hu.elte.fswp.theater_booking.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Play implements DBEntity {
    public static boolean isPlayValid(Play play){
        return play.getName() != null && play.getName().length() > 0 && play.getLength() > 0;
    }
    @Id
    private int id;
    private String name;
    private int length;
    private String logline;

    @OneToMany(mappedBy = "play", cascade = CascadeType.ALL)
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

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getLogline() {
        return logline;
    }

    public void setLogline(String logline) {
        this.logline = logline;
    }

    @Override
    public boolean isSameAs(DBEntity other) {
        return other.getClass().equals(this.getClass()) && other.getId() == id;
    }
}
