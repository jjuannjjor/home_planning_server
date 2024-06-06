package com.example.demo.repositories;

import com.example.demo.entities.Task;
import com.example.demo.entities.TaskDTO;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Transactional
    @Modifying
    @Query(value="SELECT task_id FROM user_home_task WHERE user_id = :user_id AND home_id = :home_id ",nativeQuery = true)
    List<Long> getTasks(@Param("user_id") Long user_id,@Param("home_id") Long home_id);

    @Transactional
    @Modifying
    @Query(value="SELECT task_id ,terminado FROM user_home_task WHERE user_id = :user_id AND home_id = :home_id ",nativeQuery = true)
    List<Object[]> getTasksall(@Param("user_id") Long user_id,@Param("home_id") Long home_id);
    //SELECT task_id,terminado FROM user_home_task WHERE user_id = 2 AND home_id = 42;
    @Transactional
    @Modifying
    @Query(value="SELECT * FROM task WHERE id = :id ",nativeQuery = true)
    Task getTaskById(@Param("id") Long id);


    @Transactional
    @Modifying
    @Query(value="SELECT terminado FROM user_home_task WHERE home_id = :homeId AND task_id = :task_id",nativeQuery = true)
    String getTasksDTO(Long homeId,Long task_id);


    @Transactional
    @Modifying
    @Query(value="UPDATE user_home_task SET terminado = :terminado WHERE user_id = :id_user AND home_id = :id_home AND task_id = :id_task",nativeQuery = true)
    int updateUserHomeTask(@Param("id_user") Long id_user,
                             @Param("id_home") Long id_home,
                             @Param("id_task") Long id_task,
                             @Param("terminado") String terminado);
}
