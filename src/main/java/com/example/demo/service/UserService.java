package com.example.demo.service;

import com.example.demo.domain.User;

/**
 * Created by Oliver Seth on 2020/1/31 23:00
 */
public interface UserService {
    User findByUserName(String userName);

    User save(User user);
}
