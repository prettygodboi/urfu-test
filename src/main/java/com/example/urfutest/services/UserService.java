package com.example.urfutest.services;

import com.example.urfutest.entities.User;
import com.example.urfutest.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    //    @Override
//    @Transactional
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<User> optionalUser = userRepository.findByUsername(username);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
//                    user.getRoles()
//                            .stream()
//                            .map(role -> new SimpleGrantedAuthority(role.getName()))
//                            .collect(Collectors.toSet()));
//        } else {
//            throw new UsernameNotFoundException("User " + username + " not found");
//        }
//    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .map(user -> new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                        user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                                .collect(Collectors.toSet())))
                .orElseThrow(() -> new UsernameNotFoundException("failed to retrieve user: " + username));
    }
}
