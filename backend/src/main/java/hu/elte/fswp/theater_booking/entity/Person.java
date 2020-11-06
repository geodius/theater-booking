package hu.elte.fswp.theater_booking.entity;

import lombok.*;
import org.apache.logging.log4j.util.Strings;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Data
@Entity
@Table(name = "people")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Person {
    private static Pattern emailPattern = Pattern.compile("^([A-z0-9\\.\\-\\_]+)@([a-z0-9\\.\\-\\_]+)\\.([a-z]{2,})$");
    private static Pattern namePattern = Pattern.compile("^[\\p{Lu}][\\p{Ll}]+( [\\p{Lu}][\\p{Ll}]+)+$");

    public static boolean isPersonValid(Person person){
        return isNameValid(person.name) && isEmailValid(person.email) && isPasswordValid(person.password);
    }

    public static boolean isEmailValid(String email) {
        return emailPattern.matcher(email).matches();
    }

    public static boolean isNameValid(String name) { return namePattern.matcher(name).matches();}

    public static boolean isPasswordValid(String password) { return Strings.isNotBlank(password);}

    @Id
    private int id;
    private String name;
    private String email;
    private String password;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Reservation> reservations;

    @ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
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

    public boolean hasRole(Role role) {
        return roles.contains(role);
    }

    public boolean hasRole(RoleType roleType) {
        return roles.stream().anyMatch(r -> r.getRoleType().equals(roleType));
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addReservation(Reservation reservation) {
        if (reservations.contains((reservation))) return;
        reservations.add(reservation);
    }

    public void removeReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public void addRole(Role role) {
        if (roles == null) roles = new ArrayList<>();
        if (roles.contains((role))) return;
        roles.add(role);
    }

    public void removeRole(Role role) {
        roles.remove(role);
    }

    public void clearRoles(){
        if (roles == null) return;
        roles.clear();
    }
}
