package com.example.demo.services;

import com.example.demo.repositories.UserHomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserHomeService {
    @Autowired
    private UserHomeRepository userHomeRepository;

    public List<Object[]> getUserIdAndHomeId() {
        return userHomeRepository.findUserIdAndHomeId();
    }

}
