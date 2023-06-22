package com.example.demo.service;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository usersRepository;

    @Autowired
    public UserService(UserRepository userRepository){
        this.usersRepository = userRepository;

    }

    // public void registerUser(User user) {
    //     User newUser = new User();
    //     newUser.setId(user.getId());
    //     newUser.setFirstName(user.getFirstName());
    //     newUser.setLastName(user.getLastName());
    //     newUser.setEmail(user.getEmail());
    //     newUser.setUsername(user.getUsername());
    //     newUser.setPassword(customPasswordEncoder.encode(user.getPassword()));
    //     userRepository.save(newUser);

    // }

    public User findUserById(int id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("user not found"));
    }

  @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usersRepository.findByEmail(username);
        System.out.println("user-"+user );
    if(user == null) throw new UsernameNotFoundException(username);

            UserDetails userDetails = new UserDetailsCustomService(user);
                    return userDetails;

    }
}