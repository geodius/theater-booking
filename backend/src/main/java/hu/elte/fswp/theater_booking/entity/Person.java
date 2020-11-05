package hu.elte.fswp.theater_booking.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "people")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Person {
    @Id
    private int id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "role_connector", joinColumns = { @JoinColumn(name = "people_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private List<Role> roles;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void addReservation(Reservation reservation) {
        if (reservations.contains((reservation))) return;
        reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public void addRole(Role role) {
        if (roles.contains((role))) return;
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }
}
