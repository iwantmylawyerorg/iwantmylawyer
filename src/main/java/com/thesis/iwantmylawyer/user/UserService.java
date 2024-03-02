package com.thesis.iwantmylawyer.user;

import com.thesis.iwantmylawyer.exception.Constant;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(String id){
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(Constant.USER_NOT_FOUND_EXCEPTION));
    }

}
