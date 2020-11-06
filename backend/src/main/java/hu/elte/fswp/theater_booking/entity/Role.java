package hu.elte.fswp.theater_booking.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Role implements GrantedAuthority {
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

    @Override
    public String getAuthority() {
        return roleType.name();
    }
}
