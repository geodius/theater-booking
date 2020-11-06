package hu.elte.fswp.theater_booking.security;

import hu.elte.fswp.theater_booking.entity.Person;
import hu.elte.fswp.theater_booking.entity.Role;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserAuthorityInterceptor implements HandlerInterceptor {
    private static Set<UserDetails> updateQueue = new HashSet<>();
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        UserWrapper user = (UserWrapper) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (!updateQueue.contains(user)) return true;
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getPerson().getRoles()));
        return true;
    }

    public static void addToUpdateQueue(Person people) {
        updateQueue.add(TheaterBookingUserDetailsService.getUserByUsername(people.getEmail()));
    }
}
