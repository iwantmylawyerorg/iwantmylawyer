package com.thesis.iwantmylawyer.user;

import com.thesis.iwantmylawyer.constant.Constant;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(String id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(Constant.USER_NOT_FOUND_EXCEPTION));
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(Constant.USER_NOT_FOUND_EXCEPTION));
    }
    public Optional<User> findUserByEmail(String email){
        return userRepository.findByEmail(email);
    }



}
