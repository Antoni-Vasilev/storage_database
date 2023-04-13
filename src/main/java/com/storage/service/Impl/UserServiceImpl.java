package com.storage.service.Impl;

import com.storage.exception.DuplicateContentException;
import com.storage.model.User;
import com.storage.repository.UserRepository;
import com.storage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(User user) {
        User findUser = userRepository.findUserByEmail(user.getEmail());
        if (findUser != null) throw new DuplicateContentException("Email already exist", "A busy email", null);

        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public void checkUser(User user) {
        User usernameUser = userRepository.findUserByUsername(user.getUsername());
        if (usernameUser != null) throw new DuplicateContentException("The username is already used", "username", null);

        User emailUser = userRepository.findUserByEmail(user.getEmail());
        if (emailUser != null) throw new DuplicateContentException("The email is already used", "email", null);
    }
}
