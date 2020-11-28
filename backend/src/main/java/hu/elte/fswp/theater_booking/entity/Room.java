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
@Table(name = "rooms")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Room {
    public static boolean isRoomValid(Room room){
        return room.getName() != null && room.getName().length() > 0;
    }
    @Id
    private int id;
    private String name;
    private int capacity;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public boolean isSeatValid(int seat) {
        return seat > 0 && seat <= capacity;
    }
}
