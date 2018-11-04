package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.job4j.domain.User;
import ru.job4j.repository.UserRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<User> getAll() {
        return (List<User>) this.repository.findAll();
    }

    @Override
    public User add(User user) {
        return this.repository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = repository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        System.out.println("id - " + user.getId());

        MyUserPrincipal principal = new MyUserPrincipal(user);
        System.out.println("password - " + principal.getPassword());

        return principal;
    }

    private class MyUserPrincipal implements UserDetails {

        String ROLE_PREFIX = "ROLE_";

        private User user;

        public MyUserPrincipal(User user) {
            this.user = user;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
            list.add(new SimpleGrantedAuthority(ROLE_PREFIX + this.user.getRole().getDescr()));
            return list;
        }

        @Override
        public String getPassword() {
            return this.user.getPassword();
        }

        @Override
        public String getUsername() {
            return this.user.getName();
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
}
