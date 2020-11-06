package hu.elte.fswp.theater_booking.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hu.elte.fswp.theater_booking.entity.Person;
import hu.elte.fswp.theater_booking.entity.Role;
import hu.elte.fswp.theater_booking.model.PersonModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserWrapper implements UserDetails {
    @JsonIgnore
    private final Person person;

    public UserWrapper(Person people) {
        this.person = people;
    }

    public Person getPerson() {
        return PersonModel.getInstance().getById(person.getId()).orElse(null);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return person.getRoles();
    }

    @Override
    public String getPassword() {
        return person.getPassword();
    }

    @Override
    public String getUsername() {
        return person.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
