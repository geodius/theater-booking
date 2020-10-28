package hu.elte.fswp.theater_booking.database;

import hu.elte.fswp.theater_booking.entity.Role;
import hu.elte.fswp.theater_booking.entity.RoleType;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepo extends CrudRepository<Role, Integer> {
    Optional<Role> findByRoleType(RoleType roleType);
}
