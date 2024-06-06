package com.example.demo.repositories;

import com.example.demo.entities.Home;
import com.example.demo.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HomeRepository extends JpaRepository<Home, Long> {
    @Query(value="SELECT house_code FROM home WHERE house_code = :housecode",nativeQuery = true)
    List<String> checkHouseCode(@Param("housecode") String housecode);


    @Query(value="SELECT id FROM home WHERE house_code = :housecode",nativeQuery = true)
    Long getHomeDetailsByHouseCode(@Param("housecode") String housecode);

    @Transactional
    @Modifying
    @Query(value="INSERT INTO home (nombre, house_code) VALUES (:nombre, :housecode)",nativeQuery = true)
    int registerNewhome(@Param("nombre") String nombre,
                        @Param("housecode") String housecode);

    @Transactional
    @Modifying
    @Query(value="INSERT INTO user_home (user_id, home_id) VALUES (:id_user, :id_home)",nativeQuery = true)
    int registerUserHome(@Param("id_user") Long id_user,
                        @Param("id_home") Long id_home);

    @Transactional
    @Modifying
    @Query(value="INSERT INTO user_home_task (user_id, home_id, task_id, terminado) VALUES (:id_user, :id_home,:id_task,:terminado)",nativeQuery = true)
    int registerUserHomeTask(@Param("id_user") Long id_user,
                             @Param("id_home") Long id_home,
                             @Param("id_task") Long id_task,
                             @Param("terminado") String terminado);
    @Transactional
    @Modifying
    @Query(value="SELECT task_id FROM user_home_task WHERE user_id = :id_user AND home_id = :id_home",nativeQuery = true)
    List<Long> chekUserHomeTask(@Param("id_user") Long id_user,
                                          @Param("id_home") Long id_home);


    @Transactional
    @Query(value="SELECT home_id FROM user_home WHERE user_id = :id",nativeQuery = true)
    List<Long> getHomes(@Param("id") Long id);

    @Transactional
    @Query(value="SELECT user_id FROM user_home WHERE home_id = :id",nativeQuery = true)
    List<Long> getUsers(@Param("id") Long id);

    @Transactional
    @Query(value="SELECT * FROM home WHERE id = :id",nativeQuery = true)
    Home getHomesById(@Param("id") Long id);

    @Transactional
    @Modifying
    @Query(value="DELETE FROM user_home_task WHERE user_id = :person AND home_id = :homeId",nativeQuery = true)
    void deleteUserHomeTask(Long person, Long homeId);


    @Transactional
    @Query(value="SELECT user_id FROM user_home WHERE user_id = :user AND home_id = :home",nativeQuery = true)
    List<Long> checkUserHome(Long user, Long home);

    @Transactional
    @Query(value="SELECT house_code FROM home WHERE id = :aLong",nativeQuery = true)
    String getHouseCode(Long aLong);


    @Transactional
    @Modifying
    @Query(value="DELETE FROM user_home WHERE user_id = :idUser AND home_id = :idHome",nativeQuery = true)
    void deleteUserHome(Long idUser, Long idHome);


}