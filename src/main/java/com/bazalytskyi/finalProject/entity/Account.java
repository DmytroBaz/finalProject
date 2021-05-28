package com.bazalytskyi.finalProject.entity;

import java.sql.Time;
import java.time.LocalDateTime;

public class Account {
    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String localeName;
    private int roleId;
    private boolean active;

    public Account() {
    }

//    public void setCreateTime(LocalDateTime createTime) {
//        this.createTime = createTime;
//    }

    public String getLocaleName() {
        return localeName;
    }

    public void setLocaleName(String localeName) {
        this.localeName = localeName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public LocalDateTime getCreateTime() {
//        return createTime;
//    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
