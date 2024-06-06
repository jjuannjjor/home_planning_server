package com.example.demo.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {


    @Query(value="SELECT email FROM users WHERE email = :email",nativeQuery = true)
    List<String> checkUserEmail(@Param("email") String email);

    @Query(value="SELECT password FROM users WHERE email = :email",nativeQuery = true)
    String checkUserPasswordByEmail(@Param("email") String email);

    @Query(value="SELECT * FROM users WHERE email = :email",nativeQuery = true)
    User getUserDetailsByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query(value="INSERT INTO users (nombre, apellido, email, password) VALUES (:nombre, :apellido, :email, :password)",nativeQuery = true)
    int registerNewUser(@Param("nombre") String nombre,
                        @Param("apellido") String apellido,
                        @Param("email") String email,
                        @Param("password") String password);

}
