package hu.elte.fswp.theater_booking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Role {
    @Id
    private int id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "role_id")
    private RoleType roleType;

    @ManyToMany(mappedBy = "roles", cascade = CascadeType.DETACH)
    private List<Person> people;

    public int getId() {
        return id;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public List<Person> getPeople() {
        return people;
    }
}
