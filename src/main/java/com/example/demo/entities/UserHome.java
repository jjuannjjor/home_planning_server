package com.example.demo.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "user_home")
public class UserHome {
    @Id
    private Long userId;
    private Long homeId;

    // Getters y Setters

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getHomeId() {
        return homeId;
    }

    public void setHomeId(Long homeId) {
        this.homeId = homeId;
    }
}