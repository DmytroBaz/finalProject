package com.bazalytskyi.finalProject.entity;

public enum Role {
    ADMIN, CLIENT;

    public static Role getRole(Account user) {
        int roleId = user.getRoleId();
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }

}
