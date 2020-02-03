package com.example.demo.domain;

import io.swagger.models.auth.In;

import javax.persistence.*;
import java.util.List;

/**
 * Created by Oliver Seth on 2020/1/31 22:49
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    @Column(unique = true)
    private String userName;

    private String password;

    private String salt;

    private String roleName;

    public User(String userName, String password, String salt, String roleName) {
        this.userName = userName;
        this.password = password;
        this.salt = salt;
        this.roleName = roleName;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
