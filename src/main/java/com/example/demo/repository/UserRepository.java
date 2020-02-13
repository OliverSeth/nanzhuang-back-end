package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

/**
 * Created by Oliver Seth on 2020/1/31 22:57
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    User findByUserName(String userName);

    @Transactional
    @Modifying
    @Query(value = "update User set " +
            "password = :password, " +
            "salt = :salt " +
            "where userId = :userId")
    void changePassword(@Param("userId") Integer userId,
                        @Param("password") String password,
                        @Param("salt") String salt);
}
