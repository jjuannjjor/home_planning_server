package com.example.demo.repositories;

import com.example.demo.entities.UserHome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.*;

import java.util.List;

@Repository
public interface UserHomeRepository extends JpaRepository<UserHome, Long> {
    @Query("SELECT u.userId, u.homeId FROM UserHome u")
    List<Object[]> findUserIdAndHomeId();
}
