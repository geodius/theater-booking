package hu.elte.fswp.theater_booking.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class SecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService = new TheaterBookingUserDetailsService();
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/person/modify").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/person/delete").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/person/getCurrent").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/reservation/create").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/reservation/getByPerson").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/reservation/modify").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/reservation/delete").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/person/getAll").hasAuthority("ADMIN")
                .antMatchers("/person/getByEmail").hasAuthority("ADMIN")
                .antMatchers("/person/getByName").hasAuthority("ADMIN")
                .antMatchers("/person/assignRole").hasAuthority("ADMIN")
                .antMatchers("/person/removeRole").hasAuthority("ADMIN")
                .antMatchers("/play/create").hasAuthority("ADMIN")
                .antMatchers("/play/modify").hasAuthority("ADMIN")
                .antMatchers("/play/delete").hasAuthority("ADMIN")
                .antMatchers("/room/create").hasAuthority("ADMIN")
                .antMatchers("/room/modify").hasAuthority("ADMIN")
                .antMatchers("/room/delete").hasAuthority("ADMIN")
                .antMatchers("/schedule/create").hasAuthority("ADMIN")
                .antMatchers("/schedule/modify").hasAuthority("ADMIN")
                .antMatchers("/schedule/delete").hasAuthority("ADMIN")
                .antMatchers("/reservation/getByPlay").hasAuthority("ADMIN")
                .antMatchers("/reservation/getByRoom").hasAuthority("ADMIN")
                .antMatchers("/reservation/getBySchedule").hasAuthority("ADMIN")
                .antMatchers("/reservation/getAll").hasAuthority("ADMIN")
                .and()
                .httpBasic();
//                .and()
//                .headers()
//                .frameOptions().disable();
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
