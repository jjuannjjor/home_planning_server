package com.example.demo.services;

import com.example.demo.entities.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public User saveUser(User user) {
        return userRepository.save(user);
    }
    
    public void deleteUser(long id) {
		userRepository.deleteById(id);
	}
    public User getUserById(long id) {
		return userRepository.findById(id).orElse(null);
	}

    public int registerNewUserServiceMethod(String fname, String lname, String email, String password) {
        return userRepository.registerNewUser(fname, lname, email, password);
    }

    public List<String> checkUserEmail(String email) {
        return userRepository.checkUserEmail(email);
    }

    public String checkUserPasswordByEmail(String email) {
        return userRepository.checkUserPasswordByEmail(email);
    }

    public User getUserDetailsByEmail(String email) {
        return userRepository.getUserDetailsByEmail(email);
    }

    // Otros métodos según sea necesario
}
