package com.storage.service;

import com.storage.model.User;

public interface UserService {

    User register(User user);

    User findByEmail(String email);

    void checkUser(User user);
}
