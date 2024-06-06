package com.example.demo.controllers;

import com.example.demo.entities.Home;
import com.example.demo.entities.Task;
import com.example.demo.entities.TaskDTO;
import com.example.demo.services.TaskService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tareas")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/getTareasDTO")
    public ResponseEntity getHomesByUserDTO(@Param("id_user") String id_user,@Param("id_home") String id_home){

        if (id_home == null || id_home.isEmpty() ) {
            return new ResponseEntity<>("Fallo al enviar id_home", HttpStatus.BAD_REQUEST);
        }
        if (id_user == null || id_user.isEmpty() ) {
            return new ResponseEntity<>("Fallo al enviar id_user", HttpStatus.BAD_REQUEST);
        }

        List<TaskDTO> terminado = new ArrayList<>();
        terminado = taskService.getAllTasksByUserHome(Long.valueOf(id_user),Long.valueOf(id_home));
        String tasksJson = convertToJsonDTO(terminado);
        System.out.println(tasksJson);

        return new ResponseEntity<>(tasksJson,HttpStatus.OK);
    }

    public static String convertToJsonDTO(List<TaskDTO> tasks) {
        Gson gson = new Gson();
        return gson.toJson(tasks);
    }

    @PostMapping("/getTareas")
    public ResponseEntity getHomesByUser(@Param("id_user") String id_user,@Param("id_home") String id_home){

        if (id_home == null || id_home.isEmpty() ) {
            return new ResponseEntity<>("Fallo al enviar id_home", HttpStatus.BAD_REQUEST);
        }
        if (id_user == null || id_user.isEmpty() ) {
            return new ResponseEntity<>("Fallo al enviar id_user", HttpStatus.BAD_REQUEST);
        }

// Obtener lista de tareas utilizando el id de usuario y casa


        List<Long> taskid;
        try {
            taskid = taskService.getTasksByUserHome(Long.valueOf(id_user),Long.valueOf(id_home));
        } catch (Exception e) {
            return new ResponseEntity<>("Error al obtener la lista de las tareas", HttpStatus.INTERNAL_SERVER_ERROR);
        }



        System.out.println("ID user:"+id_user);
        System.out.println("ID home:"+id_home);
        System.out.println("lista tareas:" +
                taskid);

        //conseguir lista de casas con la lista de sus ids
        List<Task> tasks = new ArrayList<>();
        for (Long i : taskid){
            tasks.add(taskService.getTasksById(i));

        }
        System.out.println(tasks);

        String tasksJson = convertToJson(tasks);

        return new ResponseEntity<>(tasksJson,HttpStatus.OK);
    }

    public static String convertToJson(List<Task> tasks) {
        Gson gson = new Gson();
        return gson.toJson(tasks);
    }


    @PostMapping("/updateTareas")
    public ResponseEntity getHomesByUserDTO(@Param("id_user") String id_user,@Param("id_home") String id_home,@Param("id_task") String id_task,@Param("terminado") String terminado){


        if (id_home == null || id_home.isEmpty() ) {
            return new ResponseEntity<>("Fallo al enviar id_home", HttpStatus.BAD_REQUEST);
        }
        if (id_user == null || id_user.isEmpty() ) {
            return new ResponseEntity<>("Fallo al enviar id_user", HttpStatus.BAD_REQUEST);
        }
        if (id_task == null || id_task.isEmpty() ) {
            return new ResponseEntity<>("Fallo al enviar id_task", HttpStatus.BAD_REQUEST);
        }
        if (terminado == null || terminado.isEmpty() ) {
            return new ResponseEntity<>("Fallo al enviar terminado", HttpStatus.BAD_REQUEST);
        }

        int i = taskService.updateUserHomeTaskServiceMethod(Long.valueOf(id_user),Long.valueOf(id_home),Long.valueOf(id_task),terminado);
        //


        return new ResponseEntity<>("success",HttpStatus.OK);
    }

}