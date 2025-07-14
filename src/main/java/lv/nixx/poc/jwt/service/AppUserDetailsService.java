package lv.nixx.poc.jwt.service;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AppUserDetailsService implements UserDetailsService {

    private final Map<String, User> users = Stream.of(new User("user_admin", "1", List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))),
            new User("user_ro", "1", List.of(new SimpleGrantedAuthority("ROLE_RO"))),
            new User("user_power", "1", List.of(
                    new SimpleGrantedAuthority("ROLE_RO"),
                    new SimpleGrantedAuthority("ROLE_ADMIN")
            ))).collect(Collectors.toMap(User::getUsername, Function.identity()));

    @Override
    public User loadUserByUsername(String username) {
        return users.get(username);
    }

}

