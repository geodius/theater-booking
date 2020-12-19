package hu.elte.fswp.theater_booking.security;

import hu.elte.fswp.theater_booking.entity.Person;
import hu.elte.fswp.theater_booking.entity.RoleType;
import hu.elte.fswp.theater_booking.model.PersonModel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TheaterBookingUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return getUserByUsername(username);
    }
    public static UserDetails getUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> p = PersonModel.getInstance().getByEmail(username);
        if (p.isEmpty()) throw new UsernameNotFoundException("User " + username + " not found");
        return new UserWrapper(p.get());
    }
    public static Person getCurrentUser() {
        return ((UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getPerson();
    }
}
